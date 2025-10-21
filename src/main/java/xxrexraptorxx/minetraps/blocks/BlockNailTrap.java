package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockNailTrap extends FallingBlock {

    protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);
    public static final MapCodec<BlockNailTrap> CODEC = simpleCodec(BlockNailTrap::new);


    public BlockNailTrap(Properties properties) {
        super(properties);
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return CUSTOM_SHAPE;
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSupportCenter(level, pos.below(), Direction.DOWN);
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn, InsideBlockEffectApplier effectApplier) {
        if (!level.isClientSide && !entityIn.isCrouching()) {
            if (entityIn instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) entityIn;

                if (this == ModBlocks.TOXIC_NAIL_TRAP.get())
                    entity.addEffect(new MobEffectInstance(MobEffects.POISON, Config.getToxicNailTrapEffectDuration(), Config.getToxicNailTrapEffectAmplifier()));

                entity.hurt(level.damageSources().generic(), (float) Config.getNailTrapDamage());
            }
        }
    }


    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }


    @Override
    public int getDustColor(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return defaultMapColor().calculateARGBColor(MapColor.Brightness.NORMAL);
    }


    @Override
    public boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return true;
    }
}
