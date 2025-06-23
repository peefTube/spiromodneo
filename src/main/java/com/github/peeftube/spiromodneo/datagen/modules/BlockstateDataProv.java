package com.github.peeftube.spiromodneo.datagen.modules;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.*;
import com.github.peeftube.spiromodneo.util.RLUtility;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import com.github.peeftube.spiromodneo.util.stone.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.github.peeftube.spiromodneo.util.stone.StoneSetPresets.getPresets;

public class BlockstateDataProv extends BlockStateProvider
{
    public BlockstateDataProv(PackOutput output, ExistingFileHelper eFH)
    { super(output, SpiroMod.MOD_ID, eFH); }

    @Override
    protected void registerStatesAndModels()
    {
        for (MetalCollection metal : MetalCollection.METAL_COLLECTIONS) { metalSetDesign(metal); }
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreSetDesign(ore); }
        for (StoneCollection stone : StoneCollection.STONE_COLLECTIONS) { stoneSetDesign(stone, this.models().existingFileHelper); }
        for (GrassLikeCollection grass : GrassLikeCollection.GRASS_COLLECTIONS) { grassSetDesign(grass); }

        externalModelAssociation01(Registrar.MANUAL_CRUSHER.get(), "manual_crusher");
    }

    protected void externalModelAssociation01(Block b, String template)
    {
        BlockModelBuilder bm = models().withExistingParent(name(b), RLUtility.makeRL(SpiroMod.MOD_ID, template + "_import"));
        getVariantBuilder(b).partialState().setModels(new ConfiguredModel(bm));
    }

    protected BlockModelBuilder finder(String name, String namespace, String path)
    { return models().withExistingParent(name, RLUtility.makeRL(namespace, path)); }

    private void grassSetDesign(GrassLikeCollection set)
    {
        for (Soil s : Soil.values())
        {
            boolean sanityCheckDirt =
                    (!(set.type() == GrassLike.GRASS || set.type() == GrassLike.MYCELIUM));
            boolean sanityCheckNetherrack =
                    (!(set.type() == GrassLike.CRIMSON_NYLIUM || set.type() == GrassLike.WARPED_NYLIUM));
            boolean sanityCheck =
                    s == Soil.DIRT ? sanityCheckDirt : s != Soil.NETHERRACK || sanityCheckNetherrack;

            if (sanityCheck)
            {
                Block b = set.bulkData().get(s).getBlock().get();

                ResourceLocation topOverTex =
                        set.type() == GrassLike.GRASS ? RLUtility.invokeRL("minecraft:block/grass_block_top") :
                        set.type() == GrassLike.MYCELIUM ? RLUtility.invokeRL("minecraft:block/mycelium_top") :
                        set.type() == GrassLike.CRIMSON_NYLIUM ? RLUtility.invokeRL("minecraft:block/crimson_nylium") :
                        set.type() == GrassLike.WARPED_NYLIUM ? RLUtility.invokeRL("minecraft:block/warped_nylium") :
                        set.type() == GrassLike.VITALIUM ? RLUtility.makeRL("block/vitalium_top") :
                        RLUtility.makeRL("placeholder");
                ResourceLocation sideOverTex =
                        set.type() == GrassLike.GRASS ? RLUtility.invokeRL("minecraft:block/grass_block_side_overlay") :
                        set.type() == GrassLike.MYCELIUM ? RLUtility.makeRL("block/overlays/mycelium_side_overlay") :
                        set.type() == GrassLike.CRIMSON_NYLIUM ? RLUtility.makeRL("block/overlays/crimson_nylium_side_overlay") :
                        set.type() == GrassLike.WARPED_NYLIUM ? RLUtility.makeRL("block/overlays/warped_nylium_side_overlay") :
                        set.type() == GrassLike.VITALIUM ? RLUtility.makeRL("block/overlays/vitalium_side_overlay") :
                        RLUtility.makeRL("placeholder");
                ResourceLocation snowTop = RLUtility.invokeRL("minecraft:block/snow");
                ResourceLocation snowSide = RLUtility.makeRL("block/overlays/snow_side_overlay");

                VariantBlockStateBuilder builder = getVariantBuilder(b);

                if (b instanceof SpreadingSnowyDirtBlock)
                {
                    builder = builder.partialState().with(SpreadingSnowyDirtBlock.SNOWY, false).setModels(
                            new ConfiguredModel(models()
                                    .withExistingParent(name(b), "cube_bottom_top")
                           .texture("base", blockTexture(s.getSoil().get()))
                           .element().cube("#base").end()
                           .texture("top", topOverTex).texture("side", sideOverTex).element()
                           .face(Direction.UP).texture("#top").tintindex(1).cullface(Direction.UP).end()
                           .face(Direction.EAST).texture("#side").tintindex(1).cullface(Direction.EAST).end()
                           .face(Direction.WEST).texture("#side").tintindex(1).cullface(Direction.WEST).end()
                           .face(Direction.NORTH).texture("#side").tintindex(1).cullface(Direction.NORTH).end()
                           .face(Direction.SOUTH).texture("#side").tintindex(1).cullface(Direction.SOUTH).end()
                           .end()
                           .texture("particle", blockTexture(s.getSoil().get()))
                            /* .renderType(renTranslucent) */))
                           .partialState().with(SpreadingSnowyDirtBlock.SNOWY, true).setModels(
                            new ConfiguredModel(models()
                                    .withExistingParent(name(b) + "_snowy", "cube_bottom_top")
                           .texture("base", blockTexture(s.getSoil().get()))
                           .element().cube("#base").end()
                           .texture("top", snowTop).texture("side", snowSide).element()
                           .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                           .face(Direction.EAST).texture("#side").cullface(Direction.EAST).end()
                           .face(Direction.WEST).texture("#side").cullface(Direction.WEST).end()
                           .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                           .face(Direction.SOUTH).texture("#side").cullface(Direction.SOUTH).end()
                           .end()
                           .texture("particle", blockTexture(s.getSoil().get()))
                            /* .renderType(renTranslucent) */));
                }
                else
                {
                    builder = builder.partialState().setModels(
                            new ConfiguredModel(models()
                                    .withExistingParent(name(b), "cube_bottom_top")
                                    .texture("base", blockTexture(s.getSoil().get()))
                                    .element().cube("#base").end()
                                    .texture("top", topOverTex).texture("side", sideOverTex).element()
                                    .face(Direction.UP).texture("#top").tintindex(1).cullface(Direction.UP).end()
                                    .face(Direction.EAST).texture("#side").tintindex(1).cullface(Direction.EAST).end()
                                    .face(Direction.WEST).texture("#side").tintindex(1).cullface(Direction.WEST).end()
                                    .face(Direction.NORTH).texture("#side").tintindex(1).cullface(Direction.NORTH).end()
                                    .face(Direction.SOUTH).texture("#side").tintindex(1).cullface(Direction.SOUTH).end()
                                    .end()
                                    .texture("particle", blockTexture(s.getSoil().get()))
                                    /* .renderType(renTranslucent) */));
                }
            }
        }
    }

    protected void metalSetDesign(MetalCollection set)
    {
        boolean ignoreIngotBlocks = false; // For ignoring default ingot and metallic block combinations

        MetalMaterial material = set.getMat();

        if (material == MetalMaterial.IRON || material == MetalMaterial.GOLD || material == MetalMaterial.COPPER
                || material == MetalMaterial.NETHERITE )
        { ignoreIngotBlocks = true; }

        if (!ignoreIngotBlocks)
        {
            // Make this code easier to read, PLEASE..
            Block b = set.ingotData().getMetal().getBlock().get();
            ResourceLocation r = blockTexture(b);

            // This part is extremely easy, fortunately.
            // Initialize this metal block and add it to the model set.
            BlockModelBuilder metal = packedOreBlockBuilder(b, r);
            getVariantBuilder(b).partialState().setModels(new ConfiguredModel(metal));
        }
    }

    protected void oreSetDesign(OreCollection set)
    {
        // Flags for whether we should ignore block-model creation.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks.

        // Prepare set data.
        OreMaterial                     material = set.getMat();
        Map<StoneMaterial, OreCoupling> bulkData = set.getBulkData();

        if (material == OreMaterial.COAL || material == OreMaterial.IRON || material == OreMaterial.COPPER
                || material == OreMaterial.GOLD || material == OreMaterial.LAPIS || material == OreMaterial.REDSTONE
                || material == OreMaterial.EMERALD || material == OreMaterial.DIAMOND)
        { ignoreStone = true; }

        if (material == OreMaterial.GOLD || material == OreMaterial.QUARTZ)
        { ignoreNether = true; }

        ResourceLocation mat = oreOverlayHelper(material.get());

        for (StoneMaterial s : StoneMaterial.values())
        {
            if (((s == StoneMaterial.STONE || s == StoneMaterial.DEEPSLATE) && ignoreStone)
                    || ((s == StoneMaterial.NETHERRACK) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination...

            // Make this code easier to read, PLEASE..
            Block b = bulkData.get(s).block().get();
            ResourceLocation r = blockTexture(s.getOreBase().getOreBase().get());

            // Quick sanity check for smooth sandstone and related
            switch(s.getOreBase())
            {
                case SMS -> r = getTopTex(blockTexture(Blocks.SANDSTONE));
                case SMRS -> r = getTopTex(blockTexture(Blocks.RED_SANDSTONE));
            }

            // Initialize this.
            BlockModelBuilder ore;

            switch (s)
            {
                default -> ore = modularOreBuilder(b, r, mat);
                case NETHERRACK, BASALT ->
                {
                    if (material == OreMaterial.GOLD)
                    { ore = modularOreBuilder(b, r, oreOverlayHelper(material.get(), true)); }
                    else
                    { ore = modularOreBuilder(b, r, mat); }
                }
            }

            getVariantBuilder(b).partialState().setModels(new ConfiguredModel(ore));
        }

        // Raw block and item; assume not vanilla.
        if (!(ignoreStone || ignoreNether))
        {
            // Make this code easier to read, PLEASE..
            Block b = set.getRawOre().getCoupling().getBlock().get();
            ResourceLocation r = blockTexture(b);

            // This part is extremely easy, fortunately.
            // Initialize this raw ore block and add it to the model set.
            BlockModelBuilder ore = packedOreBlockBuilder(b, r);
            getVariantBuilder(b).partialState().setModels(new ConfiguredModel(ore));
        }
    }

    private Block knownCouplingReadBlockFromKeys(StoneData d,
            StoneBlockType k0, StoneVariantType k1, StoneSubBlockType k2)
    { return d.getCouplingForKeys(k0, k1, k2).getBlock().get(); }

    private void stoneSetDesign(StoneCollection set, ExistingFileHelper eFH)
    {
        StoneData data = set.bulkData();
        StoneMaterial mat = set.material();

        for (StoneBlockType k0 : StoneBlockType.values())
        {
            for (StoneVariantType k1 : StoneVariantType.values())
            {
                for (StoneSubBlockType k2: StoneSubBlockType.values())
                {
                    boolean available = data.doesCouplingExistForKeys(k0, k1, k2);
                    String baseKey = ExistingStoneCouplings.getKey(set.material(), k0, k1, StoneSubBlockType.DEFAULT);
                    String key = ExistingStoneCouplings.getKey(set.material(), k0, k1, k2);

                    boolean isDefault = k2 == StoneSubBlockType.DEFAULT;
                    boolean textureIsStock = getPresets().containsKey(isDefault ? key : baseKey);
                    String ns = getPresets().containsKey(baseKey) ? "minecraft" : SpiroMod.MOD_ID;

                    if (available && !getPresets().containsKey(key))
                    {
                        Block            bAbsBase = knownCouplingReadBlockFromKeys(data,
                                StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT);
                        Block               bBase = knownCouplingReadBlockFromKeys(data, k0, k1, StoneSubBlockType.DEFAULT);
                        Block                   b = knownCouplingReadBlockFromKeys(data, k0, k1, k2);
                        List<BlockModelBuilder> builders = new ArrayList<>();

                        boolean isSandstoneLike = mat == StoneMaterial.SANDSTONE || mat == StoneMaterial.RED_SANDSTONE;
                        boolean isBasaltLike = mat == StoneMaterial.BASALT;
                        boolean isDeepslateLike = mat == StoneMaterial.DEEPSLATE || isBasaltLike;

                        boolean isBrickOrTile = !(k0 == StoneBlockType.BRICKS || k0 == StoneBlockType.TILES);
                        isSandstoneLike = isSandstoneLike ? isBrickOrTile : isSandstoneLike;
                        isDeepslateLike = isDeepslateLike ? isBrickOrTile : isDeepslateLike;
                        isBasaltLike = isBasaltLike ? isBrickOrTile : isBasaltLike;

                        boolean isCutSandstone = isSandstoneLike && k0 == StoneBlockType.CUT;

                        boolean isSmooth = k0 != StoneBlockType.SMOOTH;
                        isSandstoneLike = isSmooth && isSandstoneLike;
                        isDeepslateLike = isSmooth && isDeepslateLike;
                        isBasaltLike = isSmooth && isBasaltLike;

                        boolean isColumn = k0 == StoneBlockType.COLUMN;

                        ResourceLocation tex = textureIsStock ? isDefault ? blockTexture(b) : blockTexture(bBase)
                                : eFH.exists(blockTexture(b), ModelProvider.TEXTURE) ? blockTexture(b)
                                : eFH.exists(blockTexture(bBase), ModelProvider.TEXTURE) ? blockTexture(bBase)
                                : RLUtility.makeRL("placeholder");

                        ResourceLocation tex2 = isSandstoneLike && !isCutSandstone ? safeGetBottomTex(tex, eFH) :
                                isDeepslateLike || isCutSandstone || isColumn ?
                                        safeGetTopTex(isCutSandstone ? blockTexture(bAbsBase) : tex, eFH) : tex;
                        ResourceLocation tex3 = isSandstoneLike || isDeepslateLike || isColumn ?
                                safeGetTopTex(isCutSandstone ? blockTexture(bAbsBase) : tex, eFH) : tex;
                        ResourceLocation tex4 = isBasaltLike || isColumn ? safeGetSideTex(tex, eFH) : tex;

                        boolean isBaseStoneOrColumn = !(k0 == StoneBlockType.SMOOTH || k0 == StoneBlockType.POLISHED ||
                                k0 == StoneBlockType.BRICKS || k0 == StoneBlockType.TILES ||
                                k0 == StoneBlockType.COBBLE || k0 == StoneBlockType.CUT);

                        switch (k2)
                        {
                            case DEFAULT ->
                            {
                                if (!(isDeepslateLike && isBaseStoneOrColumn) && !isColumn)
                                {
                                    builders.add(models()
                                            .cubeBottomTop(key, tex4, tex2, tex3));
                                    getVariantBuilder(b).partialState().setModels(
                                            new ConfiguredModel(builders.getFirst()));
                                }
                                else
                                {
                                    builders.add(models()
                                            .withExistingParent(key + "_x", "block/cube_column_uv_locked_x")
                                            .texture("side", tex4)
                                            .texture("end", tex2));
                                    builders.add(models()
                                            .withExistingParent(key + "_y", "block/cube_column_uv_locked_y")
                                            .texture("side", tex4)
                                            .texture("end", tex2));
                                    builders.add(models()
                                            .withExistingParent(key + "_z", "block/cube_column_uv_locked_z")
                                            .texture("side", tex4)
                                            .texture("end", tex2));
                                    getVariantBuilder(b)
                                            .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                                            .setModels(new ConfiguredModel(builders.get(0)))
                                            .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                                            .setModels(new ConfiguredModel(builders.get(1)))
                                            .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                                            .setModels(new ConfiguredModel(builders.get(2)));
                                }
                            }
                            case SLAB ->
                            {
                                builders.add(models().slab(key, tex4, tex2, tex3));
                                builders.add(models().slabTop(key + "_top", tex4, tex2, tex3));
                                builders.add(finder(key + "_double", ns, "block/" + baseKey));
                                getVariantBuilder(b)
                                        .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM)
                                        .setModels(new ConfiguredModel(builders.getFirst()))
                                        .partialState().with(SlabBlock.TYPE, SlabType.TOP)
                                        .setModels(new ConfiguredModel(builders.get(1)))
                                        .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE)
                                        .setModels(new ConfiguredModel(builders.get(2)));
                            }
                            case STAIRS ->
                            {
                                builders.add(models().stairs(key, tex4, tex2, tex3));
                                builders.add(models().stairsInner(key + "_inner", tex4, tex2, tex3));
                                builders.add(models().stairsOuter(key + "_outer", tex4, tex2, tex3));
                                stairsBuilder(b, builders);
                            }
                            case WALL ->
                            {
                                builders.add(models().wallSide(key, tex2));
                                builders.add(models().wallSideTall(key + "_tall", tex2));
                                builders.add(models().wallPost(key + "_post", tex2));
                                builders.add(models().wallInventory(key + "_inv", tex2));
                                wallsBuilder(b, builders);
                            }
                            case BUTTON ->
                            {
                                builders.add(models().button(key, tex2));
                                builders.add(models().buttonPressed(key + "_pressed", tex2));
                                builders.add(models().buttonInventory(key + "_inv", tex2));
                                buttonsBuilder(b, builders);
                            }
                            case PRESSURE_PLATE ->
                            {
                                builders.add(models().pressurePlate(key, tex2));
                                builders.add(models().pressurePlateDown(key + "_pressed", tex2));
                                getVariantBuilder(b).partialState()
                                    .with(PressurePlateBlock.POWERED, Boolean.FALSE)
                                        .setModels(new ConfiguredModel(builders.getFirst()))
                                    .partialState()
                                    .with(PressurePlateBlock.POWERED, Boolean.TRUE)
                                        .setModels(new ConfiguredModel(builders.getLast()));
                            }
                        }
                    }
                }
            }
        }
    }

    private void buttonsBuilder(Block b, List<BlockModelBuilder> builders)
    {
        VariantBlockStateBuilder buttonsBuilder = getVariantBuilder(b);

        for (AttachFace f : AttachFace.values())
        {
            for (Direction d : Direction.values())
            {
                if (!(d == Direction.DOWN || d == Direction.UP))
                {
                    boolean uvLock = f == AttachFace.WALL;
                    int x = uvLock ? 90 : f == AttachFace.CEILING ? 180 : 0;
                    int y = 0;

                    switch (d)
                    {
                        case EAST -> { y = (f == AttachFace.CEILING) ? 270 : 90; }
                        case NORTH -> { y = (f == AttachFace.CEILING) ? 180 : 0; }
                        case SOUTH -> { y = (f == AttachFace.CEILING) ? 0 : 180; }
                        case WEST -> { y = (f == AttachFace.CEILING) ? 90 : 270; }
                    }

                    buttonsBuilder = buttonsBuilder.partialState()
                            .with(ButtonBlock.FACE, f)
                            .with(ButtonBlock.FACING, d)
                            .with(ButtonBlock.POWERED, Boolean.FALSE)
                            .setModels(new ConfiguredModel(builders.getFirst(), x, y, uvLock))
                            .partialState()
                            .with(ButtonBlock.FACE, f)
                            .with(ButtonBlock.FACING, d)
                            .with(ButtonBlock.POWERED, Boolean.TRUE)
                            .setModels(new ConfiguredModel(builders.get(1), x, y, uvLock));
                }
            }
        }
    }

    private void wallsBuilder(Block b, List<BlockModelBuilder> builders)
    {
        MultiPartBlockStateBuilder wallsBuilder = getMultipartBuilder(b);

        for (WallSide sv : WallSide.values())
        {
            if (sv != WallSide.NONE)
            {
                for (Direction d : Direction.values())
                {
                    switch (d)
                    {
                        case UP ->
                        {
                            if (sv == WallSide.LOW)
                            {
                                wallsBuilder = wallsBuilder.part()
                                           .modelFile(new ConfiguredModel(builders.get(2)).model)
                                           .addModel()
                                           .condition(WallBlock.UP, Boolean.TRUE).end();
                            }
                        }

                        case SOUTH, NORTH, EAST, WEST ->
                        {
                            EnumProperty<WallSide> wall = d == Direction.SOUTH ? WallBlock.SOUTH_WALL :
                                    d == Direction.NORTH ? WallBlock.NORTH_WALL :
                                            d == Direction.EAST ? WallBlock.EAST_WALL :
                                                    WallBlock.WEST_WALL;

                            int y = wall == WallBlock.SOUTH_WALL ? 180 :
                                    wall == WallBlock.WEST_WALL ? 270 :
                                    wall == WallBlock.NORTH_WALL ? 0 : 90;

                            switch (sv)
                            {
                                case LOW ->
                                {
                                    wallsBuilder = wallsBuilder.part()
                                           .modelFile(new ConfiguredModel(builders.getFirst()).model)
                                           .rotationY(y).uvLock(y != 0)
                                           .addModel()
                                           .condition(wall, sv).end();
                                }
                                case TALL ->
                                {
                                    wallsBuilder = wallsBuilder.part()
                                           .modelFile(new ConfiguredModel(builders.get(1)).model)
                                           .rotationY(y).uvLock(y != 0)
                                           .addModel()
                                           .condition(wall, sv).end();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void stairsBuilder(Block b, List<BlockModelBuilder> builders)
    {
        VariantBlockStateBuilder stairsBuilder = getVariantBuilder(b);
        for (Direction d : Direction.values())
        {
            if (!(d == Direction.DOWN || d == Direction.UP))
            {
                for (StairsShape s : StairsShape.values())
                {
                    for (Half h : Half.values())
                    {
                        boolean isBottom = h == Half.BOTTOM;

                        int x = 0;
                        int y = 0;
                        int index = 0;

                        boolean isInner = s == StairsShape.INNER_LEFT || s == StairsShape.INNER_RIGHT;

                        switch(d)
                        {
                            case EAST ->
                            {
                                switch(s)
                                {
                                    case INNER_LEFT, OUTER_LEFT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 270 : 0; index = isInner ? 1 : 2; }
                                    case INNER_RIGHT, OUTER_RIGHT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 0 : 90; index = isInner ? 1 : 2; }
                                    case STRAIGHT -> { x = isBottom ? 0 : 180; }
                                }
                            }
                            case WEST ->
                            {
                                switch(s)
                                {
                                    case INNER_LEFT, OUTER_LEFT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 90 : 180; index = isInner ? 1 : 2; }
                                    case INNER_RIGHT, OUTER_RIGHT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 180 : 270; index = isInner ? 1 : 2; }
                                    case STRAIGHT -> { x = isBottom ? 0 : 180; y = 180; }
                                }
                            }
                            case NORTH ->
                            {
                                switch(s)
                                {
                                    case INNER_LEFT, OUTER_LEFT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 180 : 270; index = isInner ? 1 : 2; }
                                    case INNER_RIGHT, OUTER_RIGHT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 270 : 0; index = isInner ? 1 : 2; }
                                    case STRAIGHT -> { x = isBottom ? 0 : 180; y = 270; }
                                }
                            }
                            case SOUTH ->
                            {
                                switch(s)
                                {
                                    case INNER_LEFT, OUTER_LEFT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 0 : 90; index = isInner ? 1 : 2; }
                                    case INNER_RIGHT, OUTER_RIGHT ->
                                    { x = isBottom ? 0 : 180; y = isBottom ? 90 : 180; index = isInner ? 1 : 2; }
                                    case STRAIGHT -> { x = isBottom ? 0 : 180; y = 90; }
                                }
                            }
                        }

                        boolean uvLock = (!(x == 0 && y == 0));

                        stairsBuilder = stairsBuilder.partialState()
                        .with(StairBlock.FACING, d)
                        .with(StairBlock.SHAPE, s)
                        .with(StairBlock.HALF, h)
                        .setModels(new ConfiguredModel(builders.get(index), x, y, uvLock));
                    }
                }
            }
        }
    }

    // These are copied from BlockStateProvider. I do not claim ownership of these!
    private ResourceLocation key(Block block)
    { return BuiltInRegistries.BLOCK.getKey(block); }

    private String name(Block block)
    { return key(block).getPath(); }
    // == Non-ownership block ends.

    // Translucent rendering.
    private static final String renTranslucent = /* "cutout"; */ "translucent";

    // Ore overlay location helper.
    protected ResourceLocation oreOverlayHelper(String material)
    { return oreOverlayHelper(material, false); }

    protected ResourceLocation oreOverlayHelper(String material, boolean usingNetherVariant)
    {
        String p = "block/overlays/"; String s = "_overlay";
        String n = (usingNetherVariant ? "nether_" : "");
        return RLUtility.makeRL(p + n + material + s);
    }

    private ResourceLocation getBottomTex(ResourceLocation block) { return RLUtility.invokeRL(block.toString() + "_bottom"); }
    private ResourceLocation getTopTex(ResourceLocation block) { return RLUtility.invokeRL(block.toString() + "_top"); }
    private ResourceLocation getFrontTex(ResourceLocation block) { return RLUtility.invokeRL(block.toString() + "_front"); }
    private ResourceLocation getSideTex(ResourceLocation block) { return RLUtility.invokeRL(block.toString() + "_side"); }

    private ResourceLocation safeGetBottomTex(ResourceLocation block, ExistingFileHelper eFH)
    { return eFH.exists(getBottomTex(block), ModelProvider.TEXTURE) ? getBottomTex(block) : block; }
    private ResourceLocation safeGetTopTex(ResourceLocation block, ExistingFileHelper eFH)
    { return eFH.exists(getTopTex(block), ModelProvider.TEXTURE) ? getTopTex(block) : block; }
    private ResourceLocation safeGetFrontTex(ResourceLocation block, ExistingFileHelper eFH)
    { return eFH.exists(getFrontTex(block), ModelProvider.TEXTURE) ? getFrontTex(block) : block; }
    private ResourceLocation safeGetSideTex(ResourceLocation block, ExistingFileHelper eFH)
    { return eFH.exists(getSideTex(block), ModelProvider.TEXTURE) ? getSideTex(block) : block; }

    protected BlockModelBuilder modularOreBuilder(Block block, ResourceLocation baseTex, ResourceLocation oreTex)
    { return modularOreBuilder(block, baseTex, oreTex, 0); }

    protected BlockModelBuilder modularOreBuilder(Block block, ResourceLocation baseTex,
            ResourceLocation oreTex, int type)
    {
        switch(type)
        {
            default ->
            {
                return models().withExistingParent(name(block), "cube")
                               .texture("base", baseTex).element().cube("#base").end()
                               .texture("over", oreTex).element().cube("#over").end()
                               .texture("particle", baseTex)
                               /* .renderType(renTranslucent) */;
            }
            case 1 ->
            {
                return models().withExistingParent(name(block), "cube_bottom_top")
                               .texture("base", baseTex)
                               .texture("top", getTopTex(baseTex))
                               .texture("bottom", getBottomTex(baseTex))
                               .element()
                               .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                               .face(Direction.DOWN).texture("#bottom").cullface(Direction.DOWN).end()
                               .face(Direction.EAST).texture("#base").cullface(Direction.EAST).end()
                               .face(Direction.WEST).texture("#base").cullface(Direction.WEST).end()
                               .face(Direction.NORTH).texture("#base").cullface(Direction.NORTH).end()
                               .face(Direction.SOUTH).texture("#base").cullface(Direction.SOUTH).end()
                               .end()
                               .texture("over", oreTex).element().cube("#over").end()
                               .texture("particle", getBottomTex(baseTex))
                               /* .renderType(renTranslucent) */;
            }
        }
    }

    protected BlockModelBuilder packedOreBlockBuilder(Block block, ResourceLocation tex)
    {
        return models().withExistingParent(name(block), "cube")
                       .texture("base", tex).element().cube("#base").end()
                       .texture("particle", tex);
    }
}
