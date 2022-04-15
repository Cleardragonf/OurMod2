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

import java.util.ArrayList;
import java.util.List;

public class Ores {

    public static final RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);

    public static Holder<PlacedFeature> NAQUDAH_OREGEN;
    public static Holder<PlacedFeature> MysteriousWorld_NAQUDAH_OREGEN;
    public static Holder<PlacedFeature> AbidosWorld_NAQUDAH_OREGEN;
    public static Holder<PlacedFeature> MysteriousWorld_NAQUDRIAH_OREGEN;
    public static Holder<PlacedFeature> AbidosWorld_NAQUDRIAH_OREGEN;
    public static List<Holder<PlacedFeature>> OVERWORLD_OREGEN = new ArrayList<>();
    public static List<Holder<PlacedFeature>> DEEPSLATE_OREGEN = new ArrayList<>();
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
        OreConfiguration overworldcobalt = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.COBALT_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldPlatinum = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.PLATINUM_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldSilver = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.SILVER_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldAluminium = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.ALUMINIUM_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldMagnesium = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.MAGNESIUM_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldNickel = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.NICKEL_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldZinc = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.ZINC_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldPhospate = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.PHOSPHATE_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OreConfiguration overworldSalt = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, Registration.SALT_ORE_OVERWORLD.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_naqudah_ore", new ConfiguredFeature<>(Feature.ORE, overworldConfig), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_cobalt_ore", new ConfiguredFeature<>(Feature.ORE, overworldcobalt), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_platinum_ore", new ConfiguredFeature<>(Feature.ORE, overworldPlatinum), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_silver_ore", new ConfiguredFeature<>(Feature.ORE, overworldSilver), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_aluminium_ore", new ConfiguredFeature<>(Feature.ORE, overworldAluminium), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_magnesium_ore", new ConfiguredFeature<>(Feature.ORE, overworldMagnesium), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_nickel_ore", new ConfiguredFeature<>(Feature.ORE, overworldNickel), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_zinc_ore", new ConfiguredFeature<>(Feature.ORE, overworldZinc), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_phospate_ore", new ConfiguredFeature<>(Feature.ORE, overworldPhospate), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));
        OVERWORLD_OREGEN.add(registerPlacedFeature("overworld_salt_ore", new ConfiguredFeature<>(Feature.ORE, overworldSalt), CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90))));


        OreConfiguration deepslateConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.NAQUDRIAH_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslateCobalt = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.COBALT_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslatePlatinum = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.PLATINUM_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslateSilver = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.SILVER_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslateAluminium = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.ALUMINIUM_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslateMagnesium = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.MAGNESIUM_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslateNickel = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.NICKEL_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslateZinc = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.ZINC_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslatePhospate = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.PHOSPHATE_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        OreConfiguration deepslateSalt = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.SALT_ORE_DEEPSLATE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, deepslateConfig), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_cobalt_ore", new ConfiguredFeature<>(Feature.ORE, deepslateCobalt), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_platinum_ore", new ConfiguredFeature<>(Feature.ORE, deepslatePlatinum), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_silver_ore", new ConfiguredFeature<>(Feature.ORE, deepslateSilver), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_aluminium_ore", new ConfiguredFeature<>(Feature.ORE, deepslateAluminium), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_magnesium_ore", new ConfiguredFeature<>(Feature.ORE, deepslateMagnesium), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_nickel_ore", new ConfiguredFeature<>(Feature.ORE, deepslateNickel), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_zinc_ore", new ConfiguredFeature<>(Feature.ORE, deepslateZinc), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_phosphate_ore", new ConfiguredFeature<>(Feature.ORE, deepslatePhospate), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));
        DEEPSLATE_OREGEN.add(registerPlacedFeature("deepslate_salt_ore", new ConfiguredFeature<>(Feature.ORE, deepslateSalt), CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64))));

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
            for (Holder<PlacedFeature> ore : OVERWORLD_OREGEN) {
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore);
            }
            for (Holder<PlacedFeature> ore : DEEPSLATE_OREGEN) {
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore);
            }
        }
    }
}
