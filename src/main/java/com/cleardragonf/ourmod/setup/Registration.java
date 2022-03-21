package com.cleardragonf.ourmod.setup;

import com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorBlock;
import com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorBlockEntity;
import com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.cleardragonf.ourmod.OurMod.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
    }

    public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    //Blocks and BlockITEMS
    public static final RegistryObject<Block> NAQUDAH_ORE_OVERWORLD = BLOCKS.register("naqudah_ore_overworld", () -> new Block(ORE_PROPERTIES));
    public static final RegistryObject<Item> NAQUDAH_ORE_OVERWORLD_ITEM = fromBlock(NAQUDAH_ORE_OVERWORLD);
    public static final RegistryObject<Block> NAQUDRIAH_ORE_DEEPSLATE = BLOCKS.register("naqudriah_ore_deepslate", () -> new Block(ORE_PROPERTIES));
    public static final RegistryObject<Item> NAQUDRIAH_ORE_DEEPSLATE_ITEM = fromBlock(NAQUDRIAH_ORE_DEEPSLATE);

    //Stand alone ITEMS
    public static final RegistryObject<Item> RAW_NAQUDAH = ITEMS.register("raw_naqudah", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> NAQUDAH_INGOT = ITEMS.register("naqudah_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> TRANSLOCATOR_TUNER = ITEMS.register("translocator_tuner", () -> new Item(ITEM_PROPERTIES));


    //Naqudah Generator
    public static final RegistryObject<NaqudahGeneratorBlock> NAQUDAH_GENERATOR_BLOCK = BLOCKS.register("naqudah_generator", NaqudahGeneratorBlock::new);
    public static final RegistryObject<Item> NAQUDAH_GENERATOR_ITEM = fromBlock(NAQUDAH_GENERATOR_BLOCK);
    public static final RegistryObject<BlockEntityType<NaqudahGeneratorBlockEntity>> NAQUDAH_GENERATOR_BE = BLOCK_ENTITIES.register("naqudah_generator", () -> BlockEntityType.Builder.of(NaqudahGeneratorBlockEntity::new, NAQUDAH_GENERATOR_BLOCK.get()).build(null));
    public static final RegistryObject<MenuType<NaqudahGeneratorContainer>> NAQUDAH_GENERATOR_CONTAINER = CONTAINERS.register("naqudah_generator", () -> IForgeMenuType.create((windowId, inv, data) -> new NaqudahGeneratorContainer(windowId, data.readBlockPos(), inv, inv.player)));

    //MCM AutoDigger
    public static final RegistryObject<DiggerBlock> DIGGER_BLOCK = BLOCKS.register("digger", DiggerBlock::new);
    public static final RegistryObject<Item> DIGGER_ITEM = fromBlock(DIGGER_BLOCK);
    public static final RegistryObject<BlockEntityType<DiggerBlockEntity>> DIGGER_BLOCKENTITY = BLOCK_ENTITIES.register("digger", () -> BlockEntityType.Builder.of(DiggerBlockEntity::new, DIGGER_BLOCK.get()).build(null));
    public static final RegistryObject<MenuType<DiggerContainer>> DIGGER_CONTAINER = CONTAINERS.register("digger", () -> IForgeMenuType.create((windowId, inv, data) -> new DiggerContainer(windowId, data.readBlockPos(), inv, inv.player)));

    //MCM Translocator
    public static final RegistryObject<TranslocatorBlock> TRANSLOCATOR_BLOCK = BLOCKS.register("translocator", TranslocatorBlock::new);
    public static final RegistryObject<Item> TRANSLOCATOR_ITEM = fromBlock(TRANSLOCATOR_BLOCK);
    public static final RegistryObject<BlockEntityType<TranslocatorBlockEntity>> TRANSLOCATOR_BLOCKENTITY = BLOCK_ENTITIES.register("translocator", () -> BlockEntityType.Builder.of(TranslocatorBlockEntity::new, TRANSLOCATOR_BLOCK.get()).build(null));
    public static final RegistryObject<MenuType<TranslocatorContainer>> TRANSLOCATOR_CONTAINER = CONTAINERS.register("translocator", () -> IForgeMenuType.create((windowId, inv, data) -> new TranslocatorContainer(windowId, data.readBlockPos(), inv, inv.player)));

    //MCM MultiBlock Battery
    public static final RegistryObject<BatteryBlock> BATTERY_BLOCK = BLOCKS.register("battery", BatteryBlock::new);
    public static final RegistryObject<Item> BATTERY_ITEM = fromBlock(BATTERY_BLOCK);
    public static final RegistryObject<BlockEntityType<BatteryBlockEntity>> BATTERY_BLOCKENTITY = BLOCK_ENTITIES.register("battery", () -> BlockEntityType.Builder.of(BatteryBlockEntity::new, BATTERY_BLOCK.get()).build(null));
    public static final RegistryObject<MenuType<BatteryContainer>> BATTERY_CONTAINER = CONTAINERS.register("battery", () -> IForgeMenuType.create((windowId, inv, data) -> new BatteryContainer(windowId, data.readBlockPos(), inv, inv.player)));


    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }

    public static final ResourceLocation RL_MYSTERIOUS_DIMENSION_SET = new ResourceLocation(OurMod.MODID, "mysterious_dimension_structure_set");
    public static final TagKey<StructureSet> MYSTERIOUS_DIMENSION_STRUCTURE_SET = TagKey.create(Registry.STRUCTURE_SET_REGISTRY, RL_MYSTERIOUS_DIMENSION_SET);

}
