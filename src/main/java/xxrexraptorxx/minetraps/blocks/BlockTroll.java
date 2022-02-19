package xxrexraptorxx.minetraps.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import xxrexraptorxx.minetraps.main.ModBlocks;
import xxrexraptorxx.minetraps.utils.TrollHelper;

import javax.annotation.Nullable;
import java.util.List;


public class BlockTroll extends Block {

	/**
	 * 	0 = empty
	 *  1 = diamond
	 *  2 = emerald
	 *  3 = iron
	 *  4 = gold
	 *  5 = deepslate diamond
	 *  6 = deepslate emerald
	 *  7 = deepslate iron
	 *  8 = deepslate gold
	 */
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 8);


	public BlockTroll() {
		super(Properties.of(Material.STONE)
				.strength(1.5F, 6.0F)
				.sound(SoundType.STONE)
				.color(MaterialColor.COLOR_GRAY)
				.noDrops()
		);
		this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 0));
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> list, TooltipFlag pFlag) {
		list.add(new TranslatableComponent("message.minetraps.troll.desc").withStyle(ChatFormatting.GRAY));
	}


	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(TYPE);
	}


	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(TYPE, 0);
	}


	//Functions

	@Override
	public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
		AreaEffectCloud dummy = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

		if(!level.isClientSide) {
			level.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), 3.0F, true, Explosion.BlockInteraction.BREAK);
		}
	}


	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {

		AreaEffectCloud dummy = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
		level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1.0F, 3);
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

		if(!level.isClientSide) {
			level.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), 3.0F, true, Explosion.BlockInteraction.BREAK);
		}

		return true;
	}


	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

		if (state.getBlock() == ModBlocks.TROLL_BLOCK.get() && state.getValue(TYPE) == 0) {

			if (TrollHelper.getTypeList().contains(player.getItemInHand(hand).getItem())) {
				level.setBlock(pos, state.setValue(TYPE, TrollHelper.getStateFromBlock(player.getItemInHand(hand).getItem().getRegistryName().toString())), 2);

				if (!player.isCreative()) {
					player.getUseItem().shrink(1);
				}
			}
		}
		return InteractionResult.SUCCESS;
	}
}