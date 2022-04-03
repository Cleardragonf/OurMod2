package com.cleardragonf.ourmod.datagen;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.data.DataGenerator;
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

        withExistingParent(Registration.NAQUDAH_GENERATOR_ITEM.get().getRegistryName().getPath(), modLoc("block/naqudah_generator/main"));
        withExistingParent(Registration.DIGGER_ITEM.get().getRegistryName().getPath(), modLoc("block/digger"));
        withExistingParent(Registration.BATTERY_ITEM.get().getRegistryName().getPath(), modLoc("block/battery"));
        withExistingParent(Registration.SMELTERY_CONTROLLER_ITEM.get().getRegistryName().getPath(), modLoc("block/smeltery_controller"));
        withExistingParent(Registration.SMELTERY_ITEM.get().getRegistryName().getPath(), modLoc("block/smeltery"));
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
