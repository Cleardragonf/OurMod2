package com.cleardragonf.ourmod.setup;

import com.cleardragonf.ourmod.OurMod;
import com.cleardragonf.ourmod.blocks.AstriaPorta.SGBaseBlock;
import com.cleardragonf.ourmod.blocks.Battery.BatteryBlock;
import com.cleardragonf.ourmod.blocks.Battery.BatteryBlockEntity;
import com.cleardragonf.ourmod.blocks.Battery.BatteryContainer;
import com.cleardragonf.ourmod.blocks.Digger.DiggerBlock;
import com.cleardragonf.ourmod.blocks.Digger.DiggerBlockEntity;
import com.cleardragonf.ourmod.blocks.Digger.DiggerContainer;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryModuleBlock;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.GenericBlock.SmelteryModuleBlockEntity;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.SmelteryControllerBlockEntity;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.SmelteryControllerContainer;
import com.cleardragonf.ourmod.blocks.MCM_Forge.Smeltery.SmeltryControllerBlock;
import com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorBlock;
import com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorBlockEntity;
import com.cleardragonf.ourmod.blocks.NaqudahGenerator.NaqudahGeneratorContainer;
import com.cleardragonf.ourmod.blocks.Translocators.TranslocatorBlock;
import com.cleardragonf.ourmod.blocks.Translocators.TranslocatorBlockEntity;
import com.cleardragonf.ourmod.blocks.Translocators.TranslocatorContainer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.structure.StructureSet;
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
    public static final RegistryObject<Block> NAQUDAH_ORE_OVERWORLD = BLOCKS.register("naqudah_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> NAQUDAH_ORE_OVERWORLD_ITEM = fromBlock(NAQUDAH_ORE_OVERWORLD);
    public static final RegistryObject<Block> NAQUDRIAH_ORE_DEEPSLATE = BLOCKS.register("naqudriah_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> NAQUDRIAH_ORE_DEEPSLATE_ITEM = fromBlock(NAQUDRIAH_ORE_DEEPSLATE);


    //("coal_ore", new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(BREAKTIME, EXPLOSIVE_RESISTANCE), UniformInt.of(0, 2)));
    //Ores Blocks
    public static final RegistryObject<Block> COBALT_ORE_OVERWORLD = BLOCKS.register("cobalt_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> COBALT_ORE_OVERWORLD_ITEM = fromBlock(COBALT_ORE_OVERWORLD);
    public static final RegistryObject<Block> PLATINUM_ORE_OVERWORLD = BLOCKS.register("platinum_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> PLATINUM_ORE_OVERWORLD_ITEM = fromBlock(PLATINUM_ORE_OVERWORLD);
    public static final RegistryObject<Block> SILVER_ORE_OVERWORLD = BLOCKS.register("silver_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> SILVER_ORE_OVERWORLD_ITEM = fromBlock(SILVER_ORE_OVERWORLD);
    public static final RegistryObject<Block> ALUMINIUM_ORE_OVERWORLD = BLOCKS.register("aluminium_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> ALUMINIUM_ORE_OVERWORLD_ITEM = fromBlock(ALUMINIUM_ORE_OVERWORLD);
    public static final RegistryObject<Block> MAGNESIUM_ORE_OVERWORLD = BLOCKS.register("magnesium_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> MAGNESIUM_ORE_OVERWORLD_ITEM = fromBlock(MAGNESIUM_ORE_OVERWORLD);
    public static final RegistryObject<Block> NICKEL_ORE_OVERWORLD = BLOCKS.register("nickel_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> NICKEL_ORE_OVERWORLD_ITEM = fromBlock(NICKEL_ORE_OVERWORLD);
    public static final RegistryObject<Block> ZINC_ORE_OVERWORLD = BLOCKS.register("zinc_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> ZINC_ORE_OVERWORLD_ITEM = fromBlock(ZINC_ORE_OVERWORLD);
    public static final RegistryObject<Block> PHOSPHATE_ORE_OVERWORLD = BLOCKS.register("phosphate_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> PHOSPHATE_ORE_OVERWORLD_ITEM = fromBlock(PHOSPHATE_ORE_OVERWORLD);
    public static final RegistryObject<Block> SALT_ORE_OVERWORLD = BLOCKS.register("salt_ore_overworld", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> SALT_ORE_OVERWORLD_ITEM = fromBlock(SALT_ORE_OVERWORLD);
    public static final RegistryObject<Block> COBALT_ORE_DEEPSLATE = BLOCKS.register("cobalt_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> COBALT_ORE_DEEPSLATE_ITEM = fromBlock(COBALT_ORE_DEEPSLATE);
    public static final RegistryObject<Block> PLATINUM_ORE_DEEPSLATE = BLOCKS.register("platinum_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> PLATINUM_ORE_DEEPSLATE_ITEM = fromBlock(PLATINUM_ORE_DEEPSLATE);
    public static final RegistryObject<Block> SILVER_ORE_DEEPSLATE = BLOCKS.register("silver_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> SILVER_ORE_DEEPSLATE_ITEM = fromBlock(SILVER_ORE_DEEPSLATE);
    public static final RegistryObject<Block> ALUMINIUM_ORE_DEEPSLATE = BLOCKS.register("aluminium_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> ALUMINIUM_ORE_DEEPSLATE_ITEM = fromBlock(ALUMINIUM_ORE_DEEPSLATE);
    public static final RegistryObject<Block> MAGNESIUM_ORE_DEEPSLATE = BLOCKS.register("magnesium_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> MAGNESIUM_ORE_DEEPSLATE_ITEM = fromBlock(MAGNESIUM_ORE_DEEPSLATE);
    public static final RegistryObject<Block> NICKEL_ORE_DEEPSLATE = BLOCKS.register("nickel_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> NICKEL_ORE_DEEPSLATE_ITEM = fromBlock(NICKEL_ORE_DEEPSLATE);
    public static final RegistryObject<Block> ZINC_ORE_DEEPSLATE = BLOCKS.register("zinc_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> ZINC_ORE_DEEPSLATE_ITEM = fromBlock(ZINC_ORE_DEEPSLATE);
    public static final RegistryObject<Block> PHOSPHATE_ORE_DEEPSLATE = BLOCKS.register("phosphate_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> PHOSPHATE_ORE_DEEPSLATE_ITEM = fromBlock(PHOSPHATE_ORE_DEEPSLATE);
    public static final RegistryObject<Block> SALT_ORE_DEEPSLATE = BLOCKS.register("salt_ore_deepslate", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F,3.0F), UniformInt.of(0,2)));
    public static final RegistryObject<Item> SALT_ORE_DEEPSLATE_ITEM = fromBlock(SALT_ORE_DEEPSLATE);
    //Ore Items
    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_ALUMINIUM = ITEMS.register("raw_aluminium", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_MAGNESIUM = ITEMS.register("raw_magnesium", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_NICKEL = ITEMS.register("raw_nickel", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_PHOSPHATE = ITEMS.register("raw_phosphate", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> PHOSPHATE_INGOT = ITEMS.register("phosphate_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new Item(ITEM_PROPERTIES));

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

    //MCM MultiBlock SMELTERY
    public static final RegistryObject<SmeltryControllerBlock> SMELTERY_CONTROLLER_BLOCK = BLOCKS.register("smeltery_controller", SmeltryControllerBlock::new);
    public static final RegistryObject<Item> SMELTERY_CONTROLLER_ITEM = fromBlock(SMELTERY_CONTROLLER_BLOCK);
    public static final RegistryObject<BlockEntityType<SmelteryControllerBlockEntity>> SMELTERY_CONTROLLER_BLOCKENTITY = BLOCK_ENTITIES.register("smeltery_controller", () -> BlockEntityType.Builder.of(SmelteryControllerBlockEntity::new, SMELTERY_CONTROLLER_BLOCK.get()).build(null));
    public static final RegistryObject<MenuType<SmelteryControllerContainer>> SMELTERY_CONTAINER = CONTAINERS.register("smeltery_controller", () -> IForgeMenuType.create((windowId, inv, data) -> new SmelteryControllerContainer(windowId, data.readBlockPos(), inv, inv.player)));
    public static final RegistryObject<SmelteryModuleBlock> SMELTERY_TANK_MODULE = BLOCKS.register("smeltery_tank_module", SmelteryModuleBlock::new);
    public static final RegistryObject<Item> SMELTERY_TANK_MODULE_ITEM = fromBlock(SMELTERY_TANK_MODULE);
    public static final RegistryObject<BlockEntityType<SmelteryModuleBlockEntity>> SMELTERY_TANK_MODULE_BLOCKENTITY = BLOCK_ENTITIES.register("smeltery_tank_module", () -> BlockEntityType.Builder.of(SmelteryModuleBlockEntity::new, SMELTERY_TANK_MODULE.get()).build(null));
    public static final RegistryObject<SmelteryModuleBlock> SMELTERY_BATTERY_MODULE = BLOCKS.register("smeltery_battery_module", SmelteryModuleBlock::new);
    public static final RegistryObject<Item> SMELTERY_BATTERY_MODULE_ITEM = fromBlock(SMELTERY_BATTERY_MODULE);
    public static final RegistryObject<BlockEntityType<SmelteryModuleBlockEntity>> SMELTERY_BATTERY_MODULE_BLOCKENTITY = BLOCK_ENTITIES.register("smeltery_battery_module", () -> BlockEntityType.Builder.of(SmelteryModuleBlockEntity::new, SMELTERY_BATTERY_MODULE.get()).build(null));
    public static final RegistryObject<SmelteryModuleBlock> SMELTERY_HEAT_MODULE = BLOCKS.register("smeltery_heat_module", SmelteryModuleBlock::new);
    public static final RegistryObject<Item> SMELTERY_HEAT_MODULE_ITEM = fromBlock(SMELTERY_HEAT_MODULE);
    public static final RegistryObject<BlockEntityType<SmelteryModuleBlockEntity>> SMELTERY_HEAT_MODULE_BLOCKENTITY = BLOCK_ENTITIES.register("smeltery_heat_module", () -> BlockEntityType.Builder.of(SmelteryModuleBlockEntity::new, SMELTERY_HEAT_MODULE.get()).build(null));



    //AstriaPorta Blocks
    public static final RegistryObject<SGBaseBlock> STARGATE_BLOCK = BLOCKS.register("stargate_base_block", SGBaseBlock::new);
    public static final RegistryObject<Item> STARGATE_ITEM = fromBlock(STARGATE_BLOCK);

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }

    public static final ResourceLocation RL_MYSTERIOUS_DIMENSION_SET = new ResourceLocation(OurMod.MODID, "mysterious_dimension_structure_set");
    public static final TagKey<StructureSet> MYSTERIOUS_DIMENSION_STRUCTURE_SET = TagKey.create(Registry.STRUCTURE_SET_REGISTRY, RL_MYSTERIOUS_DIMENSION_SET);

}
