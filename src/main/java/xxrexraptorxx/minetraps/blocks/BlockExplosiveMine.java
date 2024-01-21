package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockExplosiveMine extends FallingBlock {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);

	public BlockExplosiveMine() {
		super(Properties.of()
				.requiresCorrectToolForDrops()
				.strength(1.0F, 0.0F)
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
				.noOcclusion()
				.noCollission()
		);
	}


	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		world.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 1.0F, 3);

		if (!world.isClientSide) {
			if(this == ModBlocks.TOXIC_MINE.get()) {
				AreaEffectCloud cloud = new AreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
				cloud.addEffect(new MobEffectInstance(MobEffects.POISON, Config.POSION_MINE_EFFECT_DURATION.get(), Config.POSION_MINE_EFFECT_AMPLIFIER.get()));
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION.get());
				cloud.setRadius(10);
				cloud.setFixedColor(0x27ae60);
				cloud.setWaitTime(10);
				world.addFreshEntity(cloud);
			}

			entity.hurt(world.damageSources().generic(), (float) Config.MINE_DAMAGE.get());
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			world.explode(entity, pos.getX(), pos.getY(), pos.getZ(), (float) Config.MINE_EXPLOSION_RADIUS.get(), true, Level.ExplosionInteraction.TNT);
		}
	}


	@Override
	public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
		AreaEffectCloud cloud = new AreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());

		if (!world.isClientSide) {
			if (this == ModBlocks.TOXIC_MINE.get()) {
				cloud.addEffect(new MobEffectInstance(MobEffects.POISON, Config.POSION_MINE_EFFECT_DURATION.get(), Config.POSION_MINE_EFFECT_AMPLIFIER.get()));
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION.get());
				cloud.setRadius(10);
				cloud.setFixedColor(0x27ae60);
				cloud.setWaitTime(10);
				world.addFreshEntity(cloud);
			}

			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			world.explode(cloud, pos.getX(), pos.getY(), pos.getZ(), Config.MINE_EXPLOSION_RADIUS.get(), true, Level.ExplosionInteraction.TNT);
		}
	}


	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return canSupportCenter(pLevel, pPos.below(), Direction.DOWN);
	}


	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return null;
	}
}