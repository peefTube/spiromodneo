package com.github.peeftube.spiromodneo.core.init.registry.data.subdata;

import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.subdata.BedsCollection;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.CraftingTableBlock;

public record WoodExtrasCollection(BedsCollection beds, ChestBlock chest, BarrelBlock barrel,
                                   CraftingTableBlock craftingTable)
{
}
