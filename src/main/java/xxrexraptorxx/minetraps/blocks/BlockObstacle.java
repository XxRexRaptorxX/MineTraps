package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;


public class BlockObstacle extends Block {
	public static final MapCodec<BlockObstacle> CODEC = BlockObstacle.createCodec(BlockObstacle::new);

	public MapCodec<BlockObstacle> getCodec() {
		return CODEC;
	}

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 24.0D, 16.0D);

	public BlockObstacle(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.IRON_GRAY)
				.requiresTool()
				.nonOpaque()
				.strength(20.0F, 20.0F)
				.sounds(BlockSoundGroup.METAL)
				.instrument(Instrument.IRON_XYLOPHONE)
		);
	}

	@Override
	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_SHAPE;
	}
}