package xxrexraptorxx.minetraps.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.minetraps.items.ItemToxinBottle;
import xxrexraptorxx.minetraps.main.References;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(References.MODID);

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredItem<Item> NAILS = ITEMS.register("nails", () -> new Item(new Item.Properties().setId(itemId("nails"))));
    public static final DeferredItem<ItemToxinBottle> TOXIN_BOTTLE = ITEMS.register("toxin_bottle",
            () -> new ItemToxinBottle(new Item.Properties().food(ItemToxinBottle.TOXIN_BOTTLE_FOOD, ItemToxinBottle.TOXIN_BOTTLE_CONSUMABLE).setId(itemId("toxin_bottle"))));
    public static final DeferredItem<BucketItem> TOXIN_BUCKET = ITEMS.register("toxin_bucket",
            () -> new BucketItem(ModFluids.TOXIN.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).setId(itemId("toxin_bucket"))));

    public static ResourceKey<Item> itemId(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(References.MODID, name));
    }
}
