package com.cleardragonf.ourmod.setup.datagen;

import com.cleardragonf.ourmod.OurMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = OurMod.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        if(event.includeServer()){
            generator.addProvider(new OurRecipes(generator));
            generator.addProvider(new OurLootTables(generator));
            OurBlockTags blockTags = new OurBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new OurItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if(event.includeClient()){
            generator.addProvider(new OurBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new OurItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new OurLanguageProvider(generator, "en_us"));
        }
    }

}
