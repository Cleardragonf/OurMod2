package com.cleardragonf.ourmod.worldgen.ores;

import com.cleardragonf.ourmod.setup.Registration;
import com.cleardragonf.ourmod.worldgen.dimensions.Dimensions;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
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

    public static final RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);

    public static Holder<PlacedFeature> NAQUDAH_OREGEN;
    public static Holder<PlacedFeature> MysteriousWorld_NAQUDAH_OREGEN;
    public static Holder<PlacedFeature> AbidosWorld_NAQUDAH_OREGEN;
    public static Holder<PlacedFeature> MysteriousWorld_NAQUDRIAH_OREGEN;
    public static Holder<PlacedFeature> AbidosWorld_NAQUDRIAH_OREGEN;
    public static Holder<PlacedFeature> OVERWORLD_OREGEN;
    public static Holder<PlacedFeature> DEEPSLATE_OREGEN;
    public static Holder<PlacedFeature> NETHER_OREGEN;
    public static Holder<PlacedFeature> END_OREGEN;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> NAQUDAH_TEST;

    public static void registerConfiguredFeatures() {
        OreConfiguration OverWorldOffWorldConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.NAQUDAH_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.MYSTERIOUS_VEINSIZE.get());
        MysteriousWorld_NAQUDAH_OREGEN = registerPlacedFeature("mysterious_naqudah_ore", new ConfiguredFeature<>(Feature.ORE, OverWorldOffWorldConfig),
                CountPlacement.of(5),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(Dimensions.MYSTERIOUS)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
        AbidosWorld_NAQUDAH_OREGEN = registerPlacedFeature("abidos_naqudah_ore", new ConfiguredFeature<>(Feature.ORE, OverWorldOffWorldConfig),
                CountPlacement.of(50),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(Dimensions.ABIDOS)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
        MysteriousWorld_NAQUDRIAH_OREGEN = registerPlacedFeature("mysterious_naqudriah_ore", new ConfiguredFeature<>(Feature.ORE, OverWorldOffWorldConfig),
                CountPlacement.of(5),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(Dimensions.MYSTERIOUS)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
        AbidosWorld_NAQUDRIAH_OREGEN = registerPlacedFeature("abidos_naqudriah_ore", new ConfiguredFeature<>(Feature.ORE, OverWorldOffWorldConfig ),
                CountPlacement.of(25),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(Dimensions.ABIDOS)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(120)));


        OreConfiguration overworldConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.NAQUDAH_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OVERWORLD_OREGEN = registerPlacedFeature("overworld_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, overworldConfig),
                CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> !Dimensions.MYSTERIOUS.equals(key)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration deepslateConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.NAQUDRIAH_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        DEEPSLATE_OREGEN = registerPlacedFeature("deepslate_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, deepslateConfig),
                CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        OreConfiguration netherConfig = new OreConfiguration(OreFeatures.NETHER_ORE_REPLACEABLES, Registration.NAQUDRIAH_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.NETHER_VEINSIZE.get());
        NETHER_OREGEN = registerPlacedFeature("nether_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, netherConfig),
                CountPlacement.of(OresConfig.NETHER_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration endConfig = new OreConfiguration(IN_ENDSTONE, Registration.NAQUDRIAH_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.END_VEINSIZE.get());
        END_OREGEN = registerPlacedFeature("end_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, endConfig),
                CountPlacement.of(OresConfig.END_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100)));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NETHER_OREGEN);
        } else if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_OREGEN);
        } else {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AbidosWorld_NAQUDRIAH_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MysteriousWorld_NAQUDRIAH_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AbidosWorld_NAQUDAH_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MysteriousWorld_NAQUDAH_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_OREGEN);
        }
    }
}
