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

        singleTexture(Registration.RAW_NAQUDAH.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_naqudah"));
        singleTexture(Registration.NAQUDAH_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/naqudah_ingot"));
        singleTexture(Registration.TRANSLOCATOR_TUNER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/translocator_tuner"));
    }
}
