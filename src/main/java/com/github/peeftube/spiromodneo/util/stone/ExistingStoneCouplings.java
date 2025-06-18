package com.github.peeftube.spiromodneo.util.stone;

import com.github.peeftube.spiromodneo.core.init.registry.data.StoneMaterial;
import com.github.peeftube.spiromodneo.util.GenericBlockItemCoupling;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ExistingStoneCouplings
{
    public static class StoneCouplings
    {
        public static final GenericBlockItemCoupling STONE =
                new GenericBlockItemCoupling(() -> Blocks.STONE, () -> Items.STONE);
        public static final GenericBlockItemCoupling STONE_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.STONE_SLAB, () -> Items.STONE_SLAB);
        public static final GenericBlockItemCoupling STONE_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.STONE_STAIRS, () -> Items.STONE_STAIRS);
        public static final GenericBlockItemCoupling STONE_BUTTON =
                new GenericBlockItemCoupling(() -> Blocks.STONE_BUTTON, () -> Items.STONE_BUTTON);
        public static final GenericBlockItemCoupling STONE_PRESSURE_PLATE =
                new GenericBlockItemCoupling(() -> Blocks.STONE_PRESSURE_PLATE, () -> Items.STONE_PRESSURE_PLATE);

        public static final GenericBlockItemCoupling COBBLESTONE =
                new GenericBlockItemCoupling(() -> Blocks.COBBLESTONE, () -> Items.COBBLESTONE);
        public static final GenericBlockItemCoupling COBBLESTONE_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.COBBLESTONE_SLAB, () -> Items.COBBLESTONE_SLAB);
        public static final GenericBlockItemCoupling COBBLESTONE_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.COBBLESTONE_STAIRS, () -> Items.COBBLESTONE_STAIRS);
        public static final GenericBlockItemCoupling COBBLESTONE_WALL =
                new GenericBlockItemCoupling(() -> Blocks.COBBLESTONE_WALL, () -> Items.COBBLESTONE_WALL);

        public static final GenericBlockItemCoupling MOSSY_COBBLESTONE =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_COBBLESTONE, () -> Items.MOSSY_COBBLESTONE);
        public static final GenericBlockItemCoupling MOSSY_COBBLESTONE_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_COBBLESTONE_SLAB, () -> Items.MOSSY_COBBLESTONE_SLAB);
        public static final GenericBlockItemCoupling MOSSY_COBBLESTONE_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_COBBLESTONE_STAIRS, () -> Items.MOSSY_COBBLESTONE_STAIRS);
        public static final GenericBlockItemCoupling MOSSY_COBBLESTONE_WALL =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_COBBLESTONE_WALL, () -> Items.MOSSY_COBBLESTONE_WALL);

        public static final GenericBlockItemCoupling SMOOTH_STONE =
                new GenericBlockItemCoupling(() -> Blocks.SMOOTH_STONE, () -> Items.SMOOTH_STONE);
        public static final GenericBlockItemCoupling SMOOTH_STONE_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.SMOOTH_STONE_SLAB, () -> Items.SMOOTH_STONE_SLAB);
        
        public static final GenericBlockItemCoupling STONE_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.STONE_BRICKS, () -> Items.STONE_BRICKS);
        public static final GenericBlockItemCoupling STONE_BRICK_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.STONE_BRICK_SLAB, () -> Items.STONE_BRICK_SLAB);
        public static final GenericBlockItemCoupling STONE_BRICK_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.STONE_BRICK_STAIRS, () -> Items.STONE_BRICK_STAIRS);
        public static final GenericBlockItemCoupling STONE_BRICK_WALL =
                new GenericBlockItemCoupling(() -> Blocks.STONE_BRICK_WALL, () -> Items.STONE_BRICK_WALL);

        public static final GenericBlockItemCoupling MOSSY_STONE_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_STONE_BRICKS, () -> Items.MOSSY_STONE_BRICKS);
        public static final GenericBlockItemCoupling MOSSY_STONE_BRICK_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_STONE_BRICK_SLAB, () -> Items.MOSSY_STONE_BRICK_SLAB);
        public static final GenericBlockItemCoupling MOSSY_STONE_BRICK_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_STONE_BRICK_STAIRS, () -> Items.MOSSY_STONE_BRICK_STAIRS);
        public static final GenericBlockItemCoupling MOSSY_STONE_BRICK_WALL =
                new GenericBlockItemCoupling(() -> Blocks.MOSSY_STONE_BRICK_WALL, () -> Items.MOSSY_STONE_BRICK_WALL);

        public static final GenericBlockItemCoupling CRACKED_STONE_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.CRACKED_STONE_BRICKS, () -> Items.CRACKED_STONE_BRICKS);

        public static final GenericBlockItemCoupling CHISELED_STONE_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.CHISELED_STONE_BRICKS, () -> Items.CHISELED_STONE_BRICKS);
    }
    
    public static class DeepslateCouplings
    {
        public static final GenericBlockItemCoupling DEEPSLATE =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE, () -> Items.DEEPSLATE);

        public static final GenericBlockItemCoupling COBBLED_DEEPSLATE =
                new GenericBlockItemCoupling(() -> Blocks.COBBLED_DEEPSLATE, () -> Items.COBBLED_DEEPSLATE);
        public static final GenericBlockItemCoupling COBBLED_DEEPSLATE_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.COBBLED_DEEPSLATE_SLAB, () -> Items.COBBLED_DEEPSLATE_SLAB);
        public static final GenericBlockItemCoupling COBBLED_DEEPSLATE_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.COBBLED_DEEPSLATE_STAIRS, () -> Items.COBBLED_DEEPSLATE_STAIRS);
        public static final GenericBlockItemCoupling COBBLED_DEEPSLATE_WALL =
                new GenericBlockItemCoupling(() -> Blocks.COBBLED_DEEPSLATE_WALL, () -> Items.COBBLED_DEEPSLATE_WALL);
        
        public static final GenericBlockItemCoupling DEEPSLATE_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_BRICKS, () -> Items.DEEPSLATE_BRICKS);
        public static final GenericBlockItemCoupling DEEPSLATE_BRICK_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_BRICK_SLAB, () -> Items.DEEPSLATE_BRICK_SLAB);
        public static final GenericBlockItemCoupling DEEPSLATE_BRICK_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_BRICK_STAIRS, () -> Items.DEEPSLATE_BRICK_STAIRS);
        public static final GenericBlockItemCoupling DEEPSLATE_BRICK_WALL =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_BRICK_WALL, () -> Items.DEEPSLATE_BRICK_WALL);
        
        public static final GenericBlockItemCoupling DEEPSLATE_TILES =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_TILES, () -> Items.DEEPSLATE_TILES);
        public static final GenericBlockItemCoupling DEEPSLATE_TILE_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_TILE_SLAB, () -> Items.DEEPSLATE_TILE_SLAB);
        public static final GenericBlockItemCoupling DEEPSLATE_TILE_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_TILE_STAIRS, () -> Items.DEEPSLATE_TILE_STAIRS);
        public static final GenericBlockItemCoupling DEEPSLATE_TILE_WALL =
                new GenericBlockItemCoupling(() -> Blocks.DEEPSLATE_TILE_WALL, () -> Items.DEEPSLATE_TILE_WALL);

        public static final GenericBlockItemCoupling CRACKED_DEEPSLATE_TILES =
                new GenericBlockItemCoupling(() -> Blocks.CRACKED_DEEPSLATE_TILES, () -> Items.CRACKED_DEEPSLATE_TILES);

        public static final GenericBlockItemCoupling POLISHED_DEEPSLATE =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_DEEPSLATE, () -> Items.POLISHED_DEEPSLATE);
        public static final GenericBlockItemCoupling POLISHED_DEEPSLATE_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_DEEPSLATE_SLAB, () -> Items.POLISHED_DEEPSLATE_SLAB);
        public static final GenericBlockItemCoupling POLISHED_DEEPSLATE_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_DEEPSLATE_STAIRS, () -> Items.POLISHED_DEEPSLATE_STAIRS);
        public static final GenericBlockItemCoupling POLISHED_DEEPSLATE_WALL =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_DEEPSLATE_WALL, () -> Items.POLISHED_DEEPSLATE_WALL);

        public static final GenericBlockItemCoupling CHISELED_DEEPSLATE =
                new GenericBlockItemCoupling(() -> Blocks.CHISELED_DEEPSLATE, () -> Items.CHISELED_DEEPSLATE);
    }

    public static class OverworldVariantCouplings
    {
        interface Variant
        {
            GenericBlockItemCoupling getBase();
            GenericBlockItemCoupling getSlab();
            GenericBlockItemCoupling getStairs();
            GenericBlockItemCoupling getWall();

            GenericBlockItemCoupling getPolished();
            GenericBlockItemCoupling getPolishedSlab();
            GenericBlockItemCoupling getPolishedStairs();
        }

        public static class Andesite implements Variant
        {
            public static final GenericBlockItemCoupling VARIANT_BASE =
                    new GenericBlockItemCoupling(() -> Blocks.ANDESITE, () -> Items.ANDESITE);
            public static final GenericBlockItemCoupling VARIANT_BASE_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.ANDESITE_SLAB, () -> Items.ANDESITE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_BASE_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.ANDESITE_STAIRS, () -> Items.ANDESITE_STAIRS);
            public static final GenericBlockItemCoupling VARIANT_BASE_WALL =
                    new GenericBlockItemCoupling(() -> Blocks.ANDESITE_WALL, () -> Items.ANDESITE_WALL);
            
            public static final GenericBlockItemCoupling VARIANT_POLISHED =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_ANDESITE, () -> Items.POLISHED_ANDESITE);
            public static final GenericBlockItemCoupling VARIANT_POLISHED_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_ANDESITE_SLAB, () -> Items.POLISHED_ANDESITE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_POLISHED_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_ANDESITE_STAIRS, () -> Items.POLISHED_ANDESITE_STAIRS);

            public GenericBlockItemCoupling getBase()
            { return VARIANT_BASE; }
            public GenericBlockItemCoupling getSlab()
            { return VARIANT_BASE_SLAB; }
            public GenericBlockItemCoupling getStairs()
            { return VARIANT_BASE_STAIRS; }
            public GenericBlockItemCoupling getWall()
            { return VARIANT_BASE_WALL; }

            public GenericBlockItemCoupling getPolished()
            { return VARIANT_POLISHED; }
            public GenericBlockItemCoupling getPolishedSlab()
            { return VARIANT_POLISHED_SLAB; }
            public GenericBlockItemCoupling getPolishedStairs()
            { return VARIANT_POLISHED_STAIRS; }
        }

        public static class Granite implements Variant
        {
            public static final GenericBlockItemCoupling VARIANT_BASE =
                    new GenericBlockItemCoupling(() -> Blocks.GRANITE, () -> Items.GRANITE);
            public static final GenericBlockItemCoupling VARIANT_BASE_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.GRANITE_SLAB, () -> Items.GRANITE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_BASE_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.GRANITE_STAIRS, () -> Items.GRANITE_STAIRS);
            public static final GenericBlockItemCoupling VARIANT_BASE_WALL =
                    new GenericBlockItemCoupling(() -> Blocks.GRANITE_WALL, () -> Items.GRANITE_WALL);

            public static final GenericBlockItemCoupling VARIANT_POLISHED =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_GRANITE, () -> Items.POLISHED_GRANITE);
            public static final GenericBlockItemCoupling VARIANT_POLISHED_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_GRANITE_SLAB, () -> Items.POLISHED_GRANITE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_POLISHED_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_GRANITE_STAIRS, () -> Items.POLISHED_GRANITE_STAIRS);

            public GenericBlockItemCoupling getBase()
            { return VARIANT_BASE; }
            public GenericBlockItemCoupling getSlab()
            { return VARIANT_BASE_SLAB; }
            public GenericBlockItemCoupling getStairs()
            { return VARIANT_BASE_STAIRS; }
            public GenericBlockItemCoupling getWall()
            { return VARIANT_BASE_WALL; }

            public GenericBlockItemCoupling getPolished()
            { return VARIANT_POLISHED; }
            public GenericBlockItemCoupling getPolishedSlab()
            { return VARIANT_POLISHED_SLAB; }
            public GenericBlockItemCoupling getPolishedStairs()
            { return VARIANT_POLISHED_STAIRS; }
        }

        public static class Diorite implements Variant
        {
            public static final GenericBlockItemCoupling VARIANT_BASE =
                    new GenericBlockItemCoupling(() -> Blocks.DIORITE, () -> Items.DIORITE);
            public static final GenericBlockItemCoupling VARIANT_BASE_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.DIORITE_SLAB, () -> Items.DIORITE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_BASE_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.DIORITE_STAIRS, () -> Items.DIORITE_STAIRS);
            public static final GenericBlockItemCoupling VARIANT_BASE_WALL =
                    new GenericBlockItemCoupling(() -> Blocks.DIORITE_WALL, () -> Items.DIORITE_WALL);

            public static final GenericBlockItemCoupling VARIANT_POLISHED =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_DIORITE, () -> Items.POLISHED_DIORITE);
            public static final GenericBlockItemCoupling VARIANT_POLISHED_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_DIORITE_SLAB, () -> Items.POLISHED_DIORITE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_POLISHED_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.POLISHED_DIORITE_STAIRS, () -> Items.POLISHED_DIORITE_STAIRS);

            public GenericBlockItemCoupling getBase()
            { return VARIANT_BASE; }
            public GenericBlockItemCoupling getSlab()
            { return VARIANT_BASE_SLAB; }
            public GenericBlockItemCoupling getStairs()
            { return VARIANT_BASE_STAIRS; }
            public GenericBlockItemCoupling getWall()
            { return VARIANT_BASE_WALL; }

            public GenericBlockItemCoupling getPolished()
            { return VARIANT_POLISHED; }
            public GenericBlockItemCoupling getPolishedSlab()
            { return VARIANT_POLISHED_SLAB; }
            public GenericBlockItemCoupling getPolishedStairs()
            { return VARIANT_POLISHED_STAIRS; }
        }
    }

    public static class SandstoneVariantCouplings
    {
        interface Variant
        {
            GenericBlockItemCoupling getBase();
            GenericBlockItemCoupling getSlab();
            GenericBlockItemCoupling getStairs();
            GenericBlockItemCoupling getWall();

            GenericBlockItemCoupling getSmooth();
            GenericBlockItemCoupling getSmoothSlab();
            GenericBlockItemCoupling getSmoothStairs();

            GenericBlockItemCoupling getChiseled();

            GenericBlockItemCoupling getCut();
            GenericBlockItemCoupling getCutSlab();
        }

        public static class Sandstone implements Variant
        {
            public static final GenericBlockItemCoupling VARIANT_BASE =
                    new GenericBlockItemCoupling(() -> Blocks.SANDSTONE, () -> Items.SANDSTONE);
            public static final GenericBlockItemCoupling VARIANT_BASE_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.SANDSTONE_SLAB, () -> Items.SANDSTONE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_BASE_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.SANDSTONE_STAIRS, () -> Items.SANDSTONE_STAIRS);
            public static final GenericBlockItemCoupling VARIANT_BASE_WALL =
                    new GenericBlockItemCoupling(() -> Blocks.SANDSTONE_WALL, () -> Items.SANDSTONE_WALL);

            public static final GenericBlockItemCoupling VARIANT_SMOOTH =
                    new GenericBlockItemCoupling(() -> Blocks.SMOOTH_SANDSTONE, () -> Items.SMOOTH_SANDSTONE);
            public static final GenericBlockItemCoupling VARIANT_SMOOTH_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.SMOOTH_SANDSTONE_SLAB, () -> Items.SMOOTH_SANDSTONE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_SMOOTH_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.SMOOTH_SANDSTONE_STAIRS, () -> Items.SMOOTH_SANDSTONE_STAIRS);

            public static final GenericBlockItemCoupling VARIANT_CHISELED =
                    new GenericBlockItemCoupling(() -> Blocks.CHISELED_SANDSTONE, () -> Items.CHISELED_SANDSTONE);

            public static final GenericBlockItemCoupling VARIANT_CUT =
                    new GenericBlockItemCoupling(() -> Blocks.CUT_SANDSTONE, () -> Items.CUT_SANDSTONE);
            public static final GenericBlockItemCoupling VARIANT_CUT_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.CUT_SANDSTONE_SLAB, () -> Items.CUT_STANDSTONE_SLAB);

            public GenericBlockItemCoupling getBase()
            { return VARIANT_BASE; }
            public GenericBlockItemCoupling getSlab()
            { return VARIANT_BASE_SLAB; }
            public GenericBlockItemCoupling getStairs()
            { return VARIANT_BASE_STAIRS; }
            public GenericBlockItemCoupling getWall()
            { return VARIANT_BASE_WALL; }

            public GenericBlockItemCoupling getSmooth()
            { return VARIANT_SMOOTH; }
            public GenericBlockItemCoupling getSmoothSlab()
            { return VARIANT_SMOOTH_SLAB; }
            public GenericBlockItemCoupling getSmoothStairs()
            { return VARIANT_SMOOTH_STAIRS; }

            public GenericBlockItemCoupling getChiseled()
            { return VARIANT_CHISELED; }

            public GenericBlockItemCoupling getCut()
            { return VARIANT_CUT; }
            public GenericBlockItemCoupling getCutSlab()
            { return VARIANT_CUT_SLAB; }
        }
        
        public static class RedSandstone implements Variant
        {
            public static final GenericBlockItemCoupling VARIANT_BASE =
                    new GenericBlockItemCoupling(() -> Blocks.RED_SANDSTONE, () -> Items.RED_SANDSTONE);
            public static final GenericBlockItemCoupling VARIANT_BASE_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.RED_SANDSTONE_SLAB, () -> Items.RED_SANDSTONE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_BASE_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.RED_SANDSTONE_STAIRS, () -> Items.RED_SANDSTONE_STAIRS);
            public static final GenericBlockItemCoupling VARIANT_BASE_WALL =
                    new GenericBlockItemCoupling(() -> Blocks.RED_SANDSTONE_WALL, () -> Items.RED_SANDSTONE_WALL);

            public static final GenericBlockItemCoupling VARIANT_SMOOTH =
                    new GenericBlockItemCoupling(() -> Blocks.SMOOTH_RED_SANDSTONE, () -> Items.SMOOTH_RED_SANDSTONE);
            public static final GenericBlockItemCoupling VARIANT_SMOOTH_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.SMOOTH_RED_SANDSTONE_SLAB, () -> Items.SMOOTH_RED_SANDSTONE_SLAB);
            public static final GenericBlockItemCoupling VARIANT_SMOOTH_STAIRS =
                    new GenericBlockItemCoupling(() -> Blocks.SMOOTH_RED_SANDSTONE_STAIRS, () -> Items.SMOOTH_RED_SANDSTONE_STAIRS);

            public static final GenericBlockItemCoupling VARIANT_CHISELED =
                    new GenericBlockItemCoupling(() -> Blocks.CHISELED_RED_SANDSTONE, () -> Items.CHISELED_RED_SANDSTONE);

            public static final GenericBlockItemCoupling VARIANT_CUT =
                    new GenericBlockItemCoupling(() -> Blocks.CUT_RED_SANDSTONE, () -> Items.CUT_RED_SANDSTONE);
            public static final GenericBlockItemCoupling VARIANT_CUT_SLAB =
                    new GenericBlockItemCoupling(() -> Blocks.CUT_RED_SANDSTONE_SLAB, () -> Items.CUT_RED_SANDSTONE_SLAB);

            public GenericBlockItemCoupling getBase()
            { return VARIANT_BASE; }
            public GenericBlockItemCoupling getSlab()
            { return VARIANT_BASE_SLAB; }
            public GenericBlockItemCoupling getStairs()
            { return VARIANT_BASE_STAIRS; }
            public GenericBlockItemCoupling getWall()
            { return VARIANT_BASE_WALL; }

            public GenericBlockItemCoupling getSmooth()
            { return VARIANT_SMOOTH; }
            public GenericBlockItemCoupling getSmoothSlab()
            { return VARIANT_SMOOTH_SLAB; }
            public GenericBlockItemCoupling getSmoothStairs()
            { return VARIANT_SMOOTH_STAIRS; }

            public GenericBlockItemCoupling getChiseled()
            { return VARIANT_CHISELED; }

            public GenericBlockItemCoupling getCut()
            { return VARIANT_CUT; }
            public GenericBlockItemCoupling getCutSlab()
            { return VARIANT_CUT_SLAB; }
        }
    }
    
    public static class TuffCouplings
    {
        public static final GenericBlockItemCoupling TUFF =
                new GenericBlockItemCoupling(() -> Blocks.TUFF, () -> Items.TUFF);
        public static final GenericBlockItemCoupling TUFF_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.TUFF_SLAB, () -> Items.TUFF_SLAB);
        public static final GenericBlockItemCoupling TUFF_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.TUFF_STAIRS, () -> Items.TUFF_STAIRS);
        public static final GenericBlockItemCoupling TUFF_WALL =
                new GenericBlockItemCoupling(() -> Blocks.TUFF_WALL, () -> Items.TUFF_WALL);

        public static final GenericBlockItemCoupling TUFF_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.TUFF_BRICKS, () -> Items.TUFF_BRICKS);
        public static final GenericBlockItemCoupling TUFF_BRICK_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.TUFF_BRICK_SLAB, () -> Items.TUFF_BRICK_SLAB);
        public static final GenericBlockItemCoupling TUFF_BRICK_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.TUFF_BRICK_STAIRS, () -> Items.TUFF_BRICK_STAIRS);
        public static final GenericBlockItemCoupling TUFF_BRICK_WALL =
                new GenericBlockItemCoupling(() -> Blocks.TUFF_BRICK_WALL, () -> Items.TUFF_BRICK_WALL);

        public static final GenericBlockItemCoupling CHISELED_TUFF_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.CHISELED_TUFF_BRICKS, () -> Items.CHISELED_TUFF_BRICKS);

        public static final GenericBlockItemCoupling POLISHED_TUFF =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_TUFF, () -> Items.POLISHED_TUFF);
        public static final GenericBlockItemCoupling POLISHED_TUFF_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_TUFF_SLAB, () -> Items.POLISHED_TUFF_SLAB);
        public static final GenericBlockItemCoupling POLISHED_TUFF_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_TUFF_STAIRS, () -> Items.POLISHED_TUFF_STAIRS);
        public static final GenericBlockItemCoupling POLISHED_TUFF_WALL =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_TUFF_WALL, () -> Items.POLISHED_TUFF_WALL);

        public static final GenericBlockItemCoupling CHISELED_TUFF =
                new GenericBlockItemCoupling(() -> Blocks.CHISELED_TUFF, () -> Items.CHISELED_TUFF);
    }

    public static class BasaltCouplings
    {
        public static final GenericBlockItemCoupling BASALT =
                new GenericBlockItemCoupling(() -> Blocks.BASALT, () -> Items.BASALT);

        public static final GenericBlockItemCoupling SMOOTH_BASALT =
                new GenericBlockItemCoupling(() -> Blocks.SMOOTH_BASALT, () -> Items.SMOOTH_BASALT);

        public static final GenericBlockItemCoupling POLISHED_BASALT =
                new GenericBlockItemCoupling(() -> Blocks.POLISHED_BASALT, () -> Items.POLISHED_BASALT);
    }

    public static class EnderCouplings
    {
        public static final GenericBlockItemCoupling END_STONE =
                new GenericBlockItemCoupling(() -> Blocks.END_STONE, () -> Items.END_STONE);

        public static final GenericBlockItemCoupling END_STONE_BRICKS =
                new GenericBlockItemCoupling(() -> Blocks.END_STONE_BRICKS, () -> Items.END_STONE_BRICKS);
        public static final GenericBlockItemCoupling END_STONE_BRICK_SLAB =
                new GenericBlockItemCoupling(() -> Blocks.END_STONE_BRICK_SLAB, () -> Items.END_STONE_BRICK_SLAB);
        public static final GenericBlockItemCoupling END_STONE_BRICK_STAIRS =
                new GenericBlockItemCoupling(() -> Blocks.END_STONE_BRICK_STAIRS, () -> Items.END_STONE_BRICK_STAIRS);
        public static final GenericBlockItemCoupling END_STONE_BRICK_WALL =
                new GenericBlockItemCoupling(() -> Blocks.END_STONE_BRICK_WALL, () -> Items.END_STONE_BRICK_WALL);
    }

    public static String getKey(StoneMaterial mat, StoneBlockType key0, StoneVariantType key1, StoneSubBlockType key2)
    {
        String base = (key1.getName().isEmpty() && key2.getGeneric().isEmpty()) ? mat.getAsBlock() : mat.get();
        String type;
        switch(key0)
        {
            case COBBLE, SMOOTH, POLISHED, CUT -> type =
                    (mat == StoneMaterial.STONE && key0 == StoneBlockType.COBBLE) ? "cobblestone" :
                            key0.getNamePrepend() + base;
            default -> type = base + key0.getNameAppend();
        }
        String type2 = key1.getNamePrepend() + type;
        return (type2.endsWith("s") && !key2.getGeneric().isEmpty()) ?
                type2.substring(0, type2.length() - 1) + key2.getAppendable() : type2 + key2.getAppendable();
    }
}
