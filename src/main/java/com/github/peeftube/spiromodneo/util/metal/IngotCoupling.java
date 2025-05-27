package com.github.peeftube.spiromodneo.util.metal;

import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record IngotCoupling(Supplier<Item> ingot, MetalBlockCoupling metal)
{
    public static List<IngotCoupling> INGOT_COUPLINGS = new ArrayList<>();

    public static IngotCoupling couple(Supplier<Item> i, MetalBlockCoupling m)
    { IngotCoupling c = new IngotCoupling(i, m); INGOT_COUPLINGS.add(c); return c;}

    public MetalBlockCoupling getMetal()
    { return metal; }

    public Supplier<Item> getIngot()
    { return ingot; }
}
