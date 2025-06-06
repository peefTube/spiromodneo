package com.github.peeftube.spiromodneo.util.metal;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record MetalBlockCoupling(Supplier<Block> block, Supplier<Item> item)
{
    public static List<MetalBlockCoupling> METAL_COUPLINGS = new ArrayList<>();

    public static MetalBlockCoupling couple(Supplier<Block> b, Supplier<Item> i)
    { MetalBlockCoupling c = new MetalBlockCoupling(b, i); METAL_COUPLINGS.add(c); return c; }

    public Supplier<Block> getBlock()
    { return block; }

    public Supplier<Item> getItem()
    { return item; }
}