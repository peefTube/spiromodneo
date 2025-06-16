package com.github.peeftube.spiromodneo.util.generic;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.util.RLUtility;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record GenBlockItemCoupling(Supplier<Block> block, Supplier<Item> item, boolean isVanilla)
{
    public static List<GenBlockItemCoupling> COUPLINGS = new ArrayList<>();

    public static GenBlockItemCoupling couple(Supplier<Block> b, Supplier<Item> i, boolean isVanilla)
    { GenBlockItemCoupling c = new GenBlockItemCoupling(b, i, isVanilla); COUPLINGS.add(c); return c; }

    public Supplier<Block> getBlock()
    { return block; }

    public Supplier<Item> getItem()
    { return item; }

    public boolean isVanilla()
    { return isVanilla; }

    public static GenBlockItemCoupling createNewBasic(Supplier<Block> b)
    {
        Supplier<Item> i = Registrar.regSimpleBlockItem((DeferredBlock<Block>) b);
        return new GenBlockItemCoupling(b, i, false);
    }

    public static GenBlockItemCoupling findFromBlock(Supplier<Block> b)
    {
        Block b0 = b.get();
        if (BuiltInRegistries.BLOCK.containsValue(b0))
        { return new GenBlockItemCoupling(b, b0::asItem, true); }
        else
        { return createNewBasic(b); }
    }

    public static GenBlockItemCoupling findFromID(String identifier)
    {
        if (BuiltInRegistries.BLOCK.containsKey(ResourceLocation.withDefaultNamespace(identifier)))
        {
            Block b = BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace(identifier));
            Supplier<Block> b0 = () -> b;

            return findFromBlock(b0);
        }
        else
        { return null; }
    }
}