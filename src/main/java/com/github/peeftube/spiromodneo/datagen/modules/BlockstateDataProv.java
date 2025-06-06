package com.github.peeftube.spiromodneo.datagen.modules;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.*;
import com.github.peeftube.spiromodneo.util.RLUtility;
import com.github.peeftube.spiromodneo.util.ore.BaseStone;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.function.Supplier;

public class BlockstateDataProv extends BlockStateProvider
{
    public BlockstateDataProv(PackOutput output, ExistingFileHelper eFH)
    { super(output, SpiroMod.MOD_ID, eFH); }

    @Override
    protected void registerStatesAndModels()
    {
        for (MetalCollection metal : MetalCollection.METAL_COLLECTIONS) { metalSetDesign(metal); }
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreSetDesign(ore); }

        externalModelAssociation01(Registrar.MANUAL_CRUSHER.get(), "manual_crusher");
    }

    protected void externalModelAssociation01(Block b, String template)
    {
        BlockModelBuilder bm = models().withExistingParent(name(b), RLUtility.makeRL(SpiroMod.MOD_ID, template + "_import"));
        getVariantBuilder(b).partialState().setModels(new ConfiguredModel(bm));
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
            ResourceLocation r = blockTexture(s.getOreBase().getAssociatedBlock().get());

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
