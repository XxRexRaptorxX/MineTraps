package xxrexraptorxx.minetraps.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.main.References;

public class CreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, References.MODID);

    public static void init() { CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus()); }


    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register(References.MODID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + References.MODID + ".tab"))
            .icon(() -> ModBlocks.PITFALL_TRAP_BLOCKITEM.get().getDefaultInstance())
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
