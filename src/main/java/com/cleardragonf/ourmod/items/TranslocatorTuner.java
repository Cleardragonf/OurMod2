package com.cleardragonf.ourmod.items;

import com.cleardragonf.ourmod.blocks.Translocators.TranslocatorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class TranslocatorTuner extends Item {
    public TranslocatorTuner(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        CompoundTag tag;
        ItemStack itemStack = context.getItemInHand();
        if(itemStack.hasTag()){
            tag = itemStack.getTag();
        }else{
            tag = new CompoundTag();
        }
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(blockEntity instanceof TranslocatorBlockEntity){
            if(level.isClientSide()){
                if(tag.contains("energypos")){
                    TranslatableComponent text = new TranslatableComponent("Connecting to: " + tag.get("energypos"));
                    context.getPlayer().sendMessage(text, context.getPlayer().getUUID());
                }else{
                    TranslatableComponent text = new TranslatableComponent("Please select a Energy Source First");
                    context.getPlayer().sendMessage(text, context.getPlayer().getUUID());
                }
            }

            return InteractionResult.SUCCESS;
        }else{
            return InteractionResult.PASS;
        }
    }
}
