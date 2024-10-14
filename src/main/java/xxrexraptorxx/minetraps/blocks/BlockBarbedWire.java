package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import xxrexraptorxx.minetraps.damage_type.MineTrapsDamageTypes;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;

public class BlockBarbedWire extends HorizontalFacingBlock {

	public BlockBarbedWire(AbstractBlock.Settings settings) {
		super(settings
				.mapColor(MapColor.IRON_GRAY)
				.nonOpaque()
				.noCollision()
				.requiresTool()
				.strength(5.0f,10.0f)
				.sounds(BlockSoundGroup.METAL));

		setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.slowMovement(state, new Vec3d(0.25, 0.05f, 0.25));

		if (!world.isClient()) {
			if (Config.BARBED_WIRE_DESTROY_ITEMS) {
				if (this == ModBlocks.BARBED_WIRE)
					entity.damage(MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.BARBED_WIRE_DAMAGE);
				if (this == ModBlocks.RAZOR_WIRE)
					entity.damage(MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.RAZOR_WIRE_DAMAGE);
			} else {
				if (entity instanceof LivingEntity) {
					if (this == ModBlocks.BARBED_WIRE)
						entity.damage(MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.BARBED_WIRE_DAMAGE);
					if (this == ModBlocks.RAZOR_WIRE)
						entity.damage(MineTrapsDamageTypes.of(entity.getWorld(), MineTrapsDamageTypes.SPIKES), Config.RAZOR_WIRE_DAMAGE);
				}
			}
		}
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.HORIZONTAL_FACING);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}
}
