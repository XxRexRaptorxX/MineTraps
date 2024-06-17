package xxrexraptorxx.minetraps.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
    public static final Block BARBED_WIRE = registerBlock("barbed_wire", (Block) new BlockBarbedWire(AbstractBlock.Settings.create()));
    public static final Block BARBED_WIRE_FENCE = registerBlock("barbed_wire_fence", new BlockBarbedWireFence(AbstractBlock.Settings.create()));
    public static final Block RAZOR_WIRE = registerBlock("razor_wire", (Block) new BlockBarbedWire(AbstractBlock.Settings.create()));
    public static final Block BEAR_TRAP = registerBlock("bear_trap", (Block) new BlockBearTrap(AbstractBlock.Settings.create()));
    public static final Block CHEST_BOMB = registerBlock("chest_bomb", (Block) new BlockChestBomb(AbstractBlock.Settings.create()));
    public static final Block EXPLOSIVE_MINE = registerBlock("explosive_mine", (Block) new BlockExplosiveMine(AbstractBlock.Settings.create()));
    public static final Block TOXIC_MINE = registerBlock("toxic_mine", (Block) new BlockExplosiveMine(AbstractBlock.Settings.create()));
    public static final Block QUICK_SAND = registerBlock("quicksand", (Block) new BlockQuicksand(AbstractBlock.Settings.create()));
    public static final Block OBSTACLE = registerBlock("obstacle", (Block) new BlockObstacle(AbstractBlock.Settings.create()));
    public static final Block NAIL_TRAP = registerBlock("nail_trap", (Block) new BlockNailTrap(AbstractBlock.Settings.create()));
    public static final Block TOXIC_NAIL_TRAP = registerBlock("toxic_nail_trap", (Block) new BlockNailTrap(AbstractBlock.Settings.create()));
    public static final Block SPIKES = registerBlock("spikes", (Block) new BlockSpikes(AbstractBlock.Settings.create()));
    public static final Block TOXIC_SPIKES = registerBlock("spikes_toxic", (Block) new BlockSpikes(AbstractBlock.Settings.create()));
    public static final Block PITFALL_TRAP = registerBlock("pitfall_trap", (Block) new BlockPitfallTrap(AbstractBlock.Settings.create()));
    public static final Block GHOST_BLOCK = registerBlock("ghost_block", (Block) new BlockGhost(AbstractBlock.Settings.create()));
    public static final Block TROLL_BLOCK = registerBlock("troll_block", (Block) new BlockTroll(AbstractBlock.Settings.create()));
    public static final Block TOXIN = registerBlock("toxin", (Block) new BlockToxin(ModFluids.TOXIN, FabricBlockSettings.copy(Blocks.WATER)){});


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(References.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(References.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        MineTraps.LOGGER.info("Registering ModBlocks for " + References.MODID);
    }
}
