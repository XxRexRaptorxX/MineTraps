package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class BlockMud extends FallingBlock {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);


	public BlockMud() {
		super(Properties.of(Material.DIRT)
				.strength(0.3F, 0.0F)
				.sound(SoundType.SLIME_BLOCK)
				.color(MaterialColor.COLOR_BROWN)
		);
	}


	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vec3(0.6D, 0.6D, 0.6D));
	}


	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		entity.causeFallDamage(fallDistance, 0.8F, (level.damageSources().fall()));
	}
}