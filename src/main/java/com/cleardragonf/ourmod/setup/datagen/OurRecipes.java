package com.cleardragonf.ourmod.setup.datagen;

import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class OurRecipes extends RecipeProvider {

    public OurRecipes(DataGenerator gen){
        super(gen);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer){

        ShapedRecipeBuilder.shaped(Registration.NAQUDAH_GENERATOR_BLOCK.get())
                        .pattern("mmm")
                        .pattern("x#x")
                        .pattern("#x#")
                        .define('x', Tags.Items.DUSTS_REDSTONE)
                        .define('#', Tags.Items.INGOTS_IRON)
                        .define('m', Registration.NAQUDAH_INGOT.get())
                        .group("ourmod")
                        .unlockedBy("naqudah", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.NAQUDAH_INGOT.get()))
                        .save(consumer);


        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.NAQUDAH_ORE_OVERWORLD_ITEM.get()),
                Registration.NAQUDAH_INGOT.get(), 1.0f,100)
                .unlockedBy("has_ore", has(Registration.NAQUDAH_ORE_OVERWORLD_ITEM.get()))
                .save(consumer, "naqudah_ingot1");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.RAW_NAQUDAH.get()),
                Registration.NAQUDAH_INGOT.get(), 0.0f, 100)
                .unlockedBy("has_chunk", has(Registration.RAW_NAQUDAH.get()))
                .save(consumer, "naqudah_ingot2");
    }
}
