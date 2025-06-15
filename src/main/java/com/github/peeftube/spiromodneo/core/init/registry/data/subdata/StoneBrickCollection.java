package com.github.peeftube.spiromodneo.core.init.registry.data.subdata;

import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.subdata.StoneVariantSubCollection;
import net.minecraft.world.level.block.RotatedPillarBlock;

public record StoneBrickCollection(StoneVariantSubCollection base, StoneVariantSubCollection mossy,
                                   StoneVariantSubCollection cracked, StoneVariantSubCollection chiseled,
                                   RotatedPillarBlock pillar)
{
}
