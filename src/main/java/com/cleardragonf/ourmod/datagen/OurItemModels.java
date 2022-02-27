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
    }
}
