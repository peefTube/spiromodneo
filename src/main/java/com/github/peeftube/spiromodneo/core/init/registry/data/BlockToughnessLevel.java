package com.github.peeftube.spiromodneo.core.init.registry.data;

public enum BlockToughnessLevel
{
    PUNY(0.5f),
    FLIMSY(1f),
    WEAK(2f),
    NORMAL(3f),
    TOUGH(4.5f),
    STRONG(6f),
    EXTREME(9f);

    private float toughness;

    private BlockToughnessLevel(float str)
    { this.toughness = str; }

    public float get()
    { return toughness; }
}
