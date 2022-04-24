package com.cleardragonf.ourmod.datagen;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorBlock.MESSAGE_POWERGEN;
import static com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorBlock.SCREEN_TUTORIAL_POWERGEN;
import static com.cleardragonf.ourmod.setup.ModSetup.TAB_NAME;

public class OurLanguageProvider extends LanguageProvider {

    public OurLanguageProvider(DataGenerator gen, String locale){
        super(gen, OurMod.MODID, locale);
    }

    @Override
    protected void addTranslations(){
        add("itemGroup." + TAB_NAME, "Our Mod");
        add(MESSAGE_POWERGEN, "Power Generator generating %s per tick!");
        add(SCREEN_TUTORIAL_POWERGEN, "Naqudah Power Generator");

        add(Registration.NAQUDAH_GENERATOR_BLOCK.get(), "Naqudah Power Generator");
        add(Registration.DIGGER_BLOCK.get(), "Automated Mining");
        add(Registration.BATTERY_BLOCK.get(), "MCM Battery");

        add(Registration.STARGATE_BLOCK.get(), "Stargate Portal");

        add(Registration.TRANSLOCATOR_BLOCK.get(), "Translocator");
        add(Registration.TRANSLOCATOR_TUNER.get(), "Link Translocators together");

        add(Registration.SMELTERY_CONTROLLER_BLOCK.get(), "Smeltery Controller");
        add(Registration.SMELTERY_TANK_MODULE.get(), "Smeltery Tank");

        add(Registration.NAQUDAH_ORE_OVERWORLD.get(), "Naqudah Ore");
        add(Registration.NAQUDRIAH_ORE_DEEPSLATE.get(), "Naqudriah Ore");
        add(Registration.RAW_NAQUDAH.get(), "Raw Naqudah");
        add(Registration.NAQUDAH_INGOT.get(), "Naqudah Ingot");
        add(Registration.COBALT_ORE_OVERWORLD.get(), "Cobalt Ore");
        add(Registration.COBALT_ORE_DEEPSLATE.get(), "Cobalt Ore");
        add(Registration.RAW_COBALT.get(), "Raw Cobalt");
        add(Registration.COBALT_INGOT.get(), "Cobalt Ingot");
        add(Registration.PLATINUM_ORE_OVERWORLD.get(), "Platinum Ore");
        add(Registration.PLATINUM_ORE_DEEPSLATE.get(), "Platinum Ore");
        add(Registration.RAW_PLATINUM.get(), "Raw Platinum");
        add(Registration.PLATINUM_INGOT.get(), "Platinum Ingot");
        add(Registration.SILVER_ORE_OVERWORLD.get(), "Silver Ore");
        add(Registration.SALT_ORE_DEEPSLATE.get(), "Silver Ore");
        add(Registration.RAW_SILVER.get(), "Raw Silver");
        add(Registration.SILVER_INGOT.get(), "Silver Ingot");
        add(Registration.ALUMINIUM_ORE_OVERWORLD.get(), "Aluminium Ore");
        add(Registration.ALUMINIUM_ORE_DEEPSLATE.get(), "Aluminium Ore");
        add(Registration.RAW_ALUMINIUM.get(), "Raw Aluminium");
        add(Registration.ALUMINIUM_INGOT.get(), "Aluminium Ingot");
        add(Registration.MAGNESIUM_ORE_OVERWORLD.get(), "Magnesium Ore");
        add(Registration.MAGNESIUM_ORE_DEEPSLATE.get(), "Magnesium Ore");
        add(Registration.RAW_MAGNESIUM.get(), "Raw Magnesium");
        add(Registration.MAGNESIUM_INGOT.get(), "Magnesium Ingot");
        add(Registration.NICKEL_ORE_OVERWORLD.get(), "Nickel Ore");
        add(Registration.NICKEL_ORE_DEEPSLATE.get(), "Nickel Ore");
        add(Registration.RAW_NICKEL.get(), "Raw Nickel");
        add(Registration.NICKEL_INGOT.get(), "Nickel Ingot");
        add(Registration.ZINC_ORE_OVERWORLD.get(), "Zinc Ore");
        add(Registration.ZINC_ORE_DEEPSLATE.get(), "Zinc Ore");
        add(Registration.RAW_ZINC.get(), "Raw Zinc");
        add(Registration.ZINC_INGOT.get(), "Zinc Ingot");
        add(Registration.PHOSPHATE_ORE_OVERWORLD.get(), "Phosphate Ore");
        add(Registration.PHOSPHATE_ORE_DEEPSLATE.get(), "Phosphate Ore");
        add(Registration.RAW_PHOSPHATE.get(), "Raw Phosphate");
        add(Registration.PHOSPHATE_INGOT.get(), "Phosphate Ingot");


    }
}
