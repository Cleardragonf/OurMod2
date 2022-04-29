package com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryTankBlock;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryTankBlockEntity;
import com.cleardragonf.ourmod.setup.Registration;
import com.cleardragonf.ourmod.variables.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
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

import static com.cleardragonf.ourmod.setup.ModSetup.*;


public class SmelteryControllerBlockEntity extends BlockEntity {

    public int x, y, z, tick;
    boolean initialized = false;

    //Controller Block Settings
    public BlockPos controllerCords = this.worldPosition;
    public List<SmelteryControllerBlockEntity> wholeSmeltery = new ArrayList<>();
    public List<BlockPos> allTanks = new ArrayList<>();
    public List<BlockPos> allHeats = new ArrayList<>();
    public BlockPos masterTank;
    public BlockPos masterHeat;

    //public list or Resources
    public int MAX_TANK_AMOUNT = 100;
    public int HEAT_AMOUNT = 10;
    public int ironAmount = 0;
    public int goldAmount = 0;
    public int diamondAmount = 0;
    public int meltingCost = 500;
    public int CURR_TANK_AMOUNT = 0;
    public int CURR_HEAT_AMOUNT = 0;

    private int ENERGY_CAPACITY = 50000;
    private int ENERGY_RECEIVE = 1000;
    private int COLLECTING_DELAY = 5;

    public void addToList(SmelteryControllerBlockEntity block){
        wholeSmeltery.add(block);
    }

    public void removeToList(SmelteryControllerBlockEntity block){
        wholeSmeltery.remove(block);
    }

    public BlockPos getControllerCords(){
        return controllerCords;
    }


    public SmelteryControllerBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.SMELTERY_CONTROLLER_BLOCKENTITY.get(), pos, state);
    }

    public void tickServer() {
        if (!initialized)
            init();
        tick++;
        if (tick == 40) {
            tick = 0;
            if (y > 2)
                execute();
        }
    }

    private void init() {
        initialized = true;
        x = this.worldPosition.getX() - 1;
        y = this.worldPosition.getY() - 1;
        z = this.worldPosition.getZ() - 1;
        tick = 0;
    }

    private void execute() {
        //if there's enough energy go through Melting (eventually making this number lower possibly)
        OurMod.LOGGER.log(Level.INFO, "Number of Tanks: " + allTanks.stream().count());
        OurMod.LOGGER.log(Level.INFO, "Number of HEATS: " + allHeats.stream().count());
        if(energy.getEnergyStored() > meltingCost){
            melt();
            OurMod.LOGGER.log(Level.INFO, "Iron: " + ironAmount + ", Gold: " + goldAmount + ", Diamond: " + diamondAmount);
        }
    }
    
    private void melt(){
        //loops through all Inventory slots available in the Smeltery Controllers GUI
        CURR_TANK_AMOUNT = ironAmount + diamondAmount + goldAmount;
        CURR_HEAT_AMOUNT = HEAT_AMOUNT * Math.toIntExact(allHeats.stream().count());
        for (int i = 0; i < 26; i++) {
            //find the slots that are not empty and then 'melt them down piece by peice'
            if(!inputItems.getStackInSlot(i).isEmpty()){
                //do check here for items...if it's iron melt it down
                ItemStack stack = inputItems.getStackInSlot(i);
                if(ironRecipes.contains(inputItems.getStackInSlot(i).getItem())){
                    if(CURR_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        convertToMB(inputItems.getStackInSlot(i), "iron");
                        stack.setCount(stack.getCount() - 1);
                        energy.consumeEnergy(meltingCost);
                        setChanged();
                    }
                }
                //leave it
                else if(goldRecipes.contains(inputItems.getStackInSlot(i).getItem())){
                    if(CURR_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        convertToMB(inputItems.getStackInSlot(i), "gold");
                        stack.setCount(stack.getCount() - 1);
                        energy.consumeEnergy(meltingCost);
                        setChanged();
                    }
                }
                else if(diamondRecipes.contains(inputItems.getStackInSlot(i).getItem())){
                    if(CURR_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        convertToMB(inputItems.getStackInSlot(i), "diamond");
                        stack.setCount(stack.getCount() - 1);
                        energy.consumeEnergy(meltingCost);
                        setChanged();
                    }
                }
                else if(inputItems.getStackInSlot(i).is(Tags.Items.ORES)){
                    if(CURR_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        convertToMB(inputItems.getStackInSlot(i), "ores");
                        stack.setCount(stack.getCount() - 1);
                        energy.consumeEnergy(meltingCost);
                        setChanged();
                    }
                }
                else if(inputItems.getStackInSlot(i).is(Tags.Items.INGOTS)){
                    if(CURR_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        convertToMB(inputItems.getStackInSlot(i), "ingot");
                        stack.setCount(stack.getCount() - 1);
                        energy.consumeEnergy(meltingCost);
                        setChanged();
                    }
                }
                else{

                }
            }
        }
    }

    private void convertToMB(ItemStack stackInSlot, String iron) {
        switch (iron){
            case "iron":
                if(stackInSlot.is(Items.IRON_NUGGET)){
                    ironAmount += 2;
                }else{
                    ironAmount += 36;
                }
                break;
            case "gold":
                if(stackInSlot.is(Items.GOLD_NUGGET)){
                    goldAmount += 2;
                }else{
                    goldAmount += 36;
                }
                break;
            case "diamond":
                if(stackInSlot.is(Items.DIAMOND)){
                    diamondAmount += 18;
                }else{
                    diamondAmount += 36;
                }
                break;
            case "ores":
                if(stackInSlot.is(Items.IRON_ORE)){
                    ironAmount += 162;
                }else if(stackInSlot.is(Items.GOLD_ORE)){
                    goldAmount += 162;
                }else if(stackInSlot.is(Items.DIAMOND_ORE)){
                    diamondAmount += 162;
                }else{

                }
                break;
            case "ingot":
                if(stackInSlot.is(Items.IRON_INGOT)){
                    ironAmount += 18;
                }else if(stackInSlot.is(Items.GOLD_INGOT)){
                    goldAmount += 18;
                }else{

                }
                break;
            default:
                break;
        }
    }

    public void addTankToList(List<BlockPos> list){
        for (BlockPos tank :
                list) {
            if(!allTanks.contains(tank)){
                allTanks.add(tank);
            }

        }
    }
    public void addHeatToList(List<BlockPos> list){
        for (BlockPos heat :
                list) {
            if(!allHeats.contains(heat)){
                allHeats.add(heat);
            }

        }
    }


    public static final int INPUT_SLOTS = 26;
    public static final int OUTPUT_SLOTS = 1;

    // For collecting
    private int collectingTicker = 0;
    private AABB collectingBox = null;

    // For generating our ores
    private int generatingCounter = 0;

    // A direct reference to our items and energy for easy access inside our block entity
    // LazyOptionals to return with getCapability()
    public final ItemStackHandler inputItems = createInputItemHandler();
    private final LazyOptional<IItemHandler> inputItemHandler = LazyOptional.of(() -> inputItems);
    private final ItemStackHandler outputItems = createOutputItemHandler();
    private final LazyOptional<IItemHandler> outputItemHandler = LazyOptional.of(() -> outputItems);
    private final LazyOptional<IItemHandler> combinedItemHandler = LazyOptional.of(this::createCombinedItemHandler);

    public CustomEnergyStorage energy = createEnergyStorage();
    public LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);

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
                return super.extractItem(slot, amount, simulate);
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

        CompoundTag tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(tag);

        // If any of the values was changed we request a refresh of our model data and send a block update (this will cause
        // the baked model to be recreated)

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
        }
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