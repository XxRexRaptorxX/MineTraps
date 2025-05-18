package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockQuicksand extends FallingBlock {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	public static final MapCodec<BlockQuicksand> CODEC = simpleCodec(BlockQuicksand::new);


	public BlockQuicksand(Properties properties) {
		super(properties);
	}

	//TODO waterlogging

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn, InsideBlockEffectApplier effectApplier) {
		if (entityIn instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity) entityIn;

			entity.makeStuckInBlock(state, new Vec3(0.25D, 0.10D, 0.25D));
			if(entity.getEyePosition().y < pos.getY() + 1) entity.hurt(level.damageSources().inWall(), (float) Config.getQuicksandDamage());
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
}