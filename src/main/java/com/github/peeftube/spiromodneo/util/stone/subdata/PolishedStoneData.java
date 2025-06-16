package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record PolishedStoneData(GenBlockItemCoupling base, GenBlockItemCoupling slab,
                                GenBlockItemCoupling stair)
{
    public Map<Supplier<Block>, Boolean> getValues()
    {
        Map<Supplier<Block>, Boolean> mappings = new HashMap<>();
        mappings.put(base.block(), base.isVanilla());
        mappings.put(slab.block(), slab.isVanilla());
        mappings.put(stair.block(), stair.isVanilla());
        return mappings;
    }
}
