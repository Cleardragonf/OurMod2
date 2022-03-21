package com.cleardragonf.ourmod.setup.datagen;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OurBlockTags extends BlockTagsProvider {

    public OurBlockTags(DataGenerator gen, ExistingFileHelper helper){
        super(gen, OurMod.MODID, helper);
    }

    @Override
    protected void addTags(){
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.NAQUDAH_ORE_OVERWORLD.get())
                .add(Registration.NAQUDRIAH_ORE_DEEPSLATE.get())
                .add(Registration.NAQUDAH_GENERATOR_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.NAQUDAH_ORE_OVERWORLD.get())
                .add(Registration.NAQUDAH_GENERATOR_BLOCK.get())
                .add(Registration.NAQUDRIAH_ORE_DEEPSLATE.get());
        tag(Tags.Blocks.ORES)
                .add(Registration.NAQUDAH_ORE_OVERWORLD.get())
                .add(Registration.NAQUDRIAH_ORE_DEEPSLATE.get());
    }

    @Override
    public String getName() {return "Our Mod Tags";}
}
