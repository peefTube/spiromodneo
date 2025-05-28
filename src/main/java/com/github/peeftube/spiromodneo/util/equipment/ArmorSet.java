package com.github.peeftube.spiromodneo.util.equipment;

import net.minecraft.world.item.Item;

public record ArmorSet(Item helmet, Item chestplate, Item leggings, Item boots)
{
    public Item getHelmet()
    { return helmet; }

    public Item getChestplate()
    { return chestplate; }

    public Item getLeggings()
    { return leggings; }

    public Item getBoots()
    { return boots; }
}