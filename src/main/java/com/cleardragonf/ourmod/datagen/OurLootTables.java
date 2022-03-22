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
        lootTables.put(Registration.NAQUDAH_GENERATOR_BLOCK.get(), createStandardTable("naqudah_generator", Registration.NAQUDAH_GENERATOR_BLOCK.get(), Registration.NAQUDAH_GENERATOR_BE.get()));
        lootTables.put(Registration.DIGGER_BLOCK.get(), createStandardTable("digger", Registration.DIGGER_BLOCK.get(), Registration.DIGGER_BLOCKENTITY.get()));
        lootTables.put(Registration.BATTERY_BLOCK.get(), createStandardTable("battery", Registration.BATTERY_BLOCK.get(), Registration.BATTERY_BLOCKENTITY.get()));
        lootTables.put(Registration.TRANSLOCATOR_BLOCK.get(), createStandardTable("translocator", Registration.TRANSLOCATOR_BLOCK.get(), Registration.TRANSLOCATOR_BLOCKENTITY.get()));
        lootTables.put(Registration.STARGATE_BLOCK.get(), createSimpleTable("stargate_block", Registration.STARGATE_BLOCK.get()));
    }
}
