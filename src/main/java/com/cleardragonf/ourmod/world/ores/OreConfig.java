package com.cleardragonf.ourmod.world.ores;

import net.minecraftforge.common.ForgeConfigSpec;

public class OreConfig {

    public static ForgeConfigSpec.IntValue NAQUDAH_VEINSIZE;
    public static ForgeConfigSpec.IntValue NAQUDAH_AMOUNT;
    public static ForgeConfigSpec.IntValue OVERWORLD_NAQUDAH_VEINSIZE;
    public static ForgeConfigSpec.IntValue OVERWORLD_NAQUDAH_AMOUNT;
    public static ForgeConfigSpec.IntValue DEEPSLATE_VEINSIZE;
    public static ForgeConfigSpec.IntValue DEEPSLATE_AMOUNT;


    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for ore generation").push("ores");

        NAQUDAH_VEINSIZE = COMMON_BUILDER
                .comment("Veinsize of our ore in the mysterious dimension")
                .defineInRange("mysteriousVeinsize", 25, 1, Integer.MAX_VALUE);
        NAQUDAH_AMOUNT = COMMON_BUILDER
                .comment("Amount of veines of our ore in the mysterious dimension")
                .defineInRange("mysteriousAmount", 10, 1, Integer.MAX_VALUE);
        OVERWORLD_NAQUDAH_VEINSIZE = COMMON_BUILDER
                .comment("Veinsize of our ore in the overworld dimension")
                .defineInRange("overworldVeinsize", 5, 1, Integer.MAX_VALUE);
        OVERWORLD_NAQUDAH_AMOUNT = COMMON_BUILDER
                .comment("Amount of veines of our ore in the overworld dimension")
                .defineInRange("overworldAmount", 3, 1, Integer.MAX_VALUE);
        DEEPSLATE_VEINSIZE = COMMON_BUILDER
                .comment("Veinsize of our ore in the overworld dimension but for deepslate")
                .defineInRange("deepslateVeinsize", 5, 1, Integer.MAX_VALUE);
        DEEPSLATE_AMOUNT = COMMON_BUILDER
                .comment("Amount of veines of our ore in the overworld dimension but for deepslate")
                .defineInRange("deepslateAmount", 3, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

}
