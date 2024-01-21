package xxrexraptorxx.minetraps.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockNailTrap extends FallingBlock {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);


	public BlockNailTrap() {
		super(Properties.of()
				.requiresCorrectToolForDrops()
				.strength(1.0F, 8.0F)
				.sound(SoundType.GRAVEL)
				.mapColor(MapColor.METAL)
				.instrument(NoteBlockInstrument.BELL)
				.noOcclusion()
				.noCollission()
		);
	}


	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return CUSTOM_SHAPE;
	}


	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return Shapes.empty();
	}


	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return canSupportCenter(pLevel, pPos.below(), Direction.DOWN);
	}


	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		if (!level.isClientSide && !entityIn.isCrouching()) {
			if (entityIn instanceof LivingEntity) {
				LivingEntity entity = (LivingEntity) entityIn;

				if (this == ModBlocks.TOXIC_NAIL_TRAP.get())
					entity.addEffect(new MobEffectInstance(MobEffects.POISON, Config.TOXIC_NAIL_TRAP_EFFECT_DURATION.get(), Config.TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER.get()));

				entity.hurt(level.damageSources().generic(), (float) Config.NAIL_TRAP_DAMAGE.get());
			}
		}
	}


	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return null;
	}
}