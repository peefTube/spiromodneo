package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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
    public Map<Supplier<Block>, Boolean> getValues()
    {
        Map<Supplier<Block>, Boolean> mappings = new HashMap<>();
        mappings.put(base.block(), base.isVanilla());
        mappings.put(slab.block(), slab.isVanilla());
        mappings.put(stair.block(), stair.isVanilla());
        mappings.put(wall.block(), wall.isVanilla());
        mappings.put(chiseled.block(), chiseled.isVanilla());
        mappings.put(mossy.block(), mossy.isVanilla());
        mappings.put(mossySlab.block(), mossySlab.isVanilla());
        mappings.put(mossyStair.block(), mossyStair.isVanilla());
        mappings.put(mossyWall.block(), mossyWall.isVanilla());
        mappings.put(cracked.block(), cracked.isVanilla());
        mappings.put(crackedSlab.block(), crackedSlab.isVanilla());
        mappings.put(crackedStair.block(), crackedStair.isVanilla());
        mappings.put(crackedWall.block(), crackedWall.isVanilla());
        mappings.put(small.block(), small.isVanilla());
        mappings.put(smallSlab.block(), smallSlab.isVanilla());
        mappings.put(smallStair.block(), smallStair.isVanilla());
        mappings.put(smallWall.block(), smallWall.isVanilla());
        return mappings;
    }
}
