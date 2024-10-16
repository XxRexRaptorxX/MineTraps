package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockChestBomb extends Block {

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

	public BlockChestBomb(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.OAK_TAN)
				.requiresTool()
				.strength(2.5f, 0.0f)
				.sounds(BlockSoundGroup.WOOD)
				.instrument(Instrument.BASS)
		);
	}

	@Deprecated
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_SHAPE;
	}

	@Override
	public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		AreaEffectCloudEntity dummy = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);

		if (!world.isClient()) {
			world.createExplosion(dummy, pos.getX(), pos.getY(), pos.getZ(), 3.0F, true, World.ExplosionSourceType.TNT);
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		AreaEffectCloudEntity dummy = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0F, 3);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);

		if (!world.isClient()) {
			world.createExplosion(dummy, pos.getX(), pos.getY(), pos.getZ(), Config.CHEST_BOMB_EXPLOSION_RADIUS, true, World.ExplosionSourceType.TNT);
		}

		return ActionResult.SUCCESS;
	}

	//Facing
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.HORIZONTAL_FACING);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}
}