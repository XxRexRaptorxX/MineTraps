package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;

import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.LeadItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.utils.Config;
import java.util.function.Function;


public class BlockBarbedWireFence extends HorizontalConnectingBlock {
	public static final MapCodec<BlockBarbedWireFence> CODEC = BlockBarbedWireFence.createCodec(BlockBarbedWireFence::new);
	private final Function<BlockState, VoxelShape> cullingShapes;

	@Override
	protected MapCodec<? extends HorizontalConnectingBlock> getCodec() {
		return CODEC;
	}

	public BlockBarbedWireFence(AbstractBlock.Settings settings) {
		super(1.0F, 16.0F, 1.0F, 16.0F, 24.0F, settings
				.mapColor(MapColor.IRON_GRAY)
				.nonOpaque()
				.noCollision()
				.requiresTool()
				.strength(5.0f,10.0f)
				.sounds(BlockSoundGroup.METAL)
				.instrument(NoteBlockInstrument.CHIME)
		);

		this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
		this.cullingShapes = this.createShapeFunction(2.0f, 1.0f, 16.0f, 6.0f, 15.0f);
	}

	@Override
	public VoxelShape getCullingShape(BlockState state) {
		return (VoxelShape)this.cullingShapes.apply(state);
	}

	@Override
	public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return this.getOutlineShape(state, world, pos, context);
	}

	@Override
	protected boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}

	public boolean canConnect(BlockState state, boolean neighborIsFullSquare, Direction dir) {
		Block block = state.getBlock();
		boolean bl = this.canConnectToFence(state);
		boolean bl2 = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, dir);
		return !FenceBlock.cannotConnect(state) && neighborIsFullSquare || bl || bl2;
	}

	private boolean canConnectToFence(BlockState state) {
		return state.isIn(BlockTags.FENCES) && state.isIn(BlockTags.WOODEN_FENCES) == this.getDefaultState().isIn(BlockTags.WOODEN_FENCES);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (world.isClient) {
			ItemStack itemStack = player.getStackInHand(player.getActiveHand());
			if (itemStack.isOf(Items.LEAD)) {
				return ActionResult.SUCCESS;
			}
			return ActionResult.PASS;
		}
		return LeadItem.attachHeldMobsToBlock(player, world, pos);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		World blockView = ctx.getWorld();
		BlockPos blockPos = ctx.getBlockPos();
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		BlockPos blockPos2 = blockPos.north();
		BlockPos blockPos3 = blockPos.east();
		BlockPos blockPos4 = blockPos.south();
		BlockPos blockPos5 = blockPos.west();
		BlockState blockState = blockView.getBlockState(blockPos2);
		BlockState blockState2 = blockView.getBlockState(blockPos3);
		BlockState blockState3 = blockView.getBlockState(blockPos4);
		BlockState blockState4 = blockView.getBlockState(blockPos5);
		return ((((super.getPlacementState(ctx).with(NORTH, this.canConnect(blockState, blockState.isSideSolidFullSquare(blockView, blockPos2, Direction.SOUTH), Direction.SOUTH))).with(EAST, this.canConnect(blockState2, blockState2.isSideSolidFullSquare(blockView, blockPos3, Direction.WEST), Direction.WEST))).with(SOUTH, this.canConnect(blockState3, blockState3.isSideSolidFullSquare(blockView, blockPos4, Direction.NORTH), Direction.NORTH))).with(WEST, this.canConnect(blockState4, blockState4.isSideSolidFullSquare(blockView, blockPos5, Direction.EAST), Direction.EAST))).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
		if (state.get(WATERLOGGED)) {
			tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		if (direction.getAxis().getType() == Direction.Type.HORIZONTAL) {
			return state.with(FACING_PROPERTIES.get(direction), this.canConnect(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction.getOpposite()), direction.getOpposite()));
		}
		return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
		entity.slowMovement(state, new Vec3d(0.25, 0.05f, 0.25));

		if (!world.isClient()) {
			if (world instanceof ServerWorld) {
				if (Config.BARBED_WIRE_DESTROY_ITEMS) {
					entity.damage((ServerWorld) world, MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.BARBED_WIRE_FENCE_DAMAGE);
				} else {
					if (entity instanceof LivingEntity) {
						entity.damage((ServerWorld) world, MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.BARBED_WIRE_FENCE_DAMAGE);
					}
				}
			}
		}
	}
}