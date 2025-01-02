package xxrexraptorxx.minetraps.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ItemToxinBottle extends Item {

    public ItemToxinBottle() {
        super(new Properties()
                .stacksTo(4)
                .craftRemainder(Items.GLASS_BOTTLE)
                .usingConvertsTo(Items.GLASS_BOTTLE)
                .component(DataComponents.CONSUMABLE,
                        Consumable.builder()
                                .consumeSeconds(1.6f)
                                .animation(ItemUseAnimation.DRINK)
                                .sound(SoundEvents.GENERIC_DRINK)
                                .soundAfterConsume(SoundEvents.GENERIC_DRINK)
                                .hasConsumeParticles(true)
                                .onConsume(
                                        new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 800, 1), 1.0F)
                                )
                                .onConsume(
                                        new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 400, 0), 0.95F)
                                )
                                .build()
                )
        );

    }

}