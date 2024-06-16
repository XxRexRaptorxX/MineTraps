package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.utils.Config;

public class BlockBarbedWireFence extends CrossCollisionBlock {

	public BlockBarbedWireFence() {
		super(1.0F, 1.0F, 16.0F, 16.0F, 16.0F, Properties.of()
				.requiresCorrectToolForDrops()
				.strength(5.0F, 10.0F)
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.instrument(NoteBlockInstrument.PLING)
				.noOcclusion()
				.noCollission()
		);

		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}


	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockGetter blockgetter = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		BlockPos blockpos1 = blockpos.north();
		BlockPos blockpos2 = blockpos.south();
		BlockPos blockpos3 = blockpos.west();
		BlockPos blockpos4 = blockpos.east();
		BlockState blockstate = blockgetter.getBlockState(blockpos1);
		BlockState blockstate1 = blockgetter.getBlockState(blockpos2);
		BlockState blockstate2 = blockgetter.getBlockState(blockpos3);
		BlockState blockstate3 = blockgetter.getBlockState(blockpos4);
		return this.defaultBlockState().setValue(NORTH, Boolean.valueOf(this.attachsTo(blockstate, blockstate.isFaceSturdy(blockgetter, blockpos1, Direction.SOUTH)))).setValue(SOUTH, Boolean.valueOf(this.attachsTo(blockstate1, blockstate1.isFaceSturdy(blockgetter, blockpos2, Direction.NORTH)))).setValue(WEST, Boolean.valueOf(this.attachsTo(blockstate2, blockstate2.isFaceSturdy(blockgetter, blockpos3, Direction.EAST)))).setValue(EAST, Boolean.valueOf(this.attachsTo(blockstate3, blockstate3.isFaceSturdy(blockgetter, blockpos4, Direction.WEST)))).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}


	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return facing.getAxis().isHorizontal() ? state.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(this.attachsTo(facingState, facingState.isFaceSturdy(level, facingPos, facing.getOpposite())))) : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}


	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}


	@Override
	public boolean skipRendering(BlockState state, BlockState state2, Direction direction) {
		if (state2.is(this)) {
			if (!direction.getAxis().isHorizontal()) {
				return true;
			}

			if (state.getValue(PROPERTY_BY_DIRECTION.get(direction)) && state2.getValue(PROPERTY_BY_DIRECTION.get(direction.getOpposite()))) {
				return true;
			}
		}

		return super.skipRendering(state, state2, direction);
	}


	public final boolean attachsTo(BlockState state, boolean p_54219_) {
		return !isExceptionForConnection(state) && p_54219_ || state.getBlock() instanceof IronBarsBlock || state.getBlock() instanceof BlockBarbedWireFence || state.is(BlockTags.WALLS);
	}


	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vec3(0.25D, (double)0.05F, 0.25D));

		if (!level.isClientSide) {
			if (Config.BARBED_WIRE_DESTROY_ITEMS.get()) {
				entity.hurt(level.damageSources().generic(), (float) Config.BARBED_WIRE_FENCE_DAMAGE.get());

			} else {
				if (entity instanceof LivingEntity) {
					entity.hurt(level.damageSources().generic(), (float) Config.BARBED_WIRE_FENCE_DAMAGE.get());
				}
			}
		}
	}


	@Override
	protected MapCodec<? extends CrossCollisionBlock> codec() {
		return null;
	}


	@Override
	public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType type) {
		return false;
	}
}