package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.main.ModBlocks;


public class BlockExplosiveMine extends FallingBlock {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);

	public BlockExplosiveMine() {
		super(Properties.of(Material.METAL)
				.requiresCorrectToolForDrops()
				.strength(1.0F, 0.0F)
				.sound(SoundType.METAL)
				.color(MaterialColor.METAL)
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
				cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 280, 1));
				cloud.setDuration(50);
				cloud.setRadius(10);
				cloud.setFixedColor(0x27ae60);
				cloud.setWaitTime(10);
				world.addFreshEntity(cloud);
			}

			entity.hurt(DamageSource.GENERIC, 10.0F);
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			world.explode(entity, pos.getX(), pos.getY(), pos.getZ(), 5.0F, true, Explosion.BlockInteraction.DESTROY);
		}
	}


	@Override
	public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
		AreaEffectCloud dummy = new AreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
		if(this == ModBlocks.TOXIC_MINE.get()) {
			dummy.addEffect(new MobEffectInstance(MobEffects.POISON, 280, 1));
			dummy.setDuration(50);
			dummy.setRadius(10);
			dummy.setFixedColor(0x27ae60);
			dummy.setWaitTime(10);
			world.addFreshEntity(dummy);
		}

		world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
		world.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), 5.0F, true, Explosion.BlockInteraction.DESTROY);
	}


	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return canSupportCenter(pLevel, pPos.below(), Direction.DOWN);
	}

}