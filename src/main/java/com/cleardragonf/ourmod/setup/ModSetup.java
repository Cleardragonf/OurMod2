package com.cleardragonf.ourmod.setup;

import com.cleardragonf.ourmod.worldgen.dimensions.Dimensions;
import com.cleardragonf.ourmod.worldgen.ores.Ores;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ModSetup {

    public static final String TAB_NAME = "ourmod";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(Items.DIAMOND);
        }
    };


    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(Ores::onBiomeLoadingEvent);
        //bus.addListener(EventPriority.NORMAL, Structures::addDi);
    }

    public static List<CraftingRecipe> ironRecipes;
    public static List<CraftingRecipe> goldRecipes;
    public static List<CraftingRecipe> diamondRecipes;

    public static List<CraftingRecipe> getRecipesWithItem(RecipeManager manager, Item item)
    {
        ItemStack testStack = new ItemStack(item);
        return manager.getAllRecipesFor(RecipeType.CRAFTING)
                .stream()
                .filter(recipe -> recipe.getIngredients()
                        .stream()
                        .anyMatch(ing -> ing.test(testStack))
                )
                .toList();
    }
    public static void recipeInit(OnDatapackSyncEvent event){
        diamondRecipes = getRecipesWithItem(event.getPlayerList().getServer().getRecipeManager(), Items.DIAMOND);
        goldRecipes = getRecipesWithItem(event.getPlayerList().getServer().getRecipeManager(), Items.GOLD_INGOT);
        ironRecipes = getRecipesWithItem(event.getPlayerList().getServer().getRecipeManager(), Items.IRON_INGOT);
    }

    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Ores.registerConfiguredFeatures();
            Dimensions.register();
        });
        Messages.register();
    }
}
