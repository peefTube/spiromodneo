package com.github.peeftube.spiromodneo.core.init.content.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;

import javax.annotation.Nullable;

public class FuelItem extends Item
{
    private final int burnTime;

    public FuelItem(Properties properties, int burnTimeInTicks)
    { super(properties); this.burnTime = burnTimeInTicks; new FurnaceFuel(this.burnTime); }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) { return this.burnTime; }
    public int getBurnTimeRaw() { return this.burnTime; }
}
