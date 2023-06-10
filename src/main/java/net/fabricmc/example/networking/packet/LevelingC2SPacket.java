package net.fabricmc.example.networking.packet;

import net.fabricmc.example.skills.Skill;
import net.fabricmc.example.skills.SkillData;
import net.fabricmc.example.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class LevelingC2SPacket {
    public static void receive(MinecraftServer minecraftServer, ServerPlayerEntity player,
            ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {

        // Increase level by 1
        String skillName =packetByteBuf.readString();
        int level = packetByteBuf.readInt();

        SkillData.addSkillLevel(((IEntityDataSaver) player), skillName, level);
    }
}
