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

public class MineTraps implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
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