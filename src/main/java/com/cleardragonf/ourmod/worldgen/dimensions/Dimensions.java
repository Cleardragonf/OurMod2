package com.cleardragonf.ourmod.worldgen.dimensions;

import com.cleardragonf.ourmod.OurMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class Dimensions {

    public static final ResourceKey<Level> MYSTERIOUS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(OurMod.MODID, "mysterious"));
    public static final ResourceKey<Level> ABIDOS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(OurMod.MODID, "abidos"));

    public static void register() {
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(OurMod.MODID, "mysterious_chunkgen"),
                MCMChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(OurMod.MODID, "biomes"),
                MCMBiomeProvider.CODEC);

        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(OurMod.MODID, "abidos_chunkgen"),
                MultiDimensionalChunkGenerator.CODEC);
    }
}
