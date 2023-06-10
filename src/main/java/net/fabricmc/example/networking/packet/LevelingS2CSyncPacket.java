package net.fabricmc.example.networking.packet;

import net.fabricmc.example.skills.Skill;
import net.fabricmc.example.skills.SkillData;
import net.fabricmc.example.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

import java.util.Arrays;

public class LevelingS2CSyncPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler,
            PacketByteBuf buf, PacketSender packetSender) {

        String key = buf.readString();
        int level = buf.readInt();

        client.execute(() -> {
            // Everything in this lambda is run on the render thread
            ((IEntityDataSaver) client.player).getPersistentData().putInt(key, level);
        });
    }
}



