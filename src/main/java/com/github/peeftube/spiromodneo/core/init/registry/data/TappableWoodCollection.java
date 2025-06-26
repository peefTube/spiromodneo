package com.github.peeftube.spiromodneo.core.init.registry.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record TappableWoodCollection(TappableWoodMaterial type, WoodCollection wood)
{
    public static List<TappableWoodCollection> TAPPABLE_WOOD_COLLECTIONS = new ArrayList<>();

    public static TappableWoodCollection registerCollection(TappableWoodMaterial type)
    { return registerCollection(type, 0); }

    public static TappableWoodCollection registerCollection(TappableWoodMaterial type, int li)
    {
        Map<Boolean, Tappable> tappableMapping = new HashMap<>();
        tappableMapping.put(true, type.getTapOutput());

        TappableWoodCollection collection = new TappableWoodCollection(type,
                WoodCollection.registerCollection(type.getWoodMaterial(), li, tappableMapping));
        TAPPABLE_WOOD_COLLECTIONS.add(collection); return collection;
    }
}
