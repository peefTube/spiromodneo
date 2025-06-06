package com.github.peeftube.spiromodneo.core.init.content.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class ManualCrusherRecipeBuilder implements RecipeBuilder
{
    private final RecipeCategory            category;
    private final ItemStack                 result;
    private final int                       count;
    private final Ingredient                ingredient;
    private final float                     experience;
    private final Map<String, Criterion<?>> criteria    = new LinkedHashMap<>();
    @javax.annotation.Nullable
    private       String                    group;

    private ManualCrusherRecipeBuilder(RecipeCategory cat, ItemStack result, int count, Ingredient ingredient,
            float exp)
    {
        this.category = cat;
        this.result = result;
        this.count = count;
        this.ingredient = ingredient;
        this.experience = exp;
    }

    public static ManualCrusherRecipeBuilder crush(Ingredient ing, RecipeCategory cat, ItemLike item, int c, float exp)
    { return new ManualCrusherRecipeBuilder(cat, item.asItem().getDefaultInstance(), c, ing, exp); }

    @Override
    public ManualCrusherRecipeBuilder unlockedBy(String name, Criterion<?> criterion)
    { this.criteria.put(name, criterion); return this; }

    @Override
    public RecipeBuilder group(@Nullable String groupName) { this.group = groupName; return this; }

    @Override
    public Item getResult() { return this.result.getItem(); }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id)
    {
        this.ensureValid(id);
        Advancement.Builder builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        ManualCrusherRecipe recipe = new ManualCrusherRecipe(this.ingredient, this.result);
        recipeOutput.accept(id, recipe, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation id)
    { if (this.criteria.isEmpty()) { throw new IllegalStateException("No way of obtaining recipe " + id); }}
}
