package xxrexraptorxx.minetraps.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import xxrexraptorxx.minetraps.main.References;
import xxrexraptorxx.minetraps.registry.ModBlocks;

import java.util.List;

public class MineTrapsConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> QUICKSAND_KEY = registerKey("quicksand");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest sandReplacables = new TagMatchRuleTest(BlockTags.SAND);

        List<OreFeatureConfig.Target> overworldQuicksand =
                List.of(OreFeatureConfig.createTarget(sandReplacables, ModBlocks.QUICK_SAND.getDefaultState()));

        register(context, QUICKSAND_KEY, Feature.ORE, new OreFeatureConfig(overworldQuicksand, 20));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(References.MODID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
