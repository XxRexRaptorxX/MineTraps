package xxrexraptorxx.minetraps.registry;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.fluids.ToxinFluid;
import xxrexraptorxx.minetraps.main.MineTraps;
import xxrexraptorxx.minetraps.main.References;


public class ModFluids {
    public static final FlowableFluid TOXIN = ModFluids.registerFluid("toxin", new ToxinFluid.Still());
    public static final FlowableFluid FLOWING_TOXIN = ModFluids.registerFluid("toxin_flowing", new ToxinFluid.Flowing());

    private static FlowableFluid registerFluid(String name, FlowableFluid fluid) {
        return Registry.register(Registries.FLUID, Identifier.of(References.MODID, name), fluid);
    }

    public static void registerModFluids() {
        MineTraps.LOGGER.info("Registering ModFluids for " + References.MODID);
    }
}