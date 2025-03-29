package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;
import xxrexraptorxx.minetraps.utils.TrollHelper;

import javax.annotation.Nullable;


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


	public BlockTroll(Properties properties) {
		super(properties);

		this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 0));
	}


	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(TYPE);
	}


	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(TYPE, 0);
	}


	//Functions

	@Override
	public void onBlockExploded(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion) {
		AreaEffectCloud dummy = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

		if(!level.isClientSide) {
			level.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), (float) Config.EXPLOSIVE_BLOCK_RADIUS.get(), true, Level.ExplosionInteraction.TNT);
		}
	}


	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
		AreaEffectCloud dummy = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
		level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1.0F, 3);
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

		if(!level.isClientSide) {
			level.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), (float) Config.EXPLOSIVE_BLOCK_RADIUS.get(), true, Level.ExplosionInteraction.TNT);
		}

		return true;
	}


	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (state.getBlock() == ModBlocks.TROLL_BLOCK.get() && state.getValue(TYPE) == 0) {
			InteractionHand hand = player.getUsedItemHand();

			if (TrollHelper.getTypeList().contains(player.getItemInHand(hand).getItem())) {
				level.setBlock(pos, state.setValue(TYPE, TrollHelper.getStateFromBlock(BuiltInRegistries.ITEM.getKey(player.getItemInHand(hand).getItem()).toString())), 2);

				if (!player.isCreative()) {
					player.getItemInHand(hand).shrink(1);
				}
			}
		}
		return InteractionResult.SUCCESS;
	}
}