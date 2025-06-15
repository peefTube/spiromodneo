package com.github.peeftube.spiromodneo.util.stone;

import com.github.peeftube.spiromodneo.util.ore.OreTagCoupling;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public record StoneMatTagCoupling(TagKey<Block> blockTag, TagKey<Item> itemTag)
{
    public static List<StoneMatTagCoupling> STONE_MAT_TAG_COUPLINGS = new ArrayList<>();

    public static StoneMatTagCoupling couple(TagKey<Block> b, TagKey<Item> i)
    { StoneMatTagCoupling c = new StoneMatTagCoupling(b, i); STONE_MAT_TAG_COUPLINGS.add(c); return c; }

    public TagKey<Block> getBlockTag()
    { return blockTag; }

    public TagKey<Item> getItemTag()
    { return itemTag; }
}
