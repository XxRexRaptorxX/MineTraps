package xxrexraptorxx.minetraps.registry;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.blocks.*;
import xxrexraptorxx.minetraps.main.MineTraps;
import xxrexraptorxx.minetraps.main.References;

public class ModBlocks {
    public static final Block BARBED_WIRE = registerBlock(
            "barbed_wire",
            new BlockBarbedWire(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "barbed_wire")))));
    public static final Block BARBED_WIRE_FENCE = registerBlock(
            "barbed_wire_fence",
            new BlockBarbedWireFence(AbstractBlock.Settings.create()
                    .registryKey(
                            RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "barbed_wire_fence")))));
    public static final Block RAZOR_WIRE = registerBlock(
            "razor_wire",
            new BlockBarbedWire(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "razor_wire")))));
    public static final Block BEAR_TRAP = registerBlock(
            "bear_trap",
            new BlockBearTrap(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "bear_trap")))));
    public static final Block CHEST_BOMB = registerBlock(
            "chest_bomb",
            new BlockChestBomb(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "chest_bomb")))));
    public static final Block EXPLOSIVE_MINE = registerBlock(
            "explosive_mine",
            new BlockExplosiveMine(AbstractBlock.Settings.create()
                    .registryKey(
                            RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "explosive_mine")))));
    public static final Block TOXIC_MINE = registerBlock(
            "toxic_mine",
            new BlockExplosiveMine(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "toxic_mine")))));
    public static final Block QUICK_SAND = registerBlock(
            "quicksand",
            new BlockQuicksand(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "quicksand")))));
    public static final Block OBSTACLE = registerBlock(
            "obstacle",
            new BlockObstacle(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "obstacle")))));
    public static final Block NAIL_TRAP = registerBlock(
            "nail_trap",
            new BlockNailTrap(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "nail_trap")))));
    public static final Block TOXIC_NAIL_TRAP = registerBlock(
            "toxic_nail_trap",
            new BlockNailTrap(AbstractBlock.Settings.create()
                    .registryKey(
                            RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "toxic_nail_trap")))));
    public static final Block SPIKES = registerBlock(
            "spikes",
            new BlockSpikes(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "spikes")))));
    public static final Block TOXIC_SPIKES = registerBlock(
            "spikes_toxic",
            new BlockSpikes(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "spikes_toxic")))));
    public static final Block PITFALL_TRAP = registerBlock(
            "pitfall_trap",
            new BlockPitfallTrap(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "pitfall_trap")))));
    public static final Block GHOST_BLOCK = registerBlock(
            "ghost_block",
            new BlockGhost(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "ghost_block")))));
    public static final Block TROLL_BLOCK = registerBlock(
            "troll_block",
            new BlockTroll(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "troll_block")))));
    public static final Block TOXIN = registerBlock(
            "toxin",
            new BlockToxin(
                    ModFluids.TOXIN,
                    AbstractBlock.Settings.copy(Blocks.WATER)
                            .registryKey(
                                    RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(References.MODID, "toxin")))) {});

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(References.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(References.MODID, name),
                new BlockItem(
                        block,
                        new Item.Settings()
                                .registryKey(
                                        RegistryKey.of(RegistryKeys.ITEM, Identifier.of(References.MODID, name)))));
    }

    public static void registerModBlocks() {
        MineTraps.LOGGER.info("Registering ModBlocks for " + References.MODID);
    }
}
