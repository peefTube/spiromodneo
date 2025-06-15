package com.github.peeftube.spiromodneo.core.init.registry.data.subdata;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;

/** A collection containing a basic block, with slab and stair variants.
 * This is generalized as it applies to multiple block type collections, such
 * as wood and stone, which is useful as not all potential uses of one material will apply to the others;
 * additionally, certain materials such as stone do not consistently have items like walls or buttons/plates
 * associated with their individual variants, so it will be necessary to provide unique collections in those
 * cases. */
public record GenericConstructionCollection(Block base, SlabBlock slab, StairBlock stair)
{
}
