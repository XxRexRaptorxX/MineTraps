package xxrexraptorxx.minetraps.fluids;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentMap;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.entry.RegistryEntry;
import xxrexraptorxx.minetraps.registry.ModFluids;

public class ToxinFluidVariant implements FluidVariant {

    @Override
    public Fluid getFluid() {
        return ModFluids.TOXIN;
    }

    @Override
    public RegistryEntry<Fluid> getRegistryEntry() {
        return ModFluids.TOXIN.getRegistryEntry();
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public Fluid getObject() {
        return null;
    }

    @Override
    public ComponentChanges getComponents() {
        return null;
    }

    @Override
    public ComponentMap getComponentMap() {
        return null;
    }

    @Override
    public ToxinFluidVariant withComponentChanges(ComponentChanges changes) {
        return null;
    }
}
