/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package xxrexraptorxx.minetraps.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.registry.ModFluids;
import xxrexraptorxx.minetraps.registry.ModItems;

import java.util.Optional;

public abstract class ToxinFluid extends FlowingFluid {

    @Override
    public @NotNull Fluid getFlowing() {
        return ModFluids.FLOWING_TOXIN.get();
    }


    @Override
    public @NotNull Fluid getSource() {
        return ModFluids.TOXIN.get();
    }


    @Override
    public @NotNull Item getBucket() {
        return ModItems.TOXIN_BUCKET.get();
    }


    @Override
    public void animateTick(@NotNull Level level, @NotNull BlockPos blockPos, FluidState fluidState, @NotNull RandomSource randomSource) {
        if (!fluidState.isSource() && !fluidState.getValue(FALLING)) {
            if (randomSource.nextInt(64) == 0) {
                level.playLocalSound((double) blockPos.getX() + 0.5, (double) blockPos.getY() + 0.5, (double) blockPos.getZ() + 0.5, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS,
                        randomSource.nextFloat() * 0.25f + 0.75f, randomSource.nextFloat() + 0.5f, false);

            }
        } else if (randomSource.nextInt(10) == 0) {
            level.addParticle(ParticleTypes.UNDERWATER, (double) blockPos.getX() + randomSource.nextDouble(), (double) blockPos.getY() + randomSource.nextDouble(),
                    (double) blockPos.getZ() + randomSource.nextDouble(), 0.0, 0.0, 0.0);
        }
    }


    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.TOXIN_FLUID_TYPE.value();
    }


    @javax.annotation.Nullable
    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }


    @Override
    protected boolean canConvertToSource(@NotNull ServerLevel serverLevel) {
        return false;
    }


    @Override
    protected void beforeDestroyingBlock(@NotNull LevelAccessor level, @NotNull BlockPos pos, BlockState state) {
        BlockEntity blockentity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockentity);
    }


    @Override
    public int getSlopeFindDistance(@NotNull LevelReader level) {
        return 4;
    }


    @Override
    public @NotNull BlockState createLegacyBlock(@NotNull FluidState state) {
        return ModBlocks.TOXIN.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }


    @Override
    public boolean isSame(@NotNull Fluid fluid) {
        return fluid == ModFluids.TOXIN.get() || fluid == ModFluids.FLOWING_TOXIN.get();
    }


    @Override
    public int getDropOff(@NotNull LevelReader level) {
        return 2;
    }


    @Override
    public int getTickDelay(@NotNull LevelReader levelReader) {
        return 5;
    }


    @Override
    public boolean canBeReplacedWith(@NotNull FluidState fluidState, @NotNull BlockGetter blockReader, @NotNull BlockPos pos, @NotNull Fluid fluid, @NotNull Direction direction) {
        return direction == Direction.DOWN && !fluid.is(FluidTags.WATER);
    }


    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }


    @Override
    public @NotNull Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    public static class Flowing extends ToxinFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }


        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }


        @Override
        public boolean isSource(@NotNull FluidState state) {
            return false;
        }
    }

    public static class Source extends ToxinFluid {
        @Override
        public int getAmount(@NotNull FluidState state) {
            return 8;
        }


        @Override
        public boolean isSource(@NotNull FluidState state) {
            return true;
        }
    }
}
