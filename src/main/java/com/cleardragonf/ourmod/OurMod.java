package com.cleardragonf.ourmod;

import com.cleardragonf.ourmod.events.TranslocatorEvents;
import com.cleardragonf.ourmod.setup.ClientSetup;
import com.cleardragonf.ourmod.setup.Config;
import com.cleardragonf.ourmod.setup.ModSetup;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("ourmod")
public class OurMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "ourmod";

    public OurMod(){

        //Register the Class Registration which is our Deffered Registry
        ModSetup.setup();
        Registration.init();
        Config.register();

        //Register the setupMethod for modloading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        IEventBus modbus2 = MinecraftForge.EVENT_BUS;
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->modbus2.addListener(TranslocatorEvents::onUse));
    }
}
