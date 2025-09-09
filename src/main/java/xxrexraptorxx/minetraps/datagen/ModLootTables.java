package xxrexraptorxx.minetraps.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import xxrexraptorxx.minetraps.registry.ModBlocks;

public class ModLootTables extends FabricBlockLootTableProvider {

    protected ModLootTables(
            FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BARBED_WIRE);
        addDrop(ModBlocks.BARBED_WIRE_FENCE);
        addDrop(ModBlocks.RAZOR_WIRE);
        addDrop(ModBlocks.BEAR_TRAP);
        addDrop(ModBlocks.CHEST_BOMB);
        addDrop(ModBlocks.EXPLOSIVE_MINE);
        addDrop(ModBlocks.TOXIC_MINE);
        addDrop(ModBlocks.NAIL_TRAP);
        addDrop(ModBlocks.TOXIC_NAIL_TRAP);
        addDrop(ModBlocks.OBSTACLE);
        addDrop(ModBlocks.PITFALL_TRAP);
        addDrop(ModBlocks.QUICK_SAND);
        addDrop(ModBlocks.SPIKES);
        addDrop(ModBlocks.TOXIC_SPIKES);
        addDrop(ModBlocks.GHOST_BLOCK);
        addDrop(ModBlocks.TROLL_BLOCK);
    }
}
