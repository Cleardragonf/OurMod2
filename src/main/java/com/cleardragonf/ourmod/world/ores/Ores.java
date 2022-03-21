package com.cleardragonf.ourmod.world.ores;

import com.cleardragonf.ourmod.setup.Registration;
import com.cleardragonf.ourmod.world.dimensions.Dimensions;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Ores {


    public static Holder<PlacedFeature> MYSTERIOUS_OREGEN;
    public static Holder<PlacedFeature> OVERWORLD_OREGEN;
    public static Holder<PlacedFeature> DEEPSLATE_OREGEN;


    public static void registerConfiguredFeatures() {
        OreConfiguration mysteriousConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.NAQUDAH_ORE_OVERWORLD.get().defaultBlockState(), OreConfig.NAQUDAH_VEINSIZE.get());
        MYSTERIOUS_OREGEN = registerPlacedFeature("mysterious_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, mysteriousConfig),
                CountPlacement.of(OreConfig.NAQUDAH_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(Dimensions.MYSTERIOUS)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration overworldConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.NAQUDAH_ORE_OVERWORLD.get().defaultBlockState(), OreConfig.OVERWORLD_NAQUDAH_VEINSIZE.get());
        OVERWORLD_OREGEN = registerPlacedFeature("overworld_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, overworldConfig),
                CountPlacement.of(OreConfig.OVERWORLD_NAQUDAH_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> !Dimensions.MYSTERIOUS.equals(key)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration deepslateConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.NAQUDRIAH_ORE_DEEPSLATE.get().defaultBlockState(), OreConfig.DEEPSLATE_VEINSIZE.get());
        DEEPSLATE_OREGEN = registerPlacedFeature("deepslate_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, deepslateConfig),
                CountPlacement.of(OreConfig.DEEPSLATE_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MYSTERIOUS_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_OREGEN);
    }
}