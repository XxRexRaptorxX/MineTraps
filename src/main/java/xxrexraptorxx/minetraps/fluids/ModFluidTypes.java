package xxrexraptorxx.minetraps.fluids;

import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.main.References;

public class ModFluidTypes {

    public static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING = new ResourceLocation("block/water_flow");
    public static final ResourceLocation TOXIN_STILL = new ResourceLocation(References.MODID, "block/toxin_still");
    public static final ResourceLocation TOXIN_FLOWING = new ResourceLocation(References.MODID , "block/toxin_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, References.MODID);

    public static void init() {
        FLUID_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<FluidType> TOXIN_FLUID_TYPE = register("toxin_fluid",
            FluidType.Properties.create().density(2500).viscosity(3000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL, WATER_FLOWING, TOXIN_STILL,
                0x3F7529, new Vector3f(63f / 255f, 117f / 255f, 41f / 255f), properties));
    }


}