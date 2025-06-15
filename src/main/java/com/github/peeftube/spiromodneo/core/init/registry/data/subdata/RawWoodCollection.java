package com.github.peeftube.spiromodneo.core.init.registry.data.subdata;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;

public record RawWoodCollection(RotatedPillarBlock log, RotatedPillarBlock stripped_log,
                                RotatedPillarBlock wood, RotatedPillarBlock stripped_wood,
                                LeavesBlock leaves, SaplingBlock treeSapling)
{
}
