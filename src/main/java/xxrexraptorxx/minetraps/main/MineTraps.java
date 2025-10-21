package xxrexraptorxx.minetraps.main;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.magmacore.config.ConfigHelper;
import xxrexraptorxx.magmacore.main.ModRegistry;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.registry.CreativeTabs;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.registry.ModFluids;
import xxrexraptorxx.minetraps.registry.ModItems;
import xxrexraptorxx.minetraps.utils.Config;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage <a href="https://www.curseforge.com/minecraft/mc-mods/minetraps">...</a>
 **/
@Mod(References.MODID)
public class MineTraps {

    public static final Logger LOGGER = LogManager.getLogger();


    public MineTraps(IEventBus bus, ModContainer container) {
        ModBlocks.init(bus);
        ModItems.init(bus);
        ModFluids.init(bus);
        ModFluidTypes.init(bus);
        CreativeTabs.init(bus);

        bus.addListener(this::addCreative);

        ConfigHelper.registerConfigs(container, References.MODID, false, Config.SERVER_CONFIG, null);
        ModRegistry.register(References.MODID, References.NAME, References.URL);
    }


    @Mod(value = References.MODID, dist = Dist.CLIENT)
    public static class MineTrapsClient {

        public MineTrapsClient(ModContainer container) {
            ConfigHelper.registerIngameConfig(container);
        }
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> key = event.getTabKey();

        if (key == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ModBlocks.SPIKES);
            event.accept(ModBlocks.TOXIC_SPIKES);

        } else if (key == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.BARBED_WIRE);
            event.accept(ModBlocks.RAZOR_WIRE);
            event.accept(ModBlocks.BARBED_WIRE_FENCE);
            event.accept(ModBlocks.BEAR_TRAP);
            event.accept(ModBlocks.CHEST_BOMB);
            event.accept(ModBlocks.EXPLOSIVE_MINE);
            event.accept(ModBlocks.TOXIC_MINE);
            event.accept(ModBlocks.PITFALL_TRAP);
            event.accept(ModBlocks.NAIL_TRAP);
            event.accept(ModBlocks.TOXIC_NAIL_TRAP);
            event.accept(ModBlocks.GHOST_BLOCK);
            event.accept(ModBlocks.TROLL_BLOCK);

        } else if (key == CreativeModeTabs.COMBAT) {
            event.accept(ModBlocks.BEAR_TRAP);
            event.accept(ModBlocks.EXPLOSIVE_MINE);
            event.accept(ModBlocks.TOXIC_MINE);
            event.accept(ModBlocks.NAIL_TRAP);
            event.accept(ModBlocks.TOXIC_NAIL_TRAP);

        } else if (key == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.NAILS);

        } else if (key == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.OBSTACLE);

        } else if (key == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.QUICK_SAND);

        } else if (key == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.TOXIN_BOTTLE);

        } else if (key == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.TOXIN_BUCKET);
        }
    }

}
