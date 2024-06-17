package xxrexraptorxx.minetraps.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.items.ItemToxinBottle;
import xxrexraptorxx.minetraps.main.MineTraps;
import xxrexraptorxx.minetraps.main.References;

public class ModItems {
    public static final Item NAILS = registerItem("nails", new Item(new FabricItemSettings()));
    public static final Item TOXIN_BUCKET = registerItem("toxin_bucket", new BucketItem(ModFluids.TOXIN, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item TOXIN_BOTTLE = registerItem("toxin_bottle", new ItemToxinBottle(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(References.MODID, name), item);
    }

    public static void registerModItems() {
        MineTraps.LOGGER.info("Registering ModItems for " + References.MODID);
    }
}
