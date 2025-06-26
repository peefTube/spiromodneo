package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.util.wood.growers.CustomTreeGrowers;
import net.minecraft.world.level.block.grower.TreeGrower;

public enum WoodMaterial
{
    OAK("oak", TreeGrower.OAK),
    BIRCH("birch", TreeGrower.BIRCH),
    SPRUCE("spruce", TreeGrower.SPRUCE),
    JUNGLE("jungle", TreeGrower.JUNGLE),
    ACACIA("acacia", TreeGrower.ACACIA),
    DARK_OAK("dark_oak", TreeGrower.DARK_OAK),
    CHERRY("cherry", TreeGrower.CHERRY),
    CRIMSON_FUNGUS("crimson_fungus", null, true),
    WARPED_FUNGUS("warped_fungus", null, true),
    MANGROVE("mangrove", TreeGrower.MANGROVE, false, true),

    // Modded types
    ASHEN_OAK("ashen_oak", CustomTreeGrowers.ASHEN_OAK),
    ASHEN_BIRCH("ashen_birch", CustomTreeGrowers.ASHEN_BIRCH),

    // Modded tappables
    RUBBERWOOD("rubberwood", CustomTreeGrowers.ASHEN_BIRCH),
    MAPLE("maple", CustomTreeGrowers.ASHEN_BIRCH);

    private final String name;
    private final boolean netherFungusLike;
    private final boolean mangroveLike;
    private final TreeGrower treeGrower;

    WoodMaterial(String name, TreeGrower treeGrower)
    { this.name = name; this.treeGrower = treeGrower; this.netherFungusLike = false; this.mangroveLike = false; }

    WoodMaterial(String name, TreeGrower treeGrower, boolean isNetherFungusLike)
    { this.name = name; this.treeGrower = treeGrower; this.netherFungusLike = isNetherFungusLike; this.mangroveLike = false; }

    WoodMaterial(String name, TreeGrower treeGrower, boolean isNetherFungusLike, boolean isMangroveLike)
    { this.name = name; this.treeGrower = treeGrower; this.netherFungusLike = isNetherFungusLike; this.mangroveLike = isMangroveLike; }

    public String getName() { return name; }
    public boolean isLikeNetherFungus() { return netherFungusLike; }
    public boolean isLikeMangroves() { return mangroveLike; }
    public TreeGrower getGrower() { return treeGrower; }
}
