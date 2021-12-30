package xxrexraptorxx.minetraps.main;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.minetraps.items.ItemBasic;
import xxrexraptorxx.minetraps.items.ItemToxinBottle;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MODID);


    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
    }

    public static final RegistryObject<ItemBasic> NAILS = ITEMS.register("nails", ItemBasic::new);
    public static final RegistryObject<ItemToxinBottle> TOXIN_BOTTLE = ITEMS.register("toxin_bottle", ItemToxinBottle::new);

}