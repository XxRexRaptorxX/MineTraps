package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;

import javax.annotation.Nullable;


public class BlockBarbedWire extends HalfTransparentBlock {

	private static final VoxelShape RENDER_SHAPE = Shapes.empty();

	public BlockBarbedWire() {
		super(Properties.of()
				.requiresCorrectToolForDrops()
				.strength(5.0F, 10.0F)
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.instrument(NoteBlockInstrument.PLING)
				.noCollission()
				.noOcclusion()
		);
	}


	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
		return true;
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vec3(0.25D, (double)0.05F, 0.25D));
		if (!level.isClientSide) {
			if (Config.BARBED_WIRE_DESTROY_ITEMS.get()) {
				if (this == ModBlocks.BARBED_WIRE.get())  entity.hurt(level.damageSources().generic(), Config.BARBED_WIRE_DAMAGE.get());
				if (this == ModBlocks.RAZOR_WIRE.get())  entity.hurt(level.damageSources().generic(), Config.RAZOR_WIRE_DAMAGE.get());
			} else {
				if (entity instanceof LivingEntity) {
					if (this == ModBlocks.BARBED_WIRE.get())  entity.hurt(level.damageSources().generic(), (float) Config.BARBED_WIRE_DAMAGE.get());
					if (this == ModBlocks.RAZOR_WIRE.get())  entity.hurt(level.damageSources().generic(), (float) Config.RAZOR_WIRE_DAMAGE.get());
				}
			}
		}
	}


	//Facing

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING);

	}


	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}

}