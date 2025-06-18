package com.github.peeftube.spiromodneo.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public record TagCoupling(TagKey<Block> blockTag, TagKey<Item> itemTag)
{
    public static List<TagCoupling> TAG_COUPLINGS = new ArrayList<>();

    public static TagCoupling couple(TagKey<Block> b, TagKey<Item> i)
    { TagCoupling c = new TagCoupling(b, i); TAG_COUPLINGS.add(c); return c; }

    public TagKey<Block> getBlockTag()
    { return blockTag; }

    public TagKey<Item> getItemTag()
    { return itemTag; }
}
