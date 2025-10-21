package xxrexraptorxx.minetraps.main;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.magmacore.config.ConfigHelper;
import xxrexraptorxx.magmacore.main.ModRegistry;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.registry.CreativeModeTabs;
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


    public MineTraps(IEventBus eventBus, ModContainer container) {
        ModBlocks.init(eventBus);
        ModItems.init(eventBus);
        ModFluids.init(eventBus);
        ModFluidTypes.init(eventBus);
        CreativeModeTabs.init(eventBus);

        ConfigHelper.registerConfigs(container, References.MODID, false, Config.SERVER_CONFIG, null);
        ModRegistry.register(References.MODID, References.NAME, References.URL);
    }


    @Mod(value = References.MODID, dist = Dist.CLIENT)
    public static class MineTrapsClient {

        public MineTrapsClient(ModContainer container) {
            ConfigHelper.registerIngameConfig(container);
        }
    }

}
