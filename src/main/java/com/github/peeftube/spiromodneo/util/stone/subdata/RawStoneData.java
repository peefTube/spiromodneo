package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;

public record RawStoneData(GenBlockItemCoupling base, GenBlockItemCoupling slab, GenBlockItemCoupling stair,
                           GenBlockItemCoupling button, GenBlockItemCoupling plate, GenBlockItemCoupling wall)
{
}
