package com.github.peeftube.spiromodneo.util.stone;

public enum StoneBlockType
{
    BASE("", false, false, false),
    COBBLE("cobbled", true, false, false),
    SMOOTH("smooth", false, false, false),
    POLISHED("polished", false, false, true),
    BRICKS("bricks", true, true, true),
    TILES("tiles", false, true, false),
    CUT("cut", false, false, false),
    COLUMN("column", true, true, true);

    private final String typeName;
    private final boolean canBeMossy;
    private final boolean canBeCracked;
    private final boolean canBeChiseled;

    StoneBlockType(String typeName, boolean hasMossVariant, boolean hasCrackedVariant, boolean hasChiseledVariant)
    {
        this.typeName = typeName; this.canBeMossy = hasMossVariant;
        this.canBeCracked = hasCrackedVariant; this.canBeChiseled = hasChiseledVariant;
    }

    public String getTypeName()
    { return typeName; }

    public String getNameAppend()
    { return typeName.isEmpty() ? typeName : "_" + typeName; }

    public String getNamePrepend()
    { return typeName.isEmpty() ? typeName : typeName + "_"; }

    public boolean isMossVariantAllowed()
    { return canBeMossy; }

    public boolean isCrackedVariantAllowed()
    { return canBeCracked; }

    public boolean isChiseledVariantAllowed()
    { return canBeChiseled; }
}
