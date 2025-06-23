package com.github.peeftube.spiromodneo.util.soil;

import com.github.peeftube.spiromodneo.core.init.registry.data.GrassLike;
import com.github.peeftube.spiromodneo.core.init.registry.data.Soil;
import com.github.peeftube.spiromodneo.util.GenericBlockItemCoupling;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public interface GrassLikeUtilities
{
    static Map<GrassLike, Map<Soil, GenericBlockItemCoupling>> getComboPresets()
    {
        Map<GrassLike, Map<Soil, GenericBlockItemCoupling>> reference = new HashMap<>();
        Map<Soil, GenericBlockItemCoupling> grassPresets = new HashMap<>();
        Map<Soil, GenericBlockItemCoupling> myceliumPresets = new HashMap<>();
        Map<Soil, GenericBlockItemCoupling> crimsonNyliumPresets = new HashMap<>();
        Map<Soil, GenericBlockItemCoupling> warpedNyliumPresets = new HashMap<>();

        grassPresets.put(Soil.DIRT, new GenericBlockItemCoupling(() -> Blocks.GRASS_BLOCK, () -> Items.GRASS_BLOCK));
        reference.put(GrassLike.GRASS, grassPresets);

        myceliumPresets.put(Soil.DIRT, new GenericBlockItemCoupling(() -> Blocks.MYCELIUM, () -> Items.MYCELIUM));
        reference.put(GrassLike.MYCELIUM, myceliumPresets);

        crimsonNyliumPresets.put(Soil.NETHERRACK,
                new GenericBlockItemCoupling(() -> Blocks.CRIMSON_NYLIUM, () -> Items.CRIMSON_NYLIUM));
        reference.put(GrassLike.CRIMSON_NYLIUM, crimsonNyliumPresets);

        warpedNyliumPresets.put(Soil.NETHERRACK,
                new GenericBlockItemCoupling(() -> Blocks.WARPED_NYLIUM, () -> Items.WARPED_NYLIUM));
        reference.put(GrassLike.WARPED_NYLIUM, warpedNyliumPresets);

        return reference;
    }
}
