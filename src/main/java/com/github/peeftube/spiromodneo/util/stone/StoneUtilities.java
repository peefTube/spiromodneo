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
        return null;
    }

    private static PolishedStoneData gatherPolishedData(StoneMaterial mat)
    {
        return null;
    }

    private static StoneBrickData gatherBricksData(StoneMaterial mat)
    {
        return null;
    }

    private static StoneTileData gatherTileData(StoneMaterial mat)
    {
        return null;
    }

    private static CutStoneData gatherCutData(StoneMaterial mat)
    {
        return null;
    }

    private static MiscStoneData gatherMiscData(StoneMaterial mat)
    {
        return null;
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
                String type1A = "_" + type1;
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
        }

        return null;
    }

    private static BlockState getDefaultStateForMaterialAndType(StoneMaterial mat, String type)
    { return getDefaultStateForMaterialAndTypeWithFallback(mat, type, ""); }

    private static BlockState getDefaultStateForMaterialAndTypeWithFallback(StoneMaterial mat,
            String type, String fallback)
    {
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
                String fallbackModified = fallback.toLowerCase().substring(0, fallback.length() - 7);
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
