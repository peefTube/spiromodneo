package com.github.peeftube.spiromodneo.util.worldgen;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.StoneMaterial;
import external.com.github.auburn.fastnoiselite.FastNoiseLite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.OreVeinifier;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.neoforged.neoforge.common.Tags;

public class HugeVeins
{
    public static NoiseChunk.BlockStateFiller
    create(DensityFunction veinToggle, DensityFunction veinRidged, DensityFunction veinGap,
            PositionalRandomFactory random/*, ChunkAccess chunk*/)
    {
        return fc -> {
            BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(fc.blockX(), fc.blockY(), fc.blockZ());
            RandomSource rSrc = random.at(mPos);

            double d0 = veinToggle.compute(fc);

            FastNoiseLite n0 = new FastNoiseLite();
            n0.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
            n0.SetSeed(rSrc.nextInt());
            double x0 = n0.GetNoise(fc.blockX(), fc.blockY(), fc.blockZ());

            double c0 = (d0 + x0) / 2;

            // ResourceKey<Biome> b = chunk.getLevel().getBiome(mPos).getKey();
            // boolean vtFlag0 = (b.equals(Biomes.BADLANDS) || b.equals(Biomes.ERODED_BADLANDS) || b.equals(Biomes.WOODED_BADLANDS));
            // boolean vtFlag1 = (b.equals(Biomes.BIRCH_FOREST) || b.equals(Biomes.OLD_GROWTH_BIRCH_FOREST));

            int                   i                     = fc.blockY();

            /* HugeVeins.Type veintype =
                    c0 > 0.0 ? x0 > d0 ? (vtFlag0) ? Type.GOLD : (vtFlag1) ? Type.LEAD : d0 > 0.0 ? Type.COPPER : Type.IRON :
                    d0 > x0 ? (vtFlag0) ? Type.GOLD : (vtFlag1) ? Type.COPPER : d0 > 0.0 ? Type.COPPER : Type.IRON : Type.COPPER : Type.IRON; */
            Type veintype = c0 > 0.0 ? c0 > 0.8 ? Type.GOLD : Type.COPPER : c0 > -0.8 ? Type.IRON : Type.LEAD;

            double                d1                    = Math.abs(d0);
            int j = veintype.maxY - i;
            int k = i - veintype.minY;

            if (k >= 0 && j >= 0) {
                int l = Math.min(j, k);
                double d2 = Mth.clampedMap((double)l, 0.0, 20.0, -0.2, 0.0);
                if (d1 + d2 < 0.4F) {
                    return null;
                } else {
                    RandomSource randomsource = random.at(fc.blockX(), i, fc.blockZ());
                    if (randomsource.nextFloat() > 0.7F) {
                        return null;
                    } else if (veinRidged.compute(fc) >= 0.0) {
                        return null;
                    } else {
                        double d3 = Mth.clampedMap(d1, 0.4F, 0.6F, 0.1F, 0.3F);
                        BlockState newblockstate = successfulCreation(randomsource, d3, (veinGap.compute(fc) > -0.3F), veintype, /*chunk,*/ mPos);
                        return newblockstate;
                    }
                }
            } else {
                return null;
            }
        };
    }

    public enum Type
    {
        IRON(Registrar.IRON_ORES, -160, 30),
        COPPER(Registrar.COPPER_ORES, -10, 60),
        GOLD(Registrar.GOLD_ORES, -210, -110),
        LEAD(Registrar.LEAD_ORES, -120, 80);

        final OreCollection ore;
        public final int minY;
        public final int maxY;

        private Type(OreCollection ore, int minY, int maxY) {
            this.ore = ore;
            this.minY = minY;
            this.maxY = maxY;
        }
    }

    /** Returns the default block state for a given stone type. */
    private static BlockState rDBS(OreCollection oreType, StoneMaterial baseStone)
    { return oreType.getBulkData().get(baseStone).getBlock().get().defaultBlockState(); }

    /** Custom function to handle the final if-else check in create(). */
    private static BlockState successfulCreation(RandomSource rSrc, double t0, boolean c0, HugeVeins.Type type,
            /*ChunkAccess c, */BlockPos.MutableBlockPos pos)
    {
        OreCollection typeOre = type.ore;

        return ((double)rSrc.nextFloat() < t0 && c0) ?
                rSrc.nextFloat() < 0.02F ? typeOre.getRawOre().getCoupling().getBlock().get().defaultBlockState() :
                        biomeSanityCheck(/*c,*/ pos, type.ore, false) : biomeSanityCheck(/*c,*/ pos, type.ore, true);
    }

    /** Custom biome check function to supplement successfulCreation().
     * TODO: THIS NO LONGER SERVES AS A BIOME CHECK, WHICH MAKES THIS NAMING POINTLESS */
    private static BlockState biomeSanityCheck(/*ChunkAccess c,*/ BlockPos.MutableBlockPos pos, OreCollection o, boolean isForFiller)
    {
        int y = pos.getY();
        boolean isAtOrAboveZero = y >= 0;

        // ResourceKey<Biome> b = c.getLevel().getBiome(pos).getKey();
        // boolean bFlag1 = b.equals(Biomes.BIRCH_FOREST) || b.equals(Biomes.OLD_GROWTH_BIRCH_FOREST);
        // boolean bFlag2 = b.equals(Biomes.BADLANDS) || b.equals(Biomes.ERODED_BADLANDS) || b.equals(Biomes.WOODED_BADLANDS);

        switch(o.material())
        {
            case IRON ->
            { return (!isForFiller) ? ((isAtOrAboveZero) ? rDBS(o, StoneMaterial.STONE) : rDBS(o, StoneMaterial.DEEPSLATE))
                            : ((isAtOrAboveZero) ? Blocks.ANDESITE.defaultBlockState() : Blocks.TUFF.defaultBlockState()); }

            case COPPER ->
            { return (!isForFiller) ? ((isAtOrAboveZero) ? rDBS(o, StoneMaterial.STONE) : rDBS(o, StoneMaterial.DEEPSLATE))
                            : ((isAtOrAboveZero) ? Blocks.GRANITE.defaultBlockState() : Blocks.TUFF.defaultBlockState()); }

            case GOLD, LEAD ->
            { return (!isForFiller) ? ((isAtOrAboveZero) ? rDBS(o, StoneMaterial.STONE) : rDBS(o, StoneMaterial.DEEPSLATE))
                            : ((isAtOrAboveZero) ? Blocks.ANDESITE.defaultBlockState() : Blocks.BASALT.defaultBlockState()); }

            // Something has gone wrong. Replace everything with stone so it can be overridden by normal worldgen.
            default -> { return null; }
        }
    }
}
