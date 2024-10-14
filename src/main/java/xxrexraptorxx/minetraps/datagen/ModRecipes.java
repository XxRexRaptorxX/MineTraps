package xxrexraptorxx.minetraps.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import xxrexraptorxx.minetraps.registry.ModBlocks;
import xxrexraptorxx.minetraps.registry.ModItems;

import java.util.function.Consumer;

public class ModRecipes extends FabricRecipeProvider {
    public ModRecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // Nails Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NAILS, 9)
                .pattern("#")
                .pattern("#")
                .input('#', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NAILS)));

        // Barbed Wire Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BARBED_WIRE, 4)
                .pattern("#X#")
                .pattern("X#X")
                .pattern("#X#")
                .input('#', ModItems.NAILS)
                .input('X', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModItems.NAILS), conditionsFromItem(ModItems.NAILS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BARBED_WIRE)));

        // Barbed Wire Fence Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BARBED_WIRE_FENCE, 3)
                .pattern("X#X")
                .pattern("X#X")
                .input('#', ModBlocks.BARBED_WIRE)
                .input('X', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModBlocks.BARBED_WIRE), conditionsFromItem(ModBlocks.BARBED_WIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BARBED_WIRE_FENCE)));

        // Bear Trap Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BEAR_TRAP, 1)
                .pattern("###")
                .pattern("XOX")
                .pattern("###")
                .input('#', ModItems.NAILS)
                .input('X', Items.IRON_INGOT)
                .input('O', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModItems.NAILS), conditionsFromItem(ModItems.NAILS))
                .criterion(hasItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), conditionsFromItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BEAR_TRAP)));

        // Chest Bomb Recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CHEST_BOMB, 1)
                .input(Items.TRAPPED_CHEST)
                .input(Items.TNT)
                .criterion(FabricRecipeProvider.hasItem(Items.TRAPPED_CHEST), FabricRecipeProvider.conditionsFromItem(Items.TRAPPED_CHEST))
                .criterion(FabricRecipeProvider.hasItem(Items.TNT), FabricRecipeProvider.conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CHEST_BOMB)));

        // Explosive Mine Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.EXPLOSIVE_MINE, 1)
                .pattern("#X#")
                .pattern("#O#")
                .input('#', Items.IRON_INGOT)
                .input('X', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .input('O', Items.TNT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), conditionsFromItem(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
                .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.EXPLOSIVE_MINE)));

        // Ghost Block Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GHOST_BLOCK, 1)
                .pattern("X#X")
                .pattern("# #")
                .pattern("X#X")
                .input('#', Items.GLASS)
                .input('X', Items.STICK)
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GHOST_BLOCK)));

        // Ghost Block Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.NAIL_TRAP, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.NAILS)
                .criterion(hasItem(ModItems.NAILS), conditionsFromItem(ModItems.NAILS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.NAIL_TRAP)));

        // Obstacle Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.OBSTACLE, 1)
                .pattern("# #")
                .pattern(" # ")
                .pattern("# #")
                .input('#', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.OBSTACLE)));

        // Pitfall Trap Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PITFALL_TRAP, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PITFALL_TRAP)));

        // Quick sand Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.QUICK_SAND, 4)
                .pattern("#X#")
                .pattern("X#X")
                .pattern("#X#")
                .input('#', Items.SAND)
                .input('X', Items.GRAVEL)
                .criterion(hasItem(Items.SAND), conditionsFromItem(Items.SAND))
                .criterion(hasItem(Items.GRAVEL), conditionsFromItem(Items.GRAVEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.QUICK_SAND)));

        // Razor wire Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAZOR_WIRE, 1)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .input('#', ModBlocks.BARBED_WIRE)
                .criterion(hasItem(ModBlocks.BARBED_WIRE), conditionsFromItem(ModBlocks.BARBED_WIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RAZOR_WIRE)));

        // Spikes Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SPIKES, 1)
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
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SPIKES)));

        // Toxic Spikes Recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TOXIC_SPIKES, 1)
                .input(ModItems.TOXIN_BOTTLE)
                .input(ModBlocks.SPIKES)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TOXIN_BOTTLE), FabricRecipeProvider.conditionsFromItem(ModItems.TOXIN_BOTTLE))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.SPIKES), FabricRecipeProvider.conditionsFromItem(ModBlocks.SPIKES))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TOXIC_SPIKES)));

        // Toxic Mine Recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TOXIC_MINE, 1)
                .input(ModItems.TOXIN_BOTTLE)
                .input(ModBlocks.EXPLOSIVE_MINE)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TOXIN_BOTTLE), FabricRecipeProvider.conditionsFromItem(ModItems.TOXIN_BOTTLE))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.EXPLOSIVE_MINE), FabricRecipeProvider.conditionsFromItem(ModBlocks.EXPLOSIVE_MINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TOXIC_MINE)));

        // Toxic Nail Trap Recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TOXIC_NAIL_TRAP, 1)
                .input(ModItems.TOXIN_BOTTLE)
                .input(ModBlocks.NAIL_TRAP)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TOXIN_BOTTLE), FabricRecipeProvider.conditionsFromItem(ModItems.TOXIN_BOTTLE))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.NAIL_TRAP), FabricRecipeProvider.conditionsFromItem(ModBlocks.NAIL_TRAP))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TOXIC_NAIL_TRAP)));

        // Toxin Bottle Recipe 1
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOXIN_BOTTLE, 1)
                .input(Items.GLASS_BOTTLE)
                .input(Items.ROTTEN_FLESH)
                .input(Items.PUFFERFISH)
                .input(Items.FERMENTED_SPIDER_EYE)
                .criterion(FabricRecipeProvider.hasItem(Items.GLASS_BOTTLE), FabricRecipeProvider.conditionsFromItem(Items.GLASS_BOTTLE))
                .criterion(FabricRecipeProvider.hasItem(Items.ROTTEN_FLESH), FabricRecipeProvider.conditionsFromItem(Items.ROTTEN_FLESH))
                .criterion(FabricRecipeProvider.hasItem(Items.PUFFERFISH), FabricRecipeProvider.conditionsFromItem(Items.PUFFERFISH))
                .criterion(FabricRecipeProvider.hasItem(Items.FERMENTED_SPIDER_EYE), FabricRecipeProvider.conditionsFromItem(Items.FERMENTED_SPIDER_EYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOXIN_BOTTLE)));

        // Toxin Bottle Recipe 2
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOXIN_BOTTLE, 4)
                .input(ModItems.TOXIN_BUCKET)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TOXIN_BUCKET), FabricRecipeProvider.conditionsFromItem(ModItems.TOXIN_BUCKET))
                .criterion(FabricRecipeProvider.hasItem(Items.GLASS_BOTTLE), FabricRecipeProvider.conditionsFromItem(Items.GLASS_BOTTLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOXIN_BOTTLE) + "_alt"));

        // Toxin Bucket Recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOXIN_BUCKET, 1)
                .input(Items.BUCKET)
                .input(Items.ROTTEN_FLESH)
                .input(Items.ROTTEN_FLESH)
                .input(Items.PUFFERFISH)
                .input(Items.PUFFERFISH)
                .input(Items.PUFFERFISH)
                .input(Items.FERMENTED_SPIDER_EYE)
                .input(Items.FERMENTED_SPIDER_EYE)
                .input(Items.FERMENTED_SPIDER_EYE)
                .criterion(FabricRecipeProvider.hasItem(Items.BUCKET), FabricRecipeProvider.conditionsFromItem(Items.BUCKET))
                .criterion(FabricRecipeProvider.hasItem(Items.ROTTEN_FLESH), FabricRecipeProvider.conditionsFromItem(Items.ROTTEN_FLESH))
                .criterion(FabricRecipeProvider.hasItem(Items.PUFFERFISH), FabricRecipeProvider.conditionsFromItem(Items.PUFFERFISH))
                .criterion(FabricRecipeProvider.hasItem(Items.FERMENTED_SPIDER_EYE), FabricRecipeProvider.conditionsFromItem(Items.FERMENTED_SPIDER_EYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOXIN_BUCKET)));

        // Troll Block Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TROLL_BLOCK, 1)
                .pattern("#X#")
                .pattern("XOX")
                .pattern("#X#")
                .input('#', Items.STICK)
                .input('X', Items.STONE_BUTTON)
                .input('O', Items.TNT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.STONE_BUTTON), conditionsFromItem(Items.STONE_BUTTON))
                .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TROLL_BLOCK)));

    }
}
