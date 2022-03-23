package com.cleardragonf.ourmod.blocks.AstriaPorta;

import com.cleardragonf.ourmod.variables.CustomMethod;
import com.cleardragonf.ourmod.worldgen.dimensions.Dimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Properties;


public class SGBaseBlock extends Block {
    private static final VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, .8, 1);

    public SGBaseBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(-1.0F, 3600000.0F)
                .noDrops());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ServerPlayer player) {
            if (level.dimension().equals(Dimensions.MYSTERIOUS)) {
                teleportTo(player, pos.north(), Level.OVERWORLD);
            } else if(level.dimension().equals(Level.OVERWORLD)){
                teleportTo(player, pos.north(), Dimensions.ABIDOS);
            }else if(level.dimension().equals(Dimensions.ABIDOS)){
                teleportTo(player, pos.north(), Dimensions.MYSTERIOUS);
            }
        }
    }

    private void teleportTo(ServerPlayer player, BlockPos pos, ResourceKey<Level> id) {
            ServerLevel world = player.getServer().getLevel(id);
            CustomMethod.teleport(player, world, new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
    }
}
