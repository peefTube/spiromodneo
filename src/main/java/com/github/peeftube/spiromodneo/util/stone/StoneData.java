package com.github.peeftube.spiromodneo.util.stone;

import com.github.peeftube.spiromodneo.util.stone.subdata.*;

/**
 * A massive data collection which processes all potential options for a given stone type, in terms of crafting and
 * variation. Only the <code>name</code> and <code>stone</code> parameters are strictly necessary, all other types
 * should be nullable - however, it is recommended that all subdata collections are utilized.
 * @param name Contains the raw name of this data, as a substitute for using a StoneMaterial pass-in. This makes
 *             data readability far easier to maintain, since this parameter can be used to fill in during ID
 *             creation.
 * @param stone Contains the raw stone data, specifically whatever will generate naturally in the world and what
 *              blocks can immediately be crafted from this base stone type, such as walls, buttons, slabs and stairs.
 * @param cobble Contains the cobblestone data, which is primarily useful for determining drops and also for opening up
 *               new crafting options. Includes mossy variations.
 * @param smooth Contains the smooth stone data, i.e. the results of smelting raw stone and whatever immediate crafting
 *               options are available for smooth stone.
 * @param polished Contains the polished stone data, i.e. the results of crafting stone variants into their polished
 *                 variants and whatever limited options are available for crafting from this variation.
 * @param bricks Contains stone brick data, (optionally) including infested variations. Has parameters for
 *               mossy, cracked, and chiseled variants.
 * @param tile Contains stone tile data, (optionally) including infested variations. Has parameters for
 *             mossy, cracked, small, and chiseled variants.
 * @param cut Contains cut stone data, i.e. cut sandstone. Should not see use often, but it is here for convenience.
 * @param misc Contains miscellaneous stone data, e.g. carved pillars. In rare cases, may allow for the creation of
 *             recursive StoneData. TODO: Determine if this is actually possible
 */
public record StoneData(String name, RawStoneData stone, CobblestoneData cobble, SmoothStoneData smooth,
                        PolishedStoneData polished, StoneBrickData bricks, StoneTileData tile, CutStoneData cut,
                        MiscStoneData misc)
{
    public RawStoneData getStone()
    { return this.stone; }

    public CobblestoneData getCobble()
    { return this.cobble; }

    public SmoothStoneData getSmooth()
    { return this.smooth; }

    public PolishedStoneData getPolished()
    { return this.polished; }

    public StoneBrickData getBricks()
    { return this.bricks; }

    public StoneTileData getTile()
    { return this.tile; }

    public CutStoneData getCut()
    { return this.cut; }

    public MiscStoneData getMisc()
    { return this.misc; }
}
