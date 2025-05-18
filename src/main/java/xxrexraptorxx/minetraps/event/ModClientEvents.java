package xxrexraptorxx.minetraps.event;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector4f;
import xxrexraptorxx.minetraps.fluids.ModFluidTypes;
import xxrexraptorxx.minetraps.main.References;

@EventBusSubscriber(value = Dist.CLIENT, modid = References.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation WATER_STILL = ResourceLocation.withDefaultNamespace("block/water_still");
            private static final ResourceLocation WATER_FLOW = ResourceLocation.withDefaultNamespace("block/water_flow");
            private static final ResourceLocation WATER_OVERLAY = ResourceLocation.withDefaultNamespace("block/toxin_still");

            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return WATER_STILL;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return WATER_FLOW;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return WATER_OVERLAY;
            }

            @Override
            public int getTintColor() {
                return 0x3F7529;
            }

            @Override
            public @NotNull Vector4f modifyFogColor(@NotNull Camera camera, float partialTick, @NotNull ClientLevel level, int renderDistance, float darkenWorldAmount, @NotNull Vector4f fluidFogColor) {
                return new Vector4f(63f / 255f, 117f / 255f, 41f / 255f, 255f / 255f);
            }

            @Override
            public @NotNull FogParameters modifyFogRender(@NotNull Camera camera, FogRenderer.@NotNull FogMode mode, float renderDistance, float partialTick, @NotNull FogParameters fogParameters) {
                return new FogParameters(1.0f, 3.0f, FogShape.CYLINDER, 63f / 255f, 117f / 255f, 41f / 255f, 255f / 255f);
            }

        }, ModFluidTypes.TOXIN_FLUID_TYPE);
    }
}
