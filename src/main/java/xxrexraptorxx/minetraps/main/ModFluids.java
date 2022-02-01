package xxrexraptorxx.minetraps.main;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.fluids.FluidToxin;

public class ModFluids {

    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, References.MODID);

    public static void init() {
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<FlowingFluid> TOXIN = FLUIDS.register("toxin",() -> new ForgeFlowingFluid.Source(FluidToxin.PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_TOXIN = FLUIDS.register("toxin_flowing",() -> new ForgeFlowingFluid.Flowing(FluidToxin.PROPERTIES));
}