package xxrexraptorxx.minetraps.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import xxrexraptorxx.minetraps.main.ModBlocks;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }


    @Override
    protected void addTables() {
        addBaseLootTable(ModBlocks.BARBED_WIRE.get());
        addBaseLootTable(ModBlocks.BARBED_WIRE_FENCE.get());
        addBaseLootTable(ModBlocks.RAZOR_WIRE.get());
        addBaseLootTable(ModBlocks.BEAR_TRAP.get());
        addBaseLootTable(ModBlocks.CHEST_BOMB.get());
        addBaseLootTable(ModBlocks.EXPLOSIVE_MINE.get());
        addBaseLootTable(ModBlocks.TOXIC_MINE.get());
        addBaseLootTable(ModBlocks.MUD.get());
        addBaseLootTable(ModBlocks.NAIL_TRAP.get());
        addBaseLootTable(ModBlocks.TOXIC_NAIL_TRAP.get());
        addBaseLootTable(ModBlocks.OBSTACLE.get());
        addBaseLootTable(ModBlocks.PITFALL_TRAP.get());
        addBaseLootTable(ModBlocks.QUICK_SAND.get());
        addBaseLootTable(ModBlocks.SPIKES.get());
        addBaseLootTable(ModBlocks.TOXIC_SPIKES.get());

    }



    private void addBaseLootTable(Block block) {
        lootTables.put(block, createStandardTable( block.getRegistryName().toString(), block));
    }
}