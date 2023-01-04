package xxrexraptorxx.minetraps.main;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.utils.Config;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/minetraps
 **/
@Mod(References.MODID)
public class MineTraps {

    public static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceLocation CREATIVE_TAB = new ResourceLocation(References.MODID, "tab");


    public MineTraps() {
        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(MineTraps.class);
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.init();
        ModItems.init();
        ModFluids.init();
        ModFluidTypes.init();
        Config.init();
    }


    //Creative Tab
    @SubscribeEvent
    public static void registerTabs(final CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(CREATIVE_TAB, (cf) -> cf.icon(() -> new ItemStack(ModBlocks.PITFALL_TRAP.get()))
                .title(Component.translatable("itemGroup." + References.MODID + ".tab")).displayItems((flagSet, output, ifSth) -> {
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
                })
        );
    }
}