package com.cleardragonf.ourmod.setup;

import com.cleardragonf.ourmod.client.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

public class ClientSetup {
    public static void init(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.NAQUDAH_GENERATOR_CONTAINER.get(), NaqudahGeneratorScreen::new);
            ItemBlockRenderTypes.setRenderLayer(Registration.NAQUDAH_GENERATOR_BLOCK.get(), RenderType.translucent());
            MenuScreens.register(Registration.DIGGER_CONTAINER.get(), DiggerScreen::new);
            MenuScreens.register(Registration.BATTERY_CONTAINER.get(), BatteryScreen::new);
            MenuScreens.register(Registration.SMELTERY_CONTAINER.get(), SmelteryControllerScreen::new);
            MenuScreens.register(Registration.TRANSLOCATOR_CONTAINER.get(), TranslocatorScreen::new);
        });
    }
}
