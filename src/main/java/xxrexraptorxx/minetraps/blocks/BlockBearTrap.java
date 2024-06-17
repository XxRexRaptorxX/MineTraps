package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;

import net.minecraft.entity.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockBearTrap extends FallingBlock {
	public static final MapCodec<BlockBearTrap> CODEC = BlockBearTrap.createCodec(BlockBearTrap::new);

	public MapCodec<BlockBearTrap> getCodec() {
		return CODEC;
	}

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);

	public BlockBearTrap(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.IRON_GRAY)
				.nonOpaque()
				.noCollision()
				.requiresTool()
				.strength(5.0f, 10.0f)
				.sounds(BlockSoundGroup.METAL)
				.instrument(Instrument.CHIME)
		);
	}

	@Deprecated
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_SHAPE;
	}

	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return Block.sideCoversSmallSquare(world, pos.down(), Direction.DOWN);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity entity) {
            if (!entity.hasStatusEffect(StatusEffects.SLOWNESS)) {
                entityIn.damage(MineTrapsDamageTypes.of(entityIn.getWorld(), MineTrapsDamageTypes.SPIKES), 6.0f);
            }

            entityIn.damage(MineTrapsDamageTypes.of(entityIn.getWorld(), MineTrapsDamageTypes.SPIKES), Config.BEAR_TRAP_DAMAGE);
            entityIn.slowMovement(state, new Vec3d(0.1D, 0.25D, 0.1D));

            if (!world.isClient()) {
                if (!entity.hasStatusEffect(StatusEffects.SLOWNESS)) {
                    world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, 3);
                }
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 5, 0, false, false, false));
            }
		}
	}
}
