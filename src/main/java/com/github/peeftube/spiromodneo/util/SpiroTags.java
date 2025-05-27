package com.github.peeftube.spiromodneo.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SpiroTags
{
    public static class Blocks
    {
        public static TagKey<Block> NEEDS_FLINT_TOOL  = tag("needs_flint_tool");
        public static TagKey<Block> NEEDS_COPPER_TOOL = tag("needs_copper_tool");

        public static TagKey<Block> tag(String name) { return BlockTags.create(RLUtility.makeRL(name)); }
        public static TagKey<Block> forgeTag(String name) { return BlockTags.create(RLUtility.makeRL("forge", name)); }
    }

    public static class Items
    {
        public static TagKey<Item> tag(String name) { return ItemTags.create(RLUtility.makeRL(name)); }
        public static TagKey<Item> forgeTag(String name) { return ItemTags.create(RLUtility.makeRL("forge", name)); }
    }
}
