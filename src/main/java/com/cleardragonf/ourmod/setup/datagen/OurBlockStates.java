package com.cleardragonf.ourmod.setup.datagen;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OurBlockStates extends BlockStateProvider {

    public OurBlockStates(DataGenerator gen, ExistingFileHelper helper){
        super(gen, OurMod.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels(){
        registerNaqudahGenerator();
        simpleBlock(Registration.NAQUDAH_ORE_OVERWORLD.get());
        simpleBlock(Registration.NAQUDRIAH_ORE_DEEPSLATE.get());
    }

    private void registerNaqudahGenerator(){
        BlockModelBuilder frame = models().getBuilder("block/naqudah_generator/main");
        frame.parent(models().getExistingFile(mcLoc("cube")));

        floatingCube(frame, 0f,0f,0f, 1f, 16f, 1f);
        floatingCube(frame, 15f, 0f, 0f, 16f, 16f, 1f);
        floatingCube(frame, 0f, 0f, 15f, 1f, 16f, 16f);
        floatingCube(frame, 15f, 0f, 15f, 16f, 16f, 16f);

        floatingCube(frame, 1f, 0f, 0f, 15f, 1f, 1f);
        floatingCube(frame, 1f, 15f, 0f, 15f, 16f, 1f);
        floatingCube(frame, 1f, 0f, 15f, 15f, 1f, 16f);
        floatingCube(frame, 1f, 15f, 15f, 15f, 16f, 16f);

        floatingCube(frame, 0f, 0f, 1f, 1f, 1f, 15f);
        floatingCube(frame, 15f, 0f, 1f, 16f, 1f, 15f);
        floatingCube(frame, 0f, 15f, 1f, 1f, 16f, 15f);
        floatingCube(frame, 15f, 15f, 1f, 16f, 16f, 15f);

        floatingCube(frame, 1f, 1f, 1f, 15f, 15f, 15f);

        frame.texture("window", modLoc("block/naqudah_generator_window"));
        frame.texture("particle", modLoc("block/naqudah_generator_off"));

        createNaqudahGeneratorModel(Registration.NAQUDAH_GENERATOR_BLOCK.get(), frame);
    }

    private void floatingCube(BlockModelBuilder builder, float fx, float fy, float fz, float tx, float ty, float tz){
        builder.element()
                .from(fx, fy, fz)
                .to(tx, ty, tz)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#window"))
                .end();
    }

    private void createNaqudahGeneratorModel(Block block, BlockModelBuilder frame){
        BlockModelBuilder singleOff = models().getBuilder("block/naqudah_generator/singleoff")
                .element().from(3,3,3).to(13,13,13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/naqudah_generator_off"));
        BlockModelBuilder singleOn = models().getBuilder("block/naqudah_generator/singleon")
                .element().from(3,3,3).to(13,13,13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/naqudah_generator_on"));

        MultiPartBlockStateBuilder bid = getMultipartBuilder(block);

        bid.part().modelFile(frame).addModel();

        BlockModelBuilder[] models = new BlockModelBuilder[]{singleOff, singleOn};
        for(int i = 0; i <2; i++){
            bid.part().modelFile(models[i]).addModel().condition(BlockStateProperties.POWERED, i == 1);
            bid.part().modelFile(models[i]).rotationX(180).addModel().condition(BlockStateProperties.POWERED, i == 1);
            bid.part().modelFile(models[i]).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i == 1);
            bid.part().modelFile(models[i]).rotationX(270).addModel().condition(BlockStateProperties.POWERED, i == 1);
            bid.part().modelFile(models[i]).rotationY(90).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i == 1);
            bid.part().modelFile(models[i]).rotationY(270).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i == 1);
        }
    }
}
