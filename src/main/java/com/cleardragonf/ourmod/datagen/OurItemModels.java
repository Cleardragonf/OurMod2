package com.cleardragonf.ourmod.datagen;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OurItemModels extends ItemModelProvider {

    public OurItemModels(DataGenerator gen, ExistingFileHelper helper){
        super (gen, OurMod.MODID, helper);
    }

    @Override
    protected void registerModels(){
        withExistingParent(Registration.NAQUDAH_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/naqudah_ore_overworld"));
        withExistingParent(Registration.NAQUDRIAH_ORE_DEEPSLATE_ITEM.get().getRegistryName().getPath(), modLoc("block/naqudriah_ore_deepslate"));
        withExistingParent(Registration.COBALT_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/cobalt_ore_overworld"));
        withExistingParent(Registration.PLATINUM_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/platinum_ore_overworld"));
        withExistingParent(Registration.SILVER_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/silver_ore_overworld"));
        withExistingParent(Registration.ALUMINIUM_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/aluminium_ore_overworld"));
        withExistingParent(Registration.MAGNESIUM_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/magnesium_ore_overworld"));
        withExistingParent(Registration.NICKEL_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/nickel_ore_overworld"));
        withExistingParent(Registration.ZINC_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/zinc_ore_overworld"));
        withExistingParent(Registration.PHOSPHATE_ORE_DEEPSLATE_ITEM.get().getRegistryName().getPath(), modLoc("block/phosphate_ore_overworld"));
        withExistingParent(Registration.SALT_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/salt_ore_overworld"));


        withExistingParent(Registration.NAQUDAH_GENERATOR_ITEM.get().getRegistryName().getPath(), modLoc("block/naqudah_generator/main"));
        withExistingParent(Registration.DIGGER_ITEM.get().getRegistryName().getPath(), modLoc("block/digger"));
        withExistingParent(Registration.BATTERY_ITEM.get().getRegistryName().getPath(), modLoc("block/battery"));
        withExistingParent(Registration.TRANSLOCATOR_ITEM.get().getRegistryName().getPath(), modLoc("block/translocator"));
        withExistingParent(Registration.STARGATE_ITEM.get().getRegistryName().getPath(), modLoc("block/stargate_base_block"));

        singleTexture(Registration.RAW_NAQUDAH.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_naqudah"));
        singleTexture(Registration.RAW_COBALT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_cobalt"));
        singleTexture(Registration.RAW_PLATINUM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_platinum"));
        singleTexture(Registration.RAW_SILVER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_silver"));
        singleTexture(Registration.RAW_ALUMINIUM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_aluminium"));
        singleTexture(Registration.RAW_MAGNESIUM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_magnesium"));
        singleTexture(Registration.RAW_NICKEL.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_nickel"));
        singleTexture(Registration.RAW_ZINC.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_zinc"));
        singleTexture(Registration.RAW_PHOSPHATE.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_phosphate"));
        singleTexture(Registration.NAQUDAH_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/naqudah_ingot"));
        singleTexture(Registration.COBALT_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/cobalt_ingot"));
        singleTexture(Registration.PLATINUM_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/platinum_ingot"));
        singleTexture(Registration.SILVER_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/silver_ingot"));
        singleTexture(Registration.ALUMINIUM_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/aluminium_ingot"));
        singleTexture(Registration.MAGNESIUM_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/magnesium_ingot"));
        singleTexture(Registration.NICKEL_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/nickel_ingot"));
        singleTexture(Registration.ZINC_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/zinc_ingot"));
        singleTexture(Registration.PHOSPHATE_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/phosphate_ingot"));
        singleTexture(Registration.TRANSLOCATOR_TUNER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/translocator_tuner"));
    }
}
