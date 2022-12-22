package xxrexraptorxx.minetraps.world;
/**
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.main.References;

import java.util.List;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registries.PLACED_FEATURE, References.MODID);


    public static final RegistryObject<PlacedFeature> QUICKSAND_PLACED = PLACED_FEATURES.register("quicksand_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.QUICKSAND.getHolder().get(),
                    commonOrePlacement(20, // Rarity
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(100)))));


    public static List<PlacementModifier> orePlacement(PlacementModifier type, PlacementModifier codec) {
        return List.of(type, InSquarePlacement.spread(), codec, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placement) {
        return orePlacement(CountPlacement.of(count), placement);
    }

    public static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier placement) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), placement);
    }


    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}**/