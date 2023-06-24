package xxrexraptorxx.minetraps.registry;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.main.References;

public class ModFluids {

    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, References.MODID);

    public static void init() {
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<FlowingFluid> TOXIN = FLUIDS.register("toxin",() -> new ForgeFlowingFluid.Source(ModFluids.TOXIN_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_TOXIN = FLUIDS.register("toxin_flowing",() -> new ForgeFlowingFluid.Flowing(ModFluids.TOXIN_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties TOXIN_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.TOXIN_FLUID_TYPE, TOXIN, FLOWING_TOXIN)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.TOXIN)
            .bucket(ModItems.TOXIN_BUCKET);

}