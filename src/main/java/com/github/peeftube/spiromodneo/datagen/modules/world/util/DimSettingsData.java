package com.github.peeftube.spiromodneo.datagen.modules.world.util;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.util.RLUtility;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.OptionalLong;

public class DimSettingsData
{
    public static final ResourceKey<DimensionType> DIMENSION_TYPE_UNSPOILED     = registerDimKey("unspoiled");
    public static final ResourceKey<DimensionType> DIMENSION_TYPE_OVERWORLD_NEO = registerOverwriteDimKey("overworld");

    public static void bootstrap(final BootstrapContext<DimensionType> context)
    {
        context.register(DIMENSION_TYPE_UNSPOILED, new DimensionType(
                OptionalLong.empty(), true, false,
                false, true, 1.0D,
                true, false,
                -64, 384, 384,
                BlockTags.INFINIBURN_OVERWORLD, BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F, new DimensionType.MonsterSettings(false, true,
                UniformInt.of(0, 7), 0)));
        context.register(DIMENSION_TYPE_OVERWORLD_NEO, new DimensionType(
                OptionalLong.empty(), true, false,
                false, true, 1.0D,
                true, false,
                -384, 1024, 1024,
                BlockTags.INFINIBURN_OVERWORLD, BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F, new DimensionType.MonsterSettings(false, true,
                UniformInt.of(0, 7), 0)));
    }

    private static ResourceKey<DimensionType> registerDimKey(String name)
    { return ResourceKey.create(Registries.DIMENSION_TYPE, RLUtility.makeRL(SpiroMod.MOD_ID, name)); }

    private static ResourceKey<DimensionType> registerDimKey(String mod, String name)
    { return ResourceKey.create(Registries.DIMENSION_TYPE, RLUtility.makeRL(mod, name)); }

    private static ResourceKey<DimensionType> registerOverwriteDimKey(String name)
    { return ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.withDefaultNamespace(name)); }
}
