package net.fabricmc.example.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.example.skills.PlayerStatsSaver;
import net.fabricmc.example.skills.Skill;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;

public class PlayerStatsClientPacket {


    public static void writeC2SIncreaseLevelPacket(PlayerStatsSaver playerStatsManager, Skill skill, int amount) {
        int level = playerStatsManager.getSkillLevel(skill);

        playerStatsManager.increaseSkillLevel(skill, amount);
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeString(skill.name());
        buf.writeInt(level);

        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(PlayerStatsServerPacket.STATS_INCREASE_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

}
