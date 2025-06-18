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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        for (StoneCollection stone : StoneCollection.STONE_COLLECTIONS) { stoneSetDesign(stone); }

        externalModelAssociation01(Registrar.MANUAL_CRUSHER.get(), "manual_crusher");
    }

    protected void externalModelAssociation01(Block b, String template)
    {
        BlockModelBuilder bm = models().withExistingParent(name(b), RLUtility.makeRL(SpiroMod.MOD_ID, template + "_import"));
        getVariantBuilder(b).partialState().setModels(new ConfiguredModel(bm));
    }

    protected BlockModelBuilder finder(String name, String namespace, String path)
    { return models().withExistingParent(name, RLUtility.makeRL(namespace, path)); }

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

    private void stoneSetDesign(StoneCollection set)
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

                        ResourceLocation tex = textureIsStock ? isDefault ? blockTexture(b) : blockTexture(bBase)
                                : RLUtility.makeRL("placeholder");
                        ResourceLocation tex2 = isSandstoneLike && !isCutSandstone ? getBottomTex(tex) :
                                isDeepslateLike || isCutSandstone ?
                                        getTopTex(isCutSandstone ? blockTexture(bAbsBase) : tex) : tex;
                        ResourceLocation tex3 = isSandstoneLike || isDeepslateLike ?
                                getTopTex(isCutSandstone ? blockTexture(bAbsBase) : tex) : tex;
                        ResourceLocation tex4 = isBasaltLike ? getSideTex(tex) : tex;

                        switch (k2)
                        {
                            case DEFAULT ->
                            {
                                builders.add(models()
                                        .cubeBottomTop(key, tex4, tex2, tex3));
                                getVariantBuilder(b).partialState().setModels(
                                        new ConfiguredModel(builders.getFirst()));
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
