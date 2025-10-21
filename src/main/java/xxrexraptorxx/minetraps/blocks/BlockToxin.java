package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import xxrexraptorxx.minetraps.utils.Config;

public class BlockToxin extends LiquidBlock {

    public BlockToxin(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn, InsideBlockEffectApplier effectApplier) {
        if (entityIn instanceof LivingEntity entity) {
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, Config.getToxinPoisonEffectDuration(), Config.getToxinPoisonEffectAmplifier()));
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, Config.getToxinConfusionEffectDuration(), Config.getToxinConfusionEffectAmplifier()));
        }
    }
}
