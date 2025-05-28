package com.github.peeftube.spiromodneo.datagen.modules.recipe;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.MetalCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodneo.util.RLUtility;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class RecipeDataProv extends RecipeProvider implements IConditionBuilder
{
    public RecipeDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    { super(output, lookup); }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        // Metal crafting handler
        for (MetalCollection metal : MetalCollection.METAL_COLLECTIONS) { metalCraftingHandler(metal, output); }

        // Automatic ore smelting handler, this will later handle ore-to-ingot conversions when that's implemented
        // NOTE: The ore-to-ingot conversion mentioned is not block-to-ingot smelting, but item-to-ingot, which will
        //       need to be handled later when new non-gems are added
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreSmeltingHandler(ore, output); }
    }

    private void metalCraftingHandler(MetalCollection set, RecipeOutput consumer)
    {
        String mat = set.getMat().get();

        Item  ingot    = set.ingotData().getIngot().get();
        Block block = set.ingotData().getMetal().getBlock().get();

        // Item-to-block crafting (ingots only) and vice versa
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                           .pattern("III")
                           .pattern("III")
                           .pattern("III")
                           .define('I', ingot)
                           .unlockedBy("has_" + mat + "_ore", has(ingot))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_block_from_ingots"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                              .requires(Ingredient.of(block))
                              .unlockedBy("has_" + mat + "_ore", has(ingot))
                              .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_ingots_from_block"));
    }

    private void oreSmeltingHandler(OreCollection set, RecipeOutput consumer)
    {
        TagKey<Item> tag = set.getOreIT();
        String mat = set.getMat().get();

        Item  rawOre    = set.rawOreCoupling().getRawItem().get();
        Block packedOre = set.rawOreCoupling().getCoupling().getBlock().get();

        Item output = ( set.getMat().getIngotConvertible() != null ) ? set.getMat().getIngotConvertible().get() :
                set.getRawOre().getRawItem().get();

        // TODO: Add data to OreCollection which handles the experience and cooking time; should probably be enumerated
        // TODO: There should eventually be an advanced smelter which can output multiple ores per raw ore/ore block
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(tag), RecipeCategory.MISC, output, 1.0f, 200)
                .unlockedBy("has_packed_" + mat + "_ore", has(tag))
                .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_smelt_" + mat + "_from_ore_block"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(rawOre), RecipeCategory.MISC, output, 1.0f, 200)
              .unlockedBy("has_" + mat + "_ore", has(rawOre))
              .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_smelt_" + mat + "_from_ore"));

        // Item-to-block crafting (raw ore only) and vice versa; ONLY DO THIS FOR NON-GEMS
        if (!set.material().isGem())
        {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, packedOre)
                               .pattern("RRR")
                               .pattern("RRR")
                               .pattern("RRR")
                               .define('R', rawOre)
                               .unlockedBy("has_" + mat + "_ore", has(rawOre))
                               .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_packed_" + mat + "_ore_from_ore"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, rawOre, 9)
                                  .requires(Ingredient.of(packedOre))
                                  .unlockedBy("has_" + mat + "_ore", has(rawOre))
                                  .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_ore_from_packed_ore"));
        }
    }
}
