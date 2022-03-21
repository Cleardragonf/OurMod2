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
                .add(Registration.NAQUDRIAH_ORE_DEEPSLATE_ITEM.get());
        tag(Tags.Items.INGOTS)
                .add(Registration.NAQUDAH_INGOT.get());


    }

    @Override
    public String getName(){
        return "Our Mod Tags";
    }
}
