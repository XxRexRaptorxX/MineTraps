package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;


public class BlockExplosive extends Block {

	public BlockExplosive() {
		super(Properties.of(Material.WOOD)
				.requiresCorrectToolForDrops()
				.strength(2.5F, 0.0F)
				.sound(SoundType.STONE)
				.color(MaterialColor.WOOD)
		);
	}


	@Override
	public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
		AreaEffectCloud dummy = new AreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());

		world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
		world.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), 3.0F, true, Explosion.BlockInteraction.DESTROY);
	}


	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		AreaEffectCloud dummy = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
		level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1.0F, 3);
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
		level.explode(dummy, pos.getX(), pos.getY(), pos.getZ(), 3.0F, true, Explosion.BlockInteraction.DESTROY);

		return InteractionResult.SUCCESS;
	}

}