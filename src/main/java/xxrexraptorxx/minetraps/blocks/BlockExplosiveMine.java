package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockExplosiveMine extends FallingBlock {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);
	public static final MapCodec<BlockExplosiveMine> CODEC = simpleCodec(BlockExplosiveMine::new);


	public BlockExplosiveMine(Properties properties) {
		super(properties);
	}


	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier) {
		level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 1.0F, 3);

		if (!level.isClientSide) {
			if(this == ModBlocks.TOXIC_MINE.get()) {
				AreaEffectCloud cloud = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
				cloud.addEffect(new MobEffectInstance(MobEffects.POISON, Config.POSION_MINE_EFFECT_DURATION.get(), Config.POSION_MINE_EFFECT_AMPLIFIER.get()));
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION.get());
				cloud.setRadius(10);
				//cloud.setFixedColor(0x27ae60);		//TODO
				cloud.setWaitTime(10);
				level.addFreshEntity(cloud);
			}

			entity.hurt(level.damageSources().generic(), (float) Config.MINE_DAMAGE.get());
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			level.explode(entity, pos.getX(), pos.getY(), pos.getZ(), (float) Config.MINE_EXPLOSION_RADIUS.get(), true, Level.ExplosionInteraction.TNT);
		}
	}


	@Override
	public void onBlockExploded(BlockState state, ServerLevel world, BlockPos pos, Explosion explosion) {
		AreaEffectCloud cloud = new AreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());

		if (!world.isClientSide) {
			if (this == ModBlocks.TOXIC_MINE.get()) {
				cloud.addEffect(new MobEffectInstance(MobEffects.POISON, Config.POSION_MINE_EFFECT_DURATION.get(), Config.POSION_MINE_EFFECT_AMPLIFIER.get()));
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION.get());
				cloud.setRadius(10);
				//cloud.setFixedColor(0x27ae60); TODO
				cloud.setWaitTime(10);
				world.addFreshEntity(cloud);
			}

			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			world.explode(cloud, pos.getX(), pos.getY(), pos.getZ(), Config.MINE_EXPLOSION_RADIUS.get(), true, Level.ExplosionInteraction.TNT);
		}
	}


	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.below(), Direction.DOWN);
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