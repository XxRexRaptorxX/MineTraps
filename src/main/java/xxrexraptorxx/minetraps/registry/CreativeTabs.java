package xxrexraptorxx.minetraps.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.minetraps.main.References;

public class CreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, References.MODID);

    public static void init(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register(References.MODID,
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + References.MODID + ".tab")).icon(() -> ModBlocks.PITFALL_TRAP.asItem().getDefaultInstance())
                    .displayItems((params, output) -> {
                        output.accept(ModBlocks.BARBED_WIRE.get());
                        output.accept(ModBlocks.RAZOR_WIRE.get());
                        output.accept(ModBlocks.BARBED_WIRE_FENCE.get());
                        output.accept(ModBlocks.BEAR_TRAP.get());
                        output.accept(ModBlocks.CHEST_BOMB.get());
                        output.accept(ModBlocks.EXPLOSIVE_MINE.get());
                        output.accept(ModBlocks.TOXIC_MINE.get());
                        output.accept(ModBlocks.QUICK_SAND.get());
                        output.accept(ModBlocks.OBSTACLE.get());
                        output.accept(ModBlocks.NAIL_TRAP.get());
                        output.accept(ModBlocks.TOXIC_NAIL_TRAP.get());
                        output.accept(ModBlocks.SPIKES.get());
                        output.accept(ModBlocks.TOXIC_SPIKES.get());
                        output.accept(ModBlocks.PITFALL_TRAP.get());
                        output.accept(ModBlocks.GHOST_BLOCK.get());
                        output.accept(ModBlocks.TROLL_BLOCK.get());
                        output.accept(ModItems.NAILS.get());
                        output.accept(ModItems.TOXIN_BOTTLE.get());
                        output.accept(ModItems.TOXIN_BUCKET.get());
                    }).build());
}
