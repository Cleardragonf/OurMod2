package com.cleardragonf.ourmod.datagen;

import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
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
        ShapedRecipeBuilder.shaped(Registration.DIGGER_BLOCK.get())
                        .pattern("iii")
                        .pattern("ixi")
                        .pattern("iii")
                        .define('i', Tags.Items.INGOTS_IRON)
                        .define('x', Registration.NAQUDAH_INGOT.get())
                        .unlockedBy("digger", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.NAQUDAH_INGOT.get()))
                        .group("ourmod")
                        .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.BATTERY_BLOCK.get())
                .pattern("iii")
                .pattern("ixi")
                .pattern("iii")
                .define('i', Registration.NAQUDAH_INGOT.get())
                .define('x', Registration.RAW_NAQUDAH.get())
                .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.NAQUDAH_INGOT.get()))
                .group("ourmod")
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.TRANSLOCATOR_BLOCK.get())
                .pattern("iii")
                .pattern("iii")
                .pattern("ixi")
                .define('i', Registration.NAQUDAH_INGOT.get())
                .define('x', Registration.RAW_NAQUDAH.get())
                .unlockedBy("translocator", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.NAQUDAH_INGOT.get()))
                .group("ourmod")
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.TRANSLOCATOR_TUNER.get())
                .pattern("0i0")
                .pattern("0ii")
                .pattern("i00")
                .define('i', Registration.NAQUDAH_INGOT.get())
                .define('0', Items.AIR)
                .unlockedBy("translocator", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.NAQUDAH_INGOT.get()))
                .group("ourmod")
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
