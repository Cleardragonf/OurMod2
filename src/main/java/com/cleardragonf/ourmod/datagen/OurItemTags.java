package com.cleardragonf.ourmod.datagen;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OurItemTags extends ItemTagsProvider {

    public OurItemTags(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper helper){
        super(gen, blockTags, OurMod.MODID, helper);
    }

    @Override
    protected void addTags(){
        tag(Tags.Items.ORES)
                .add(Registration.NAQUDAH_ORE_OVERWORLD_ITEM.get())
                .add(Registration.NAQUDRIAH_ORE_DEEPSLATE_ITEM.get())
                .add(Registration.COBALT_ORE_OVERWORLD_ITEM.get())
                .add(Registration.PLATINUM_ORE_OVERWORLD_ITEM.get())
                .add(Registration.SILVER_ORE_OVERWORLD_ITEM.get())
                .add(Registration.ALUMINIUM_ORE_OVERWORLD_ITEM.get())
                .add(Registration.MAGNESIUM_ORE_OVERWORLD_ITEM.get())
                .add(Registration.NICKEL_ORE_OVERWORLD_ITEM.get())
                .add(Registration.ZINC_ORE_OVERWORLD_ITEM.get())
                .add(Registration.PHOSPHATE_ORE_DEEPSLATE_ITEM.get())
                .add(Registration.SALT_ORE_OVERWORLD_ITEM.get());
        tag(Tags.Items.INGOTS)
                .add(Registration.NAQUDAH_INGOT.get())
                .add(Registration.COBALT_INGOT.get())
                .add(Registration.PLATINUM_INGOT.get())
                .add(Registration.SILVER_INGOT.get())
                .add(Registration.ALUMINIUM_INGOT.get())
                .add(Registration.MAGNESIUM_INGOT.get())
                .add(Registration.NICKEL_INGOT.get())
                .add(Registration.ZINC_INGOT.get())
                .add(Registration.PHOSPHATE_INGOT.get());


    }

    @Override
    public String getName(){
        return "Our Mod Tags";
    }
}
