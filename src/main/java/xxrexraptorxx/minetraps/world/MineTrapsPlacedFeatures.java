package xxrexraptorxx.minetraps.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import xxrexraptorxx.minetraps.main.References;

import java.util.List;

public class MineTrapsPlacedFeatures {
    public static final RegistryKey<PlacedFeature> QUICKSAND_PLACED_KEY = registerKey("quicksand_placed");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, QUICKSAND_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(MineTrapsConfiguredFeatures.QUICKSAND_KEY),
                MineTrapsOrePlacement.modifiersWithCount(20, // Veins per Chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(50), YOffset.fixed(100))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(References.MODID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
