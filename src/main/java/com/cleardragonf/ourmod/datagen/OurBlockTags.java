package com.cleardragonf.ourmod.datagen;

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
                .add(Registration.COBALT_ORE_OVERWORLD.get())
                .add(Registration.PLATINUM_ORE_OVERWORLD.get())
                .add(Registration.SILVER_ORE_OVERWORLD.get())
                .add(Registration.ALUMINIUM_ORE_OVERWORLD.get())
                .add(Registration.MAGNESIUM_ORE_OVERWORLD.get())
                .add(Registration.NICKEL_ORE_OVERWORLD.get())
                .add(Registration.ZINC_ORE_OVERWORLD.get())
                .add(Registration.PHOSPHATE_ORE_DEEPSLATE.get())
                .add(Registration.SALT_ORE_OVERWORLD.get())
                .add(Registration.NAQUDAH_GENERATOR_BLOCK.get())
                .add(Registration.DIGGER_BLOCK.get())
                .add(Registration.BATTERY_BLOCK.get())
                .add(Registration.TRANSLOCATOR_BLOCK.get())
                .add(Registration.STARGATE_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.NAQUDAH_ORE_OVERWORLD.get())
                .add(Registration.NAQUDAH_GENERATOR_BLOCK.get())
                .add(Registration.NAQUDRIAH_ORE_DEEPSLATE.get())
                .add(Registration.DIGGER_BLOCK.get())
                .add(Registration.BATTERY_BLOCK.get())
                .add(Registration.ALUMINIUM_ORE_OVERWORLD.get())
                .add(Registration.MAGNESIUM_ORE_OVERWORLD.get())
                .add(Registration.NICKEL_ORE_OVERWORLD.get())
                .add(Registration.ZINC_ORE_OVERWORLD.get())
                .add(Registration.PHOSPHATE_ORE_DEEPSLATE.get())
                .add(Registration.TRANSLOCATOR_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Registration.ALUMINIUM_ORE_OVERWORLD.get())
                .add(Registration.MAGNESIUM_ORE_OVERWORLD.get())
                .add(Registration.NICKEL_ORE_OVERWORLD.get())
                .add(Registration.ZINC_ORE_OVERWORLD.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(Registration.SALT_ORE_OVERWORLD.get());
        tag(Tags.Blocks.ORES)
                .add(Registration.COBALT_ORE_OVERWORLD.get())
                .add(Registration.PLATINUM_ORE_OVERWORLD.get())
                .add(Registration.SILVER_ORE_OVERWORLD.get())
                .add(Registration.ALUMINIUM_ORE_OVERWORLD.get())
                .add(Registration.MAGNESIUM_ORE_OVERWORLD.get())
                .add(Registration.NICKEL_ORE_OVERWORLD.get())
                .add(Registration.ZINC_ORE_OVERWORLD.get())
                .add(Registration.PHOSPHATE_ORE_DEEPSLATE.get())
                .add(Registration.SALT_ORE_OVERWORLD.get())
                .add(Registration.NAQUDAH_ORE_OVERWORLD.get())
                .add(Registration.NAQUDRIAH_ORE_DEEPSLATE.get());
    }

    @Override
    public String getName() {return "Our Mod Tags";}
}
