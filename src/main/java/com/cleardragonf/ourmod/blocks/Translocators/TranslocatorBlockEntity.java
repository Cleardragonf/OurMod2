package com.cleardragonf.ourmod.blocks.Translocators;

import com.cleardragonf.ourmod.setup.Registration;
import com.cleardragonf.ourmod.variables.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TranslocatorBlockEntity extends BlockEntity{
    private static final int COLLECTING_DELAY = 5;
    public int x, y, z, tick;
    boolean initialized = false;

    //Translocation Booleans for Buttons in GUI
    public boolean itemRecieverOn = false;
    public boolean itemSendingOn = true;

    //Variables and such for Energy
    private  int ENERGY_CAPACITY = 50000;
    private  int ENERGY_RECEIVE = 200;
    public boolean energyRecieverOn = false;
    public boolean energySendingOn = true;
    public List<BlockEntity> recievePowerBlocks = new ArrayList<>();
    public List<BlockEntity> powerSendingBlocks = new ArrayList<>();
    public CustomEnergyStorage energy = createEnergyStorage();
    public LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);
    Tag powerBlocks;


    //Constructor
    public TranslocatorBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.TRANSLOCATOR_BLOCKENTITY.get(), pos, state);
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
        //Try Transporting Items and Energy to the Correlating Inputs
        if(energySendingOn) {
            if (!recievePowerBlocks.isEmpty()) {
                sendOutPower();
                executeEnergySearch();
            }
        }
    }


    public void executeEnergySearch(){
        if(powerBlocks!=null){
            CompoundTag tag = (CompoundTag) powerBlocks;
            BlockPos pos = new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
            TranslocatorBlockEntity target = (TranslocatorBlockEntity) level.getBlockEntity(pos);
            if(target.energy.getEnergyStored() >0 && this.energy.getEnergyStored() < ENERGY_CAPACITY){
                target.energy.extractEnergy(target.energy.getEnergyStored(),false);
                saveWithFullMetadata();
                setChanged();
            }
        }
    }


    //Setup Energy for Translocation
    private CustomEnergyStorage createEnergyStorage() {
        return new CustomEnergyStorage(ENERGY_CAPACITY, ENERGY_RECEIVE) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    private void sendOutPower(){
        AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
        if(capacity.get() > 0){
            for (BlockEntity block :
                    recievePowerBlocks){
                if(block !=null){
                    boolean doContinue = block.getCapability(CapabilityEnergy.ENERGY).map(handler ->{
                        if(handler.canReceive()){
                            int received = handler.receiveEnergy(Math.min(capacity.get(), 5000), false);
                            capacity.addAndGet(-received);
                            setChanged();
                            return capacity.get() > 0;
                        } else{
                            return true;
                        }
                    }
                    ).orElse(true);
                    if(!doContinue){
                        return;
                    }
                }
            }
        }
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energyHandler.cast();
        }else {
            return super.getCapability(cap, side);
        }
    }



}
