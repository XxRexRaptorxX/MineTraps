package xxrexraptorxx.minetraps.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import xxrexraptorxx.minetraps.main.ModBlocks;
import xxrexraptorxx.minetraps.main.References;

public class TagsBlock extends BlockTagsProvider {

    public TagsBlock(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, References.MODID, helper);
    }


    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(   ModBlocks.TOXIC_SPIKES.get(),
                        ModBlocks.SPIKES.get(),
                        ModBlocks.BARBED_WIRE.get(),
                        ModBlocks.BARBED_WIRE_FENCE.get(),
                        ModBlocks.RAZOR_WIRE.get(),
                        ModBlocks.BEAR_TRAP.get(),
                        ModBlocks.OBSTACLE.get(),
                        ModBlocks.GHOST_BLOCK.get(),
                        ModBlocks.TROLL_BLOCK.get()
                        );


        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(   ModBlocks.CHEST_BOMB.get()
                );


        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(   ModBlocks.QUICK_SAND.get(),
                        ModBlocks.NAIL_TRAP.get(),
                        ModBlocks.TOXIC_NAIL_TRAP.get(),
                        ModBlocks.EXPLOSIVE_MINE.get(),
                        ModBlocks.TOXIC_MINE.get(),
                        ModBlocks.PITFALL_TRAP.get(),
                        ModBlocks.BEAR_TRAP.get(),
                        ModBlocks.MUD.get()
                );


        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(   ModBlocks.TOXIC_SPIKES.get(),
                        ModBlocks.SPIKES.get(),
                        ModBlocks.BARBED_WIRE.get(),
                        ModBlocks.BARBED_WIRE_FENCE.get(),
                        ModBlocks.RAZOR_WIRE.get(),
                        ModBlocks.BEAR_TRAP.get(),
                        ModBlocks.OBSTACLE.get(),
                        ModBlocks.CHEST_BOMB.get(),
                        ModBlocks.QUICK_SAND.get(),
                        ModBlocks.NAIL_TRAP.get(),
                        ModBlocks.NAIL_TRAP.get(),
                        ModBlocks.TOXIC_NAIL_TRAP.get(),
                        ModBlocks.EXPLOSIVE_MINE.get(),
                        ModBlocks.TOXIC_MINE.get(),
                        ModBlocks.PITFALL_TRAP.get(),
                        ModBlocks.GHOST_BLOCK.get(),
                        ModBlocks.TROLL_BLOCK.get()
                );


        tag(BlockTags.WALLS)
                .add(   ModBlocks.BARBED_WIRE_FENCE.get()
                );


        tag(BlockTags.SAND)
                .add(   ModBlocks.QUICK_SAND.get()
                );



    }
}
