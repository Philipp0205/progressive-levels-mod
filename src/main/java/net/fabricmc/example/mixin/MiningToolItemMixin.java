package net.fabricmc.example.mixin;

import net.fabricmc.example.networking.ModMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {

    @Inject(at = @At("TAIL"), method = "postMine")
    private void postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner,
            CallbackInfoReturnable<Boolean> info) {

        // Client
        if (miner instanceof PlayerEntity player) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeString("mining");
            buf.writeInt(1);
            ClientPlayNetworking.send(ModMessages.LEVELING_ID, buf);
        }
    }
}
