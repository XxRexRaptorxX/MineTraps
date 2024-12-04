package xxrexraptorxx.minetraps.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


public class ItemToxinBottle extends Item {
    public ItemToxinBottle(Item.Settings settings) {
        super(settings
                .maxCount(4)
                .recipeRemainder(Items.GLASS_BOTTLE));
    }

    public static final FoodComponent TOXIN_BOTTLE_FOOD = new FoodComponent.Builder().build();
    public static final ConsumableComponent TOXIN_BOTTLE_CONSUMABLE = ConsumableComponent.builder()
            .consumeSeconds(2.0F)
            .useAction(UseAction.DRINK)
            .sound(SoundEvents.ENTITY_GENERIC_DRINK)
            .consumeParticles(false).build();


    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 800, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 1));
        }

        // Note: stack decrement is done by super.finishUsing
        super.finishUsing(stack, world, user);

        if (user instanceof PlayerEntity playerEntity) {
            if (!playerEntity.isInCreativeMode()) {
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
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
