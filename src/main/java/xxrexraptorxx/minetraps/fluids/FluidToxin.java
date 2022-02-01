package xxrexraptorxx.minetraps.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import xxrexraptorxx.minetraps.main.ModBlocks;
import xxrexraptorxx.minetraps.main.ModFluids;
import xxrexraptorxx.minetraps.main.ModItems;
import xxrexraptorxx.minetraps.main.References;


public class FluidToxin {

    public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(ModFluids.TOXIN, ModFluids.FLOWING_TOXIN,
                FluidAttributes.builder(new ResourceLocation(References.MODID, "block/toxin_still"), new ResourceLocation(References.MODID, "block/toxin_flow"))
                        .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY).viscosity(3000).density(2500)).block(() -> (LiquidBlock) ModBlocks.TOXIN.get()).bucket(ModItems.TOXIN_BUCKET);

        private FluidToxin() {}
}