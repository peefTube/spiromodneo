package com.github.peeftube.spiromodneo.core.init.registry.data;

public record FuelOreData(boolean isFuel, int burnTime)
{
    public static FuelOreData nonFuel()
    { return populate(false, 0); }

    private static FuelOreData populate(boolean isFuel, int burnTime)
    { return new FuelOreData(isFuel, burnTime); }

    private static FuelOreData populate(boolean isFuel, float burnTimeInSec)
    { int burnTime = (int) (burnTimeInSec * 20); return populate(isFuel, burnTime); }

    public static FuelOreData asFuel(float burnTimeInSec)
    { return populate(true, burnTimeInSec); }
}
