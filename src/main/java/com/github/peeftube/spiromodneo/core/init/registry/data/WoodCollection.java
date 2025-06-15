package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.GenericConstructionCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.PlanksCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.RawWoodCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.WoodExtrasCollection;
import net.minecraft.world.level.block.*;

/** A collection to hold wood construction sets, dominated almost exclusively by planks and their derivatives. */
public record WoodCollection(WoodMaterial material, RawWoodCollection raw,
                             PlanksCollection planks, WoodExtrasCollection extras)
{
}
