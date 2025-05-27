package com.github.peeftube.spiromodneo.util;

import com.github.peeftube.spiromodneo.SpiroMod;
import net.minecraft.resources.ResourceLocation;

public class RLUtility
{
    public static ResourceLocation makeRL(String ns, String path)
    { return ResourceLocation.fromNamespaceAndPath(ns, path); }

    public static ResourceLocation makeRL(String path)
    { return makeRL(SpiroMod.MOD_ID, path); }
}
