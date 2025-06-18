package com.github.peeftube.spiromodneo.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record GenericBlockItemCoupling(Supplier<Block> block, Supplier<Item> item)
{
    public static List<GenericBlockItemCoupling> GENERIC_COUPLINGS = new ArrayList<>();

    public static GenericBlockItemCoupling couple(Supplier<Block> b, Supplier<Item> i)
    { GenericBlockItemCoupling c = new GenericBlockItemCoupling(b, i); GENERIC_COUPLINGS.add(c); return c; }

    public Supplier<Block> getBlock()
    { return block; }

    public Supplier<Item> getItem()
    { return item; }
}
