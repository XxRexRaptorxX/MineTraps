package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.utils.Config;

import javax.annotation.Nullable;


public class BlockChestBomb extends Block {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(	1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);


	public BlockChestBomb(Properties properties) {
		super(properties);
	}


	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void onBlockExploded(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion) {
		AreaEffectCloud dummy = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

		if(!level.isClientSide) {
			level.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), 3.0F, true, Level.ExplosionInteraction.TNT);
		}
	}


	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		AreaEffectCloud dummy = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
		level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1.0F, 3);
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

		if(!level.isClientSide) {
			level.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), (float) Config.CHEST_BOMB_EXPLOSION_RADIUS.get(), true, Level.ExplosionInteraction.TNT);
		}

		return InteractionResult.SUCCESS;
	}


	//Facing

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING);
	}


	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}
}