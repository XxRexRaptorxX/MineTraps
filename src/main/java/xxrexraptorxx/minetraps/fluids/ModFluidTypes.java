package xxrexraptorxx.minetraps.fluids;

import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xxrexraptorxx.minetraps.main.References;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, References.MODID);

    public static void init(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

    public static final DeferredHolder<FluidType, ToxinFluidType> TOXIN_FLUID_TYPE = register("toxin_fluid",
            FluidType.Properties.create().density(2500).viscosity(3000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK.value()));


    private static DeferredHolder<FluidType, ToxinFluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new ToxinFluidType(properties));
    }
}