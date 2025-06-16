package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;

public record StoneBrickData(GenBlockItemCoupling base, GenBlockItemCoupling mossyBase,
                             GenBlockItemCoupling crackedBase, GenBlockItemCoupling chiseled,
                             GenBlockItemCoupling slab, GenBlockItemCoupling mossySlab,
                             GenBlockItemCoupling crackedSlab,
                             GenBlockItemCoupling stair, GenBlockItemCoupling mossyStair,
                             GenBlockItemCoupling crackedStair,
                             GenBlockItemCoupling wall, GenBlockItemCoupling mossyWall,
                             GenBlockItemCoupling crackedWall)
{
}
