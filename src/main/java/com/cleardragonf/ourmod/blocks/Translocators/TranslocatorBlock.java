package com.cleardragonf.ourmod.blocks.Translocators;

import com.cleardragonf.ourmod.items.TranslocatorTuner;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class TranslocatorBlock extends Block implements EntityBlock {


    public static final String MESSAGE_POWERGEN = "message.powergen";
    public static final String SCREEN_TUTORIAL_POWERGEN = "screen.tutorial.powergen";

    private static final VoxelShape RENDER_SHAPE = Shapes.box(0.1, 0.1, 0.1, 0.9, 0.9, 0.9);

    public TranslocatorBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(2.0f)
                .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
                .requiresCorrectToolForDrops()
        );
    }
    public static final int POWERGEN_GENERATE = 60;

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return RENDER_SHAPE;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flag) {
        list.add(new TranslatableComponent(MESSAGE_POWERGEN, Integer.toString(POWERGEN_GENERATE))
                .withStyle(ChatFormatting.BLUE));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TranslocatorBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return (lvl, pos, blockState, t) -> {
            if (t instanceof TranslocatorBlockEntity tile) {
                tile.tickServer();
            }
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.POWERED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(BlockStateProperties.POWERED, false);
    }



    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
            BlockEntity be = level.getBlockEntity(pos);
            if(!level.isClientSide()){
                if (be instanceof TranslocatorBlockEntity) {
                    if(player.getMainHandItem().getItem().equals(Registration.TRANSLOCATOR_TUNER.get())){
                        ItemStack item = player.getMainHandItem();
                        CompoundTag tag;
                        if(item.hasTag()){
                            tag = item.getTag();
                        }else{
                            tag = new CompoundTag();
                        }
                        if(tag.contains("energypos")){
                            CompoundTag tagger = tag.getCompound("energypos");
                            BlockPos blockPos = new BlockPos(tagger.getInt("x"), tagger.getInt("y"), tagger.getInt("z"));
                            TranslocatorBlockEntity reciever = (TranslocatorBlockEntity)level.getBlockEntity(blockPos);
                            TranslocatorBlockEntity sender = (TranslocatorBlockEntity)level.getBlockEntity(pos);
                            sender.recievePowerBlocks.add(reciever);
                            TranslatableComponent text = new TranslatableComponent("Recieving energy from: " + tag.get("energypos"));
                            player.sendMessage(text, player.getUUID());
                            item.removeTagKey("energypos");
                        }
                        else{
                            CompoundTag tagpos = new CompoundTag();
                            tagpos.putInt("x", be.getBlockPos().getX());
                            tagpos.putInt("y", be.getBlockPos().getY());
                            tagpos.putInt("z", be.getBlockPos().getZ());
                            tag.put("energypos", tagpos);
                            TranslatableComponent text = new TranslatableComponent("Connected to: " + tag.get("energypos"));
                            player.sendMessage(text, player.getUUID());
                            item.setTag(tag);
                            return InteractionResult.SUCCESS;
                        }
                    }else{
                        MenuProvider containerProvider = new MenuProvider() {
                            @Override
                            public Component getDisplayName() {
                                return new TranslatableComponent(SCREEN_TUTORIAL_POWERGEN);
                            }

                            @Override
                            public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                                return new TranslocatorContainer(windowId, pos, playerInventory, playerEntity);
                            }
                        };
                        NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
                    }

                } else {
                    throw new IllegalStateException("Our named container provider is missing!");
                }
            }
        return InteractionResult.SUCCESS;
    }


}
