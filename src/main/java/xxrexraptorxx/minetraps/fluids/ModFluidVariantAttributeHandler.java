package xxrexraptorxx.minetraps.fluids;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.minetraps.main.MineTraps;

public class ModFluidVariantAttributeHandler implements FluidVariantAttributeHandler {

    @Override
    public int getViscosity(FluidVariant variant, @Nullable World world) {
        MineTraps.LOGGER.info("Mod fluid");
        return 3000;
    }
}
