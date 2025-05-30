package com.github.peeftube.spiromodneo.datagen.modules.world.util;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.datagen.modules.world.util.helpers.NoiseDataOverrides;
import com.github.peeftube.spiromodneo.util.RLUtility;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;

public class NoiseSettingsData implements NoiseDataOverrides
{
    /** This is the vanilla overworld generation; this is designed to allow players to return to vanilla generation. */
    public static final ResourceKey<NoiseGeneratorSettings> UNSPOILED = registerNGSKey("unspoiled");

    /** This is the new overworld generation. */
    public static final ResourceKey<NoiseGeneratorSettings> OVERWORLD_NEO = registerOverwriteNGSKey("overworld");

    public static void bootstrap(final BootstrapContext<NoiseGeneratorSettings> context)
    {
        NoiseGeneratorSettings overworld = NoiseGeneratorSettings.overworld(context, false, false);

        context.register(UNSPOILED, overworld);

        context.register(OVERWORLD_NEO, new NoiseGeneratorSettings(
                new NoiseSettings(-384, 1024, 1, 2),
                overworld.defaultBlock(), overworld.defaultFluid(), overworld.noiseRouter(),
                NoiseDataOverrides.overrideOverworld(true, false, true, 640),
                overworld.spawnTarget(), overworld.seaLevel(),
                false, true, false, false));
    }

    private static ResourceKey<NoiseGeneratorSettings> registerNGSKey(String name)
    { return ResourceKey.create(Registries.NOISE_SETTINGS, RLUtility.makeRL(SpiroMod.MOD_ID, name)); }

    private static ResourceKey<NoiseGeneratorSettings> registerNGSKey(String mod, String name)
    { return ResourceKey.create(Registries.NOISE_SETTINGS, RLUtility.makeRL(mod, name)); }

    private static ResourceKey<NoiseGeneratorSettings> registerOverwriteNGSKey(String name)
    { return ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.withDefaultNamespace(name)); }
}
