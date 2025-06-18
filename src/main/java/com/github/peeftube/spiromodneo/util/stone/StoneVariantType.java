package com.github.peeftube.spiromodneo.util.stone;

public enum StoneVariantType
{
    DEFAULT(""),
    MOSSY("mossy"),
    CRACKED("cracked"),
    CHISELED("chiseled");

    private final String name;

    StoneVariantType(String name)
    { this.name = name; }

    public String getName()
    { return name; }

    public String getNamePrepend()
    { return name.isEmpty() ? name : name + "_"; }
}
