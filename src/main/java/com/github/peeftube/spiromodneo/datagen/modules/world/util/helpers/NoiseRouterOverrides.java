package com.github.peeftube.spiromodneo.datagen.modules.world.util.helpers;

import com.github.peeftube.spiromodneo.util.worldgen.HugeVeins;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.stream.Stream;

import static net.minecraft.world.level.levelgen.NoiseRouterData.*;

public interface NoiseRouterOverrides
{
    static NoiseRouter overrideOverworld( HolderGetter<DensityFunction> densityFunctions,
            HolderGetter<NormalNoise.NoiseParameters> noiseParameters, boolean large, boolean amplified)
    {
        DensityFunction densityfunction = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_BARRIER), 0.5);
        DensityFunction densityfunction1 = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67);
        DensityFunction densityfunction2 = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143);
        DensityFunction densityfunction3 = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = getFunction(densityFunctions, SHIFT_X);
        DensityFunction densityfunction5 = getFunction(densityFunctions, SHIFT_Z);
        DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, 0.25, noiseParameters.getOrThrow(large ? Noises.TEMPERATURE_LARGE : Noises.TEMPERATURE)
        );
        DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, 0.25, noiseParameters.getOrThrow(large ? Noises.VEGETATION_LARGE : Noises.VEGETATION)
        );
        DensityFunction densityfunction8 = getFunction(densityFunctions, large ? FACTOR_LARGE : (amplified ? FACTOR_AMPLIFIED : FACTOR));
        DensityFunction densityfunction9 = getFunction(densityFunctions, large ? DEPTH_LARGE : (amplified ? DEPTH_AMPLIFIED : DEPTH));
        DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
        DensityFunction densityfunction11 = getFunction(densityFunctions, large ? SLOPED_CHEESE_LARGE : (amplified ? SLOPED_CHEESE_AMPLIFIED : SLOPED_CHEESE));
        DensityFunction densityfunction12 = DensityFunctions.min(
                densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0), getFunction(densityFunctions, ENTRANCES))
        );
        DensityFunction densityfunction13 = DensityFunctions.rangeChoice(
                densityfunction11, -1000000.0, 1.5625, densityfunction12, underground(densityFunctions, noiseParameters, densityfunction11)
        );
        DensityFunction densityfunction14 = DensityFunctions.min(postProcess(slideOverworld(amplified, densityfunction13)), getFunction(densityFunctions, NOODLE));
        DensityFunction densityfunction15 = getFunction(densityFunctions, Y);
        int i = Stream.of(HugeVeins.Type.values()).mapToInt(p_224495_ -> p_224495_.minY).min().orElse(-DimensionType.MIN_Y * 2);
        int j = Stream.of(HugeVeins.Type.values()).mapToInt(p_224457_ -> p_224457_.maxY).max().orElse(-DimensionType.MIN_Y * 2);
        DensityFunction densityfunction16 = yLimitedInterpolatable(
                densityfunction15, DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEININESS), 1.5, 1.5), i, j, 0
        );
        float f = 4.0F;
        DensityFunction densityfunction17 = yLimitedInterpolatable(
                densityfunction15, DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEIN_A), 4.0, 4.0), i, j, 0
        )
                .abs();
        DensityFunction densityfunction18 = yLimitedInterpolatable(
                densityfunction15, DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEIN_B), 4.0, 4.0), i, j, 0
        )
                .abs();
        DensityFunction densityfunction19 = DensityFunctions.add(DensityFunctions.constant(-0.08F), DensityFunctions.max(densityfunction17, densityfunction18));
        DensityFunction densityfunction20 = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_GAP));
        return new NoiseRouter(
                densityfunction,
                densityfunction1,
                densityfunction2,
                densityfunction3,
                densityfunction6,
                densityfunction7,
                getFunction(densityFunctions, large ? CONTINENTS_LARGE : CONTINENTS),
                getFunction(densityFunctions, large ? EROSION_LARGE : EROSION),
                densityfunction9,
                getFunction(densityFunctions, RIDGES),
                slideOverworld(amplified, DensityFunctions.add(densityfunction10, DensityFunctions.constant(-0.703125)).clamp(-64.0, 64.0)),
                densityfunction14,
                densityfunction16,
                densityfunction19,
                densityfunction20
        );
    }
}
