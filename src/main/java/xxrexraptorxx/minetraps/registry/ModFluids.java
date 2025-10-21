package xxrexraptorxx.minetraps.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.minetraps.fluids.ToxinFluid;
import xxrexraptorxx.minetraps.main.References;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, References.MODID);

    public static final DeferredHolder<Fluid, ToxinFluid> TOXIN = FLUIDS.register("toxin", ToxinFluid.Source::new);
    public static final DeferredHolder<Fluid, ToxinFluid> FLOWING_TOXIN = FLUIDS.register("toxin_flowing", ToxinFluid.Flowing::new);

    static {
        for (Fluid fluid : BuiltInRegistries.FLUID) {
            for (FluidState fluidstate : fluid.getStateDefinition().getPossibleStates()) {
                Fluid.FLUID_STATE_REGISTRY.add(fluidstate);
            }
        }
    }

    public static void init(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
