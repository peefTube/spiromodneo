package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.util.DataCheckResult;
import com.github.peeftube.spiromodneo.util.equipment.EquipmentData;
import com.github.peeftube.spiromodneo.util.equipment.EquipmentUtilities;

import java.util.ArrayList;
import java.util.List;

public record EquipmentCollection(EquipmentMaterial material, EquipmentData bulkData) implements EquipmentUtilities
{
    public static List<EquipmentCollection> EQUIP_COLLECTIONS = new ArrayList<>();

    public static EquipmentCollection registerCollection(EquipmentMaterial material)
    {
        EquipmentCollection collection = new EquipmentCollection(material,
                EquipmentUtilities.generateEquipmentData(material));
        EQUIP_COLLECTIONS.add(collection); return collection;
    }
}
