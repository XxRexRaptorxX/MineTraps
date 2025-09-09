package xxrexraptorxx.minetraps.utils;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.main.References;

public class ModTags {
    public static final TagKey<Fluid> TOXIN = ModTags.createTag("toxin");

    private static TagKey<Fluid> createTag(String name) {
        return TagKey.of(RegistryKeys.FLUID, Identifier.of(References.MODID, name));
    }
}
