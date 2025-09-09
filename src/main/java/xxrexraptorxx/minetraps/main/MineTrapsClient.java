package xxrexraptorxx.minetraps.main;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.registry.ModFluids;
import xxrexraptorxx.minetraps.utils.Config;

public class MineTrapsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Config.initClient();

        BlockRenderLayerMap.putBlock(ModBlocks.BARBED_WIRE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BARBED_WIRE_FENCE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.RAZOR_WIRE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BEAR_TRAP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.EXPLOSIVE_MINE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TOXIC_MINE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.OBSTACLE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.NAIL_TRAP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TOXIC_NAIL_TRAP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SPIKES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TOXIC_SPIKES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PITFALL_TRAP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GHOST_BLOCK, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putFluids(BlockRenderLayer.TRANSLUCENT, ModFluids.TOXIN, ModFluids.FLOWING_TOXIN);

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.TOXIN,
                ModFluids.FLOWING_TOXIN,
                new SimpleFluidRenderHandler(
                        Identifier.of("minetraps:block/toxin_still"),
                        Identifier.of("minetraps:block/toxin_flow"),
                        0x3F7529));
    }
}
