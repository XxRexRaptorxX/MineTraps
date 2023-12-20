package xxrexraptorxx.minetraps.registry;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.minetraps.items.ItemBasic;
import xxrexraptorxx.minetraps.items.ItemToxinBottle;
import xxrexraptorxx.minetraps.main.References;

public class ModItems {

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(References.MODID);


    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final DeferredItem<ItemBasic> NAILS = ITEMS.register("nails", ItemBasic::new);
    public static final DeferredItem<ItemToxinBottle> TOXIN_BOTTLE = ITEMS.register("toxin_bottle", ItemToxinBottle::new);
    public static final DeferredItem<BucketItem> TOXIN_BUCKET = ITEMS.register("toxin_bucket", () -> new BucketItem(ModFluids.TOXIN, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

}