package com.github.peeftube.spiromodneo.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class SpiroTags
{
    public static class Blocks
    {
        public static TagKey<Block> INCORRECT_FOR_COPPER = tag("incorrect_for_copper_tool");
        public static TagKey<Block> INCORRECT_FOR_LEAD = tag("incorrect_for_lead_tool");

        public static TagKey<Block> NEEDS_COPPER_TOOL = tag("needs_copper_tool");
        public static TagKey<Block> NEEDS_LEAD_TOOL = tag("needs_lead_tool");
        public static TagKey<Block> NEEDS_GOLD_TOOL = tag("needs_gold_tool");

        public static TagKey<Block> SANDSTONE_ORE_REPLACEABLES = tag("sandstone_ore_replaceables");
        public static TagKey<Block> RED_SANDSTONE_ORE_REPLACEABLES = tag("red_sandstone_ore_replaceables");
        public static TagKey<Block> BASALT_ORE_REPLACEABLES = tag("basalt_ore_replaceables");

        public static TagKey<Block> tag(String name) { return BlockTags.create(RLUtility.makeRL(name)); }
        public static TagKey<Block> forgeTag(String name) { return BlockTags.create(RLUtility.makeRL("forge", name)); }
    }

    public static class Items
    {
        public static TagKey<Item> STRING_LIKE = tag("string_like");

        public static TagKey<Item> LEATHER_MATERIAL = tag("mat_leather");
        public static TagKey<Item> WOOD_MATERIAL = tag("mat_wooden");
        public static TagKey<Item> STONE_MATERIAL = tag("mat_stone");
        public static TagKey<Item> CHAINMAIL_MATERIAL = tag("mat_chainmail");
        public static TagKey<Item> COPPER_MATERIAL = tag("mat_copper");
        public static TagKey<Item> IRON_MATERIAL = tag("mat_iron");
        public static TagKey<Item> LEAD_MATERIAL = tag("mat_lead");
        public static TagKey<Item> GOLD_MATERIAL = tag("mat_golden");
        public static TagKey<Item> DIAMOND_MATERIAL = tag("mat_diamond");
        public static TagKey<Item> NETHERITE_MATERIAL = tag("mat_netherite");

        public static TagKey<Item> tag(String name) { return ItemTags.create(RLUtility.makeRL(name)); }
        public static TagKey<Item> forgeTag(String name) { return ItemTags.create(RLUtility.makeRL("forge", name)); }
    }

    public static class Biomes
    {
        public static TagKey<Biome> RUBY_SPAWNABLE = tag("is_ruby_spawnable");

        public static TagKey<Biome> tag(String name) { return TagKey.create(Registries.BIOME, RLUtility.makeRL(name)); }
        public static TagKey<Biome> forgeTag(String name) { return TagKey.create(Registries.BIOME, RLUtility.makeRL("forge", name)); }
    }
}
