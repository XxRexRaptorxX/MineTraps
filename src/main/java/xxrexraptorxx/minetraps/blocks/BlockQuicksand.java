package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockQuicksand extends FallingBlock {
	public static final MapCodec<BlockQuicksand> CODEC = BlockQuicksand.createCodec(BlockQuicksand::new);

	public MapCodec<BlockQuicksand> getCodec() {
		return CODEC;
	}

	private final VoxelShape CUSTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockQuicksand(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.PALE_YELLOW)
				.nonOpaque()
				.strength(0.65F, 0.0F)
				.sounds(BlockSoundGroup.SAND)
				.instrument(Instrument.SNARE)
		);
	}

	//TODO waterlogging

	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CUSTOM_SHAPE;
	}


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity entity) {

			entity.slowMovement(state, new Vec3d(0.25D, 0.10D, 0.25D));
			if (entity.getEyePos().y < pos.getY() + 1) {
				entityIn.damage(MineTrapsDamageTypes.of(entityIn.getWorld(), DamageTypes.IN_WALL), Config.QUICKSAND_DAMAGE);
			}
		}
	}
}