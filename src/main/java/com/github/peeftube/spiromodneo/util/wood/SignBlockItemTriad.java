package com.github.peeftube.spiromodneo.util.wood;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record SignBlockItemTriad(Supplier<? extends Block> sign,
                                 Supplier<? extends Block> wallSign,
                                 Supplier<? extends Item> item)
{
    public static List<SignBlockItemTriad> SIGN_TRIADS = new ArrayList<>();

    public static SignBlockItemTriad couple(Supplier<? extends Block> s,
            Supplier<? extends Block> w, Supplier<? extends Item> i)
    { SignBlockItemTriad c = new SignBlockItemTriad(s, w, i); SIGN_TRIADS.add(c); return c; }

    public Supplier<? extends Block> getSign()
    { return sign; }

    public Supplier<? extends Block> getWallSign()
    { return wallSign; }

    public Supplier<? extends Item> getItem()
    { return item; }
}
