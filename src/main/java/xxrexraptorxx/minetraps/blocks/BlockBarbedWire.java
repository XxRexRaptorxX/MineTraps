package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;

import javax.annotation.Nullable;


public class BlockBarbedWire extends HalfTransparentBlock {

    public BlockBarbedWire(Properties properties) {
        super(properties);
    }


    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean p_451772_) {
        entity.makeStuckInBlock(state, new Vec3(0.25D, (double) 0.05F, 0.25D));
        if (!level.isClientSide()) {
            if (Config.getBarbedWireDestroyItems()) {
                if (this == ModBlocks.BARBED_WIRE.get()) entity.hurt(level.damageSources().generic(), Config.getBarbedWireDamage());
                if (this == ModBlocks.RAZOR_WIRE.get()) entity.hurt(level.damageSources().generic(), Config.getRazorWireDamage());
            } else {
                if (entity instanceof LivingEntity) {
                    if (this == ModBlocks.BARBED_WIRE.get()) entity.hurt(level.damageSources().generic(), (float) Config.getBarbedWireDamage());
                    if (this == ModBlocks.RAZOR_WIRE.get()) entity.hurt(level.damageSources().generic(), (float) Config.getRazorWireDamage());
                }
            }
        }
    }


    // Facing


    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);

    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

}
