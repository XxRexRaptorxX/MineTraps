package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.TrapHelper;

import java.util.List;


public class BlockGhost extends Block {
	public static final MapCodec<BlockGhost> CODEC = BlockGhost.createCodec(BlockGhost::new);

	public MapCodec<BlockGhost> getCodec() {
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

	public BlockGhost(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.STONE_GRAY)
				.nonOpaque()
				.noCollision()
				.strength(1.5F, 6.0F)
				.sounds(BlockSoundGroup.STONE)
				.instrument(NoteBlockInstrument.XYLOPHONE)
		);
		this.setDefaultState(this.getDefaultState().with(TYPE, 0));
	}


	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
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
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (state.getBlock() == ModBlocks.GHOST_BLOCK && state.get(TYPE) == 0) {
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