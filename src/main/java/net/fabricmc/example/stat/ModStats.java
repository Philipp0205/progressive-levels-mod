package net.fabricmc.example.stat;

import net.fabricmc.example.ExampleMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class ModStats {
    public static final Identifier MINING_STAT = new Identifier(ExampleMod.MOD_ID, "mining_stat");

    public static void registerModStats() {
        Registry.register(Registries.CUSTOM_STAT, "mining_stat", MINING_STAT);
        Stats.CUSTOM.getOrCreateStat(MINING_STAT, StatFormatter.DEFAULT);
    }

}
