package com.github.peeftube.spiromodneo.core.init.registry.data;

public enum TappableWoodMaterial
{
    RUBBERWOOD("rubberwood", WoodMaterial.RUBBERWOOD, Tappable.CAOUTCHOUC),
    MAPLE("maple", WoodMaterial.MAPLE, Tappable.MAPLE_SAP);

    private final String name;
    private final WoodMaterial wood;
    private final Tappable output;

    TappableWoodMaterial(String name, WoodMaterial wood, Tappable output)
    { this.name = name; this.wood = wood; this.output = output; }

    public String getName() { return name; }
    public WoodMaterial getWoodMaterial() { return wood; }
    public Tappable getTapOutput() { return output; }
}
