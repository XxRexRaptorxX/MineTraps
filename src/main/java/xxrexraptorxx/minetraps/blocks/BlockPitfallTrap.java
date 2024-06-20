package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.TrapHelper;

import java.util.List;


public class BlockPitfallTrap extends Block {
	public static final MapCodec<BlockPitfallTrap> CODEC = BlockPitfallTrap.createCodec(BlockPitfallTrap::new);

	public MapCodec<BlockPitfallTrap> getCodec() {
		return CODEC;
	}

	/**
	 * 	0 = empty
	 *  1 = dirt
	 *  2 = stone
	 *  3 = planks
	 *  4 = sand
	 *  5 = cobblestone
	 *  6 = stonebricks
	 */
	public static final IntProperty TYPE = IntProperty.of("type", 0, 6);
	protected final VoxelShape CUSTOM_COLLISION_AABB = Block.createCuboidShape(0.0625D, 0.0625D, 0.0625D, 15.9375D, 15.9375D, 15.9375D);

	public BlockPitfallTrap(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.OAK_TAN)
				.nonOpaque()
				.noCollision()
				.strength(1.0F, 1.0F)
				.sounds(BlockSoundGroup.WOOD)
				.instrument(NoteBlockInstrument.BASS)
		);
		this.setDefaultState(this.getDefaultState().with(TYPE, 0));
	}


	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_COLLISION_AABB;
	}


	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
		tooltip.add(Text.translatable("message.minetraps.pitfall.desc").withColor(Colors.GRAY));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(TYPE);
	}


	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(TYPE, 0);
	}


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 3);

		if (!world.isClient()) {
			world.removeBlock(pos, false);
			ItemEntity drop = new ItemEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, new ItemStack(Items.STICK));

			world.spawnEntity(drop);
		}
	}


	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (state.getBlock() == ModBlocks.PITFALL_TRAP && state.get(TYPE) == 0) {
			Hand hand = player.getActiveHand();
			if (TrapHelper.getTypeList().contains(player.getStackInHand(hand).getItem())) {
				world.setBlockState(pos, state.with(TYPE, TrapHelper.getStateFromBlock(Registries.ITEM.getId(player.getStackInHand(hand).getItem()).toString())), 2);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 3);
				if (!player.isCreative()) {
					player.getStackInHand(hand).decrement(1);
				}
			}
		}
		return ActionResult.SUCCESS;
	}
}