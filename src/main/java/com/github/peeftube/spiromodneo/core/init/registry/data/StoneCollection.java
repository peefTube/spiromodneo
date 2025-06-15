package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.GenericConstructionCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.RawStoneCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.StoneBrickCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.StoneTileCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.subdata.subdata.BedsCollection;
import com.github.peeftube.spiromodneo.util.stone.StoneMatTagCoupling;

public record StoneCollection(StoneMaterial material, RawStoneCollection raw,
                              GenericConstructionCollection cobble, GenericConstructionCollection mossCobble,
                              GenericConstructionCollection smooth, GenericConstructionCollection polished,
                              StoneBrickCollection bricks, StoneTileCollection tile, StoneMatTagCoupling materialTags,
                              BedsCollection beds)
{
}