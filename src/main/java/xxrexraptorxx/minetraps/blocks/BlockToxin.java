package xxrexraptorxx.minetraps.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xxrexraptorxx.minetraps.utils.Config;

public class BlockToxin extends FluidBlock {
    public BlockToxin(FlowableFluid fluid, AbstractBlock.Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(
            BlockState state, World world, BlockPos pos, Entity entityIn, EntityCollisionHandler handler) {
        if (entityIn instanceof LivingEntity entity) {
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.POISON, Config.TOXIN_POISON_EFFECT_DURATION, Config.TOXIN_POISON_EFFECT_AMPLIFIER));
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NAUSEA,
                    Config.TOXIN_CONFUSION_EFFECT_DURATION,
                    Config.TOXIN_CONFUSION_EFFECT_AMPLIFIER));
        }
    }
}
