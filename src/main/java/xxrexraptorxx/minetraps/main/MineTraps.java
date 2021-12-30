package xxrexraptorxx.minetraps.main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.minetraps.utils.Config;
import xxrexraptorxx.minetraps.world.OreGenerator;

@Mod(References.MODID)
public class MineTraps {

    public static final Logger LOGGER = LogManager.getLogger();

    public MineTraps() {
        ModBlocks.init();
        ModItems.init();
        ModFluids.init();
        Config.init();

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGenerator::oreGeneration);
        MinecraftForge.EVENT_BUS.register(this);
    }
}