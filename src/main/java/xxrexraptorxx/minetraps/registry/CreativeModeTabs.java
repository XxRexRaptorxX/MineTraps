package xxrexraptorxx.minetraps.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.main.MineTraps;
import xxrexraptorxx.minetraps.main.References;


public class CreativeModeTabs {
    public static final ItemGroup MINETRAPS_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(References.MODID),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + References.MODID + ".tab"))
                    .icon(() -> new ItemStack(ModBlocks.PITFALL_TRAP.asItem())).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.BARBED_WIRE);
                        entries.add(ModBlocks.BARBED_WIRE_FENCE);
                        entries.add(ModBlocks.RAZOR_WIRE);
                        entries.add(ModBlocks.BEAR_TRAP);
                        entries.add(ModBlocks.CHEST_BOMB);
                        entries.add(ModBlocks.EXPLOSIVE_MINE);
                        entries.add(ModBlocks.TOXIC_MINE);
                        entries.add(ModBlocks.QUICK_SAND);
                        entries.add(ModBlocks.OBSTACLE);
                        entries.add(ModBlocks.NAIL_TRAP);
                        entries.add(ModBlocks.TOXIC_NAIL_TRAP);
                        entries.add(ModBlocks.SPIKES);
                        entries.add(ModBlocks.TOXIC_SPIKES);
                        entries.add(ModBlocks.PITFALL_TRAP);
                        entries.add(ModBlocks.GHOST_BLOCK);
                        entries.add(ModBlocks.TROLL_BLOCK);
                        entries.add(ModItems.NAILS);
                        entries.add(ModItems.TOXIN_BUCKET);
                        entries.add(ModItems.TOXIN_BOTTLE);

                    }).build());

    public static void registerItemGroups() {
        MineTraps.LOGGER.info("Registering Item Groups for " + References.MODID);
    }
}
