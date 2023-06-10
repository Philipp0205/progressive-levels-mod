package net.fabricmc.example.skills;

import io.netty.buffer.Unpooled;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.networking.ModMessages;
import net.fabricmc.example.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class SkillData {
    public static void addSkillLevel(IEntityDataSaver player, String skillName, int amount) {
        NbtCompound nbt = player.getPersistentData();
        String skillKey = "level_" + skillName;

        int level = nbt.getInt(skillKey);
        level += amount;
        //        level = (level + amount) / ExampleMod.CONFIG.agilityFactor;

        nbt.putInt(skillKey, level);
        syncLevel(level, skillKey, (ServerPlayerEntity) player);
    }


    public static int totalCount(IEntityDataSaver player, String skillName) {
        NbtCompound nbt = player.getPersistentData();
        String skillKey = "level_" + skillName;

        return nbt.getInt(skillKey);
    }

    public static double getSkillLevel(IEntityDataSaver player, String skillName) {
        NbtCompound nbt = player.getPersistentData();
        String skillKey = "level_" + skillName;

        int level = nbt.getInt(skillKey);

        // return double with level / agilityFactor
        return level / ExampleMod.CONFIG.agilityFactor;
    }

    public static int getProgress(IEntityDataSaver player, String skillName) {
        NbtCompound nbt = player.getPersistentData();
        String skillKey = "level_" + skillName;

        int level = nbt.getInt(skillKey);
        int factor = ExampleMod.CONFIG.agilityFactor;

        // How much percent is level from factor?
        double percent = (level % factor) * 100 / factor;
        // return result raning from 1 to 10
        return (int) (percent / 10) + 1;

    }

    public static void syncLevel(int level, String key, ServerPlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeString(key);
        buf.writeInt(level);

        ServerPlayNetworking.send(player, ModMessages.LEVELING_SYNC_ID, buf);
    }
}
