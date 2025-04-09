package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
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

import java.util.List;
import java.util.Optional;


public class BlockExplosiveMine extends FallingBlock {
	public static final MapCodec<BlockExplosiveMine> CODEC = BlockExplosiveMine.createCodec(BlockExplosiveMine::new);

	public MapCodec<BlockExplosiveMine> getCodec() {
		return CODEC;
	}

	@Override
	public int getColor(BlockState state, BlockView world, BlockPos pos) {
		return getDefaultMapColor().getRenderColor(MapColor.Brightness.NORMAL);
	}

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);

	public BlockExplosiveMine(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.IRON_GRAY)
				.nonOpaque()
				.noCollision()
				.requiresTool()
				.strength(1.0f, 0.0f)
				.sounds(BlockSoundGroup.METAL)
				.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
		);
	}

	@Deprecated
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn, EntityCollisionHandler handler) {
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 1.0F, 3);

		if (!world.isClient()) {
			if(this == ModBlocks.TOXIC_MINE) {
				AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION);
				cloud.setRadius(10);
				cloud.setPotionContents(new PotionContentsComponent(Optional.empty(), Optional.of(0x27ae60), List.of(new StatusEffectInstance(StatusEffects.POISON, Config.POSION_MINE_EFFECT_DURATION, Config.POSION_MINE_EFFECT_AMPLIFIER)), Optional.empty()));
				cloud.setWaitTime(10);
				world.spawnEntity(cloud);
			}

			entityIn.damage((ServerWorld) world, MineTrapsDamageTypes.of(entityIn.getWorld(), DamageTypes.GENERIC), Config.MINE_DAMAGE);
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			world.createExplosion(entityIn, pos.getX(), pos.getY(), pos.getZ(), Config.MINE_EXPLOSION_RADIUS, true, World.ExplosionSourceType.TNT);
		}
	}

	@Override
	public void onDestroyedByExplosion(ServerWorld world, BlockPos pos, Explosion explosion) {
		AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());

		if (!world.isClient()) {
			if (this == ModBlocks.TOXIC_MINE) {
				cloud.setDuration(Config.POSION_MINE_CLOUD_DURATION);
				cloud.setRadius(10);
				cloud.setPotionContents(new PotionContentsComponent(Optional.empty(), Optional.of(0x27ae60), List.of(new StatusEffectInstance(StatusEffects.POISON, Config.POSION_MINE_EFFECT_DURATION, Config.POSION_MINE_EFFECT_AMPLIFIER)), Optional.empty()));
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