package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record RawStoneData(GenBlockItemCoupling base, GenBlockItemCoupling slab, GenBlockItemCoupling stair,
                           GenBlockItemCoupling button, GenBlockItemCoupling plate, GenBlockItemCoupling wall)
{
    public Map<Supplier<Block>, Boolean> getValues()
    {
        Map<Supplier<Block>, Boolean> mappings = new HashMap<>();
        mappings.put(base.block(), base.isVanilla());
        mappings.put(slab.block(), slab.isVanilla());
        mappings.put(stair.block(), stair.isVanilla());
        mappings.put(button.block(), button.isVanilla());
        mappings.put(plate.block(), plate.isVanilla());
        mappings.put(wall.block(), wall.isVanilla());
        return mappings;
    }
}
