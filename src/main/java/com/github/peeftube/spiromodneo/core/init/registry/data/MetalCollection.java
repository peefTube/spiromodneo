package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.util.metal.IngotCoupling;
import com.github.peeftube.spiromodneo.util.metal.MetalUtilities;

import java.util.ArrayList;
import java.util.List;

public record MetalCollection (MetalMaterial material, IngotCoupling ingotData) implements MetalUtilities
{
    public static List<MetalCollection> METAL_COLLECTIONS = new ArrayList<>();

    public static MetalCollection registerCollection(MetalMaterial material)
    { return registerCollection(material, 0); }

    public static MetalCollection registerCollection(MetalMaterial material, int lightEmissionLevel)
    {
        MetalCollection collection = new MetalCollection(material, MetalUtilities.getIngotData(material, lightEmissionLevel));
        METAL_COLLECTIONS.add(collection); return collection;
    }

    public MetalMaterial getMat()
    { return this.material; }
}
