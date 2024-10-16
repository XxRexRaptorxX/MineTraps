package xxrexraptorxx.minetraps.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;


public class ItemToxinBottle extends Item {
    public ItemToxinBottle(Item.Settings settings) {
        super(settings
                .maxCount(4)
                .recipeRemainder(Items.GLASS_BOTTLE));
    }


    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 800, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 1));
        }
        if (user instanceof PlayerEntity playerEntity) {
            if (!playerEntity.isCreative()) {
                stack.decrement(1);
                if (!stack.isEmpty()) {
                    ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                    if (!playerEntity.getInventory().insertStack(itemStack)) {
                        playerEntity.dropItem(itemStack, false);
                    }
                } else {
                    return new ItemStack(Items.GLASS_BOTTLE);
                }
            }
        }
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
