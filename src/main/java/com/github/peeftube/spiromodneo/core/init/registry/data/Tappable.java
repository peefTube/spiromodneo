package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum Tappable implements StringRepresentable
{
    EMPTY(), // Support tappers.
    CAOUTCHOUC("caoutchouc", Registrar.CAOUTCHOUC),
    MAPLE_SAP("maple_sap", Registrar.MAPLE_SAP);

    private final String name;
    private final Supplier<? extends Item> item;

    Tappable()
    { this.name = "empty"; this.item = null; }

    Tappable(String name, Supplier<? extends Item> item)
    { this.name = name; this.item = item; }

    @Override
    public @NotNull String getSerializedName()
    { return name.toLowerCase(); }

    public Item getItem()
    { return item.get(); }
}
