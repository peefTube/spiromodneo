package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;

public record CobblestoneData(GenBlockItemCoupling base, GenBlockItemCoupling mossyBase,
                              GenBlockItemCoupling slab, GenBlockItemCoupling mossySlab,
                              GenBlockItemCoupling stair, GenBlockItemCoupling mossyStair,
                              GenBlockItemCoupling wall, GenBlockItemCoupling mossyWall)
{
}
