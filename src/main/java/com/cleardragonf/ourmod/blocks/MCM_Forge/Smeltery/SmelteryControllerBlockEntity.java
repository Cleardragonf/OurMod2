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
    public int cookingTimeSlot1;public int cookingTimeSlot2;public int cookingTimeSlot3;public int cookingTimeSlot4;public int cookingTimeSlot5;public int cookingTimeSlot6;public int cookingTimeSlot7;
    public int cookingTimeSlot8;public int cookingTimeSlot9;public int cookingTimeSlot10;public int cookingTimeSlot11;public int cookingTimeSlot12;public int cookingTimeSlot13;
    public int cookingTimeSlot14;public int cookingTimeSlot15;public int cookingTimeSlot16;public int cookingTimeSlot17;public int cookingTimeSlot18;public int cookingTimeSlot19;
    public int cookingTimeSlot20;public int cookingTimeSlot21;public int cookingTimeSlot22;public int cookingTimeSlot23;public int cookingTimeSlot24;public int cookingTimeSlot25;public int cookingTimeSlot26;
    public int melttime;

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
        MAX_TANK_AMOUNT = Math.toIntExact(allTanks.stream().count()) * 100;
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
                    int ATTEMPTING_TANK_AMOUNT = CURR_TANK_AMOUNT + checkMBAmount(inputItems.getStackInSlot(i));
                    if(ATTEMPTING_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        checkMeltingProgress(i, "iron",stack);
                    }
                }
                else if(goldRecipes.contains(inputItems.getStackInSlot(i).getItem())){
                    int ATTEMPTING_TANK_AMOUNT = CURR_TANK_AMOUNT + checkMBAmount(inputItems.getStackInSlot(i));
                    if(ATTEMPTING_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        checkMeltingProgress(i, "gold",stack);
                    }
                }
                else if(diamondRecipes.contains(inputItems.getStackInSlot(i).getItem())){
                    int ATTEMPTING_TANK_AMOUNT = CURR_TANK_AMOUNT + checkMBAmount(inputItems.getStackInSlot(i));
                    if(ATTEMPTING_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        checkMeltingProgress(i, "diamond",stack);
                    }
                }
                else if(inputItems.getStackInSlot(i).is(Tags.Items.ORES)){
                    int ATTEMPTING_TANK_AMOUNT = CURR_TANK_AMOUNT + checkMBAmount(inputItems.getStackInSlot(i));
                    if(ATTEMPTING_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        checkMeltingProgress(i, "ores",stack);
                    }
                }
                else if(inputItems.getStackInSlot(i).is(Tags.Items.INGOTS)){
                    int ATTEMPTING_TANK_AMOUNT = CURR_TANK_AMOUNT + checkMBAmount(inputItems.getStackInSlot(i));
                    if(ATTEMPTING_TANK_AMOUNT < MAX_TANK_AMOUNT){
                        checkMeltingProgress(i, "ingot",stack);
                    }
                }
                else{

                }
            }
        }
    }

    private int checkMBAmount(ItemStack stackInSlot) {
        if(stackInSlot.is(Tags.Items.NUGGETS)){
            return 2;
        }
        else if(stackInSlot.is(Tags.Items.ORES)){
            return 162;
        }
        else if(stackInSlot.is(Tags.Items.INGOTS)){
            return 18;
        }
        else if(stackInSlot.is(Tags.Items.GEMS)){
            return 18;
        }
        else{
            return 1;
        }
    }


    private void checkMeltingProgress(int i, String material,ItemStack stack){
        switch (i){
            case 1:
                if(cookingTimeSlot1 == melttime){
                    cookingTimeSlot1 = 0;
                    convertToMB(inputItems.getStackInSlot(1), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot1++;
                }
                break;
            case 2:
                if(cookingTimeSlot2 == melttime){
                    cookingTimeSlot2 = 0;
                    convertToMB(inputItems.getStackInSlot(2), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot2++;
                }
                break;
            case 3:
                if(cookingTimeSlot3 == melttime){
                    cookingTimeSlot3 = 0;
                    convertToMB(inputItems.getStackInSlot(3), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot3++;
                }
                break;
            case 4:
                if(cookingTimeSlot4 == melttime){
                    cookingTimeSlot4 = 0;
                    convertToMB(inputItems.getStackInSlot(4), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot4++;
                }
                break;
            case 5:
                if(cookingTimeSlot5 == melttime){
                    cookingTimeSlot5 = 0;
                    convertToMB(inputItems.getStackInSlot(5), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot5++;
                }
                break;
            case 6:
                if(cookingTimeSlot6 == melttime){
                    cookingTimeSlot6 = 0;
                    convertToMB(inputItems.getStackInSlot(6), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot6++;
                }
                break;
            case 7:
                if(cookingTimeSlot7 == melttime){
                    cookingTimeSlot7 = 0;
                    convertToMB(inputItems.getStackInSlot(7), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot7++;
                }
                break;
            case 8:
                if(cookingTimeSlot8 == melttime){
                cookingTimeSlot8 = 0;
                convertToMB(inputItems.getStackInSlot(8), material);
                stack.setCount(stack.getCount() -1);
                energy.consumeEnergy(meltingCost);
                setChanged();
            }else{
                cookingTimeSlot8++;
            }
                break;
            case 9:
                if(cookingTimeSlot9 == melttime){
                    cookingTimeSlot9 = 0;
                    convertToMB(inputItems.getStackInSlot(9), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot9++;
                }
                break;
            case 10:
                if(cookingTimeSlot10 == melttime){
                    cookingTimeSlot10 = 0;
                    convertToMB(inputItems.getStackInSlot(10), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot10++;
                }
                break;
            case 11:
                if(cookingTimeSlot11 == melttime){
                    cookingTimeSlot11 = 0;
                    convertToMB(inputItems.getStackInSlot(11), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot11++;
                }
                break;
            case 12:
                if(cookingTimeSlot12 == melttime){
                    cookingTimeSlot12 = 0;
                    convertToMB(inputItems.getStackInSlot(12), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot12++;
                }
                break;
            case 13:
                if(cookingTimeSlot13 == melttime){
                    cookingTimeSlot13 = 0;
                    convertToMB(inputItems.getStackInSlot(13), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot13++;
                }
                break;
            case 14:
                if(cookingTimeSlot14 == melttime){
                    cookingTimeSlot14 = 0;
                    convertToMB(inputItems.getStackInSlot(14), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot14++;
                }
                break;
            case 15:
                if(cookingTimeSlot15 == melttime){
                    cookingTimeSlot15 = 0;
                    convertToMB(inputItems.getStackInSlot(15), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot15++;
                }
                break;
            case 16:
                if(cookingTimeSlot16 == melttime){
                    cookingTimeSlot16 = 0;
                    convertToMB(inputItems.getStackInSlot(16), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot16++;
                }
                break;
            case 17:
                if(cookingTimeSlot17 == melttime){
                    cookingTimeSlot17 = 0;
                    convertToMB(inputItems.getStackInSlot(17), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot17++;
                }
                break;
            case 18:
                if(cookingTimeSlot18 == melttime){
                    cookingTimeSlot18 = 0;
                    convertToMB(inputItems.getStackInSlot(18), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot18++;
                }
                break;
            case 19:
                if(cookingTimeSlot19 == melttime){
                    cookingTimeSlot19 = 0;
                    convertToMB(inputItems.getStackInSlot(19), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot19++;
                }
                break;
            case 20:
                if(cookingTimeSlot20 == melttime){
                    cookingTimeSlot20 = 0;
                    convertToMB(inputItems.getStackInSlot(20), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot20++;
                }
                break;
            case 21:
                if(cookingTimeSlot21 == melttime){
                    cookingTimeSlot21 = 0;
                    convertToMB(inputItems.getStackInSlot(21), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot21++;
                }
                break;
            case 22:
                if(cookingTimeSlot22 == melttime){
                    cookingTimeSlot22 = 0;
                    convertToMB(inputItems.getStackInSlot(22), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot22++;
                }
                break;
            case 23:
                if(cookingTimeSlot23 == melttime){
                    cookingTimeSlot23 = 0;
                    convertToMB(inputItems.getStackInSlot(23), material);
                    stack.setCount(stack.getCount() -1);
                    energy.consumeEnergy(meltingCost);
                    setChanged();
                }else{
                    cookingTimeSlot23++;
                }
                break;
            case 24:
                break;
            case 25:
                break;
            case 26:
                break;
            default:
                break;
        }
    }

    private void convertToMB(ItemStack stackInSlot, String iron) {
        switch (iron){
            case "iron":
                if(stackInSlot.is(Tags.Items.NUGGETS)){
                    ironAmount += 2;
                }
                else if(stackInSlot.is(Tags.Items.INGOTS))
                {
                    ironAmount += 18;
                }
                else if(stackInSlot.is(Tags.Items.ORES_IRON)){
                    ironAmount += 36;
                }
                else if(stackInSlot.is(Items.IRON_BLOCK)){
                    ironAmount += 162;
                }
                else if(stackInSlot.is(Items.IRON_AXE)){
                    ironAmount += 54;
                }
                else if(stackInSlot.is(Items.IRON_BARS)){
                    ironAmount += 108;
                }
                else if(stackInSlot.is(Items.IRON_BOOTS)){
                    ironAmount += 72;
                }
                else if(stackInSlot.is(Items.IRON_CHESTPLATE)){
                    ironAmount += 144;
                }
                else if(stackInSlot.is(Items.IRON_DOOR)){
                    ironAmount += 108;
                }
                else if(stackInSlot.is(Items.IRON_HELMET)){
                    ironAmount += 90;
                }
                else if(stackInSlot.is(Items.IRON_HOE)){
                    ironAmount += 36;
                }
                else if(stackInSlot.is(Items.IRON_HORSE_ARMOR)){
                    ironAmount += 108;
                }
                else if(stackInSlot.is(Items.IRON_LEGGINGS)){
                    ironAmount += 126;
                }
                else if(stackInSlot.is(Items.IRON_PICKAXE)){
                    ironAmount += 54;
                }
                else if(stackInSlot.is(Items.IRON_SHOVEL)){
                    ironAmount += 18;
                }
                else if(stackInSlot.is(Items.IRON_SWORD)){
                    ironAmount += 36;
                }
                else if(stackInSlot.is(Items.IRON_TRAPDOOR)){
                    ironAmount += 72;
                }
                else{
                    ironAmount += 2;
                }
                break;
            case "gold":
                if(stackInSlot.is(Tags.Items.NUGGETS)){
                    goldAmount += 2;
                }
                else if(stackInSlot.is(Tags.Items.INGOTS)){
                    goldAmount += 18;
                }
                else if(stackInSlot.is(Tags.Items.ORES)){
                    goldAmount += 36;
                }
                else if(stackInSlot.is(Items.GOLD_BLOCK)){
                    goldAmount += 162;
                }
                else if(stackInSlot.is(Items.GOLDEN_APPLE)){
                    goldAmount += 144;
                }
                else if(stackInSlot.is(Items.GOLDEN_AXE)){
                    goldAmount += 54;
                }
                else if(stackInSlot.is(Items.GOLDEN_BOOTS)){
                    goldAmount += 72;
                }
                else if(stackInSlot.is(Items.GOLDEN_CARROT)){
                    goldAmount += 144;
                }
                else if(stackInSlot.is(Items.GOLDEN_CHESTPLATE)){
                    goldAmount += 144;
                }
                else if(stackInSlot.is(Items.GOLDEN_HELMET)){
                    goldAmount += 90;
                }
                else if(stackInSlot.is(Items.GOLDEN_HOE)){
                    goldAmount += 36;
                }
                else if(stackInSlot.is(Items.GOLDEN_HORSE_ARMOR)){
                    goldAmount += 108;
                }
                else if(stackInSlot.is(Items.GOLDEN_LEGGINGS)){
                    goldAmount += 126;
                }
                else if(stackInSlot.is(Items.GOLDEN_PICKAXE)){
                    goldAmount += 54;
                }
                else if(stackInSlot.is(Items.GOLDEN_SHOVEL)){
                    goldAmount += 18;
                }
                else if(stackInSlot.is(Items.GOLDEN_SWORD)){
                    goldAmount += 36;
                }
                else if(stackInSlot.is(Items.ENCHANTED_GOLDEN_APPLE)){
                    goldAmount += 144;
                }
                else{
                    goldAmount += 2;
                }
                break;
            case "diamond":
                if(stackInSlot.is(Tags.Items.ORES)){
                    diamondAmount += 36;
                }
                else if(stackInSlot.is(Tags.Items.NUGGETS)){
                    diamondAmount += 2;
                }
                else if(stackInSlot.is(Tags.Items.GEMS)){
                    diamondAmount += 8;
                }
                else if(stackInSlot.is(Items.DIAMOND_BLOCK)){
                    diamondAmount += 162;
                }
                else if(stackInSlot.is(Items.DIAMOND_AXE)){
                    diamondAmount += 54;
                }
                else if(stackInSlot.is(Items.DIAMOND_BOOTS)){
                    diamondAmount += 72;
                }
                else if(stackInSlot.is(Items.DIAMOND_CHESTPLATE)){
                    diamondAmount += 144;
                }
                else if(stackInSlot.is(Items.DIAMOND_HELMET)){
                    diamondAmount += 40;
                }
                else if(stackInSlot.is(Items.DIAMOND_HOE)){
                    diamondAmount += 36;
                }
                else if(stackInSlot.is(Items.DIAMOND_HORSE_ARMOR)){
                    diamondAmount += 108;
                }
                else if(stackInSlot.is(Items.DIAMOND_LEGGINGS)){
                    diamondAmount += 126;
                }
                else if(stackInSlot.is(Items.DIAMOND_PICKAXE)){
                    diamondAmount += 54;
                }
                else if(stackInSlot.is(Items.DIAMOND_SHOVEL)){
                    diamondAmount += 18;
                }
                else if(stackInSlot.is(Items.DIAMOND_SWORD)){
                    diamondAmount += 36;
                }
                else{
                    diamondAmount += 2;
                }
                break;
            case "ores":
                if(stackInSlot.is(Tags.Items.ORES_IRON)){
                    ironAmount += 36;
                }else if(stackInSlot.is(Tags.Items.ORES_GOLD)){
                    goldAmount += 36;
                }else if(stackInSlot.is(Tags.Items.ORES_DIAMOND)){
                    diamondAmount += 36;
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