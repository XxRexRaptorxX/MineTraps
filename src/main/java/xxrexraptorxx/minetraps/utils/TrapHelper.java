package xxrexraptorxx.minetraps.utils;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

import java.util.ArrayList;


public class TrapHelper {

    public static int getStateFromBlock(String inputBlock) {
        switch (inputBlock) {
            case "minecraft:dirt": 			return 1;
            case "minecraft:stone": 		return 2;
            case "minecraft:oak_planks": 	return 3;
            case "minecraft:sand": 			return 4;
            case "minecraft:cobblestone": 	return 5;
            case "minecraft:stone_bricks": 	return 6;
            default:		        		return 0;
        }
    }


    public static ArrayList<Item> getTypeList() {
        ArrayList<Item> types = new ArrayList<Item>();
            types.add(Blocks.DIRT.asItem());
            types.add(Blocks.STONE.asItem());
            types.add(Blocks.COBBLESTONE.asItem());
            types.add(Blocks.SAND.asItem());
            types.add(Blocks.STONE_BRICKS.asItem());
            types.add(Blocks.OAK_PLANKS.asItem());
        return types;
    }
}
