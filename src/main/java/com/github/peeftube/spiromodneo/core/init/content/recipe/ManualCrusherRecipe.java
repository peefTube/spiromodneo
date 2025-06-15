package com.github.peeftube.spiromodneo.core.init.content.recipe;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record ManualCrusherRecipe(Ingredient inputItem, ItemStack output) implements Recipe<ManualCrusherRecipeInput>
{
    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        NonNullList<Ingredient> ingredientList = NonNullList.create();
        ingredientList.add(inputItem);
        return ingredientList;
    }

    @Override
    public boolean matches(ManualCrusherRecipeInput input, Level level)
    {
        if (level.isClientSide()) { return false; }

        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(ManualCrusherRecipeInput input, HolderLookup.Provider registries)
    { return output.copy(); }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    { return true; }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries)
    { return output; }

    public ItemStack getResultItem()
    { return output; }

    @Override
    public RecipeSerializer<?> getSerializer()
    { return Registrar.MANUAL_CRUSHER_SERIALIZER.get(); }

    @Override
    public RecipeType<?> getType()
    { return Registrar.MANUAL_CRUSHER_TYPE.get(); }

    public static class Serializer implements RecipeSerializer<ManualCrusherRecipe>
    {
        public static final MapCodec<ManualCrusherRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ManualCrusherRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ManualCrusherRecipe::output)
        ).apply(inst, ManualCrusherRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ManualCrusherRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, ManualCrusherRecipe::inputItem,
                        ItemStack.STREAM_CODEC, ManualCrusherRecipe::output,
                        ManualCrusherRecipe::new);

        @Override
        public MapCodec<ManualCrusherRecipe> codec() { return CODEC; }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ManualCrusherRecipe> streamCodec() { return STREAM_CODEC; }
    }
}
