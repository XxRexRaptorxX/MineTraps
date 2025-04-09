package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;

import java.util.List;


public class BlockSpikes extends FallingBlock {
	public static final MapCodec<BlockSpikes> CODEC = BlockSpikes.createCodec(BlockSpikes::new);

	public MapCodec<BlockSpikes> getCodec() {
		return CODEC;
	}

	public static final BooleanProperty POWERED = Properties.POWERED;
	private final VoxelShape ON_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);
	private final VoxelShape OFF_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

	public BlockSpikes(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.IRON_GRAY)
				.nonOpaque()
				.noCollision()
				.requiresTool()
				.strength(1.8F, 7.0F)
				.sounds(BlockSoundGroup.METAL)
				.instrument(NoteBlockInstrument.BELL)
		);
		this.setDefaultState(this.getDefaultState().with(POWERED, false));
	}


	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}


	@Deprecated
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (state.get(POWERED)) {
			return ON_SHAPE;
		} else {
			return OFF_SHAPE;
		}
	}


	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return Block.sideCoversSmallSquare(world, pos.down(), Direction.DOWN);
	}


	public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
		tooltip.add(Text.translatable("message.minetraps.spike.desc").withColor(Colors.GRAY));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn, EntityCollisionHandler handler) {
		if ((entityIn instanceof LivingEntity entity) && !world.isClient() && state.get(POWERED)) {
			entityIn.damage((ServerWorld) world, MineTrapsDamageTypes.of(entityIn.getWorld(), MineTrapsDamageTypes.SPIKES), (float) Config.SPIKES_DAMAGE);
			if(this == ModBlocks.TOXIC_SPIKES) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, Config.TOXIC_SPIKES_EFFECT_DURATION, Config.TOXIC_SPIKES_EFFECT_AMPLIFIER));
		}
	}


	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
	}


	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
		if (!world.isClient()) {
			boolean flag = state.get(POWERED);
			if (flag != world.isReceivingRedstonePower(pos)) {
				if (flag) {
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 1.0F, 3);
					world.scheduleBlockTick(pos, this, 4);
				} else {
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 3);
					world.setBlockState(pos, state.cycle(POWERED), 2);
				}
			}
		}
	}


	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (state.get(POWERED) && !world.isReceivingRedstonePower(pos)) {
			world.setBlockState(pos, state.cycle(POWERED), 2);
		}
	}

	@Override
	public int getColor(BlockState state, BlockView world, BlockPos pos) {
		return getDefaultMapColor().getRenderColor(MapColor.Brightness.NORMAL);
	}
}