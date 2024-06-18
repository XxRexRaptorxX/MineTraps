package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Colors;
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
				.instrument(Instrument.XYLOPHONE)
		);
		this.setDefaultState(this.getDefaultState().with(TYPE, 0));
	}


	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}


	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.translatable("message.minetraps.ghost.desc").withColor(Colors.GRAY));
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
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (state.getBlock() == ModBlocks.GHOST_BLOCK && state.get(TYPE) == 0) {
			if (TrapHelper.getTypeList().contains(player.getStackInHand(hand).getItem())) {
				world.setBlockState(pos, state.with(TYPE, TrapHelper.getStateFromBlock(Registries.ITEM.getId(player.getStackInHand(hand).getItem()).toString())), 2);
				if (!player.isCreative()) {
					player.getStackInHand(hand).decrement(1);
				}
			}
		}
		return ActionResult.SUCCESS;
	}
}