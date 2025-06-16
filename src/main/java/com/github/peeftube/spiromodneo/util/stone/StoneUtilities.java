package com.github.peeftube.spiromodneo.util.stone;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.StoneMaterial;
import com.github.peeftube.spiromodneo.util.DataCheckResult;
import com.github.peeftube.spiromodneo.util.generic.GenBlockItemCoupling;
import com.github.peeftube.spiromodneo.util.stone.subdata.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

public interface StoneUtilities
{
    static StoneData generateData(StoneMaterial mat)
    {
        RawStoneData      raw      = gatherRawStoneData(mat);
        CobblestoneData   cobble   = gatherCobbleData(mat);
        SmoothStoneData   smooth   = gatherSmoothData(mat);
        PolishedStoneData polished = gatherPolishedData(mat);
        StoneBrickData    bricks   = gatherBricksData(mat);
        StoneTileData     tile     = gatherTileData(mat);
        CutStoneData      cut      = gatherCutData(mat);
        MiscStoneData     misc     = gatherMiscData(mat);

        return new StoneData(mat.get(), raw, cobble, smooth, polished, bricks, tile, cut, misc);
    }

    private static RawStoneData gatherRawStoneData(StoneMaterial mat)
    {
        return new RawStoneData(
                safeParseFromID(mat.get().toLowerCase(), mat, "raw.base"),
                safeParseFromID(mat.get().toLowerCase() + "_slab", mat, "raw.slab"),
                safeParseFromID(mat.get().toLowerCase() + "_stairs", mat, "raw.stairs"),
                safeParseFromID(mat.get().toLowerCase() + "_button", mat, "raw.button"),
                safeParseFromID(mat.get().toLowerCase() + "_pressure_plate", mat, "raw.plate"),
                safeParseFromID(mat.get().toLowerCase() + "_wall", mat, "raw.wall")
        );
    }

    private static CobblestoneData gatherCobbleData(StoneMaterial mat)
    {
        String identifierBase = mat == StoneMaterial.STONE ? "cobblestone" : "cobbled_" + mat.get();

        return new CobblestoneData(
                safeParseFromID(identifierBase.toLowerCase(), mat, "cobble.d.base"),
                safeParseFromID("mossy_" + identifierBase.toLowerCase(), mat, "cobble.m.base"),
                safeParseFromID(identifierBase.toLowerCase() + "_slab", mat, "cobble.d.slab"),
                safeParseFromID("mossy_" + identifierBase.toLowerCase() + "_slab", mat, "cobble.m.slab"),
                safeParseFromID(identifierBase.toLowerCase() + "_stairs", mat, "cobble.d.stairs"),
                safeParseFromID("mossy_" + identifierBase.toLowerCase() + "_stairs", mat, "cobble.m.stairs"),
                safeParseFromID(identifierBase.toLowerCase() + "_wall", mat, "cobble.d.wall"),
                safeParseFromID("mossy_" + identifierBase.toLowerCase() + "_wall", mat, "cobble.m.wall")
        );
    }

    private static SmoothStoneData gatherSmoothData(StoneMaterial mat)
    {
        String identifier = "smooth_" + mat.get();

        return new SmoothStoneData(
                safeParseFromID(identifier.toLowerCase(), mat, "smooth.base"),
                safeParseFromID(identifier.toLowerCase() + "_slab", mat, "smooth.slab"),
                safeParseFromID(identifier.toLowerCase() + "_stairs", mat, "smooth.stairs"),
                safeParseFromID("chiseled_" + identifier.toLowerCase(), mat, "smooth.chiseled")
        );
    }

    private static PolishedStoneData gatherPolishedData(StoneMaterial mat)
    {
        String identifier = "polished_" + mat.get();

        return new PolishedStoneData(
                safeParseFromID(identifier.toLowerCase(), mat, "polished.base"),
                safeParseFromID(identifier.toLowerCase() + "_slab", mat, "polished.slab"),
                safeParseFromID(identifier.toLowerCase() + "_stairs", mat, "polished.stairs")
        );
    }

    private static StoneBrickData gatherBricksData(StoneMaterial mat)
    {
        String identifier = mat.get() + "_bricks";
        String identifierSolo = identifier.substring(0, identifier.length() - 1);

        return new StoneBrickData(
                safeParseFromID(identifier.toLowerCase(), mat, "bricks.d.base"),
                safeParseFromID("mossy_" + identifier.toLowerCase(), mat, "bricks.m.base"),
                safeParseFromID("cracked_" + identifier.toLowerCase(), mat, "bricks.c.base"),
                safeParseFromID("chiseled_" + identifier.toLowerCase(), mat, "bricks.d.chiseled"),
                safeParseFromID(identifierSolo.toLowerCase() + "_slab", mat, "bricks.d.slab"),
                safeParseFromID("mossy_" + identifierSolo.toLowerCase() + "_slab", mat, "bricks.m.slab"),
                safeParseFromID("cracked_" + identifierSolo.toLowerCase() + "_slab", mat, "bricks.c.slab"),
                safeParseFromID(identifierSolo.toLowerCase() + "_stairs", mat, "bricks.d.stairs"),
                safeParseFromID("mossy_" + identifierSolo.toLowerCase() + "_stairs", mat, "bricks.m.stairs"),
                safeParseFromID("cracked_" + identifierSolo.toLowerCase() + "_stairs", mat, "bricks.c.stairs"),
                safeParseFromID(identifierSolo.toLowerCase() + "_wall", mat, "bricks.d.wall"),
                safeParseFromID("mossy_" + identifierSolo.toLowerCase() + "_wall", mat, "bricks.m.wall"),
                safeParseFromID("cracked_" + identifierSolo.toLowerCase() + "_wall", mat, "bricks.c.wall")
        );
    }

    private static StoneTileData gatherTileData(StoneMaterial mat)
    {
        String identifier = mat.get() + "_tiles";
        String identifierSolo = identifier.substring(0, identifier.length() - 1);

        return new StoneTileData(
                safeParseFromID(identifier.toLowerCase(), mat, "tile.d.base"),
                safeParseFromID("mossy_" + identifier.toLowerCase(), mat, "tile.m.base"),
                safeParseFromID("cracked_" + identifier.toLowerCase(), mat, "tile.c.base"),
                safeParseFromID("small_" + identifier.toLowerCase(), mat, "tile.s.base"),
                safeParseFromID("chiseled_" + identifier.toLowerCase(), mat, "tile.d.chiseled"),
                safeParseFromID(identifierSolo.toLowerCase() + "_slab", mat, "tile.d.slab"),
                safeParseFromID("mossy_" + identifierSolo.toLowerCase() + "_slab", mat, "tile.m.slab"),
                safeParseFromID("cracked_" + identifierSolo.toLowerCase() + "_slab", mat, "tile.c.slab"),
                safeParseFromID("small_" + identifierSolo.toLowerCase() + "_slab", mat, "tile.s.slab"),
                safeParseFromID(identifierSolo.toLowerCase() + "_stairs", mat, "tile.d.stairs"),
                safeParseFromID("mossy_" + identifierSolo.toLowerCase() + "_stairs", mat, "tile.m.stairs"),
                safeParseFromID("cracked_" + identifierSolo.toLowerCase() + "_stairs", mat, "tile.c.stairs"),
                safeParseFromID("small_" + identifierSolo.toLowerCase() + "_stairs", mat, "tile.s.stairs"),
                safeParseFromID(identifierSolo.toLowerCase() + "_wall", mat, "tile.d.wall"),
                safeParseFromID("mossy_" + identifierSolo.toLowerCase() + "_wall", mat, "tile.m.wall"),
                safeParseFromID("cracked_" + identifierSolo.toLowerCase() + "_wall", mat, "tile.c.wall"),
                safeParseFromID("small_" + identifierSolo.toLowerCase() + "_wall", mat, "tile.s.wall")
        );
    }

    private static CutStoneData gatherCutData(StoneMaterial mat)
    {
        String identifier = "cut_" + mat.get();

        return new CutStoneData(
                safeParseFromID(identifier.toLowerCase(), mat, "cut.base")
        );
    }

    private static MiscStoneData gatherMiscData(StoneMaterial mat)
    {
        return new MiscStoneData(
                safeParseFromID(mat.get().toLowerCase() + "_pillar", mat, "misc.pillar")
        );
    }

    private static GenBlockItemCoupling createCouplingFromMatAndType(StoneMaterial mat, String type)
    {
        String name = mat.get().toLowerCase();

        String[] typeSets = type.split("\\.");
        String type0 = typeSets[0].toLowerCase();
        String type1 = typeSets[1].toLowerCase();
        String type2 = typeSets.length >= 3 ? typeSets[2].toLowerCase() : "";

        Supplier<Block> b;

        switch(type0)
        {
            case "raw" ->
            {
                String type1A = "_" + (type1.equals("plate") ? "pressure_plate" : type1);
                String casedName = name + type1A;

                switch(type1)
                {
                    case "base" ->
                    {
                        b = Registrar.BLOCKS.register(name, () -> new Block(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "slab" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new SlabBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "stairs" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new StairBlock(
                                Objects.requireNonNull(getDefaultStateForMaterialAndType(mat, type0)),
                                mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "button" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new ButtonBlock(
                                BlockSetType.STONE,
                                20,
                                mat.getOreBase().getProps().noCollission().pushReaction(PushReaction.DESTROY)));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "plate" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new PressurePlateBlock(
                                BlockSetType.STONE,
                                mat.getOreBase().getProps()
                                   .forceSolidOn()
                                   .noCollission()
                                   .requiresCorrectToolForDrops()
                                   .pushReaction(PushReaction.DESTROY)));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "wall" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new WallBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }
                }
            }

            case "cobble" ->
            {
                String type1A = type1.equals("d") ? "" : "mossy_";
                String cobbleName = mat == StoneMaterial.STONE ? "cobblestone" : "cobbled_" + name;
                String type2A = "_" + (type2.equals("plate") ? "pressure_plate" : type2);
                String casedName = type1A + cobbleName + (type2.equals("base") ? "" : type2A);

                switch(type2)
                {
                    case "base" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new Block(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "slab" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new SlabBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "stairs" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new StairBlock(
                                Objects.requireNonNull(
                                        getDefaultStateForMaterialAndTypeWithFallback(
                                                mat, type1A + type0, casedName)),
                                mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "button" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new ButtonBlock(
                                BlockSetType.STONE,
                                20,
                                mat.getOreBase().getProps().noCollission().pushReaction(PushReaction.DESTROY)));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "plate" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new PressurePlateBlock(
                                BlockSetType.STONE,
                                mat.getOreBase().getProps()
                                   .forceSolidOn()
                                   .noCollission()
                                   .requiresCorrectToolForDrops()
                                   .pushReaction(PushReaction.DESTROY)));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "wall" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new WallBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    default -> { throw new NoSuchElementException("Case parsing error"); }
                }
            }

            case "smooth", "polished", "cut", "misc" ->
            {
                boolean isChiseled = type1.equals("chiseled");

                String type0A = type0 + "_";
                String type1A = isChiseled ? type1 + "_" : "_" + type1;
                String casedName = isChiseled ? type1A + type0A + name : type0A + name + type1A;

                switch(type1)
                {
                    case "base", "chiseled" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new Block(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "slab" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new SlabBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "stairs" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new StairBlock(
                                Objects.requireNonNull(getDefaultStateForMaterialAndType(mat, type0)),
                                mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "pillar" ->
                    {
                        b = Registrar.BLOCKS.register(casedName,
                                () -> new RotatedPillarBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }
                }
            }

            case "bricks", "tile" ->
            {
                boolean isChiseled = type2.equals("chiseled");

                String type1A = type1.equals("c") ? "cracked_" : type1.equals("d") ? "" :
                        type1.equals("s") ? "small_" : "mossy_";

                String finalizedName =
                        type0.equals("bricks") ?
                        type2.equals("base") ? name + "_bricks" : name + "_brick" :
                        type2.equals("base") ? name + "_tiles" : name + "_tile";

                String type2A = type2.equals("base") ? "" : isChiseled ? "" : "_" + type2;

                String casedName = isChiseled ? "chiseled_" + type1A + finalizedName + type2A : type1A + finalizedName + type2A;

                switch(type2)
                {
                    case "base", "chiseled" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new Block(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "slab" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new SlabBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "stairs" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new StairBlock(
                                Objects.requireNonNull(
                                        getDefaultStateForMaterialAndTypeWithFallback(
                                                mat, type1A + type0, casedName)),
                                mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }

                    case "wall" ->
                    {
                        b = Registrar.BLOCKS.register(casedName, () -> new WallBlock(mat.getOreBase().getProps()));
                        return GenBlockItemCoupling.createNewBasic(b);
                    }
                }
            }
        }

        return null;
    }

    private static BlockState getDefaultStateForMaterialAndType(StoneMaterial mat, String type)
    { return getDefaultStateForMaterialAndTypeWithFallback(mat, type, ""); }

    private static BlockState getDefaultStateForMaterialAndTypeWithFallback(StoneMaterial mat,
            String type, String fallback)
    {
        String fallbackModified = (!fallback.isEmpty()) ? fallback.toLowerCase().substring(0, fallback.length() - 7)
                : fallback;

        switch(type)
        {
            case "raw" ->
            {
                switch(mat)
                {
                    case SANDSTONE -> { return Blocks.SANDSTONE.defaultBlockState(); }
                    case RED_SANDSTONE -> { return Blocks.RED_SANDSTONE.defaultBlockState(); }
                    case BASALT -> { return Blocks.BASALT.defaultBlockState(); }

                    default -> { return mat.getOreBase().getAssociatedBlock().get().defaultBlockState(); }
                }
            }

            case "cobble", "mossy_cobble" ->
            {
                DataCheckResult check = checkIfNullThenPass(
                        BuiltInRegistries.BLOCK.get(
                                ResourceLocation.fromNamespaceAndPath(
                                        SpiroMod.MOD_ID, fallbackModified)));

                switch(mat)
                {
                    case STONE -> { return type.startsWith("mossy_") ?
                            Blocks.MOSSY_COBBLESTONE.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState(); }
                    case DEEPSLATE -> { return (type.startsWith("mossy_") && check.getResult()) ? BuiltInRegistries.BLOCK.get(
                                    ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID, fallbackModified)).defaultBlockState() :
                            Blocks.COBBLED_DEEPSLATE.defaultBlockState(); }
                    default -> { return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                            fallbackModified)).defaultBlockState(); }
                }
            }

            case "smooth" ->
            {
                switch(mat)
                {
                    case STONE -> { return Blocks.SMOOTH_STONE.defaultBlockState(); }
                    case BASALT -> { return Blocks.SMOOTH_BASALT.defaultBlockState(); }

                    default -> { return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                            fallbackModified)).defaultBlockState(); }
                }
            }

            case "polished" ->
            {
                switch(mat)
                {
                    case ANDESITE -> { return Blocks.POLISHED_ANDESITE.defaultBlockState(); }
                    case GRANITE -> { return Blocks.POLISHED_GRANITE.defaultBlockState(); }
                    case DIORITE -> { return Blocks.POLISHED_DIORITE.defaultBlockState(); }

                    default -> { return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                            fallbackModified)).defaultBlockState(); }
                }
            }

            case "bricks", "mossy_bricks", "cracked_bricks" ->
            {
                DataCheckResult check = checkIfNullThenPass(
                        BuiltInRegistries.BLOCK.get(
                                ResourceLocation.fromNamespaceAndPath(
                                        SpiroMod.MOD_ID, fallbackModified)));

                switch(mat)
                {
                    case STONE ->
                    {
                        return type.startsWith("mossy_") ? Blocks.MOSSY_STONE_BRICKS.defaultBlockState() :
                                type.startsWith("cracked_") ? Blocks.CRACKED_STONE_BRICKS.defaultBlockState() :
                                        Blocks.STONE_BRICKS.defaultBlockState();
                    }
                    case DEEPSLATE ->
                    {
                        return (type.startsWith("mossy_") && check.getResult()) ?
                                BuiltInRegistries.BLOCK.get(
                                        ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                                                fallbackModified)).defaultBlockState() :
                                type.startsWith("cracked_") ? Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState() :
                                        Blocks.DEEPSLATE_BRICKS.defaultBlockState();
                    }
                    case TUFF -> {
                        return ((type.startsWith("mossy_") || type.startsWith("cracked_")) && check.getResult()) ?
                            BuiltInRegistries.BLOCK.get(
                                    ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                                            fallbackModified)).defaultBlockState() :
                                Blocks.TUFF_BRICKS.defaultBlockState();
                    }

                    default -> { return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                            fallbackModified)).defaultBlockState(); }
                }
            }

            case "tile", "mossy_tile", "cracked_tile", "small_tile" ->
            {
                DataCheckResult check = checkIfNullThenPass(
                        BuiltInRegistries.BLOCK.get(
                                ResourceLocation.fromNamespaceAndPath(
                                        SpiroMod.MOD_ID, fallbackModified)));

                switch(mat)
                {
                    case DEEPSLATE ->
                    {
                        return type.startsWith("cracked_") ? Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState() :
                                ((type.startsWith("mossy_") || type.startsWith("small_")) && check.getResult()) ?
                                        BuiltInRegistries.BLOCK.get(
                                                ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                                                        fallbackModified)).defaultBlockState() :
                                        Blocks.DEEPSLATE_TILES.defaultBlockState();

                    }

                    default -> { return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(SpiroMod.MOD_ID,
                            fallbackModified)).defaultBlockState(); }
                }
            }

            default -> { return null; }
        }
    }

    static DataCheckResult checkIfNullThenPass(Object toCheck)
    { return new DataCheckResult(Objects.nonNull(toCheck), toCheck); }

    static GenBlockItemCoupling safeParseFromID(String identifier, StoneMaterial mat, String fallbackType)
    {
        return checkIfNullThenPass(GenBlockItemCoupling.findFromID(identifier)).getResult() ?
                GenBlockItemCoupling.findFromID(identifier) :
                createCouplingFromMatAndType(mat, fallbackType);
    }
}
