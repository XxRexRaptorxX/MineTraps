package xxrexraptorxx.minetraps.world;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import xxrexraptorxx.magmacore.utils.FormattingHelper;
import xxrexraptorxx.minetraps.main.References;
import xxrexraptorxx.minetraps.registry.ModBlocks;

import java.util.List;

@EventBusSubscriber(modid = References.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Events {


    @SubscribeEvent
    public static void addingToolTips(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        List<Component> list = event.getToolTip();

        if (BuiltInRegistries.BLOCK.getKey(ModBlocks.GHOST_BLOCK.get()).getPath().equals(BuiltInRegistries.ITEM.getKey(item).getPath())) {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "ghost.desc").withStyle(ChatFormatting.GRAY));

        } else if (BuiltInRegistries.BLOCK.getKey(ModBlocks.PITFALL_TRAP.get()).getPath().equals(BuiltInRegistries.ITEM.getKey(item).getPath())) {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "pitfall.desc").withStyle(ChatFormatting.GRAY));

        } else if (BuiltInRegistries.BLOCK.getKey(ModBlocks.PITFALL_TRAP.get()).getPath().equals(BuiltInRegistries.ITEM.getKey(item).getPath())) {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "spike.desc").withStyle(ChatFormatting.GRAY));

        } else if (BuiltInRegistries.BLOCK.getKey(ModBlocks.PITFALL_TRAP.get()).getPath().equals(BuiltInRegistries.ITEM.getKey(item).getPath())) {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "troll.desc").withStyle(ChatFormatting.GRAY));
        }
    }

}