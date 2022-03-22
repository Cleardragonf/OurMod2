package com.cleardragonf.ourmod.worldgen.dimensions;

import com.cleardragonf.ourmod.OurMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class Dimensions {
    public static final ResourceKey<Level> MYSTERIOUS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(OurMod.MODID, "mysterious"));


}
