package com.github.peeftube.spiromodneo.core.init.registry.data;

public enum GrassLike
{
    GRASS("grass"),
    MYCELIUM("mycelium"),
    CRIMSON_NYLIUM("crimson_nylium"),
    WARPED_NYLIUM("warped_nylium"),
    VITALIUM("vitalium");

    private final String name;

    GrassLike(String name) { this.name = name; }

    public String getName()
    { return name; }
}
