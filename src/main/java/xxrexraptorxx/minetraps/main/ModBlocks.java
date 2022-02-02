package xxrexraptorxx.minetraps.main;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.blocks.*;
import xxrexraptorxx.minetraps.utils.CreativeTab;

public class ModBlocks {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static final RegistryObject<BlockBarbedWire> BARBED_WIRE = BLOCKS.register("barbed_wire", BlockBarbedWire::new);
    public static final RegistryObject<Item> BARBED_WIRE_BLOCKITEM = ITEMS.register("barbed_wire", () -> new BlockItem(BARBED_WIRE.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockBarbedWireFence> BARBED_WIRE_FENCE = BLOCKS.register("barbed_wire_fence", BlockBarbedWireFence::new);
    public static final RegistryObject<Item> BARBED_WIRE_FENCE_BLOCKITEM = ITEMS.register("barbed_wire_fence", () -> new BlockItem(BARBED_WIRE_FENCE.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockBarbedWire> RAZOR_WIRE = BLOCKS.register("razor_wire", BlockBarbedWire::new);
    public static final RegistryObject<Item> RAZOR_WIRE_BLOCKITEM = ITEMS.register("razor_wire", () -> new BlockItem(RAZOR_WIRE.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockBearTrap> BEAR_TRAP = BLOCKS.register("bear_trap", BlockBearTrap::new);
    public static final RegistryObject<Item> BEAR_TRAP_BLOCKITEM = ITEMS.register("bear_trap", () -> new BlockItem(BEAR_TRAP.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockChestBomb> CHEST_BOMB = BLOCKS.register("chest_bomb", BlockChestBomb::new);
    public static final RegistryObject<Item> CHEST_BOMB_BLOCKITEM = ITEMS.register("chest_bomb", () -> new BlockItem(CHEST_BOMB.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockExplosiveMine> EXPLOSIVE_MINE = BLOCKS.register("explosive_mine", BlockExplosiveMine::new);
    public static final RegistryObject<Item> EXPLOSIVE_MINE_BLOCKITEM = ITEMS.register("explosive_mine", () -> new BlockItem(EXPLOSIVE_MINE.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockExplosiveMine> TOXIC_MINE = BLOCKS.register("toxic_mine", BlockExplosiveMine::new);
    public static final RegistryObject<Item> TOXIC_MINE_BLOCKITEM = ITEMS.register("toxic_mine", () -> new BlockItem(TOXIC_MINE.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockMud> MUD = BLOCKS.register("mud", BlockMud::new);
    public static final RegistryObject<Item> MUD_BLOCKITEM = ITEMS.register("mud", () -> new BlockItem(MUD.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockQuicksand> QUICK_SAND = BLOCKS.register("quicksand", BlockQuicksand::new);
    public static final RegistryObject<Item> QUICK_SAND_BLOCKITEM = ITEMS.register("quicksand", () -> new BlockItem(QUICK_SAND.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockObstacle> OBSTACLE = BLOCKS.register("obstacle", BlockObstacle::new);
    public static final RegistryObject<Item> OBSTACLE_BLOCKITEM = ITEMS.register("obstacle", () -> new BlockItem(OBSTACLE.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockNailTrap> NAIL_TRAP = BLOCKS.register("nail_trap", BlockNailTrap::new);
    public static final RegistryObject<Item> NAIL_TRAP_BLOCKITEM = ITEMS.register("nail_trap", () -> new BlockItem(NAIL_TRAP.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockNailTrap> TOXIC_NAIL_TRAP = BLOCKS.register("toxic_nail_trap", BlockNailTrap::new);
    public static final RegistryObject<Item> TOXIC_NAIL_TRAP_BLOCKITEM = ITEMS.register("toxic_nail_trap", () -> new BlockItem(TOXIC_NAIL_TRAP.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockSpikes> SPIKES = BLOCKS.register("spikes", BlockSpikes::new);
    public static final RegistryObject<Item> SPIKES_BLOCKITEM = ITEMS.register("spikes", () -> new BlockItem(SPIKES.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockSpikes> TOXIC_SPIKES = BLOCKS.register("spikes_toxic", BlockSpikes::new);
    public static final RegistryObject<Item> TOXIC_SPIKES_BLOCKITEM = ITEMS.register("spikes_toxic", () -> new BlockItem(TOXIC_SPIKES.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockPitfallTrap> PITFALL_TRAP = BLOCKS.register("pitfall_trap", BlockPitfallTrap::new);
    public static final RegistryObject<Item> PITFALL_TRAP_BLOCKITEM = ITEMS.register("pitfall_trap", () -> new BlockItem(PITFALL_TRAP.get(), new Item.Properties().tab(CreativeTab.MOD_TAB)));

    public static final RegistryObject<BlockToxin> TOXIN = BLOCKS.register("toxin", () -> new BlockToxin(ModFluids.TOXIN, BlockBehaviour.Properties.of(Material.WATER).noCollission().randomTicks().strength(100.0F).noDrops()));

}