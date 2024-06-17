package xxrexraptorxx.minetraps.utils;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

import java.util.ArrayList;


public class TrapHelper {

    public static int getStateFromBlock(String inputBlock) {
        return switch (inputBlock) {
            case "minecraft:dirt" -> 1;
            case "minecraft:stone" -> 2;
            case "minecraft:oak_planks" -> 3;
            case "minecraft:sand" -> 4;
            case "minecraft:cobblestone" -> 5;
            case "minecraft:stone_bricks" -> 6;
            default -> 0;
        };
    }


    public static ArrayList<Item> getTypeList() {
        ArrayList<Item> types = new ArrayList<>();
            types.add(Blocks.DIRT.asItem());
            types.add(Blocks.STONE.asItem());
            types.add(Blocks.COBBLESTONE.asItem());
            types.add(Blocks.SAND.asItem());
            types.add(Blocks.STONE_BRICKS.asItem());
            types.add(Blocks.OAK_PLANKS.asItem());
        return types;
    }
}
