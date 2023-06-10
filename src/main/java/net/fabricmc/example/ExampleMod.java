package net.fabricmc.example;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.item.ModItems;
import net.fabricmc.example.networking.ModMessages;
import net.fabricmc.example.networking.PlayerStatsServerPacket;
import net.fabricmc.example.skills.Skill;
import net.fabricmc.example.skills.SkillData;
import net.fabricmc.example.stat.ModStats;
import net.fabricmc.example.util.Config;
import net.fabricmc.example.util.ConfigManager;
import net.fabricmc.example.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Config CONFIG = Config.getInstance();


	//	static final PlayerStatsManager playerStatsManager = new PlayerStatsManager(player);


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModStats.registerModStats();
		PlayerStatsServerPacket.init();
		ModMessages.registerC2SPackets();
		MixinExtrasBootstrap.init();

		ConfigManager.loadConfig();

		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				Arrays.stream(Skill.values()).forEach(skill -> {
					IEntityDataSaver dataSaver = (IEntityDataSaver) player;
					double  level = SkillData.getSkillLevel(dataSaver, skill.nbt);
//					SkillData.syncLevel(level, skill.nbt, player);
					SkillData.addSkillLevel(dataSaver, skill.nbt, 0);
				});
			}
		});



	}
}
