package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.explosion.Explosion;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockExplosiveMine extends FallingBlock {

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);

	public BlockExplosiveMine(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.IRON_GRAY)
				.nonOpaque()
				.noCollision()
				.requiresTool()
				.strength(1.0f, 0.0f)
				.sounds(BlockSoundGroup.METAL)
				.instrument(Instrument.IRON_XYLOPHONE)
		);
	}

	@Deprecated
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 1.0F, 3);

		if (!world.isClient()) {
			if(this == ModBlocks.TOXIC_MINE) {
				AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
				cloud.addEffect(new StatusEffectInstance(StatusEffects.POISON, Config.POSION_MINE_EFFECT_DURATION, Config.POSION_MINE_EFFECT_AMPLIFIER));
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION);
				cloud.setRadius(10);
				cloud.setColor(0x27ae60);
				cloud.setWaitTime(10);
				world.spawnEntity(cloud);
			}

			entityIn.damage(MineTrapsDamageTypes.of(entityIn.getWorld(), DamageTypes.GENERIC), Config.MINE_DAMAGE);
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			world.createExplosion(entityIn, pos.getX(), pos.getY(), pos.getZ(), Config.MINE_EXPLOSION_RADIUS, true, World.ExplosionSourceType.TNT);
		}
	}

	@Override
	public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());

		if (!world.isClient()) {
			if (this == ModBlocks.TOXIC_MINE) {
				cloud.addEffect(new StatusEffectInstance(StatusEffects.POISON, Config.POSION_MINE_EFFECT_DURATION, Config.POSION_MINE_EFFECT_AMPLIFIER));
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION);
				cloud.setRadius(10);
				cloud.setColor(0x27ae60);
				cloud.setWaitTime(10);
				world.spawnEntity(cloud);
			}

			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			world.createExplosion(cloud, pos.getX(), pos.getY(), pos.getZ(), Config.MINE_EXPLOSION_RADIUS, true, World.ExplosionSourceType.TNT);
		}
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return Block.sideCoversSmallSquare(world, pos.down(), Direction.DOWN);
	}
}