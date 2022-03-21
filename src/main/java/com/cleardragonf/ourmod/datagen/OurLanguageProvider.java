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

        add(Registration.NAQUDAH_ORE_OVERWORLD.get(), "Naqudah Ore");
        add(Registration.NAQUDRIAH_ORE_DEEPSLATE.get(), "Naqudriah Ore");
        add(Registration.RAW_NAQUDAH.get(), "Raw Naqudah");
        add(Registration.NAQUDAH_INGOT.get(), "Naqudah Ingot");
    }
}
