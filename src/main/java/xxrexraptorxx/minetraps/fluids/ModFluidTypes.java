package xxrexraptorxx.minetraps.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;
import xxrexraptorxx.minetraps.main.References;

public class ModFluidTypes {

    public static final ResourceLocation WATER_STILL = ResourceLocation.withDefaultNamespace("block/water_still");
    public static final ResourceLocation WATER_FLOWING = ResourceLocation.withDefaultNamespace("block/water_flow");
    public static final ResourceLocation TOXIN_STILL = ResourceLocation.fromNamespaceAndPath(References.MODID, "block/toxin_still");
    public static final ResourceLocation TOXIN_FLOWING = ResourceLocation.fromNamespaceAndPath(References.MODID , "block/toxin_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, References.MODID);

    public static void init(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

    public static final DeferredHolder<FluidType, BaseFluidType> TOXIN_FLUID_TYPE = register("toxin_fluid",
            FluidType.Properties.create().density(2500).viscosity(3000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));


    private static DeferredHolder<FluidType, BaseFluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL, WATER_FLOWING, TOXIN_STILL,
                0x3F7529, new Vector3f(63f / 255f, 117f / 255f, 41f / 255f), properties));
    }


}