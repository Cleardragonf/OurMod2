package com.cleardragonf.ourmod.items;

import com.cleardragonf.ourmod.blocks.Translocators.TranslocatorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TranslocatorTuner extends Item {
    public TranslocatorTuner(Properties properties){
        super(properties);
    }
}
