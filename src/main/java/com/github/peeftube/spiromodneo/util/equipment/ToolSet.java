package com.github.peeftube.spiromodneo.util.equipment;

import net.minecraft.world.item.Item;

public record ToolSet(Item sword, Item shovel, Item hoe, Item axe, Item pickaxe)
{
    public Item getSword()
    { return sword; }

    public Item getShovel()
    { return shovel; }

    public Item getHoe()
    { return hoe; }

    public Item getAxe()
    { return axe; }

    public Item getPickaxe()
    { return pickaxe; }
}
