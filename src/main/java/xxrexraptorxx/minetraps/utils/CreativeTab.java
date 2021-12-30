package xxrexraptorxx.minetraps.utils;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import xxrexraptorxx.minetraps.main.ModBlocks;
import xxrexraptorxx.minetraps.main.ModItems;
import xxrexraptorxx.minetraps.main.References;

public class CreativeTab {

    public static CreativeModeTab MOD_TAB = new CreativeModeTab(References.MODID + ".tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.PITFALL_TRAP.get());
        }
    };
}
