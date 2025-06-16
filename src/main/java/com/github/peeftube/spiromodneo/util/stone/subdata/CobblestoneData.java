package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record CobblestoneData(GenBlockItemCoupling base, GenBlockItemCoupling mossyBase,
                              GenBlockItemCoupling slab, GenBlockItemCoupling mossySlab,
                              GenBlockItemCoupling stair, GenBlockItemCoupling mossyStair,
                              GenBlockItemCoupling wall, GenBlockItemCoupling mossyWall)
{
    public Map<Supplier<Block>, Boolean> getValues()
    {
        Map<Supplier<Block>, Boolean> mappings = new HashMap<>();
        mappings.put(base.block(), base.isVanilla());
        mappings.put(mossyBase.block(), mossyBase.isVanilla());
        mappings.put(slab.block(), slab.isVanilla());
        mappings.put(mossySlab.block(), mossySlab.isVanilla());
        mappings.put(stair.block(), stair.isVanilla());
        mappings.put(mossyStair.block(), mossyStair.isVanilla());
        mappings.put(wall.block(), wall.isVanilla());
        mappings.put(mossyWall.block(), mossyWall.isVanilla());
        return mappings;
    }
}
