package xxrexraptorxx.minetraps.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class ItemToxinBottle extends Item {

    public ItemToxinBottle() {
        super(new Properties()
                .stacksTo(1)
                .craftRemainder(Items.GLASS_BOTTLE)
        );

    }


    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
            ServerPlayer serverplayer = (ServerPlayer) pEntityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            pStack.shrink(1);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));


        if (!pLevel.isClientSide) {
            pEntityLiving.addEffect(new MobEffectInstance(MobEffects.POISON, 800, 1));
            pEntityLiving.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0));
        }
            return new ItemStack(Items.GLASS_BOTTLE);
    }


    @Override
    public int getUseDuration(ItemStack pStack) {
        return 40;
    }


    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }


    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }


    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }

}