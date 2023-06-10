package net.fabricmc.example.networking;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.networking.packet.ExampleC2SPacket;
import net.fabricmc.example.networking.packet.LevelingC2SPacket;
import net.fabricmc.example.networking.packet.LevelingS2CSyncPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {

    // Client -> Server
    public static final Identifier EXAMPLE_ID = new Identifier(ExampleMod.MOD_ID, "example");
    public static final Identifier LEVELING_ID = new Identifier(ExampleMod.MOD_ID, "level");

    // Server -> Client
    public static final Identifier LEVELING_SYNC_ID = new Identifier(ExampleMod.MOD_ID, "level_sync");


    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(LEVELING_ID, LevelingC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LEVELING_SYNC_ID, LevelingS2CSyncPacket::receive);
    }

}
