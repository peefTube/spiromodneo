package com.github.peeftube.spiromodneo.util.wood.growers;

import com.github.peeftube.spiromodneo.datagen.modules.world.util.ConfigFeaturesData;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class CustomTreeGrowers
{
    public static TreeGrower ASHEN_OAK = new TreeGrower(
            "ashen_oak", 0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ConfigFeaturesData.ASHEN_OAK),
            Optional.of(ConfigFeaturesData.ASHEN_OAK_FANCY),
            Optional.of(ConfigFeaturesData.ASHEN_OAK_BEES),
            Optional.of(ConfigFeaturesData.ASHEN_OAK_FANCY_BEES)
    );
    public static final TreeGrower ASHEN_BIRCH = new TreeGrower(
            "ashen_birch", Optional.empty(),
            Optional.of(ConfigFeaturesData.ASHEN_BIRCH),
            Optional.of(ConfigFeaturesData.ASHEN_BIRCH_BEES)
    );
}
