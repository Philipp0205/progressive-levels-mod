package net.fabricmc.example.networking;

import net.fabricmc.example.access.PlayerStatsManagerAccess;
import net.fabricmc.example.skills.PlayerStatsSaver;
import net.fabricmc.example.skills.Skill;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class PlayerStatsServerPacket {

    public static final Identifier STATS_INCREASE_PACKET = new Identifier("modid", "player_increase_stats");

    public static void init() {

        ServerPlayNetworking.registerGlobalReceiver(STATS_INCREASE_PACKET,
                (server, player, handler, buffer, sender) -> {
                    Skill skill = Skill.valueOf(buffer.readString().toUpperCase());
                    int level = buffer.readInt();
                    PlayerStatsSaver playerStatsManager = ((PlayerStatsManagerAccess) player).getPlayerStatsManager();
                    playerStatsManager.setSkillLevel(skill, playerStatsManager.getSkillLevel(skill) + level);
                });
    }


}
