package com.github.peeftube.spiromodneo.util.stone.subdata;

import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record StoneBrickData(GenBlockItemCoupling base, GenBlockItemCoupling mossyBase,
                             GenBlockItemCoupling crackedBase, GenBlockItemCoupling chiseled,
                             GenBlockItemCoupling slab, GenBlockItemCoupling mossySlab,
                             GenBlockItemCoupling crackedSlab,
                             GenBlockItemCoupling stair, GenBlockItemCoupling mossyStair,
                             GenBlockItemCoupling crackedStair,
                             GenBlockItemCoupling wall, GenBlockItemCoupling mossyWall,
                             GenBlockItemCoupling crackedWall)
{
    public Map<Supplier<Block>, Boolean> getValues()
    {
        Map<Supplier<Block>, Boolean> mappings = new HashMap<>();
        mappings.put(base.block(), base.isVanilla());
        mappings.put(slab.block(), slab.isVanilla());
        mappings.put(stair.block(), stair.isVanilla());
        mappings.put(wall.block(), wall.isVanilla());
        mappings.put(chiseled.block(), chiseled.isVanilla());
        mappings.put(mossyBase.block(), mossyBase.isVanilla());
        mappings.put(mossySlab.block(), mossySlab.isVanilla());
        mappings.put(mossyStair.block(), mossyStair.isVanilla());
        mappings.put(mossyWall.block(), mossyWall.isVanilla());
        mappings.put(crackedBase.block(), crackedBase.isVanilla());
        mappings.put(crackedSlab.block(), crackedSlab.isVanilla());
        mappings.put(crackedStair.block(), crackedStair.isVanilla());
        mappings.put(crackedWall.block(), crackedWall.isVanilla());
        return mappings;
    }
}
