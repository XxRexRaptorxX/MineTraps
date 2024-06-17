package xxrexraptorxx.minetraps.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.structure.JungleTempleStructure;
import xxrexraptorxx.minetraps.world.MineTrapsPlacedFeatures;

import java.util.Set;

public class ModOreGeneration {
    public static void generateOres() {
        Set<RegistryKey<Biome>> immutable = Set.of(BiomeKeys.JUNGLE, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.SPARSE_JUNGLE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(immutable),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, MineTrapsPlacedFeatures.QUICKSAND_PLACED_KEY);
    }
}
