package xxrexraptorxx.minetraps.main;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.registry.CreativeModeTabs;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.registry.ModFluids;
import xxrexraptorxx.minetraps.registry.ModItems;
import xxrexraptorxx.minetraps.utils.Config;
import xxrexraptorxx.minetraps.world.gen.ModWorldGeneration;

/**
 * @author XxRexRaptorxX (RexRaptor), MC Mods Pete [>Fabric Port]
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/minetraps-fabric
 **/
public class MineTraps implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(References.MODID);

    @Override
    public void onInitialize() {
        Config.initServer();
        ModBlocks.registerModBlocks();
        ModFluids.registerModFluids();
        ModItems.registerModItems();
        CreativeModeTabs.registerItemGroups();
        MineTrapsDamageTypes.registerDamageTypes();
        ModWorldGeneration.generateModWorldGen();
    }
}
