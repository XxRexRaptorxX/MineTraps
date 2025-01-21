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
import org.jetbrains.annotations.NotNull;

public class ItemToxinBottle extends Item {

    public ItemToxinBottle() {
        super(new Properties()
                .stacksTo(4)
                .craftRemainder(Items.GLASS_BOTTLE)
        );

    }


    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (pEntityLiving instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, pStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!pLevel.isClientSide) {
            pEntityLiving.addEffect(new MobEffectInstance(MobEffects.POISON, 800, 1));
            pEntityLiving.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0));
        }

        if (pEntityLiving instanceof Player playerEntity) {
            if (!playerEntity.isCreative()) {
                pStack.shrink(1);
                if (!pStack.isEmpty()) {
                    ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                    if (!playerEntity.getInventory().add(itemStack)) {
                        playerEntity.drop(itemStack, false);
                    }
                } else {
                    return new ItemStack(Items.GLASS_BOTTLE);
                }
            }
        }
        return pStack;
    }


    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

}