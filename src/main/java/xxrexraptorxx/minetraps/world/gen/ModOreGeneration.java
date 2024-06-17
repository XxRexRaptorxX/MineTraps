package xxrexraptorxx.minetraps.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import xxrexraptorxx.minetraps.world.MineTrapsPlacedFeatures;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, MineTrapsPlacedFeatures.QUICKSAND_PLACED_KEY);
    }
}
