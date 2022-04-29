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
        lootTables.put(Registration.SMELTERY_CONTROLLER_BLOCK.get(), createStandardTable("smeltery", Registration.SMELTERY_CONTROLLER_BLOCK.get(), Registration.SMELTERY_CONTROLLER_BLOCKENTITY.get()));
        lootTables.put(Registration.SMELTERY_TANK_MODULE.get(), createStandardTable("smeltery", Registration.SMELTERY_TANK_MODULE.get(), Registration.SMELTERY_TANK_MODULE_BLOCKENTITY.get()));
        lootTables.put(Registration.SMELTERY_HEAT_MODULE.get(), createStandardTable("smeltery", Registration.SMELTERY_HEAT_MODULE.get(), Registration.SMELTERY_HEAT_MODULE_BLOCKENTITY.get()));

        lootTables.put(Registration.COBALT_ORE_OVERWORLD.get(), createSilkTouchTable("cobalt_ore_overworld", Registration.COBALT_ORE_OVERWORLD.get(), Registration.RAW_COBALT.get(), 1,4));
        lootTables.put(Registration.PLATINUM_ORE_OVERWORLD.get(), createSilkTouchTable("platinum_ore_overworld", Registration.PLATINUM_ORE_OVERWORLD.get(), Registration.RAW_PLATINUM.get(), 1,4));
        lootTables.put(Registration.SILVER_ORE_OVERWORLD.get(), createSilkTouchTable("silver_ore_overworld", Registration.SILVER_ORE_OVERWORLD.get(), Registration.RAW_SILVER.get(), 1,4));
        lootTables.put(Registration.ALUMINIUM_ORE_OVERWORLD.get(), createSilkTouchTable("aluminium_ore_overworld", Registration.ALUMINIUM_ORE_OVERWORLD.get(), Registration.RAW_ALUMINIUM.get(), 1,4));
        lootTables.put(Registration.MAGNESIUM_ORE_OVERWORLD.get(), createSilkTouchTable("magnesium_ore_overworld", Registration.MAGNESIUM_ORE_OVERWORLD.get(), Registration.RAW_MAGNESIUM.get(), 1,4));
        lootTables.put(Registration.NICKEL_ORE_OVERWORLD.get(), createSilkTouchTable("nickel_ore_overworld", Registration.NICKEL_ORE_OVERWORLD.get(), Registration.RAW_NICKEL.get(), 1,4));
        lootTables.put(Registration.ZINC_ORE_OVERWORLD.get(), createSilkTouchTable("zinc_ore_overworld", Registration.ZINC_ORE_OVERWORLD.get(), Registration.RAW_ZINC.get(), 1,4));
        lootTables.put(Registration.PHOSPHATE_ORE_OVERWORLD.get(), createSilkTouchTable("phosphate_ore_overworld", Registration.PHOSPHATE_ORE_OVERWORLD.get(), Registration.RAW_PHOSPHATE.get(), 1,4));
        lootTables.put(Registration.SALT_ORE_OVERWORLD.get(), createSilkTouchTable("salt_ore_overworld", Registration.SALT_ORE_OVERWORLD.get(), Registration.SALT.get(), 1,4));
        lootTables.put(Registration.COBALT_ORE_DEEPSLATE.get(), createSilkTouchTable("cobalt_ore_deepslate", Registration.COBALT_ORE_DEEPSLATE.get(), Registration.RAW_COBALT.get(), 1,8));
        lootTables.put(Registration.PLATINUM_ORE_DEEPSLATE.get(), createSilkTouchTable("platinum_ore_deepslate", Registration.PLATINUM_ORE_DEEPSLATE.get(), Registration.RAW_PLATINUM.get(), 1,8));
        lootTables.put(Registration.SILVER_ORE_DEEPSLATE.get(), createSilkTouchTable("silver_ore_deepslate", Registration.SILVER_ORE_DEEPSLATE.get(), Registration.RAW_SILVER.get(), 1,8));
        lootTables.put(Registration.ALUMINIUM_ORE_DEEPSLATE.get(), createSilkTouchTable("aluminium_ore_deepslate", Registration.ALUMINIUM_ORE_DEEPSLATE.get(), Registration.RAW_ALUMINIUM.get(), 1,8));
        lootTables.put(Registration.MAGNESIUM_ORE_DEEPSLATE.get(), createSilkTouchTable("magnesium_ore_deepslate", Registration.MAGNESIUM_ORE_DEEPSLATE.get(), Registration.RAW_MAGNESIUM.get(), 1,8));
        lootTables.put(Registration.NICKEL_ORE_DEEPSLATE.get(), createSilkTouchTable("nickel_ore_deepslate", Registration.NICKEL_ORE_DEEPSLATE.get(), Registration.RAW_NICKEL.get(), 1,8));
        lootTables.put(Registration.ZINC_ORE_DEEPSLATE.get(), createSilkTouchTable("zinc_ore_deepslate", Registration.ZINC_ORE_DEEPSLATE.get(), Registration.RAW_ZINC.get(), 1,8));
        lootTables.put(Registration.PHOSPHATE_ORE_DEEPSLATE.get(), createSilkTouchTable("phosphate_ore_deepslate", Registration.PHOSPHATE_ORE_DEEPSLATE.get(), Registration.RAW_PHOSPHATE.get(), 1,8));
        lootTables.put(Registration.SALT_ORE_DEEPSLATE.get(), createSilkTouchTable("salt_ore_deepslate", Registration.SALT_ORE_DEEPSLATE.get(), Registration.SALT.get(), 1,8));

    }
}
