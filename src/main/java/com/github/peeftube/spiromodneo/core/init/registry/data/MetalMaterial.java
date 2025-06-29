package com.github.peeftube.spiromodneo.core.init.registry.data;

public enum MetalMaterial
{
    IRON("iron"),
    COPPER("copper"),
    GOLD("gold"),
    NETHERITE("netherite"),

    // Modded.
    LEAD("lead"),
    STEEL("steel"),
    CRIMSONITE("crimsonite"),
    STRAVIMITE("stravimite");

    private final String name;

    MetalMaterial(String name)
    { this.name = name; }

    public String get()
    { return name; }
}
