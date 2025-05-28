package com.github.peeftube.spiromodneo.util.equipment;

import net.minecraft.world.item.Item;

public record EquipmentData(String name, ToolSet tools, ArmorSet armor, Item horseArmor)
{
    public String getName()
    { return name; }

    public ToolSet getTools()
    { return tools; }

    public ArmorSet getArmor()
    { return armor; }

    public Item getHorseArmor()
    { return horseArmor; }
}
