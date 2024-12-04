package xxrexraptorxx.minetraps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipes extends FabricRecipeProvider {


    public ModRecipes(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                // Nails Recipe
                createShaped(RecipeCategory.MISC, ModItems.NAILS, 9)
                        .pattern("#")
                        .pattern("#")
                        .input('#', Items.IRON_INGOT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.NAILS))));

                // Barbed Wire Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.BARBED_WIRE, 4)
                        .pattern("#X#")
                        .pattern("X#X")
                        .pattern("#X#")
                        .input('#', ModItems.NAILS)
                        .input('X', Items.IRON_INGOT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(ModItems.NAILS), conditionsFromItem(ModItems.NAILS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.BARBED_WIRE))));

                // Barbed Wire Fence Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.BARBED_WIRE_FENCE, 3)
                        .pattern("X#X")
                        .pattern("X#X")
                        .input('#', ModBlocks.BARBED_WIRE)
                        .input('X', Items.IRON_INGOT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(ModBlocks.BARBED_WIRE), conditionsFromItem(ModBlocks.BARBED_WIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.BARBED_WIRE_FENCE))));

                // Bear Trap Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.BEAR_TRAP, 1)
                        .pattern("###")
                        .pattern("XOX")
                        .pattern("###")
                        .input('#', ModItems.NAILS)
                        .input('X', Items.IRON_INGOT)
                        .input('O', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(ModItems.NAILS), conditionsFromItem(ModItems.NAILS))
                        .criterion(hasItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), conditionsFromItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.BEAR_TRAP))));

                // Chest Bomb Recipe
                createShapeless(RecipeCategory.MISC, ModBlocks.CHEST_BOMB, 1)
                        .input(Items.TRAPPED_CHEST)
                        .input(Items.TNT)
                        .criterion(hasItem(Items.TRAPPED_CHEST), conditionsFromItem(Items.TRAPPED_CHEST))
                        .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.CHEST_BOMB))));

                // Explosive Mine Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.EXPLOSIVE_MINE, 1)
                        .pattern("#X#")
                        .pattern("#O#")
                        .input('#', Items.IRON_INGOT)
                        .input('X', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                        .input('O', Items.TNT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), conditionsFromItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
                        .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.EXPLOSIVE_MINE))));

                // Ghost Block Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.GHOST_BLOCK, 1)
                        .pattern("X#X")
                        .pattern("# #")
                        .pattern("X#X")
                        .input('#', Items.GLASS)
                        .input('X', Items.STICK)
                        .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.GHOST_BLOCK))));

                // Ghost Block Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.NAIL_TRAP, 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .input('#', ModItems.NAILS)
                        .criterion(hasItem(ModItems.NAILS), conditionsFromItem(ModItems.NAILS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.NAIL_TRAP))));

                // Obstacle Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.OBSTACLE, 1)
                        .pattern("# #")
                        .pattern(" # ")
                        .pattern("# #")
                        .input('#', Items.IRON_INGOT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.OBSTACLE))));

                // Pitfall Trap Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.PITFALL_TRAP, 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .input('#', Items.STICK)
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.PITFALL_TRAP))));

                // Quick sand Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.QUICK_SAND, 4)
                        .pattern("#X#")
                        .pattern("X#X")
                        .pattern("#X#")
                        .input('#', Items.SAND)
                        .input('X', Items.GRAVEL)
                        .criterion(hasItem(Items.SAND), conditionsFromItem(Items.SAND))
                        .criterion(hasItem(Items.GRAVEL), conditionsFromItem(Items.GRAVEL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.QUICK_SAND))));

                // Razor wire Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.RAZOR_WIRE, 1)
                        .pattern(" # ")
                        .pattern("# #")
                        .pattern(" # ")
                        .input('#', ModBlocks.BARBED_WIRE)
                        .criterion(hasItem(ModBlocks.BARBED_WIRE), conditionsFromItem(ModBlocks.BARBED_WIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.RAZOR_WIRE))));

                // Spikes Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.SPIKES, 1)
                        .pattern("XXX")
                        .pattern("IOI")
                        .pattern("###")
                        .input('#', Items.IRON_INGOT)
                        .input('X', ModItems.NAILS)
                        .input('O', Items.PISTON)
                        .input('I', Items.REDSTONE)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(ModItems.NAILS), conditionsFromItem(ModItems.NAILS))
                        .criterion(hasItem(Items.PISTON), conditionsFromItem(Items.PISTON))
                        .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.SPIKES))));

                // Toxic Spikes Recipe
                createShapeless(RecipeCategory.MISC, ModBlocks.TOXIC_SPIKES, 1)
                        .input(ModItems.TOXIN_BOTTLE)
                        .input(ModBlocks.SPIKES)
                        .criterion(hasItem(ModItems.TOXIN_BOTTLE), conditionsFromItem(ModItems.TOXIN_BOTTLE))
                        .criterion(hasItem(ModBlocks.SPIKES), conditionsFromItem(ModBlocks.SPIKES))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.TOXIC_SPIKES))));

                // Toxic Mine Recipe
                createShapeless(RecipeCategory.MISC, ModBlocks.TOXIC_MINE, 1)
                        .input(ModItems.TOXIN_BOTTLE)
                        .input(ModBlocks.EXPLOSIVE_MINE)
                        .criterion(hasItem(ModItems.TOXIN_BOTTLE), conditionsFromItem(ModItems.TOXIN_BOTTLE))
                        .criterion(hasItem(ModBlocks.EXPLOSIVE_MINE), conditionsFromItem(ModBlocks.EXPLOSIVE_MINE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.TOXIC_MINE))));

                // Toxic Nail Trap Recipe
                createShapeless(RecipeCategory.MISC, ModBlocks.TOXIC_NAIL_TRAP, 1)
                        .input(ModItems.TOXIN_BOTTLE)
                        .input(ModBlocks.NAIL_TRAP)
                        .criterion(hasItem(ModItems.TOXIN_BOTTLE), conditionsFromItem(ModItems.TOXIN_BOTTLE))
                        .criterion(hasItem(ModBlocks.NAIL_TRAP), conditionsFromItem(ModBlocks.NAIL_TRAP))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.TOXIC_NAIL_TRAP))));

                // Toxin Bottle Recipe 1
                createShapeless(RecipeCategory.MISC, ModItems.TOXIN_BOTTLE, 1)
                        .input(Items.GLASS_BOTTLE)
                        .input(Items.ROTTEN_FLESH)
                        .input(Items.PUFFERFISH)
                        .input(Items.FERMENTED_SPIDER_EYE)
                        .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                        .criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH))
                        .criterion(hasItem(Items.PUFFERFISH), conditionsFromItem(Items.PUFFERFISH))
                        .criterion(hasItem(Items.FERMENTED_SPIDER_EYE), conditionsFromItem(Items.FERMENTED_SPIDER_EYE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.TOXIN_BOTTLE))));

                // Toxin Bottle Recipe 2
                createShapeless(RecipeCategory.MISC, ModItems.TOXIN_BOTTLE, 4)
                        .input(ModItems.TOXIN_BUCKET)
                        .input(Items.GLASS_BOTTLE)
                        .input(Items.GLASS_BOTTLE)
                        .input(Items.GLASS_BOTTLE)
                        .input(Items.GLASS_BOTTLE)
                        .criterion(hasItem(ModItems.TOXIN_BUCKET), conditionsFromItem(ModItems.TOXIN_BUCKET))
                        .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.TOXIN_BOTTLE) + "_alt")));

                // Toxin Bucket Recipe
                createShapeless(RecipeCategory.MISC, ModItems.TOXIN_BUCKET, 1)
                        .input(Items.BUCKET)
                        .input(Items.ROTTEN_FLESH)
                        .input(Items.ROTTEN_FLESH)
                        .input(Items.PUFFERFISH)
                        .input(Items.PUFFERFISH)
                        .input(Items.PUFFERFISH)
                        .input(Items.FERMENTED_SPIDER_EYE)
                        .input(Items.FERMENTED_SPIDER_EYE)
                        .input(Items.FERMENTED_SPIDER_EYE)
                        .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                        .criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH))
                        .criterion(hasItem(Items.PUFFERFISH), conditionsFromItem(Items.PUFFERFISH))
                        .criterion(hasItem(Items.FERMENTED_SPIDER_EYE), conditionsFromItem(Items.FERMENTED_SPIDER_EYE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.TOXIN_BUCKET))));

                // Troll Block Recipe
                createShaped(RecipeCategory.MISC, ModBlocks.TROLL_BLOCK, 1)
                        .pattern("#X#")
                        .pattern("XOX")
                        .pattern("#X#")
                        .input('#', Items.STICK)
                        .input('X', Items.STONE_BUTTON)
                        .input('O', Items.TNT)
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .criterion(hasItem(Items.STONE_BUTTON), conditionsFromItem(Items.STONE_BUTTON))
                        .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.TROLL_BLOCK))));
            }
        };
    }

    @Override
    public String getName() {
        return "MineTraps Recipes";
    }
}
