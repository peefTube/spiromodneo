package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;

public record StoneTileData(GenBlockItemCoupling base, GenBlockItemCoupling mossy,
                            GenBlockItemCoupling cracked, GenBlockItemCoupling small,
                            GenBlockItemCoupling chiseled,
                            GenBlockItemCoupling slab, GenBlockItemCoupling mossySlab,
                            GenBlockItemCoupling crackedSlab, GenBlockItemCoupling smallSlab,
                            GenBlockItemCoupling stair, GenBlockItemCoupling mossyStair,
                            GenBlockItemCoupling crackedStair, GenBlockItemCoupling smallStair,
                            GenBlockItemCoupling wall, GenBlockItemCoupling mossyWall,
                            GenBlockItemCoupling crackedWall, GenBlockItemCoupling smallWall)
{
}
