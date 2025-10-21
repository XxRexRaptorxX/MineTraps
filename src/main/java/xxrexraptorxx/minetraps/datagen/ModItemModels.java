package xxrexraptorxx.minetraps.datagen;
/*
 * import net.minecraft.client.data.models.ItemModelGenerators; import net.minecraft.client.data.models.ItemModelOutput; import
 * net.minecraft.client.data.models.model.ModelInstance; import net.minecraft.client.renderer.block.model.ItemModelGenerator; import net.minecraft.data.PackOutput; import
 * net.minecraft.resources.ResourceLocation; import net.neoforged.neoforge.client.model.generators.ItemModelProvider; import net.neoforged.neoforge.common.data.ExistingFileHelper;
 * import net.neoforged.neoforge.registries.DeferredBlock; import net.neoforged.neoforge.registries.DeferredItem; import xxrexraptorxx.minetraps.main.References; import
 * xxrexraptorxx.minetraps.registry.ModBlocks; import xxrexraptorxx.minetraps.registry.ModItems;
 *
 * import java.util.function.BiConsumer;
 *
 * public class ModItemModels extends ItemModelGenerators {
 *
 * public ModItemModels(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) { super(packOutput, References.MODID); }
 *
 * @Override protected void registerModels() { itemGenerated(ModItems.NAILS); itemGenerated(ModItems.TOXIN_BOTTLE); itemGenerated(ModItems.TOXIN_BUCKET);
 *
 * itemBlock(ModBlocks.BARBED_WIRE); itemBlock(ModBlocks.RAZOR_WIRE); itemBlock(ModBlocks.BEAR_TRAP); itemBlock(ModBlocks.CHEST_BOMB); itemBlock(ModBlocks.EXPLOSIVE_MINE);
 * itemBlock(ModBlocks.TOXIC_MINE); itemBlock(ModBlocks.QUICK_SAND); itemBlock(ModBlocks.OBSTACLE); itemBlock(ModBlocks.NAIL_TRAP); itemBlock(ModBlocks.TOXIC_NAIL_TRAP);
 * itemBlock(ModBlocks.SPIKES); itemBlock(ModBlocks.TOXIC_SPIKES); itemBlock(ModBlocks.PITFALL_TRAP); itemBlock(ModBlocks.TROLL_BLOCK); itemBlock(ModBlocks.GHOST_BLOCK); }
 *
 *
 *
 * private void itemGenerated(DeferredItem item) { singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"),"layer0",
 * ResourceLocation.fromNamespaceAndPath(References.MODID, "item/" + item.getId().toString().substring(References.MODID.length() + 1))); }
 *
 *
 * private void itemHandheld(DeferredItem item) { singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/handheld"),"layer0",
 * ResourceLocation.fromNamespaceAndPath(References.MODID, "item/" + item.getId().toString().substring(References.MODID.length() + 1))); }
 *
 *
 * private void itemBlock(DeferredBlock item) { withExistingParent(item.getId().getPath(), ResourceLocation.fromNamespaceAndPath(References.MODID, "block/" +
 * item.getId().toString().substring(References.MODID.length() + 1))); }
 *
 * }
 **/
