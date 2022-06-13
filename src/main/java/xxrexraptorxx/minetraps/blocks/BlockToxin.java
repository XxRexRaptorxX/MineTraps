package xxrexraptorxx.minetraps.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import xxrexraptorxx.minetraps.utils.Config;

import java.util.function.Supplier;

public class BlockToxin extends LiquidBlock {
    public BlockToxin(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid, properties);
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
            if(entityIn instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) entityIn;
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, Config.TOXIN_POISON_EFFECT_DURATION.get(), Config.TOXIN_POISON_EFFECT_AMPLIFIER.get()));
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, Config.TOXIN_CONFUSION_EFFECT_DURATION.get(), Config.TOXIN_CONFUSION_EFFECT_AMPLIFIER.get()));
            }
        }
}
