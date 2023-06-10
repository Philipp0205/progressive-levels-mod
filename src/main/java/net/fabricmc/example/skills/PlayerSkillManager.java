package net.fabricmc.example.skills;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.stat.StatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class PlayerSkillManager {
    StatHandler statHandler;

    public PlayerSkillManager(ClientPlayerEntity clientPlayer) {
        this.statHandler = clientPlayer.getStatHandler();
    }







}
