package com.github.peeftube.spiromodneo.core.init.content.worldgen.region;

import com.github.peeftube.spiromodneo.core.init.content.worldgen.biome.NeoBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class NetherColdRegion extends Region
{
    public NetherColdRegion(ResourceLocation name, int weight)
    { super(name, RegionType.NETHER, weight); }

    @Override
    public void addBiomes(Registry<Biome> reg, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.span(ParameterUtils.Temperature.ICY,
                        ParameterUtils.Temperature.NEUTRAL))
                .humidity(ParameterUtils.Humidity.HUMID)
                .continentalness(ParameterUtils.Continentalness.FULL_RANGE)
                .erosion(ParameterUtils.Erosion.FULL_RANGE)
                .depth(ParameterUtils.Depth.FULL_RANGE)
                .weirdness(Climate.Parameter.span(0.175F, 1F))
                .build().forEach(point -> builder.add(point, NeoBiomes.NETHER_LIMBO_GARDEN));

        builder.build().forEach(mapper::accept);
    }
}
