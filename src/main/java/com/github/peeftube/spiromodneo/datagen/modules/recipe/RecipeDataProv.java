package com.github.peeftube.spiromodneo.datagen.modules.recipe;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.content.recipe.ManualCrusherRecipeBuilder;
import com.github.peeftube.spiromodneo.core.init.registry.data.EquipmentCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.EquipmentMaterial;
import com.github.peeftube.spiromodneo.core.init.registry.data.MetalCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodneo.util.RLUtility;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import com.github.peeftube.spiromodneo.util.equipment.EquipmentData;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
        // Equipment crafting handler
        for (EquipmentCollection equip : EquipmentCollection.EQUIP_COLLECTIONS)
        { equipmentCraftingHandler(equip, output); }

        // Metal crafting handler
        for (MetalCollection metal : MetalCollection.METAL_COLLECTIONS) { metalCraftingHandler(metal, output); }

        // Alloying handlers
        steelAlloyHandler(output);

        // Automatic ore smelting handler, this will later handle ore-to-ingot conversions when that's implemented
        // NOTE: The ore-to-ingot conversion mentioned is not block-to-ingot smelting, but item-to-ingot, which will
        //       need to be handled later when new non-gems are added
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreSmeltingHandler(ore, output); }

        // Additional / Other / Loose
        stringLikeHandler(output);
        manualCrusherCraftingHandler(output);
        tapperRecipe(output);
    }

    private void tapperRecipe(RecipeOutput consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registrar.TAPPER)
               .pattern("ICI")
               .pattern(" B ")
               .define('I', Items.IRON_INGOT)
               .define('C', Items.COPPER_INGOT)
               .define('B', Items.BUCKET)
               .unlockedBy("has_a_bucket", has(Items.BUCKET))
               .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_tapper_crafting"));
    }

    private void manualCrusherCraftingHandler(RecipeOutput consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registrar.MANUAL_CRUSHER)
               .pattern("LPI")
               .pattern("I I")
               .pattern("SSS")
               .define('L', Items.LEVER)
               .define('P', Items.PISTON)
               .define('I', Items.IRON_INGOT) // TODO: Replace with iron rod
               .define('S', Items.SMOOTH_STONE)
               .unlockedBy("has_iron", has(Items.IRON_INGOT))
               .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_manual_crusher_crafting"));

        ManualCrusherRecipeBuilder.crush(Ingredient.of(ItemTags.COALS),
                RecipeCategory.MISC, Registrar.CRUSHED_CARBON.get(), 2, 1.0f)
                .unlockedBy("has_coal", has(ItemTags.COALS))
                .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_crush_carbon_in_manual_crusher"));
    }

    /** As steel is technically an alloy, but the presence of iron in vanilla MC is so pervasive,
     * this special handler method is being added to deal with cases like smelting from iron ore, etc. */
    private void steelAlloyHandler(RecipeOutput consumer)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registrar.CAST_IRON_MIXTURE, 4)
                              .requires(Ingredient.of(ItemTags.COALS))
                              .requires(Ingredient.of(Items.IRON_INGOT), 4)
                              .unlockedBy("has_iron", has(Items.IRON_INGOT))
                              .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_cast_iron_mixing"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registrar.STEEL_MIXTURE, 4)
                              .requires(Ingredient.of(Registrar.CRUSHED_CARBON))
                              .requires(Ingredient.of(Items.IRON_INGOT), 4)
                              .unlockedBy("has_carbon", has(Registrar.CRUSHED_CARBON))
                              .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_better_steel_mixing"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registrar.WEAK_STEEL_MIXTURE, 7)
                              .requires(Ingredient.of(Registrar.CRUSHED_CARBON))
                              .requires(Ingredient.of(Registrar.CAST_IRON), 6)
                              .unlockedBy("has_cast_iron", has(Registrar.CAST_IRON))
                              .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_weaker_steel_mixing"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registrar.CRUSHED_CARBON, 3)
                              .requires(Ingredient.of(ItemTags.COALS), 2)
                              .requires(Ingredient.of(Items.BRICK))
                              .unlockedBy("has_coal", has(ItemTags.COALS))
                              .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_weak_carbon_crushing"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registrar.CAST_IRON_MIXTURE), RecipeCategory.MISC,
                Registrar.CAST_IRON, 0.5f, 1600)
                .unlockedBy("has_cast_iron_mix", has(Registrar.CAST_IRON_MIXTURE))
                .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_smelt_cast_iron_from_mix"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Registrar.CAST_IRON_MIXTURE), RecipeCategory.MISC,
                Registrar.CAST_IRON, 1.0f, 800)
                .unlockedBy("has_cast_iron_mix", has(Registrar.CAST_IRON_MIXTURE))
                .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_blast_cast_iron_from_mix"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(SpiroTags.Items.STEEL_MIXTURES), RecipeCategory.MISC,
                Registrar.STEEL_METAL.ingotData().getIngot().get(), 4.0f, 3200)
                .unlockedBy("has_steel_mix", has(SpiroTags.Items.STEEL_MIXTURES))
                .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_blast_steel_from_mix"));
    }

    private void equipmentCraftingHandler(EquipmentCollection set, RecipeOutput consumer)
    {
        boolean isStock = false;

        EquipmentMaterial material = set.getMat();

        if (material == EquipmentMaterial.CHAIN || material == EquipmentMaterial.WOOD ||
                material == EquipmentMaterial.STONE || material == EquipmentMaterial.IRON ||
                material == EquipmentMaterial.LEATHER || material == EquipmentMaterial.GOLD ||
                material == EquipmentMaterial.DIAMOND || material == EquipmentMaterial.NETHERITE )
        { isStock = true; }

        if (!isStock)
        {
            String mat = material.getName();
            EquipmentData bulkData = set.bulkData();

            if (set.checkIfNullThenPass(bulkData.getTools()).getResult())
            {
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bulkData.getTools().getSword().get())
                                   .pattern("  I")
                                   .pattern(" I ")
                                   .pattern("/  ")
                                   .define('/', Items.STICK)
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_sword_crafting"));
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, bulkData.getTools().getShovel().get())
                                   .pattern("I")
                                   .pattern("/")
                                   .pattern("/")
                                   .define('/', Items.STICK)
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_shovel_crafting"));
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, bulkData.getTools().getHoe().get())
                                   .pattern("II")
                                   .pattern(" /")
                                   .pattern(" /")
                                   .define('/', Items.STICK)
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_hoe_crafting"));
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, bulkData.getTools().getAxe().get())
                                   .pattern("II")
                                   .pattern("I/")
                                   .pattern(" /")
                                   .define('/', Items.STICK)
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_axe_crafting"));
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, bulkData.getTools().getPickaxe().get())
                                   .pattern("III")
                                   .pattern(" / ")
                                   .pattern(" / ")
                                   .define('/', Items.STICK)
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_pickaxe_crafting"));
            }

            if (set.checkIfNullThenPass(bulkData.getArmor()).getResult())
            {
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bulkData.getArmor().getHelmet().get())
                                   .pattern("III")
                                   .pattern("I I")
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_helmet_crafting"));
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bulkData.getArmor().getChestplate().get())
                                   .pattern("I I")
                                   .pattern("III")
                                   .pattern("III")
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_chestplate_crafting"));
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bulkData.getArmor().getLeggings().get())
                                   .pattern("III")
                                   .pattern("I I")
                                   .pattern("I I")
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_leggings_crafting"));
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bulkData.getArmor().getBoots().get())
                                   .pattern("I I")
                                   .pattern("I I")
                                   .define('I', Ingredient.of(material.getAssociatedTag()))
                                   .unlockedBy("has_material", has(material.getAssociatedTag()))
                                   .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_" + mat + "_boots_crafting"));
            }

            if (set.checkIfNullThenPass(bulkData.getHorseArmor()).getResult())
            {
                // This is potentially a special case, so I'm holding off on putting anything here for now
            }
        }
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

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(tag), RecipeCategory.MISC, output, 1.0f, 100)
                .unlockedBy("has_packed_" + mat + "_ore", has(tag))
                .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_blast_" + mat + "_from_ore_block"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(rawOre), RecipeCategory.MISC, output, 1.0f, 100)
              .unlockedBy("has_" + mat + "_ore", has(rawOre))
              .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_blast_" + mat + "_from_ore"));

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

    private void stringLikeHandler(RecipeOutput consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.BOW)
                           .pattern(" /S")
                           .pattern("/ S")
                           .pattern(" /S")
                           .define('/', Items.STICK)
                           .define('S', SpiroTags.Items.STRING_LIKE)
                           .unlockedBy("has_string_like", has(SpiroTags.Items.STRING_LIKE))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_wooden_bow_crafting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.BUNDLE)
                           .pattern("S")
                           .pattern("L")
                           .define('S', SpiroTags.Items.STRING_LIKE)
                           .define('L', Items.LEATHER)
                           .unlockedBy("has_string_like", has(SpiroTags.Items.STRING_LIKE))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_bundle_crafting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.CROSSBOW)
                           .pattern("/I/")
                           .pattern("SXS")
                           .pattern(" / ")
                           .define('/', Items.STICK)
                           .define('X', Items.TRIPWIRE_HOOK)
                           .define('I', Items.IRON_INGOT)
                           .define('S', SpiroTags.Items.STRING_LIKE)
                           .unlockedBy("has_string_like", has(SpiroTags.Items.STRING_LIKE))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_crossbow_crafting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.FISHING_ROD)
                           .pattern("  /")
                           .pattern(" /S")
                           .pattern("/ S")
                           .define('/', Items.STICK)
                           .define('S', SpiroTags.Items.STRING_LIKE)
                           .unlockedBy("has_string_like", has(SpiroTags.Items.STRING_LIKE))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_fishing_rod_crafting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.LEAD, 2)
                           .pattern("SS ")
                           .pattern("SS ")
                           .pattern("  S")
                           .define('S', SpiroTags.Items.STRING_LIKE)
                           .unlockedBy("has_string_like", has(SpiroTags.Items.STRING_LIKE))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_leash_crafting")); // leash instead of lead to avoid confusion

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.LOOM)
                           .pattern("SS")
                           .pattern("XX")
                           .define('X', Ingredient.of(ItemTags.PLANKS))
                           .define('S', SpiroTags.Items.STRING_LIKE)
                           .unlockedBy("has_string_like", has(SpiroTags.Items.STRING_LIKE))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_loom_crafting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.SCAFFOLDING, 6)
                           .pattern("XSX")
                           .pattern("X X")
                           .pattern("X X")
                           .define('X', Items.BAMBOO)
                           .define('S', SpiroTags.Items.STRING_LIKE)
                           .unlockedBy("has_string_like", has(SpiroTags.Items.STRING_LIKE))
                           .save(consumer, RLUtility.makeRL(SpiroMod.MOD_ID, "spiro_scaffold_crafting"));
    }
}
