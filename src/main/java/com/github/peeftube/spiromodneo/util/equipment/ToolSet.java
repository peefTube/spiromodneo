package com.github.peeftube.spiromodneo.util.equipment;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

public record ToolSet(Supplier<? extends Item> sword, Supplier<? extends Item> shovel, Supplier<? extends Item> hoe,
                      Supplier<? extends Item> axe, Supplier<? extends Item> pickaxe)
{
    public Supplier<? extends Item> getSword()
    { return sword; }

    public Supplier<? extends Item> getShovel()
    { return shovel; }

    public Supplier<? extends Item> getHoe()
    { return hoe; }

    public Supplier<? extends Item> getAxe()
    { return axe; }

    public Supplier<? extends Item> getPickaxe()
    { return pickaxe; }
}
