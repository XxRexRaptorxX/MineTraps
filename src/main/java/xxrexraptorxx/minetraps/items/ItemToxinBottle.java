package xxrexraptorxx.minetraps.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ItemToxinBottle extends Item {

    public ItemToxinBottle(Properties properties) {
        super(properties
                .stacksTo(4)
                .craftRemainder(Items.GLASS_BOTTLE));
    }

    public static final FoodProperties TOXIN_BOTTLE_FOOD = new FoodProperties.Builder().build();

    public static final Consumable TOXIN_BOTTLE_CONSUMABLE = Consumable.builder()
            .consumeSeconds(1.6F)
            .animation(ItemUseAnimation.DRINK)
            .sound(SoundEvents.GENERIC_DRINK)
            .soundAfterConsume(SoundEvents.GENERIC_DRINK)
            .hasConsumeParticles(false)
            .build();

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide()) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 800, 1));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 400, 1));
        }

        // Note: stack decrement is done by super.finishUsing
        super.finishUsingItem(stack, level, livingEntity);

        if (livingEntity instanceof Player playerEntity) {
            if (!playerEntity.isCreative()) {
                if (!stack.isEmpty()) {
                    ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                    if (!playerEntity.getInventory().add(itemStack)) {
                        playerEntity.drop(itemStack, false);
                    }
                } else {
                    return new ItemStack(Items.GLASS_BOTTLE);
                }
            }
        }
        return stack;
    }


    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity user) {
        return TOXIN_BOTTLE_CONSUMABLE.consumeTicks();
    }


    @Override
    public @NotNull ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
        return ItemUseAnimation.DRINK;
    }


    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
}
