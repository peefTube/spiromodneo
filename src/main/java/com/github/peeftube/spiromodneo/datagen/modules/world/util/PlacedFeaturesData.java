package com.github.peeftube.spiromodneo.datagen.modules.world.util;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.util.RLUtility;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PlacedFeaturesData
{
    public static final ResourceKey<PlacedFeature> COAL_ORE_UPPER = registerKey("coal_ore_upper");
    public static final ResourceKey<PlacedFeature> COAL_ORE_LOWER = registerKey("coal_ore_lower");

    public static final ResourceKey<PlacedFeature> IRON_ORE_UPPER = registerKey("iron_ore_upper");
    public static final ResourceKey<PlacedFeature> IRON_ORE_MID = registerKey("iron_ore_mid");
    public static final ResourceKey<PlacedFeature> IRON_ORE_SMALL = registerKey("iron_ore_small");
    public static final ResourceKey<PlacedFeature> IRON_ORE_NETHER = registerKey("iron_ore_nether");
    public static final ResourceKey<PlacedFeature> IRON_ORE_NETHER_LARGE = registerKey("iron_ore_nether_large");

    public static final ResourceKey<PlacedFeature> COPPER_ORE = registerKey("copper_ore");
    public static final ResourceKey<PlacedFeature> COPPER_ORE_LARGE = registerKey("copper_ore_large");
    public static final ResourceKey<PlacedFeature> COPPER_ORE_NETHER = registerKey("copper_ore_nether");

    public static final ResourceKey<PlacedFeature> DIAMOND_ORE_SMALL = registerKey("diamond_ore_small");
    public static final ResourceKey<PlacedFeature> DIAMOND_ORE = registerKey("diamond_ore");
    public static final ResourceKey<PlacedFeature> DIAMOND_ORE_LARGE = registerKey("diamond_ore_large");
    public static final ResourceKey<PlacedFeature> DIAMOND_ORE_BURIED = registerKey("diamond_ore_buried");

    public static final ResourceKey<PlacedFeature> EMERALD_ORE = registerKey("emerald_ore");

    public static final ResourceKey<PlacedFeature> GOLD_ORE = registerKey("gold_ore");
    public static final ResourceKey<PlacedFeature> GOLD_ORE_EXTRA = registerKey("gold_ore_extra");
    public static final ResourceKey<PlacedFeature> GOLD_ORE_LOWER = registerKey("gold_ore_lower");
    public static final ResourceKey<PlacedFeature> GOLD_ORE_NETHER = registerKey("gold_ore_nether");
    public static final ResourceKey<PlacedFeature> GOLD_ORE_DELTAS = registerKey("gold_ore_deltas");

    public static final ResourceKey<PlacedFeature> LAPIS_ORE = registerKey("lapis_ore");
    public static final ResourceKey<PlacedFeature> LAPIS_ORE_BURIED = registerKey("lapis_ore_buried");

    public static final ResourceKey<PlacedFeature> REDSTONE_ORE = registerKey("redstone_ore");
    public static final ResourceKey<PlacedFeature> REDSTONE_ORE_LOWER = registerKey("redstone_ore_lower");

    public static final ResourceKey<PlacedFeature> QUARTZ_ORE_NETHER = registerKey("quartz_ore_nether");
    public static final ResourceKey<PlacedFeature> QUARTZ_ORE_DELTAS = registerKey("quartz_ore_deltas");
    public static final ResourceKey<PlacedFeature> QUARTZ_ORE_OVERWORLD = registerKey("quartz_ore_overworld");

    public static final ResourceKey<PlacedFeature> LEAD_ORE_UPPER = registerKey("lead_ore_upper");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_MID = registerKey("lead_ore_mid");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_SMALL = registerKey("lead_ore_small");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_NETHER = registerKey("lead_ore_nether");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_NETHER_LARGE = registerKey("lead_ore_nether_large");

    public static final ResourceKey<PlacedFeature> RUBY_ORE = registerKey("ruby_ore");

    public static final ResourceKey<PlacedFeature> METHANE_ICE_ORE_OVERWORLD = registerKey("methane_ore_overworld");
    public static final ResourceKey<PlacedFeature> METHANE_ICE_ORE_NETHER = registerKey("methane_ore_nether");
    public static final ResourceKey<PlacedFeature> METHANE_ICE_ORE_MEGA_NETHER = registerKey("methane_ore_mega_nether");

    public static final ResourceKey<PlacedFeature> NETHER_WATER_LAKE = registerKey("nether_water_lake");
    public static final ResourceKey<PlacedFeature> NETHER_OVERWORLD_TREES = registerKey("limbo_garden_trees");
    public static final ResourceKey<PlacedFeature> NETHER_OVERWORLD_GRASS = registerKey("limbo_garden_grass");
    public static final ResourceKey<PlacedFeature> NETHER_OVERWORLD_FLOWERS = registerKey("limbo_garden_flowers");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, COAL_ORE_UPPER, configuredFeatures.getOrThrow(ConfigFeaturesData.COAL_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(136), VerticalAnchor.belowTop(0))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(30)
        ));
        register(context, COAL_ORE_LOWER, configuredFeatures.getOrThrow(ConfigFeaturesData.COAL_ORE_BURIED), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(20)
        ));

        register(context, IRON_ORE_UPPER, configuredFeatures.getOrThrow(ConfigFeaturesData.IRON_ORE_GENERIC), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(90)
        ));
        register(context, IRON_ORE_MID, configuredFeatures.getOrThrow(ConfigFeaturesData.IRON_ORE_GENERIC), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));
        register(context, IRON_ORE_SMALL, configuredFeatures.getOrThrow(ConfigFeaturesData.IRON_ORE_SMALL), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(72))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));
        register(context, IRON_ORE_NETHER, configuredFeatures.getOrThrow(ConfigFeaturesData.IRON_ORE_SMALL), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));
        register(context, IRON_ORE_NETHER_LARGE, configuredFeatures.getOrThrow(ConfigFeaturesData.IRON_ORE_GENERIC), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));

        register(context, COPPER_ORE, configuredFeatures.getOrThrow(ConfigFeaturesData.COPPER_ORE_SMALL), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(16)
        ));
        register(context, COPPER_ORE_LARGE, configuredFeatures.getOrThrow(ConfigFeaturesData.COPPER_ORE_LARGE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(16)
        ));
        register(context, COPPER_ORE_NETHER, configuredFeatures.getOrThrow(ConfigFeaturesData.COPPER_ORE_SMALL), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));

        register(context, DIAMOND_ORE_SMALL, configuredFeatures.getOrThrow(ConfigFeaturesData.DIAMOND_ORE_SMALL), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(7)
        ));
        register(context, DIAMOND_ORE, configuredFeatures.getOrThrow(ConfigFeaturesData.DIAMOND_ORE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(2)
        ));
        register(context, DIAMOND_ORE_LARGE, configuredFeatures.getOrThrow(ConfigFeaturesData.DIAMOND_ORE_LARGE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                RarityFilter.onAverageOnceEvery(9)
        ));
        register(context, DIAMOND_ORE_BURIED, configuredFeatures.getOrThrow(ConfigFeaturesData.DIAMOND_ORE_BURIED), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(4)
        ));

        register(context, EMERALD_ORE, configuredFeatures.getOrThrow(ConfigFeaturesData.EMERALD_ORE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(256))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(100)
        ));

        register(context, GOLD_ORE, configuredFeatures.getOrThrow(ConfigFeaturesData.GOLD_ORE_BURIED), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(4)
        ));
        register(context, GOLD_ORE_EXTRA, configuredFeatures.getOrThrow(ConfigFeaturesData.GOLD_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(32), VerticalAnchor.absolute(256))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(50)
        ));
        register(context, GOLD_ORE_LOWER, configuredFeatures.getOrThrow(ConfigFeaturesData.GOLD_ORE_BURIED), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(UniformInt.of(0, 1))
        ));
        register(context, GOLD_ORE_NETHER, configuredFeatures.getOrThrow(ConfigFeaturesData.NETHER_GOLD_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));
        register(context, GOLD_ORE_DELTAS, configuredFeatures.getOrThrow(ConfigFeaturesData.NETHER_GOLD_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(20)
        ));

        register(context, LAPIS_ORE, configuredFeatures.getOrThrow(ConfigFeaturesData.LAPIS_ORE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(2)
        ));
        register(context, LAPIS_ORE_BURIED, configuredFeatures.getOrThrow(ConfigFeaturesData.LAPIS_ORE_BURIED), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(64))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(4)
        ));

        register(context, REDSTONE_ORE, configuredFeatures.getOrThrow(ConfigFeaturesData.REDSTONE_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(15))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(4)
        ));
        register(context, REDSTONE_ORE_LOWER, configuredFeatures.getOrThrow(ConfigFeaturesData.REDSTONE_ORE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(8)
        ));

        register(context, QUARTZ_ORE_NETHER, configuredFeatures.getOrThrow(ConfigFeaturesData.QUARTZ_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(16)
        ));
        register(context, QUARTZ_ORE_DELTAS, configuredFeatures.getOrThrow(ConfigFeaturesData.QUARTZ_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(10))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(32)
        ));
        register(context, QUARTZ_ORE_OVERWORLD, configuredFeatures.getOrThrow(ConfigFeaturesData.QUARTZ_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.belowTop(256))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(2)
        ));

        register(context, LEAD_ORE_UPPER, configuredFeatures.getOrThrow(ConfigFeaturesData.LEAD_ORE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(90)
        ));
        register(context, LEAD_ORE_MID, configuredFeatures.getOrThrow(ConfigFeaturesData.LEAD_ORE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));
        register(context, LEAD_ORE_SMALL, configuredFeatures.getOrThrow(ConfigFeaturesData.LEAD_ORE_SMALL), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(72))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));
        register(context, LEAD_ORE_NETHER, configuredFeatures.getOrThrow(ConfigFeaturesData.LEAD_ORE_SMALL), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));
        register(context, LEAD_ORE_NETHER_LARGE, configuredFeatures.getOrThrow(ConfigFeaturesData.LEAD_ORE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(10)
        ));

        register(context, RUBY_ORE, configuredFeatures.getOrThrow(ConfigFeaturesData.RUBY_ORE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(256))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(100)
        ));

        register(context, METHANE_ICE_ORE_OVERWORLD, configuredFeatures.getOrThrow(ConfigFeaturesData.OVERWORLD_METHANE_ICE), List.of(
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-256), VerticalAnchor.absolute(-80))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(20)
        ));
        register(context, METHANE_ICE_ORE_NETHER, configuredFeatures.getOrThrow(ConfigFeaturesData.NETHER_METHANE_ICE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(5), VerticalAnchor.belowTop(5))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(30)
        ));
        register(context, METHANE_ICE_ORE_MEGA_NETHER, configuredFeatures.getOrThrow(ConfigFeaturesData.MEGA_NETHER_METHANE_ICE), List.of(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(5), VerticalAnchor.belowTop(5))),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                CountPlacement.of(4)
        ));

        register(context, NETHER_WATER_LAKE,
                configuredFeatures.getOrThrow(ConfigFeaturesData.NETHER_WATER_LAKE),
                List.of(BiomeFilter.biome(),
                        RarityFilter.onAverageOnceEvery(80),
                        CountOnEveryLayerPlacement.of(UniformInt.of(0, 1))));

        register(context, NETHER_OVERWORLD_TREES,
                configuredFeatures.getOrThrow(VegetationFeatures.TREES_BIRCH_AND_OAK),
                List.of(BiomeFilter.biome(),
                        CountOnEveryLayerPlacement.of(8)));
        register(context, NETHER_OVERWORLD_GRASS,
                configuredFeatures.getOrThrow(VegetationFeatures.PATCH_GRASS),
                List.of(BiomeFilter.biome(),
                        CountOnEveryLayerPlacement.of(16)));
        register(context, NETHER_OVERWORLD_FLOWERS,
                configuredFeatures.getOrThrow(VegetationFeatures.FLOWER_PLAIN),
                List.of(BiomeFilter.biome(),
                        CountOnEveryLayerPlacement.of(2)));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name)
    { return ResourceKey.create(Registries.PLACED_FEATURE, RLUtility.makeRL(SpiroMod.MOD_ID, name)); }

    private static void register(BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers)
    { context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers))); }

    private static void register(BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers)
    { register(context, key, configuration, List.of(modifiers)); }
}
