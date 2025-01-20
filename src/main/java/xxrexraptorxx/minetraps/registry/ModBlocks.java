package xxrexraptorxx.minetraps.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.minetraps.blocks.*;
import xxrexraptorxx.minetraps.main.References;

import java.util.function.Function;

public class ModBlocks {

    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(References.MODID);

    public static void init(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    public static final DeferredBlock<BlockBarbedWire> BARBED_WIRE = registerBlock("barbed_wire", properties -> new BlockBarbedWire(properties
            .requiresCorrectToolForDrops()
            .strength(5.0F, 10.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.PLING)
            .noCollission()
            .noOcclusion()
    ));

    public static final DeferredBlock<BlockBarbedWireFence> BARBED_WIRE_FENCE = registerBlock("barbed_wire_fence", properties -> new BlockBarbedWireFence(properties
            .requiresCorrectToolForDrops()
            .strength(5.0F, 10.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.PLING)
            .noOcclusion()
            .noCollission()
    ));

    public static final DeferredBlock<BlockBarbedWire> RAZOR_WIRE = registerBlock("razor_wire", properties -> new BlockBarbedWire(properties
            .requiresCorrectToolForDrops()
            .strength(5.0F, 10.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.PLING)
            .noCollission()
            .noOcclusion()
    ));

    public static final DeferredBlock<BlockBearTrap> BEAR_TRAP = registerBlock("bear_trap", properties -> new BlockBearTrap(properties
            .requiresCorrectToolForDrops()
            .strength(5.0F, 10.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.PLING)
            .noOcclusion()
            .noCollission()
    ));

    public static final DeferredBlock<BlockChestBomb> CHEST_BOMB = registerBlock("chest_bomb", properties -> new BlockChestBomb(properties
            .requiresCorrectToolForDrops()
            .strength(2.5F, 0.0F)
            .sound(SoundType.WOOD)
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
    ));

    public static final DeferredBlock<BlockExplosiveMine> EXPLOSIVE_MINE = registerBlock("explosive_mine", properties -> new BlockExplosiveMine(properties
            .requiresCorrectToolForDrops()
            .strength(1.0F, 0.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .noOcclusion()
            .noCollission()
    ));

    public static final DeferredBlock<BlockExplosiveMine> TOXIC_MINE = registerBlock("toxic_mine", properties -> new BlockExplosiveMine(properties
            .requiresCorrectToolForDrops()
            .strength(1.0F, 0.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .noOcclusion()
            .noCollission()
    ));

    public static final DeferredBlock<BlockQuicksand> QUICK_SAND = registerBlock("quicksand", properties -> new BlockQuicksand(properties
            .strength(0.65F, 0.0F)
            .sound(SoundType.SAND)
            .mapColor(MapColor.SAND)
            .instrument(NoteBlockInstrument.SNARE)
            .noOcclusion()
    ));

    public static final DeferredBlock<BlockObstacle> OBSTACLE = registerBlock("obstacle", properties -> new BlockObstacle(properties
            .requiresCorrectToolForDrops()
            .strength(20.0F, 20.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .noOcclusion()
    ));

    public static final DeferredBlock<BlockNailTrap> NAIL_TRAP = registerBlock("nail_trap", properties -> new BlockNailTrap(properties
            .requiresCorrectToolForDrops()
            .strength(1.0F, 8.0F)
            .sound(SoundType.GRAVEL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BELL)
            .noOcclusion()
            .noCollission()
    ));

    public static final DeferredBlock<BlockNailTrap> TOXIC_NAIL_TRAP = registerBlock("toxic_nail_trap", properties -> new BlockNailTrap(properties
            .requiresCorrectToolForDrops()
            .strength(1.0F, 8.0F)
            .sound(SoundType.GRAVEL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BELL)
            .noOcclusion()
            .noCollission()
    ));

    public static final DeferredBlock<BlockSpikes> SPIKES = registerBlock("spikes", properties -> new BlockSpikes(properties
            .requiresCorrectToolForDrops()
            .strength(1.8F, 7.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BELL)
            .noCollission()
            .noOcclusion()
    ));

    public static final DeferredBlock<BlockSpikes> TOXIC_SPIKES = registerBlock("spikes_toxic", properties -> new BlockSpikes(properties
            .requiresCorrectToolForDrops()
            .strength(1.8F, 7.0F)
            .sound(SoundType.METAL)
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BELL)
            .noCollission()
            .noOcclusion()
    ));

    public static final DeferredBlock<BlockPitfallTrap> PITFALL_TRAP = registerBlock("pitfall_trap", properties -> new BlockPitfallTrap(properties
            .strength(1.0F, 1.0F)
            .sound(SoundType.WOOD)
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .noOcclusion()
            .noCollission()
    ));

    public static final DeferredBlock<BlockGhost> GHOST_BLOCK = registerBlock("ghost_block", properties -> new BlockGhost(properties
            .strength(1.5F, 6.0F)
            .sound(SoundType.STONE)
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.XYLOPHONE)
            .noCollission()
            .noOcclusion()
    ));

    public static final DeferredBlock<BlockTroll> TROLL_BLOCK = registerBlock("troll_block", properties -> new BlockTroll(properties
            .strength(1.5F, 6.0F)
            .sound(SoundType.STONE)
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BELL)
            .noLootTable()
    ));

    public static final DeferredBlock<BlockToxin> TOXIN = registerBlock("toxin", properties -> new BlockToxin(ModFluids.TOXIN.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).setId(blockId("toxin"))));



    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> blockCreator) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, () -> blockCreator.apply(BlockBehaviour.Properties.of().setId(blockId(name))));
        registerBlockItems(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ModItems.itemId(name)).useBlockDescriptionPrefix()));
    }

    public static ResourceKey<Block> blockId(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(References.MODID, name));
    }

}