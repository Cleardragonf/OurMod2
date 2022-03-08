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

        withExistingParent(Registration.NAQUDAH_GENERATOR_ITEM.get().getRegistryName().getPath(), modLoc("block/naqudah_generator/main"));
        withExistingParent(Registration.DIGGER_ITEM.get().getRegistryName().getPath(), modLoc("block/digger"));

        singleTexture(Registration.RAW_NAQUDAH.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/raw_naqudah"));
        singleTexture(Registration.NAQUDAH_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0",modLoc("item/naqudah_ingot"));

    }
}
