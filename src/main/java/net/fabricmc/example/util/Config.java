package net.fabricmc.example.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {
    private static Config INSTANCE;

    @Expose
    @SerializedName("agility_factor")
    public Integer agilityFactor = 1000;
    public Integer miningFactor = 100;

    private Config() {
    }

    public static Config getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Config();
        return INSTANCE;
    }

    public static void update(Config config) {
        INSTANCE = config;
    }
}
