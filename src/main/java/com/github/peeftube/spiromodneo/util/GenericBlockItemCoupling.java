package com.github.peeftube.spiromodneo.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record GenericBlockItemCoupling(Supplier<? extends Block> block, Supplier<? extends Item> item)
{
    public static List<GenericBlockItemCoupling> GENERIC_COUPLINGS = new ArrayList<>();

    public static GenericBlockItemCoupling couple(Supplier<? extends Block> b, Supplier<? extends Item> i)
    { GenericBlockItemCoupling c = new GenericBlockItemCoupling(b, i); GENERIC_COUPLINGS.add(c); return c; }

    public Supplier<? extends Block> getBlock()
    { return block; }

    public Supplier<? extends Item> getItem()
    { return item; }
}
