package xxrexraptorxx.minetraps.world;
/**
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.main.ModBlocks;
import xxrexraptorxx.minetraps.main.References;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RuleTest SAND = new BlockMatchTest(Blocks.SAND);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, References.MODID);


    public static final Supplier<List<OreConfiguration.TargetBlockState>> QUICKSAND_GEN = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(SAND, ModBlocks.QUICK_SAND.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> QUICKSAND = CONFIGURED_FEATURES.register("quicksand",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(QUICKSAND_GEN.get(),20)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}**/