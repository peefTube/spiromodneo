package com.github.peeftube.spiromodneo.core.init.content.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class FuelBlockItem extends BlockItem
{
    private final int burnTime;

    public FuelBlockItem(Block block, Item.Properties properties, int burnTimeInTicks)
    { super(block, properties); this.burnTime = burnTimeInTicks; }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) { return this.burnTime; }
    public int getBurnTimeRaw() { return this.burnTime; }
}
