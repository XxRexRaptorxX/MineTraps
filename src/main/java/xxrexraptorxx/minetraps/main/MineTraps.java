package xxrexraptorxx.minetraps.main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.registry.CreativeModeTabs;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.registry.ModFluids;
import xxrexraptorxx.minetraps.registry.ModItems;
import xxrexraptorxx.minetraps.utils.Config;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/minetraps
 **/
@Mod(References.MODID)
public class MineTraps {

    public static final Logger LOGGER = LogManager.getLogger();


    public MineTraps() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.init();
        ModItems.init();
        ModFluids.init();
        ModFluidTypes.init();
        CreativeModeTabs.init();
        Config.init();
    }

}