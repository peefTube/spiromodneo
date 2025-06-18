package com.github.peeftube.spiromodneo.util.stone;

import com.github.peeftube.spiromodneo.core.init.registry.data.StoneMaterial;
import com.github.peeftube.spiromodneo.util.GenericBlockItemCoupling;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class StoneSetPresets
{
    public static Map<String, GenericBlockItemCoupling> getPresets()
    {
        Map<String, GenericBlockItemCoupling> presets = new HashMap<>();

        presets = stoneMaterialPresets(presets);
        presets = deepslateMaterialPresets(presets);
        presets = overworldStoneVariantMaterialPresets(StoneMaterial.DIORITE,
                  overworldStoneVariantMaterialPresets(StoneMaterial.GRANITE,
                  overworldStoneVariantMaterialPresets(StoneMaterial.ANDESITE, presets)));

        presets = sandstoneMaterialPresets(StoneMaterial.RED_SANDSTONE,
                  sandstoneMaterialPresets(StoneMaterial.SANDSTONE, presets));

        presets = tuffMaterialPresets(presets);
        presets = basaltMaterialPresets(presets);
        presets = endstoneMaterialPresets(presets);

        // Dripstone only has one main block.
        presets.put(
                ExistingStoneCouplings
                        .getKey(StoneMaterial.DRIPSTONE, StoneBlockType.BASE,
                                StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                new GenericBlockItemCoupling(() -> Blocks.DRIPSTONE_BLOCK, () -> Items.DRIPSTONE_BLOCK));

        // Calcite only has one main block.
        presets.put(
                ExistingStoneCouplings
                        .getKey(StoneMaterial.CALCITE, StoneBlockType.BASE,
                                StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                new GenericBlockItemCoupling(() -> Blocks.CALCITE, () -> Items.CALCITE));

        // Netherrack only has one main block.
        presets.put(
                ExistingStoneCouplings
                        .getKey(StoneMaterial.NETHERRACK, StoneBlockType.BASE,
                                StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                new GenericBlockItemCoupling(() -> Blocks.NETHERRACK, () -> Items.NETHERRACK));

        return presets;
    }

    private static Map<String, GenericBlockItemCoupling> stoneMaterialPresets(Map<String, GenericBlockItemCoupling> presets)
    {
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.STONE);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.StoneCouplings.STONE_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.StoneCouplings.STONE_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.BUTTON),
                ExistingStoneCouplings.StoneCouplings.STONE_BUTTON);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.PRESSURE_PLATE),
                ExistingStoneCouplings.StoneCouplings.STONE_PRESSURE_PLATE);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.COBBLESTONE);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.StoneCouplings.COBBLESTONE_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.StoneCouplings.COBBLESTONE_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.StoneCouplings.COBBLESTONE_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.MOSSY, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.MOSSY_COBBLESTONE);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.MOSSY, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.StoneCouplings.MOSSY_COBBLESTONE_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.MOSSY, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.StoneCouplings.MOSSY_COBBLESTONE_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.COBBLE, StoneVariantType.MOSSY, StoneSubBlockType.WALL),
                ExistingStoneCouplings.StoneCouplings.MOSSY_COBBLESTONE_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.SMOOTH, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.SMOOTH_STONE);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.SMOOTH, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.StoneCouplings.SMOOTH_STONE_SLAB);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.STONE_BRICKS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.StoneCouplings.STONE_BRICK_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.StoneCouplings.STONE_BRICK_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.StoneCouplings.STONE_BRICK_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.MOSSY, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.MOSSY_STONE_BRICKS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.MOSSY, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.StoneCouplings.MOSSY_STONE_BRICK_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.MOSSY, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.StoneCouplings.MOSSY_STONE_BRICK_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.MOSSY, StoneSubBlockType.WALL),
                ExistingStoneCouplings.StoneCouplings.MOSSY_STONE_BRICK_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.CRACKED, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.CRACKED_STONE_BRICKS);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.STONE, StoneBlockType.BRICKS, StoneVariantType.CHISELED, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.StoneCouplings.CHISELED_STONE_BRICKS);

        return presets;
    }

    private static Map<String, GenericBlockItemCoupling> deepslateMaterialPresets(Map<String, GenericBlockItemCoupling> presets)
    {
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.DeepslateCouplings.COBBLED_DEEPSLATE);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.DeepslateCouplings.COBBLED_DEEPSLATE_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.DeepslateCouplings.COBBLED_DEEPSLATE_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.COBBLE, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.DeepslateCouplings.COBBLED_DEEPSLATE_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_BRICKS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_BRICK_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_BRICK_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_BRICK_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.TILES, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_TILES);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.TILES, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_TILE_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.TILES, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_TILE_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.TILES, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.DeepslateCouplings.DEEPSLATE_TILE_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.TILES, StoneVariantType.CRACKED, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.DeepslateCouplings.CRACKED_DEEPSLATE_TILES);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.DeepslateCouplings.POLISHED_DEEPSLATE);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.DeepslateCouplings.POLISHED_DEEPSLATE_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.DeepslateCouplings.POLISHED_DEEPSLATE_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.DeepslateCouplings.POLISHED_DEEPSLATE_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.DEEPSLATE, StoneBlockType.POLISHED, StoneVariantType.CHISELED, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.DeepslateCouplings.CHISELED_DEEPSLATE);

        return presets;
    }

    private static Map<String, GenericBlockItemCoupling> overworldStoneVariantMaterialPresets
            (StoneMaterial mat, Map<String, GenericBlockItemCoupling> presets)
    {
        ExistingStoneCouplings.OverworldVariantCouplings.Variant variant =
                mat == StoneMaterial.ANDESITE ? new ExistingStoneCouplings.OverworldVariantCouplings.Andesite() :
                mat == StoneMaterial.GRANITE ? new ExistingStoneCouplings.OverworldVariantCouplings.Granite() :
                new ExistingStoneCouplings.OverworldVariantCouplings.Diorite() ;

        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT), variant.getBase());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB), variant.getSlab());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS), variant.getStairs());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.WALL), variant.getWall());

        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT), variant.getPolished());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB), variant.getPolishedSlab());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS), variant.getPolishedStairs());

        return presets;
    }

    private static Map<String, GenericBlockItemCoupling> sandstoneMaterialPresets
            (StoneMaterial mat, Map<String, GenericBlockItemCoupling> presets)
    {
        ExistingStoneCouplings.SandstoneVariantCouplings.Variant variant =
                mat == StoneMaterial.SANDSTONE ? new ExistingStoneCouplings.SandstoneVariantCouplings.Sandstone() :
                new ExistingStoneCouplings.SandstoneVariantCouplings.RedSandstone() ;

        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT), variant.getBase());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB), variant.getSlab());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS), variant.getStairs());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.WALL), variant.getWall());

        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.SMOOTH, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT), variant.getSmooth());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.SMOOTH, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB), variant.getSmoothSlab());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.SMOOTH, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS), variant.getSmoothStairs());

        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.POLISHED, StoneVariantType.CHISELED, StoneSubBlockType.DEFAULT), variant.getChiseled());

        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.CUT, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT), variant.getCut());
        presets.put(ExistingStoneCouplings
                .getKey(mat, StoneBlockType.CUT, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB), variant.getCutSlab());

        return presets;
    }

    private static Map<String, GenericBlockItemCoupling> tuffMaterialPresets(Map<String, GenericBlockItemCoupling> presets)
    {
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.TuffCouplings.TUFF);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.TuffCouplings.TUFF_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.TuffCouplings.TUFF_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.TuffCouplings.TUFF_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.TuffCouplings.POLISHED_TUFF);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.TuffCouplings.POLISHED_TUFF_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.TuffCouplings.POLISHED_TUFF_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.TuffCouplings.POLISHED_TUFF_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.POLISHED, StoneVariantType.CHISELED, StoneSubBlockType.WALL),
                ExistingStoneCouplings.TuffCouplings.CHISELED_TUFF);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.TuffCouplings.TUFF_BRICKS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.TuffCouplings.TUFF_BRICK_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.TuffCouplings.TUFF_BRICK_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.TuffCouplings.TUFF_BRICK_WALL);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.TUFF, StoneBlockType.BRICKS, StoneVariantType.CHISELED, StoneSubBlockType.WALL),
                ExistingStoneCouplings.TuffCouplings.CHISELED_TUFF_BRICKS);

        return presets;
    }

    private static Map<String, GenericBlockItemCoupling> basaltMaterialPresets(Map<String, GenericBlockItemCoupling> presets)
    {
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.BASALT, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.BasaltCouplings.BASALT);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.BASALT, StoneBlockType.SMOOTH, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.BasaltCouplings.SMOOTH_BASALT);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.BASALT, StoneBlockType.POLISHED, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.BasaltCouplings.POLISHED_BASALT);

        return presets;
    }

    private static Map<String, GenericBlockItemCoupling> endstoneMaterialPresets(Map<String, GenericBlockItemCoupling> presets)
    {
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.ENDSTONE, StoneBlockType.BASE, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.EnderCouplings.END_STONE);

        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.ENDSTONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.DEFAULT),
                ExistingStoneCouplings.EnderCouplings.END_STONE_BRICKS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.ENDSTONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.SLAB),
                ExistingStoneCouplings.EnderCouplings.END_STONE_BRICK_SLAB);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.ENDSTONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.STAIRS),
                ExistingStoneCouplings.EnderCouplings.END_STONE_BRICK_STAIRS);
        presets.put(ExistingStoneCouplings
                .getKey(StoneMaterial.ENDSTONE, StoneBlockType.BRICKS, StoneVariantType.DEFAULT, StoneSubBlockType.WALL),
                ExistingStoneCouplings.EnderCouplings.END_STONE_BRICK_WALL);

        return presets;
    }
}
