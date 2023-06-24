package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import xxrexraptorxx.minetraps.utils.Config;


public class BlockBearTrap extends FallingBlock {

	protected static final VoxelShape CUSTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.00D, 16.0D);

	public BlockBearTrap() {
		super(Properties.of()
				.requiresCorrectToolForDrops()
				.strength(5.0F, 10.0F)
				.sound(SoundType.METAL)
				.mapColor(MapColor.METAL)
				.instrument(NoteBlockInstrument.PLING)
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

		if (entityIn instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity) entityIn;
			if(!entity.getActiveEffects().toString().contains(ForgeRegistries.MOB_EFFECTS.getKey(MobEffects.MOVEMENT_SLOWDOWN).getNamespace() + "." + ForgeRegistries.MOB_EFFECTS.getKey(MobEffects.MOVEMENT_SLOWDOWN).getPath())) entity.hurt(level.damageSources().generic(), 6.0F);

			entity.hurt(level.damageSources().generic(), (float) Config.BEAR_TRAP_DAMAGE.get());
			entity.makeStuckInBlock(state, new Vec3(0.1D, 0.25D, 0.1D));

			if(!level.isClientSide) {
				if(!entity.getActiveEffects().toString().contains(ForgeRegistries.MOB_EFFECTS.getKey(MobEffects.MOVEMENT_SLOWDOWN).getNamespace() + "." + ForgeRegistries.MOB_EFFECTS.getKey(MobEffects.MOVEMENT_SLOWDOWN).getPath())) level.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 1.0F, 3);
				entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 0, false, false, false));
			}

		}
	}

}