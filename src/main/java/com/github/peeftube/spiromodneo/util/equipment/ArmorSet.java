package com.github.peeftube.spiromodneo.util.equipment;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public record ArmorSet(Supplier<? extends Item> helmet, Supplier<? extends Item> chestplate,
                       Supplier<? extends Item> leggings, Supplier<? extends Item> boots)
{
    public Supplier<? extends Item> getHelmet()
    { return helmet; }

    public Supplier<? extends Item> getChestplate()
    { return chestplate; }

    public Supplier<? extends Item> getLeggings()
    { return leggings; }

    public Supplier<? extends Item> getBoots()
    { return boots; }
}