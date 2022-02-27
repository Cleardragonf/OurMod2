package com.cleardragonf.ourmod.datagen;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OurBlockStates extends BlockStateProvider {

    public OurBlockStates(DataGenerator gen, ExistingFileHelper helper){
        super(gen, OurMod.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels(){
        simpleBlock(Registration.NAQUDAH_ORE_OVERWORLD.get());
        simpleBlock(Registration.NAQUDRIAH_ORE_DEEPSLATE.get());
    }
}
