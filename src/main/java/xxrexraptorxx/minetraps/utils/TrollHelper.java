package xxrexraptorxx.minetraps.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;

public class TrollHelper {


    public static int getStateFromBlock(String inputBlock) {
        switch (inputBlock) {
            case "minecraft:diamond_ore": 		    	return 1;
            case "minecraft:emerald_ore": 	    	    return 2;
            case "minecraft:iron_ore":              	return 3;
            case "minecraft:gold_ore": 			        return 4;
            case "minecraft:deepslate_diamond_ore": 	return 5;
            case "minecraft:deepslate_emerald_ore": 	return 6;
            case "minecraft:deepslate_gold_ore": 	    return 7;
            case "minecraft:deepslate_iron_ore": 	    return 8;
            default:		        		            return 0;
        }
    }


    public static ArrayList<Item> getTypeList() {
        ArrayList<Item> types = new ArrayList<Item>();
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
