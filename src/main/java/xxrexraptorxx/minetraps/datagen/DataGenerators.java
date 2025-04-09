package xxrexraptorxx.minetraps.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import xxrexraptorxx.minetraps.world.MineTrapsConfiguredFeatures;
import xxrexraptorxx.minetraps.world.MineTrapsPlacedFeatures;

public class DataGenerators implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTags::new);
		//pack.addProvider(ModItemModels::new);
		pack.addProvider(ModLootTables::new);
		//pack.addProvider(ModRecipes::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, MineTrapsConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, MineTrapsPlacedFeatures::boostrap);
	}
}
