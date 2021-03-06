package com.cleardragonf.ourmod.blocks.Battery;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.setup.Registration;
import com.cleardragonf.ourmod.variables.CustomEnergyStorage;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BatteryBlockEntity extends BlockEntity {

    public int x, y, z, tick;
    boolean initialized = false;

    //master Block Settings
    public boolean isMaster = true;
    public BlockPos masterCoords = this.worldPosition;
    public List<BatteryBlockEntity> wholeBattery = new ArrayList<>();

    private int ENERGY_CAPACITY = 50000;
    private int ENERGY_RECEIVE = 1000;
    private int COLLECTING_DELAY = 5;

    public void addToList(BatteryBlockEntity block){
        wholeBattery.add(block);

    }

    public void removeToList(BatteryBlockEntity block){
        wholeBattery.remove(block);
    }

    public BlockPos getMaster(){
        return masterCoords;
    }







    public BatteryBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.BATTERY_BLOCKENTITY.get(), pos, state);
    }

    public void setMaster(BlockPos position){
        isMaster = true;
        masterCoords = position;
        if(level.getBlockState(position.above()).getBlock() instanceof BatteryBlock){
            BatteryBlockEntity block = (BatteryBlockEntity) level.getBlockEntity(position.above());
            isMaster = false;
            masterCoords = block.masterCoords;
            BatteryBlockEntity masterBlock = (BatteryBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this);
        }
        else if(level.getBlockState(position.below()).getBlock() instanceof BatteryBlock){

            BatteryBlockEntity block = (BatteryBlockEntity) level.getBlockEntity(position.below());
            isMaster = false;
            masterCoords = block.masterCoords;
            BatteryBlockEntity masterBlock = (BatteryBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this);

        }else if(level.getBlockState(position.west()).getBlock() instanceof BatteryBlock){

            BatteryBlockEntity block = (BatteryBlockEntity) level.getBlockEntity(position.west());
            isMaster = false;
            masterCoords = block.masterCoords;
            BatteryBlockEntity masterBlock = (BatteryBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this);
        }else if(level.getBlockState(position.east()).getBlock() instanceof BatteryBlock){

            BatteryBlockEntity block = (BatteryBlockEntity) level.getBlockEntity(position.east());
            isMaster = false;
            masterCoords = block.masterCoords;
            BatteryBlockEntity masterBlock = (BatteryBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this);
        }else if(level.getBlockState(position.north()).getBlock() instanceof BatteryBlock){
            BatteryBlockEntity block = (BatteryBlockEntity) level.getBlockEntity(position.north());
            isMaster = false;
            masterCoords = block.masterCoords;
            BatteryBlockEntity masterBlock = (BatteryBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this);
        }else if(level.getBlockState(position.south()).getBlock() instanceof BatteryBlock){

            BatteryBlockEntity block = (BatteryBlockEntity) level.getBlockEntity(position.south());
            isMaster = false;
            masterCoords = block.masterCoords;
            BatteryBlockEntity masterBlock = (BatteryBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this);
        }else {
            BatteryBlockEntity masterBlock = (BatteryBlockEntity) level.getBlockEntity(worldPosition);
            masterBlock.addToList(this);
        }
    }




    public void tickServer() {
        if (!initialized)
            init();
        tick++;
        if (tick == 40) {
            tick = 0;
            if (y > 2)
                execute();
            OurMod.LOGGER.log(Level.INFO, wholeBattery.size());
            OurMod.LOGGER.log(Level.INFO, ENERGY_CAPACITY);
            OurMod.LOGGER.log(Level.INFO, this.energy.getEnergyStored());
            ENERGY_CAPACITY = 100000 * wholeBattery.size();
            energy.setCapacity(ENERGY_CAPACITY);
        }
    }

    private void init() {
        initialized = true;
        x = this.worldPosition.getX() - 1;
        y = this.worldPosition.getY() - 1;
        z = this.worldPosition.getZ() - 1;
        tick = 0;
        if(isMaster){
            setMaster(this.worldPosition);
        }

    }

    private void execute() {

    }


    public static final int INPUT_SLOTS = 5;
    public static final int OUTPUT_SLOTS = 1;

    // The actual values for these properties
    private boolean generating = false;
    private boolean collecting = false;
    private BlockState generatingBlock;
    private boolean actuallyGenerating = false;

    // For collecting
    private int collectingTicker = 0;
    private AABB collectingBox = null;

    // For generating our ores
    private int generatingCounter = 0;

    // A direct reference to our items and energy for easy access inside our block entity
    // LazyOptionals to return with getCapability()
    private final ItemStackHandler inputItems = createInputItemHandler();
    private final LazyOptional<IItemHandler> inputItemHandler = LazyOptional.of(() -> inputItems);
    private final ItemStackHandler outputItems = createOutputItemHandler();
    private final LazyOptional<IItemHandler> outputItemHandler = LazyOptional.of(() -> outputItems);
    private final LazyOptional<IItemHandler> combinedItemHandler = LazyOptional.of(this::createCombinedItemHandler);

    public CustomEnergyStorage energy = createEnergyStorage();
    public LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);

    public boolean isGenerating() {
        return generating;
    }

    public void setGenerating(boolean generating) {
        this.generating = generating;
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    public boolean isCollecting() {
        return collecting;
    }

    public void setCollecting(boolean collecting) {
        this.collecting = collecting;
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    public void setGeneratingBlock(BlockState generatingBlock) {
        // Only accept ores by checking the tag
        if (generatingBlock.is(Tags.Blocks.ORES)) {
            this.generatingBlock = generatingBlock;
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }


    @Nonnull
    private ItemStackHandler createInputItemHandler() {
        return new ItemStackHandler(INPUT_SLOTS) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return ItemStack.EMPTY;
            }
        };
    }

    @Nonnull
    private ItemStackHandler createOutputItemHandler() {
        return new ItemStackHandler(OUTPUT_SLOTS) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Nonnull
    private IItemHandler createCombinedItemHandler() {
        return new CombinedInvWrapper(inputItems, outputItems) {
            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return ItemStack.EMPTY;
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return stack;
            }
        };
    }

    private CustomEnergyStorage createEnergyStorage() {
        return new CustomEnergyStorage(ENERGY_CAPACITY, ENERGY_RECEIVE) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }


    // The getUpdateTag()/handleUpdateTag() pair is called whenever the client receives a new chunk
    // it hasn't seen before. i.e. the chunk is loaded

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveClientData(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (tag != null) {
            loadClientData(tag);
        }
    }

    // The getUpdatePacket()/onDataPacket() pair is used when a block update happens on the client
    // (a blockstate change or an explicit notificiation of a block update from the server). It's
    // easiest to implement them based on getUpdateTag()/handleUpdateTag()

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        // This is called client side: remember the current state of the values that we're interested in
        boolean oldGenerating = generating;
        boolean oldCollecting = collecting;
        boolean oldActuallyGenerating = actuallyGenerating;
        BlockState oldGeneratingBlock = generatingBlock;

        CompoundTag tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(tag);

        // If any of the values was changed we request a refresh of our model data and send a block update (this will cause
        // the baked model to be recreated)
        if (oldGenerating != generating || oldCollecting != collecting ||
                oldActuallyGenerating != actuallyGenerating ||
                !Objects.equals(generatingBlock, oldGeneratingBlock)) {
            ModelDataManager.requestModelDataRefresh(this);
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        inputItemHandler.invalidate();
        outputItemHandler.invalidate();
        combinedItemHandler.invalidate();
        energyHandler.invalidate();
    }


    @Override
    protected void saveAdditional(CompoundTag tag) {
        saveClientData(tag);
        tag.put("Inventory", inputItems.serializeNBT());
        tag.put("Energy", energy.serializeNBT());
        CompoundTag infoTag = tag.getCompound("Info");
        infoTag.putInt("Generating", generatingCounter);
    }

    private void saveClientData(CompoundTag tag) {
        CompoundTag infoTag = new CompoundTag();
        tag.put("Info", infoTag);
        infoTag.putBoolean("generating", generating);
        infoTag.putBoolean("collecting", collecting);
        tag.putBoolean("actuallyGenerating", actuallyGenerating);
        if (generatingBlock != null) {
            infoTag.put("block", NbtUtils.writeBlockState(generatingBlock));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadClientData(tag);
        if (tag.contains("Inventory")) {
            inputItems.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("Energy")) {
            energy.deserializeNBT(tag.get("Energy"));
        }
        if (tag.contains("Info")) {
            generatingCounter = tag.getCompound("Info").getInt("Generating");
        }
    }

    private void loadClientData(CompoundTag tag) {
        if (tag.contains("Info")) {
            CompoundTag infoTag = tag.getCompound("Info");
            generating = infoTag.getBoolean("generating");
            collecting = infoTag.getBoolean("collecting");
            if (infoTag.contains("block")) {
                generatingBlock = NbtUtils.readBlockState(infoTag.getCompound("block"));
            }
        }
        actuallyGenerating = tag.getBoolean("actuallyGenerating");
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) {
                return combinedItemHandler.cast();
            } else if (side == Direction.DOWN) {
                return outputItemHandler.cast();
            } else {
                return inputItemHandler.cast();
            }
        } else if (cap == CapabilityEnergy.ENERGY) {
            return energyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }


}