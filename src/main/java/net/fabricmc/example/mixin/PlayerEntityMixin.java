package net.fabricmc.example.mixin;

import net.fabricmc.example.access.PlayerStatsManagerAccess;
import net.fabricmc.example.networking.ModMessages;
import net.fabricmc.example.skills.PlayerStatsSaver;
import net.fabricmc.example.skills.Skill;
import net.fabricmc.example.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.stat.Stat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerStatsManagerAccess {

    private final PlayerStatsSaver playerStatsManager = new PlayerStatsSaver();
    PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(method = "createPlayerAttributes", at = @At(value = "TAIL"))
    private static void createPlayerAttributesMixin(CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {

    }

    @Shadow
    public abstract void increaseStat(Stat<?> stat, int amount);

    @Shadow
    public abstract boolean isSwimming();

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "TAIL"))
    private void writeCustomDataToNbtMixin(NbtCompound tag, CallbackInfo info) {
        this.playerStatsManager.writeStatsToNbt(tag);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "TAIL"))
    private void readCustomDataFromNbtMixin(NbtCompound tag, CallbackInfo info) {
        this.playerStatsManager.readStatsFromNbt(tag);
    }

    /*
         Each 100 levels of mining skill increases mining speed by 1
     */
    @Inject(method = "getBlockBreakingSpeed", at = @At(value = "RETURN"), cancellable = true)
    private void modifyMiningSpeed(BlockState block, CallbackInfoReturnable<Float> ci) {

        int level = ((IEntityDataSaver) player).getPersistentData().getInt("level_mining");
        ci.setReturnValue(ci.getReturnValue() + level / 100);
    }

    @Inject(method = "getMovementSpeed", at = @At(value = "RETURN"), cancellable = true)
    private void modifyMovementSpeed(CallbackInfoReturnable<Float> ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        int level = ((IEntityDataSaver) player).getPersistentData().getInt("level_mining");
        ci.setReturnValue(ci.getReturnValue() + level / 100);
    }

    /**
     * Tracks player motion and sends a packet to the server to track the progress for the agility skill.
     *
     * @param info CallbackInfo
     */
    @Inject(method = "increaseTravelMotionStats", at = @At(value = "INVOKE", target =
            "Lnet/minecraft/entity/player" + "/PlayerEntity;increaseStat(Lnet/minecraft/util/Identifier;I)V",
            ordinal = 6))
    private void trackPlayerMotion(CallbackInfo info) {
        if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeString("agility");
            buf.writeInt(1);
            ClientPlayNetworking.send(ModMessages.LEVELING_ID, buf);
        }
        //        }
    }

    @Override
    public PlayerStatsSaver getPlayerStatsManager() {
        return this.playerStatsManager;
    }
}
