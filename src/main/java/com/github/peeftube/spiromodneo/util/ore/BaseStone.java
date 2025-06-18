package com.github.peeftube.spiromodneo.util.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.github.peeftube.spiromodneo.core.init.Registrar.*;

public enum BaseStone
{
    STONE("", STONE_BASED_ORE, () -> Blocks.STONE, true),
    ANDESITE("andesite_", STONE_BASED_ORE, () -> Blocks.ANDESITE, true),
    DIORITE("diorite_", STONE_BASED_ORE, () -> Blocks.DIORITE, true),
    GRANITE("granite_", STONE_BASED_ORE, () -> Blocks.GRANITE, true),
    CALCITE("calcite_", CALCITE_BASED_ORE, () -> Blocks.CALCITE, true),
    SMS("smooth_sandstone_", STONE_BASED_ORE,
            () -> Blocks.SMOOTH_SANDSTONE, () -> Blocks.SANDSTONE, true),
    SMRS("smooth_red_sandstone_", STONE_BASED_ORE,
            () -> Blocks.SMOOTH_RED_SANDSTONE, () -> Blocks.RED_SANDSTONE, true),
    DEEPSLATE("deepslate_", DEEPSLATE_BASED_ORE, () -> Blocks.DEEPSLATE, true),
    TUFF("tuff_", TUFF_BASED_ORE, () -> Blocks.TUFF, true),
    DRIPSTONE("dripstone_", DRIPSTONE_BASED_ORE, () -> Blocks.DRIPSTONE_BLOCK, true),
    NETHERRACK("nether_", NETHER_BASED_ORE, () -> Blocks.NETHERRACK, true),
    BASALT("basalt_", BASALT_BASED_ORE, () -> Blocks.SMOOTH_BASALT, () -> Blocks.BASALT, true),
    ENDSTONE("end_", STONE_BASED_ORE, () -> Blocks.END_STONE, true);

    private final String                    name;
    private final BlockBehaviour.Properties props;
    private final boolean                   prePopulated;
    private Supplier<Block>           oreBase;
    private Supplier<Block>           defaultBase;

    BaseStone(String name, BlockBehaviour.Properties props, Supplier<Block> oreBase, boolean isPopulated)
    { this.name = name; this.props = props; this.oreBase = oreBase; this.defaultBase = oreBase; this.prePopulated = isPopulated; }

    BaseStone(String name, BlockBehaviour.Properties props, Supplier<Block> oreBase, Supplier<Block> defaultBase, boolean isPopulated)
    { this.name = name; this.props = props; this.oreBase = oreBase; this.defaultBase = defaultBase; this.prePopulated = isPopulated; }

    public String get()
    { return name; }

    public BlockBehaviour.Properties getProps()
    { return props; }

    /** Will only be set if this enum value is not pre-populated.
     * For vanilla stone types, this should always return preset values. */
    public void setOreBase(Supplier<Block> oreBase)
    { this.oreBase = this.prePopulated ? this.oreBase : oreBase; }

    /** Will only be set if this enum value is not pre-populated.
     * For vanilla stone types, this should always return preset values. */
    public void setDefaultBase(Supplier<Block> defaultBase)
    { this.defaultBase = this.prePopulated ? this.defaultBase : defaultBase; }

    public boolean isPrePopulated()
    { return prePopulated; }

    public Supplier<Block> getOreBase()
    { return oreBase; }

    public Supplier<Block> getDefault()
    { return defaultBase; }
}
