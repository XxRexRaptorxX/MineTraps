package xxrexraptorxx.minetraps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import xxrexraptorxx.minetraps.registry.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends FabricTagProvider.BlockTagProvider {

    public ModBlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(   ModBlocks.TOXIC_SPIKES,
                        ModBlocks.SPIKES,
                        ModBlocks.BARBED_WIRE,
                        ModBlocks.BARBED_WIRE_FENCE,
                        ModBlocks.RAZOR_WIRE,
                        ModBlocks.BEAR_TRAP,
                        ModBlocks.OBSTACLE,
                        ModBlocks.GHOST_BLOCK,
                        ModBlocks.TROLL_BLOCK);

        valueLookupBuilder(BlockTags.AXE_MINEABLE)
                .add(   ModBlocks.CHEST_BOMB);

        valueLookupBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(   ModBlocks.QUICK_SAND,
                        ModBlocks.NAIL_TRAP,
                        ModBlocks.TOXIC_NAIL_TRAP,
                        ModBlocks.EXPLOSIVE_MINE,
                        ModBlocks.TOXIC_MINE,
                        ModBlocks.PITFALL_TRAP,
                        ModBlocks.BEAR_TRAP
                );

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(   ModBlocks.TOXIC_SPIKES,
                        ModBlocks.SPIKES,
                        ModBlocks.BARBED_WIRE,
                        ModBlocks.BARBED_WIRE_FENCE,
                        ModBlocks.RAZOR_WIRE,
                        ModBlocks.BEAR_TRAP,
                        ModBlocks.OBSTACLE,
                        ModBlocks.CHEST_BOMB,
                        ModBlocks.QUICK_SAND,
                        ModBlocks.NAIL_TRAP,
                        ModBlocks.NAIL_TRAP,
                        ModBlocks.TOXIC_NAIL_TRAP,
                        ModBlocks.EXPLOSIVE_MINE,
                        ModBlocks.TOXIC_MINE,
                        ModBlocks.PITFALL_TRAP,
                        ModBlocks.GHOST_BLOCK,
                        ModBlocks.TROLL_BLOCK);

        valueLookupBuilder(BlockTags.FENCES)
                .add(   ModBlocks.BARBED_WIRE_FENCE
                );

        valueLookupBuilder(BlockTags.SAND)
                .add(   ModBlocks.QUICK_SAND
                );
    }
}
