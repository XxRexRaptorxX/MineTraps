package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.Entity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class BlockMud extends FallingBlock {

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

	public BlockMud(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.DIRT_BROWN)
				.strength(0.3F, 0.0F)
				.sounds(BlockSoundGroup.SLIME)
				.instrument(Instrument.SNARE)
		);
	}

	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
		entityIn.slowMovement(state, new Vec3d(0.6D, 0.6D, 0.6D));
	}


	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		entity.handleFallDamage(fallDistance, 0.8F, (world.getDamageSources().fall()));
	}
}