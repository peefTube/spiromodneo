package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.util.wood.LivingWoodBlockType;
import com.github.peeftube.spiromodneo.util.wood.WoodData;
import com.github.peeftube.spiromodneo.util.wood.WoodUtilities;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public record WoodCollection(WoodMaterial type, WoodData bulkData) implements WoodUtilities
{
    public static List<WoodCollection> WOOD_COLLECTIONS = new ArrayList<>();

    public static WoodCollection registerCollection(WoodMaterial type)
    { return registerCollection(type, 0); }

    public static WoodCollection registerCollection(WoodMaterial type, int li)
    {
        Map<Boolean, Tappable> tappableMapping = new HashMap<>();
        tappableMapping.put(false, null); // We'll be checking if the boolean is true or false later.

        return registerCollection(type, li, tappableMapping);
    }

    public static WoodCollection registerCollection(WoodMaterial type, Map<Boolean, Tappable> isTappable)
    { return registerCollection(type, 0, isTappable); }

    public static WoodCollection registerCollection(WoodMaterial type, int li, Map<Boolean, Tappable> isTappable)
    {
        WoodData data = WoodUtilities.populate(type, li, isTappable);
        WoodCollection collection = new WoodCollection(type, data);
        WOOD_COLLECTIONS.add(collection); return collection;
    }

    public Supplier<? extends Block> getBaseLog()
    { return this.bulkData.livingWood().get(LivingWoodBlockType.LOG).getBlock(); }
    public Supplier<? extends Block> getBaseLeaves()
    { return this.bulkData.livingWood().get(LivingWoodBlockType.LEAVES).getBlock(); }
    public Supplier<? extends Block> getBaseSapling()
    { return this.bulkData.livingWood().get(LivingWoodBlockType.SAPLING).getBlock(); }
}
