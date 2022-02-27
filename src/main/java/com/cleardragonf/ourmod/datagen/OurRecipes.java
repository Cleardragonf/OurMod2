package com.cleardragonf.ourmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class OurRecipes extends RecipeProvider {

    public OurRecipes(DataGenerator gen){
        super(gen);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer){

    }
}
