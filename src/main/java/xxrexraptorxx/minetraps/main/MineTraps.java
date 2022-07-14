package xxrexraptorxx.minetraps.main;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.client.extensions.common.IClientBlockExtensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.minetraps.utils.Config;
import xxrexraptorxx.minetraps.utils.ModSetup;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/minetraps
 **/
@Mod(References.MODID)
public class MineTraps {

    public static final Logger LOGGER = LogManager.getLogger();

    public MineTraps() {
        ModSetup.setup();
        ModBlocks.init();
        ModItems.init();
        ModFluids.init();
        Config.init();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        modbus.addListener(this::clientSetup);
    }


    private void clientSetup(final FMLClientSetupEvent event) {
        /**
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BARBED_WIRE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BARBED_WIRE_FENCE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RAZOR_WIRE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OBSTACLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BEAR_TRAP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SPIKES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PITFALL_TRAP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOXIC_SPIKES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.NAIL_TRAP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOXIC_NAIL_TRAP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOXIC_MINE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.EXPLOSIVE_MINE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHEST_BOMB.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GHOST_BLOCK.get(), RenderType.cutoutMipped());

       //ItemBlockRenderTypes.setRenderLayer(ModFluids.TOXIN.get(), RenderType.translucent());
       //ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_TOXIN.get(), RenderType.translucent());
        **/
         }
}