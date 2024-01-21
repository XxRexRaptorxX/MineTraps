package xxrexraptorxx.minetraps.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.main.References;

public class ModFluids {

    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, References.MODID);

    public static void init(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> TOXIN = FLUIDS.register("toxin",() -> new BaseFlowingFluid.Source(ModFluids.TOXIN_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_TOXIN = FLUIDS.register("toxin_flowing",() -> new BaseFlowingFluid.Flowing(ModFluids.TOXIN_FLUID_PROPERTIES));


    public static final BaseFlowingFluid.Properties TOXIN_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.TOXIN_FLUID_TYPE, TOXIN, FLOWING_TOXIN)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.TOXIN)
            .bucket(ModItems.TOXIN_BUCKET);

}