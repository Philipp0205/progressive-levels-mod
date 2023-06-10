package net.fabricmc.example.mixin;

import com.sun.jdi.IntegerValue;
import io.netty.buffer.Unpooled;
import net.fabricmc.example.networking.ModMessages;
import net.fabricmc.example.skills.SkillData;
import net.fabricmc.example.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "onSpawn", at = @At("RETURN"))
    private void onSpawn(CallbackInfo info) {
        // Do something when the player spawns

        IEntityDataSaver player = (IEntityDataSaver) (Object) this;
        SkillData.getSkillLevel(player, "mining");

        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeString("mining");
        buf.writeInt((int) SkillData.getSkillLevel(player, "mining"));
        ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.LEVELING_SYNC_ID, buf);

    }


}
