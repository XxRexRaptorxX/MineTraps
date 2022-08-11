package xxrexraptorxx.minetraps.main;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.utils.Config;
import xxrexraptorxx.minetraps.world.ModConfiguredFeatures;
import xxrexraptorxx.minetraps.world.ModPlacedFeatures;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/minetraps
 **/
@Mod(References.MODID)
public class MineTraps {

    public static final Logger LOGGER = LogManager.getLogger();

    public MineTraps() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.init();
        ModItems.init();
        ModFluids.init();
        ModFluidTypes.init();

        Config.init();

        ModConfiguredFeatures.register(modbus);
        ModPlacedFeatures.register(modbus);
    }

}