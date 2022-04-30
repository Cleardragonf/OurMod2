package com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.SmelteryControllerBlockEntity;
import com.cleardragonf.ourmod.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SmelteryTankBlockEntity extends BlockEntity {

    public int x, y, z, tick;
    boolean initialized = false;

    //master Block Settings
    public boolean isMaster = true;
    public BlockPos masterCoords = this.worldPosition;
    public List<BlockPos> wholeTank = new ArrayList<>();
    public BlockPos masterControllerCoords;

    private int TANK_CAPACITY = 100;

    public void addToList(BlockPos block){
        wholeTank.add(block);
    }

    public void removeToList(SmelteryTankBlockEntity block){
        wholeTank.remove(block);
    }

    public BlockPos getMaster(){
        return masterCoords;
    }

    public SmelteryTankBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.SMELTERY_TANK_MODULE_BLOCKENTITY.get(), pos, state);
    }

    public void setMaster(BlockPos position){
        isMaster = true;
        masterCoords = position;
        if(level.getBlockState(position.above()).getBlock() instanceof SmelteryTankBlock){
            SmelteryTankBlockEntity block = (SmelteryTankBlockEntity) level.getBlockEntity(position.above());
            isMaster = false;
            masterCoords = block.masterCoords;
            SmelteryTankBlockEntity masterBlock = (SmelteryTankBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this.getBlockPos());
        }
        else if(level.getBlockState(position.below()).getBlock() instanceof SmelteryTankBlock){

            SmelteryTankBlockEntity block = (SmelteryTankBlockEntity) level.getBlockEntity(position.below());
            isMaster = false;
            masterCoords = block.masterCoords;
            SmelteryTankBlockEntity masterBlock = (SmelteryTankBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this.getBlockPos());

        }
        else if(level.getBlockState(position.west()).getBlock() instanceof SmelteryTankBlock){

            SmelteryTankBlockEntity block = (SmelteryTankBlockEntity) level.getBlockEntity(position.west());
            isMaster = false;
            masterCoords = block.masterCoords;
            SmelteryTankBlockEntity masterBlock = (SmelteryTankBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this.getBlockPos());
        }
        else if(level.getBlockState(position.east()).getBlock() instanceof SmelteryTankBlock){

            SmelteryTankBlockEntity block = (SmelteryTankBlockEntity) level.getBlockEntity(position.east());
            isMaster = false;
            masterCoords = block.masterCoords;
            SmelteryTankBlockEntity masterBlock = (SmelteryTankBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this.getBlockPos());
        }
        else if(level.getBlockState(position.north()).getBlock() instanceof SmelteryTankBlock){
            SmelteryTankBlockEntity block = (SmelteryTankBlockEntity) level.getBlockEntity(position.north());
            isMaster = false;
            masterCoords = block.masterCoords;
            SmelteryTankBlockEntity masterBlock = (SmelteryTankBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this.getBlockPos());
        }
        else if(level.getBlockState(position.south()).getBlock() instanceof SmelteryTankBlock){

            SmelteryTankBlockEntity block = (SmelteryTankBlockEntity) level.getBlockEntity(position.south());
            isMaster = false;
            masterCoords = block.masterCoords;
            SmelteryTankBlockEntity masterBlock = (SmelteryTankBlockEntity) level.getBlockEntity(block.masterCoords);
            masterBlock.addToList(this.getBlockPos());
        }
        else {
            SmelteryTankBlockEntity masterBlock = (SmelteryTankBlockEntity) level.getBlockEntity(worldPosition);
            masterBlock.addToList(this.getBlockPos());
        }
    }

    public void tickServer() {
        if (!initialized)
            init();
        tick++;
        if (tick == 40) {
            tick = 0;
            checkForController();
            OurMod.LOGGER.log(Level.INFO, wholeTank.size());
            OurMod.LOGGER.log(Level.INFO, TANK_CAPACITY);
            TANK_CAPACITY = 100 * wholeTank.size();
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

    private void checkForController() {
        if(isMaster){
            if(masterControllerCoords == null){
                for (BlockPos tankBlock :
                        wholeTank) {
                    for (Direction direction :
                            Direction.values()) {
                        if(level.getBlockEntity(tankBlock.relative(direction)) instanceof SmelteryControllerBlockEntity){
                            this.masterControllerCoords = level.getBlockEntity(tankBlock.relative(direction)).getBlockPos();
                            SmelteryControllerBlockEntity masterBlock = (SmelteryControllerBlockEntity) level.getBlockEntity(masterControllerCoords);
                            masterBlock.masterTank = this.getBlockPos();
                            masterBlock.addTankToList(wholeTank);
                            break;
                        }
                    }
                }
            }
            else{
                SmelteryControllerBlockEntity masterBlock = (SmelteryControllerBlockEntity) level.getBlockEntity(masterControllerCoords);
                masterBlock.addTankToList(wholeTank);
            }
        }
    }

    // The actual values for these properties
    private boolean generating = false;
    private boolean collecting = false;
    private BlockState generatingBlock;
    private boolean actuallyGenerating = false;

    // For generating our ores
    private int generatingCounter = 0;

    // A direct reference to our items and energy for easy access inside our block entity
    // LazyOptionals to return with getCapability()

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
    }


    @Override
    protected void saveAdditional(CompoundTag tag) {
        saveClientData(tag);
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
            return super.getCapability(cap, side);
    }


}