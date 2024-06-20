package xxrexraptorxx.minetraps.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.blocks.*;
import xxrexraptorxx.minetraps.main.MineTraps;
import xxrexraptorxx.minetraps.main.References;


public class ModBlocks {
    public static final Block BARBED_WIRE = registerBlock("barbed_wire", new BlockBarbedWire(AbstractBlock.Settings.create()));
    public static final Block BARBED_WIRE_FENCE = registerBlock("barbed_wire_fence", new BlockBarbedWireFence(AbstractBlock.Settings.create()));
    public static final Block RAZOR_WIRE = registerBlock("razor_wire", new BlockBarbedWire(AbstractBlock.Settings.create()));
    public static final Block BEAR_TRAP = registerBlock("bear_trap", new BlockBearTrap(AbstractBlock.Settings.create()));
    public static final Block CHEST_BOMB = registerBlock("chest_bomb", new BlockChestBomb(AbstractBlock.Settings.create()));
    public static final Block EXPLOSIVE_MINE = registerBlock("explosive_mine", new BlockExplosiveMine(AbstractBlock.Settings.create()));
    public static final Block TOXIC_MINE = registerBlock("toxic_mine", new BlockExplosiveMine(AbstractBlock.Settings.create()));
    public static final Block QUICK_SAND = registerBlock("quicksand", new BlockQuicksand(AbstractBlock.Settings.create()));
    public static final Block OBSTACLE = registerBlock("obstacle", new BlockObstacle(AbstractBlock.Settings.create()));
    public static final Block NAIL_TRAP = registerBlock("nail_trap", new BlockNailTrap(AbstractBlock.Settings.create()));
    public static final Block TOXIC_NAIL_TRAP = registerBlock("toxic_nail_trap", new BlockNailTrap(AbstractBlock.Settings.create()));
    public static final Block SPIKES = registerBlock("spikes", new BlockSpikes(AbstractBlock.Settings.create()));
    public static final Block TOXIC_SPIKES = registerBlock("spikes_toxic", new BlockSpikes(AbstractBlock.Settings.create()));
    public static final Block PITFALL_TRAP = registerBlock("pitfall_trap", new BlockPitfallTrap(AbstractBlock.Settings.create()));
    public static final Block GHOST_BLOCK = registerBlock("ghost_block", new BlockGhost(AbstractBlock.Settings.create()));
    public static final Block TROLL_BLOCK = registerBlock("troll_block", new BlockTroll(AbstractBlock.Settings.create()));
    public static final Block TOXIN = registerBlock("toxin", new BlockToxin(ModFluids.TOXIN, FabricBlockSettings.copy(Blocks.WATER)){});


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(References.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(References.MODID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MineTraps.LOGGER.info("Registering ModBlocks for " + References.MODID);
    }
}
