package xxrexraptorxx.minetraps.world;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class OreGenerator {

    public static final RuleTest SAND = new BlockMatchTest(Blocks.SAND);
    public static final RuleTest DIRT = new BlockMatchTest(Blocks.DIRT);

    public static Holder<PlacedFeature> MUD_GEN;
    public static Holder<PlacedFeature> QUICK_SAND_GEN;

/**
    public static void registerConfiguredFeatures() {
        OreConfiguration mudConfig = new OreConfiguration(DIRT, ModBlocks.MUD.get().defaultBlockState(), 20);
        MUD_GEN = registerPlacedFeature("mud", new ConfiguredFeature<>(Feature.ORE, mudConfig),
                CountPlacement.of(20),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(64)));

        OreConfiguration quickSandConfig = new OreConfiguration(SAND, ModBlocks.QUICK_SAND.get().defaultBlockState(), 20);
        QUICK_SAND_GEN = registerPlacedFeature("quicksand", new ConfiguredFeature<>(Feature.ORE, quickSandConfig),
                CountPlacement.of(20),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(100)));
    }


    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }


    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (Config.WORLD_GENERATION.get()) {
            if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, QUICK_SAND_GEN);
            }
            if (event.getCategory() == Biome.BiomeCategory.SWAMP) {
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MUD_GEN);
            }
        }
    }
**/
}