package xxrexraptorxx.minetraps.main;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.items.ItemBasic;
import xxrexraptorxx.minetraps.items.ItemToxinBottle;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MODID);


    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ItemBasic> NAILS = ITEMS.register("nails", ItemBasic::new);
    public static final RegistryObject<ItemToxinBottle> TOXIN_BOTTLE = ITEMS.register("toxin_bottle", ItemToxinBottle::new);
    public static final RegistryObject<BucketItem> TOXIN_BUCKET = ITEMS.register("toxin_bucket", () -> new BucketItem(ModFluids.TOXIN, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

}