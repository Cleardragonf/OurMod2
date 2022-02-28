package com.cleardragonf.ourmod.setup;

import com.cleardragonf.ourmod.client.NaqudahGeneratorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void init(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.NAQUDAH_GENERATOR_CONTAINER.get(), NaqudahGeneratorScreen::new);
            ItemBlockRenderTypes.setRenderLayer(Registration.NAQUDAH_GENERATOR_BLOCK.get(), RenderType.translucent());
        });
    }
}
