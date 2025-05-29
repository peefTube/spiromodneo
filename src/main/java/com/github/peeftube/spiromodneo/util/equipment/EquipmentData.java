package com.github.peeftube.spiromodneo.util.equipment;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public record EquipmentData(String name, ToolSet tools, ArmorSet armor, Supplier<? extends Item> horseArmor)
{
    public String getName()
    { return name; }

    public ToolSet getTools()
    { return tools; }

    public ArmorSet getArmor()
    { return armor; }

    public Supplier<? extends Item> getHorseArmor()
    { return horseArmor; }
}
