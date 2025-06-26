package com.github.peeftube.spiromodneo.datagen.modules.world.util.helpers.custombiome;

import com.github.peeftube.spiromodneo.core.init.content.worldgen.biome.NeoBiomes;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import static com.github.peeftube.spiromodneo.datagen.modules.world.util.helpers.RuleSourceOverrides.*;

public class NetherColdRegionSourceRules
{
    public static SurfaceRules.RuleSource rules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), SOUL_SOIL);

        SurfaceRules.RuleSource vitalium = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, VITALIUM), SOUL_SOIL);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient("bedrock_floor",
                                VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                SurfaceRules.ifTrue(
                        SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_ceiling",
                                VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NeoBiomes.NETHER_LIMBO_GARDEN), SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                // Default to the limbo garden's base terrain
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, vitalium)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING,
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.NETHERRACK, -0.075, 0.075),
                                        GLOWSTONE)),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SOUL_SOIL), SMOOTH_BASALT))));
    }
}
