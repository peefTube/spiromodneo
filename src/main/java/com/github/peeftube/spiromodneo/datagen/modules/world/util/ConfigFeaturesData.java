package com.github.peeftube.spiromodneo.datagen.modules.world.util;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.util.RLUtility;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ConfigFeaturesData
{
    private static      HolderGetter<Block>                  blocks;

    /** This is an ancient holdout from a less efficient method of determining per-biome stone types.
     * Good riddance. */
    @Deprecated
    public static final ResourceKey<ConfiguredFeature<? ,?>> OVERRIDE_STOCK = registerKey("spiro_override_stock");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        blocks = context.lookup(Registries.BLOCK);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name)
    { return ResourceKey.create(Registries.CONFIGURED_FEATURE, RLUtility.makeRL(SpiroMod.MOD_ID, name)); }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void
    register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<? ,?>> key, F feature, FC configuration)
    { context.register(key, new ConfiguredFeature<>(feature, configuration)); }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value)
    {
        Registrar.FEATURES.register(key, () -> value);
        return value;
    }

    public static void register() {}
}
