package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;

import net.minecraft.entity.Entity;
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
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockBarbedWireFence extends HorizontalConnectingBlock {
	public static final MapCodec<BlockBarbedWireFence> CODEC = BlockBarbedWireFence.createCodec(BlockBarbedWireFence::new);
	private final VoxelShape[] cullingShapes;

	@Override
	protected MapCodec<? extends HorizontalConnectingBlock> getCodec() {
		return CODEC;
	}

	public BlockBarbedWireFence(AbstractBlock.Settings settings) {
		super(1.0F, 1.0F, 16.0F, 16.0F, 16.0F, settings
				.mapColor(MapColor.IRON_GRAY)
				.nonOpaque()
				.noCollision()
				.requiresTool()
				.strength(5.0f,10.0f)
				.sounds(BlockSoundGroup.METAL)
				.instrument(Instrument.CHIME)
		);

		this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(NORTH, false)).with(EAST, false)).with(SOUTH, false)).with(WEST, false)).with(WATERLOGGED, false));
		this.cullingShapes = this.createShapes(2.0f, 1.0f, 16.0f, 6.0f, 15.0f);
	}

	@Override
	public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
		return this.cullingShapes[this.getShapeIndex(state)];
	}

	@Override
	public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return this.getOutlineShape(state, world, pos, context);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
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
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (world.isClient) {
			ItemStack itemStack = player.getStackInHand(hand);
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
		return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)super.getPlacementState(ctx).with(NORTH, this.canConnect(blockState, blockState.isSideSolidFullSquare(blockView, blockPos2, Direction.SOUTH), Direction.SOUTH))).with(EAST, this.canConnect(blockState2, blockState2.isSideSolidFullSquare(blockView, blockPos3, Direction.WEST), Direction.WEST))).with(SOUTH, this.canConnect(blockState3, blockState3.isSideSolidFullSquare(blockView, blockPos4, Direction.NORTH), Direction.NORTH))).with(WEST, this.canConnect(blockState4, blockState4.isSideSolidFullSquare(blockView, blockPos5, Direction.EAST), Direction.EAST))).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED).booleanValue()) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		if (direction.getAxis().getType() == Direction.Type.HORIZONTAL) {
			return (BlockState)state.with((Property)FACING_PROPERTIES.get(direction), this.canConnect(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction.getOpposite()), direction.getOpposite()));
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.slowMovement(state, new Vec3d(0.25, 0.05f, 0.25));

		if (!world.isClient()) {
			if (Config.BARBED_WIRE_DESTROY_ITEMS) {
				entity.damage(MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.BARBED_WIRE_FENCE_DAMAGE);
			} else {
				if (entity instanceof LivingEntity) {
					entity.damage(MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.BARBED_WIRE_FENCE_DAMAGE);
				}
			}
		}
	}
}