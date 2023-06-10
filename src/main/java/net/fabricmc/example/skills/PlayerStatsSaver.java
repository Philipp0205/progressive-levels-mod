package net.fabricmc.example.skills;

import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.Map;

public class PlayerStatsSaver {
    private final Map<Skill, Integer> skills = new HashMap<>();

    public PlayerStatsSaver() {
    }

    public void setSkillLevel(Skill skill, int amount) {
        skills.put(skill, amount);
    }

    public void increaseSkillLevel(Skill skill, int amount) {
        skills.put(skill, skills.get(skill) + amount);
    }

    public Map<Skill, Integer> getSkills() {
        return this.skills;

    }

    public int getSkillLevel(Skill skill) {
        if (skills.containsKey(skill)) {
            return skills.get(skill);
        }
        return 0;
    }

    public void readStatsFromNbt(NbtCompound nbt) {
        for (Skill stat : Skill.values()) {
            skills.put(stat, nbt.getInt(stat.nbt));
        }
    }

    public void writeStatsToNbt(NbtCompound nbt) {
        skills.forEach((skill, level) -> {
            nbt.putInt(skill.nbt, level);
        });

    }
}
