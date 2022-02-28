package com.cleardragonf.ourmod.datagen;

import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.data.DataGenerator;

public class OurLootTables extends BaseLootTableProvider{

    public OurLootTables(DataGenerator gen){
        super(gen);
    }

    @Override
    protected void addTables(){
        lootTables.put(Registration.NAQUDAH_ORE_OVERWORLD.get(), createSilkTouchTable("naqudah_ore_overworld", Registration.NAQUDAH_ORE_OVERWORLD.get(), Registration.RAW_NAQUDAH.get(), 1,4));

    }
}
