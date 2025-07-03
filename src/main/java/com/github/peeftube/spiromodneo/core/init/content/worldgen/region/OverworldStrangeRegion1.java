package com.github.peeftube.spiromodneo.core.init.content.worldgen.region;

import com.github.peeftube.spiromodneo.core.init.content.worldgen.biome.NeoBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class OverworldStrangeRegion1 extends Region
{
    public OverworldStrangeRegion1(ResourceLocation name, int weight)
    { super(name, RegionType.OVERWORLD, weight); }

    @Override
    public void addBiomes(Registry<Biome> reg, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
    }
}
