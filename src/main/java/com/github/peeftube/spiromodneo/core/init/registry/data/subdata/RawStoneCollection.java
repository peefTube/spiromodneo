package com.github.peeftube.spiromodneo.core.init.registry.data.subdata;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.WallBlock;

public record RawStoneCollection(GenericConstructionCollection raw, ButtonBlock button,
                                 PressurePlateBlock plate, WallBlock wall)
{
}
