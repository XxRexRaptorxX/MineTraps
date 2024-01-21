package xxrexraptorxx.minetraps.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.minetraps.blocks.*;
import xxrexraptorxx.minetraps.main.References;

public class ModBlocks {

    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(References.MODID);
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(References.MODID);

    public static void init(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }


    public static final DeferredBlock<BlockBarbedWire> BARBED_WIRE = BLOCKS.register("barbed_wire", BlockBarbedWire::new);
    public static final DeferredItem<Item> BARBED_WIRE_BLOCKITEM = ITEMS.register("barbed_wire", () -> new BlockItem(BARBED_WIRE.get(), new Item.Properties()));

    public static final DeferredBlock<BlockBarbedWireFence> BARBED_WIRE_FENCE = BLOCKS.register("barbed_wire_fence", BlockBarbedWireFence::new);
    public static final DeferredItem<Item> BARBED_WIRE_FENCE_BLOCKITEM = ITEMS.register("barbed_wire_fence", () -> new BlockItem(BARBED_WIRE_FENCE.get(), new Item.Properties()));

    public static final DeferredBlock<BlockBarbedWire> RAZOR_WIRE = BLOCKS.register("razor_wire", BlockBarbedWire::new);
    public static final DeferredItem<Item> RAZOR_WIRE_BLOCKITEM = ITEMS.register("razor_wire", () -> new BlockItem(RAZOR_WIRE.get(), new Item.Properties()));

    public static final DeferredBlock<BlockBearTrap> BEAR_TRAP = BLOCKS.register("bear_trap", BlockBearTrap::new);
    public static final DeferredItem<Item> BEAR_TRAP_BLOCKITEM = ITEMS.register("bear_trap", () -> new BlockItem(BEAR_TRAP.get(), new Item.Properties()));

    public static final DeferredBlock<BlockChestBomb> CHEST_BOMB = BLOCKS.register("chest_bomb", BlockChestBomb::new);
    public static final DeferredItem<Item> CHEST_BOMB_BLOCKITEM = ITEMS.register("chest_bomb", () -> new BlockItem(CHEST_BOMB.get(), new Item.Properties()));

    public static final DeferredBlock<BlockExplosiveMine> EXPLOSIVE_MINE = BLOCKS.register("explosive_mine", BlockExplosiveMine::new);
    public static final DeferredItem<Item> EXPLOSIVE_MINE_BLOCKITEM = ITEMS.register("explosive_mine", () -> new BlockItem(EXPLOSIVE_MINE.get(), new Item.Properties()));

    public static final DeferredBlock<BlockExplosiveMine> TOXIC_MINE = BLOCKS.register("toxic_mine", BlockExplosiveMine::new);
    public static final DeferredItem<Item> TOXIC_MINE_BLOCKITEM = ITEMS.register("toxic_mine", () -> new BlockItem(TOXIC_MINE.get(), new Item.Properties()));

    public static final DeferredBlock<BlockQuicksand> QUICK_SAND = BLOCKS.register("quicksand", BlockQuicksand::new);
    public static final DeferredItem<Item> QUICK_SAND_BLOCKITEM = ITEMS.register("quicksand", () -> new BlockItem(QUICK_SAND.get(), new Item.Properties()));

    public static final DeferredBlock<BlockObstacle> OBSTACLE = BLOCKS.register("obstacle", BlockObstacle::new);
    public static final DeferredItem<Item> OBSTACLE_BLOCKITEM = ITEMS.register("obstacle", () -> new BlockItem(OBSTACLE.get(), new Item.Properties()));

    public static final DeferredBlock<BlockNailTrap> NAIL_TRAP = BLOCKS.register("nail_trap", BlockNailTrap::new);
    public static final DeferredItem<Item> NAIL_TRAP_BLOCKITEM = ITEMS.register("nail_trap", () -> new BlockItem(NAIL_TRAP.get(), new Item.Properties()));

    public static final DeferredBlock<BlockNailTrap> TOXIC_NAIL_TRAP = BLOCKS.register("toxic_nail_trap", BlockNailTrap::new);
    public static final DeferredItem<Item> TOXIC_NAIL_TRAP_BLOCKITEM = ITEMS.register("toxic_nail_trap", () -> new BlockItem(TOXIC_NAIL_TRAP.get(), new Item.Properties()));

    public static final DeferredBlock<BlockSpikes> SPIKES = BLOCKS.register("spikes", BlockSpikes::new);
    public static final DeferredItem<Item> SPIKES_BLOCKITEM = ITEMS.register("spikes", () -> new BlockItem(SPIKES.get(), new Item.Properties()));

    public static final DeferredBlock<BlockSpikes> TOXIC_SPIKES = BLOCKS.register("spikes_toxic", BlockSpikes::new);
    public static final DeferredItem<Item> TOXIC_SPIKES_BLOCKITEM = ITEMS.register("spikes_toxic", () -> new BlockItem(TOXIC_SPIKES.get(), new Item.Properties()));

    public static final DeferredBlock<BlockPitfallTrap> PITFALL_TRAP = BLOCKS.register("pitfall_trap", BlockPitfallTrap::new);
    public static final DeferredItem<Item> PITFALL_TRAP_BLOCKITEM = ITEMS.register("pitfall_trap", () -> new BlockItem(PITFALL_TRAP.get(), new Item.Properties()));

    public static final DeferredBlock<BlockGhost> GHOST_BLOCK = BLOCKS.register("ghost_block", BlockGhost::new);
    public static final DeferredItem<Item> GHOST_BLOCK_BLOCKITEM = ITEMS.register("ghost_block", () -> new BlockItem(GHOST_BLOCK.get(), new Item.Properties()));

    public static final DeferredBlock<BlockTroll> TROLL_BLOCK = BLOCKS.register("troll_block", BlockTroll::new);
    public static final DeferredItem<Item> TROLL_BLOCKITEM = ITEMS.register("troll_block", () -> new BlockItem(TROLL_BLOCK.get(), new Item.Properties()));

    public static final DeferredBlock<BlockToxin> TOXIN = BLOCKS.register("toxin", () -> new BlockToxin(ModFluids.TOXIN, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

}