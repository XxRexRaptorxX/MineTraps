package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSpikes extends FallingBlock {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	protected static final VoxelShape OM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);
	protected static final VoxelShape OFF_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

	public BlockSpikes() {
		super(Properties.of()
				.requiresCorrectToolForDrops()
				.strength(1.8F, 7.0F)
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.instrument(NoteBlockInstrument.BELL)
				.noCollission()
				.noOcclusion()
		);

		this.registerDefaultState(this.defaultBlockState().setValue(POWERED, Boolean.valueOf(false)));
	}


	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}


	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(POWERED)) {
			return OM_SHAPE;
		} else {
			return OFF_SHAPE;
		}
	}


	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction direction) {
		return true;
	}


	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.below(), Direction.DOWN);
	}


	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		list.add(Component.translatable("message.minetraps.spike.desc").withStyle(ChatFormatting.GRAY));
	}


	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(POWERED);
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !level.isClientSide && state.getValue(POWERED)) {
			LivingEntity entity = (LivingEntity) entityIn;

			entity.hurt(level.damageSources().generic(), (float) Config.SPIKES_DAMAGE.get());
			if(this == ModBlocks.TOXIC_SPIKES.get()) entity.addEffect(new MobEffectInstance(MobEffects.POISON, Config.TOXIC_SPIKES_EFFECT_DURATION.get(), Config.TOXIC_SPIKES_EFFECT_AMPLIFIER.get()));
		}
	}


	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(POWERED, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
	}


	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (!level.isClientSide) {
			boolean flag = state.getValue(POWERED);
			if (flag != level.hasNeighborSignal(pos)) {
				if (flag) {
					level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PISTON_CONTRACT, SoundSource.BLOCKS, 1.0F, 3);
					level.scheduleTick(pos, this, 4);
				} else {
					level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1.0F, 3);
					level.setBlock(pos, state.cycle(POWERED), 2);
				}
			}
		}
	}


	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return null;
	}


	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (state.getValue(POWERED) && !level.hasNeighborSignal(pos)) {
			level.setBlock(pos, state.cycle(POWERED), 2);
		}
	}


}