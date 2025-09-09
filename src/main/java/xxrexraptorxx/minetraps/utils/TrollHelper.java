package xxrexraptorxx.minetraps.utils;

import java.util.ArrayList;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

public class TrollHelper {

    public static int getStateFromBlock(String inputBlock) {
        return switch (inputBlock) {
            case "minecraft:diamond_ore" -> 1;
            case "minecraft:emerald_ore" -> 2;
            case "minecraft:iron_ore" -> 3;
            case "minecraft:gold_ore" -> 4;
            case "minecraft:deepslate_diamond_ore" -> 5;
            case "minecraft:deepslate_emerald_ore" -> 6;
            case "minecraft:deepslate_gold_ore" -> 7;
            case "minecraft:deepslate_iron_ore" -> 8;
            default -> 0;
        };
    }

    public static ArrayList<Item> getTypeList() {
        ArrayList<Item> types = new ArrayList<>();
        types.add(Blocks.DIAMOND_ORE.asItem());
        types.add(Blocks.EMERALD_ORE.asItem());
        types.add(Blocks.IRON_ORE.asItem());
        types.add(Blocks.GOLD_ORE.asItem());
        types.add(Blocks.DEEPSLATE_DIAMOND_ORE.asItem());
        types.add(Blocks.DEEPSLATE_GOLD_ORE.asItem());
        types.add(Blocks.DEEPSLATE_IRON_ORE.asItem());
        return types;
    }
}
