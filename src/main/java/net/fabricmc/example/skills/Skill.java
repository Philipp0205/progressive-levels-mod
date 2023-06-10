package net.fabricmc.example.skills;

public enum Skill {
    MINING(1, "mining"), AGILITY(2, "agility");

    public final int id;
    public final String nbt;

    Skill(int id, String nbt) {
        this.id = id;
        this.nbt = nbt;
    }
}
