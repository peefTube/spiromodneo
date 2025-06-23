package com.github.peeftube.spiromodneo.core.init.content.worldgen.biome;

import com.github.peeftube.spiromodneo.util.RLUtility;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class NeoBiomes
{
    public static final ResourceKey<Biome> NETHER_LIMBO_GARDEN = registerKey("spiro_nether_limbo_garden");

    private static ResourceKey<Biome> registerKey(String name)
    { return ResourceKey.create(Registries.BIOME, RLUtility.makeRL(name)); }
}
