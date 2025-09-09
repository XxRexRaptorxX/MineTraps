package xxrexraptorxx.minetraps.registry;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.fluids.ModFluidVariantAttributeHandler;
import xxrexraptorxx.minetraps.fluids.ToxinFluid;
import xxrexraptorxx.minetraps.main.MineTraps;
import xxrexraptorxx.minetraps.main.References;


public class ModFluids {
    public static final FlowableFluid TOXIN = ModFluids.registerFluid("toxin", new ToxinFluid.Still());
    public static final FlowableFluid FLOWING_TOXIN = ModFluids.registerFluid("toxin_flowing", new ToxinFluid.Flowing());
    public static final ModFluidVariantAttributeHandler handler = new ModFluidVariantAttributeHandler();

    private static FlowableFluid registerFluid(String name, FlowableFluid fluid) {

        //MineTraps.LOGGER.info(" -- registerFluid ------------------------");
        return Registry.register(Registries.FLUID, Identifier.of(References.MODID, name), fluid);
    }

    public static void registerModFluids() {
        MineTraps.LOGGER.info("Registering ModFluids for " + References.MODID);
        FluidVariantAttributes.register(TOXIN, new ModFluidVariantAttributeHandler());
        FluidVariantAttributes.register(FLOWING_TOXIN, new ModFluidVariantAttributeHandler());
    }
}