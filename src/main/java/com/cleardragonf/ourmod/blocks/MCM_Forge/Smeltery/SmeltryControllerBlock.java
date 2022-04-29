package com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery;

import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryHeatBlock;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryHeatBlockEntity;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryTankBlock;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryTankBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.Explosion;
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

public class SmeltryControllerBlock extends Block implements EntityBlock {


    public static final String MESSAGE_POWERGEN = "message.powergen";
    public static final String SCREEN_TUTORIAL_POWERGEN = "screen.tutorial.powergen";

    private static final VoxelShape RENDER_SHAPE = Shapes.box(0.1, 0.1, 0.1, 0.9, 0.9, 0.9);


    public SmeltryControllerBlock() {
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
        return new SmelteryControllerBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return (lvl, pos, blockState, t) -> {
            if (t instanceof SmelteryControllerBlockEntity tile) {
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

        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof SmelteryControllerBlockEntity) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return new TranslatableComponent(SCREEN_TUTORIAL_POWERGEN);
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        SmelteryControllerBlockEntity block = (SmelteryControllerBlockEntity) level.getBlockEntity(pos);

                        return new SmelteryControllerContainer(windowId, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState p_49854_, Player p_49855_) {
        SmelteryControllerBlockEntity block = (SmelteryControllerBlockEntity) level.getBlockEntity(pos);
        if(block.energy.getEnergyStored() > 0){
            int explosion = block.energy.getEnergyStored() / 10000;

            level.explode(p_49855_,pos.getX(),pos.getY(),pos.getZ(),(1.0f * explosion), Explosion.BlockInteraction.BREAK);
            if(block.masterTank != null){
                level.explode(p_49855_,block.masterTank.getX(),block.masterTank.getY(),block.masterTank.getZ(),(1.0f * (block.energy.getEnergyStored() / 10000)), Explosion.BlockInteraction.BREAK);
            }
            if(block.masterHeat != null){
                level.explode(p_49855_,block.masterHeat.getX(),block.masterHeat.getY(),block.masterHeat.getZ(),(1.0f * (block.energy.getEnergyStored() / 10000)), Explosion.BlockInteraction.BREAK);
            }
        }
        if(block.masterTank != null && level.getBlockEntity(block.masterTank) instanceof SmelteryTankBlockEntity){
            SmelteryTankBlock tankblock = (SmelteryTankBlock) level.getBlockState(block.masterTank).getBlock();
            tankblock.playerWillDestroy(level,block.masterTank,p_49854_,p_49855_);
        }
        if(block.masterHeat != null&& level.getBlockEntity(block.masterHeat) instanceof SmelteryHeatBlockEntity){
            SmelteryHeatBlock heatBlock = (SmelteryHeatBlock) level.getBlockState(block.masterHeat).getBlock();
            heatBlock.playerWillDestroy(level,block.masterHeat,p_49854_,p_49855_);
        }
    }


}
