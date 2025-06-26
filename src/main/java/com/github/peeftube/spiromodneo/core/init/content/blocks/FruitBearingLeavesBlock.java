package com.github.peeftube.spiromodneo.core.init.content.blocks;

import net.minecraft.world.level.block.LeavesBlock;

/** Fruit-bearing trees have the ability to produce fruit directly on the leaves, which can be picked
 * by right-clicking with an empty hand on the leaf blocks. This opens up options for orchard-based
 * agriculture, which also renders apple drops from oak trees obsolete. (Why rely on oak trees for
 * apples when you can pick them straight off an apple tree?)
 */
public class FruitBearingLeavesBlock extends LeavesBlock
{
    public FruitBearingLeavesBlock(Properties properties)
    { super(properties); }
}
