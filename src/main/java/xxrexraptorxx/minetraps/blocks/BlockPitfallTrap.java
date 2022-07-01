package xxrexraptorxx.minetraps.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import xxrexraptorxx.minetraps.main.ModBlocks;
import xxrexraptorxx.minetraps.utils.TrapHelper;

import javax.annotation.Nullable;
import java.util.List;


public class BlockPitfallTrap extends Block {

	/**
	 * 	0 = empty
	 *  1 = dirt
	 *  2 = stone
	 *  3 = planks
	 *  4 = sand
	 *  5 = cobblestone
	 *  6 = stonebricks
	 */
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 6);
	protected static final VoxelShape CUSTOM_COLLISION_AABB = Block.box(0.0625D, 0.0625D, 0.0625D, 15.9375D, 15.9375D, 15.9375D);


	public BlockPitfallTrap() {
		super(Properties.of(Material.WOOD)
				.strength(1.0F, 1.0F)
				.sound(SoundType.WOOD)
				.color(MaterialColor.WOOD)
				.noOcclusion()
				.noCollission()
		);
		this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 0));
	}


	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return CUSTOM_COLLISION_AABB;
	}


	@Override
	public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> list, TooltipFlag pFlag) {
		list.add(Component.translatable("message.minetraps.pitfall.desc").withStyle(ChatFormatting.GRAY));
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


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1.0F, 3);

		if (!level.isClientSide) {
			level.removeBlock(pos, false);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

		if (state.getBlock() == ModBlocks.PITFALL_TRAP.get() && state.getValue(TYPE) == 0) {

			if (TrapHelper.getTypeList().contains(player.getItemInHand(hand).getItem())) {
				level.setBlock(pos, state.setValue(TYPE, TrapHelper.getStateFromBlock(ForgeRegistries.ITEMS.getKey(player.getItemInHand(hand).getItem()).toString())), 2);

				if (!player.isCreative()) {
					player.getUseItem().shrink(1);
				}
			}
		}
		return InteractionResult.SUCCESS;
	}
}