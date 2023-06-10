package net.fabricmc.example.access;

import net.fabricmc.example.skills.PlayerStatsSaver;

/**
 * Duck interface is used to access the PlayerStatsManager from the PlayerEntityMixin.
 */
public interface PlayerStatsManagerAccess {

    PlayerStatsSaver getPlayerStatsManager();
}
