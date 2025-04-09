package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockNailTrap extends FallingBlock {
	public static final MapCodec<BlockNailTrap> CODEC = BlockNailTrap.createCodec(BlockNailTrap::new);

	public MapCodec<BlockNailTrap> getCodec() {
		return CODEC;
	}

	@Override
	public int getColor(BlockState state, BlockView world, BlockPos pos) {
		return getDefaultMapColor().getRenderColor(MapColor.Brightness.NORMAL);
	}

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);

	public BlockNailTrap(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.IRON_GRAY)
				.requiresTool()
				.nonOpaque()
				.noCollision()
				.strength(1.0F, 8.0F)
				.sounds(BlockSoundGroup.GRAVEL)
				.instrument(NoteBlockInstrument.BELL)
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
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn, EntityCollisionHandler handler) {
		if (!world.isClient() && !entityIn.isCrawling()) {
			if (entityIn instanceof LivingEntity entity) {

				if (this == ModBlocks.TOXIC_NAIL_TRAP)
					entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, Config.TOXIC_NAIL_TRAP_EFFECT_DURATION, Config.TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER));

				entity.damage((ServerWorld) world, MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.NAIL_TRAP_DAMAGE);
			}
		}
	}
}