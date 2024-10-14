package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;
import xxrexraptorxx.minetraps.utils.TrollHelper;

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
	public static final IntProperty TYPE = IntProperty.of("type", 0, 8);

	public BlockTroll(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.STONE_GRAY)
				.strength(1.5F, 6.0F)
				.sounds(BlockSoundGroup.STONE)
				.instrument(Instrument.BELL)
				.dropsNothing()
		);
		this.setDefaultState(this.getDefaultState().with(TYPE, 0));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.translatable("message.minetraps.troll.desc").setStyle(Style.EMPTY.withColor(Colors.GRAY)));
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


	//Functions

	@Override
	public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		AreaEffectCloudEntity dummy = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);

		if (!world.isClient()) {
			world.createExplosion(dummy, pos.getX(), pos.getY(), pos.getZ(), (float) Config.EXPLOSIVE_BLOCK_RADIUS, true, World.ExplosionSourceType.TNT);
		}
	}


	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		AreaEffectCloudEntity dummy = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0F, 3);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);

		if (!world.isClient()) {
			world.createExplosion(dummy, pos.getX(), pos.getY(), pos.getZ(), Config.EXPLOSIVE_BLOCK_RADIUS, true, World.ExplosionSourceType.TNT);
		}
	}


	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (state.getBlock() == ModBlocks.TROLL_BLOCK && state.get(TYPE) == 0) {
			if (TrollHelper.getTypeList().contains(player.getStackInHand(hand).getItem())) {
				world.setBlockState(pos, state.with(TYPE, TrollHelper.getStateFromBlock(Registries.ITEM.getId(player.getStackInHand(hand).getItem()).toString())), 2);
				if (!player.isCreative()) {
					player.getStackInHand(hand).decrement(1);
				}
			}
		}
		return ActionResult.SUCCESS;
	}
}