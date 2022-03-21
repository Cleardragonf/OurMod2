package com.cleardragonf.ourmod.setup;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.world.dimensions.Dimensions;
import com.cleardragonf.ourmod.world.ores.Ores;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = OurMod.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "ourmod";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(Items.DIAMOND);
        }
    };

    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(Ores::onBiomeLoadingEvent);
    }

    public static void init(FMLCommonSetupEvent event){
        event.enqueueWork(()->{
            Ores.registerConfiguredFeatures();
            Dimensions.register();
        });
    }
}
