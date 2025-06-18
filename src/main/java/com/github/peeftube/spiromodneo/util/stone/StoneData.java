package com.github.peeftube.spiromodneo.util.stone;

import com.github.peeftube.spiromodneo.util.GenericBlockItemCoupling;

import java.util.Map;
import java.util.Objects;

public record StoneData(String name,
                        Map<StoneBlockType, Map<StoneVariantType,
                                Map<StoneSubBlockType, GenericBlockItemCoupling>>> bulkData)
{
    public GenericBlockItemCoupling getCouplingForKeys(StoneBlockType k0, StoneVariantType k1, StoneSubBlockType k2)
    {
        GenericBlockItemCoupling coupling = this.bulkData.get(k0).get(k1).get(k2);
        if (coupling == null) // Leave this in, it may prove useful later.
        { throw new RuntimeException("Block coupling does not exist for: " + name.toUpperCase() + " " + k0 + " " + k1 + " " + k2  + " "); }
        return coupling;
    }

    public boolean doesCouplingExistForKeys(StoneBlockType k0, StoneVariantType k1, StoneSubBlockType k2)
    {
        return this.bulkData.containsKey(k0) ? this.bulkData.get(k0).containsKey(k1) ?
                        this.bulkData.get(k0).get(k1).containsKey(k2) : false : false;
    }
}
