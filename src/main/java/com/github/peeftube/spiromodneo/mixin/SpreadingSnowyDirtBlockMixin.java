package com.github.peeftube.spiromodneo.mixin;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.GrassLikeCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.Soil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.lighting.LightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SpreadingSnowyDirtBlock.class)
public abstract class SpreadingSnowyDirtBlockMixin
{
    @Shadow private static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        BlockState blockstate = levelReader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(
                    levelReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(levelReader, blockpos)
            );
            return i < levelReader.getMaxLightLevel();
        }
    }

    @Shadow private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    /**
     * @author Jeremy Peifer (spiro9)
     * @reason Extensibility for grass & mycelium variants, plus new block types.
     */
    @Overwrite
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (!canBeGrass(state, level, pos))
        {
            if (!level.isAreaLoaded(pos, 1)) return;
            if (state.is(Soil.DIRT.getTag())) level.setBlockAndUpdate(pos,
                    Soil.DIRT.getSoil().get().defaultBlockState());
            else if (state.is(Soil.MUD.getTag())) level.setBlockAndUpdate(pos,
                    Soil.MUD.getSoil().get().defaultBlockState());
            else if (state.is(Soil.NETHERRACK.getTag())) level.setBlockAndUpdate(pos,
                    Soil.NETHERRACK.getSoil().get().defaultBlockState());
            else if (state.is(Soil.SOUL_SOIL.getTag())) level.setBlockAndUpdate(pos,
                    Soil.SOUL_SOIL.getSoil().get().defaultBlockState());
        }
        else
        {
            if (!level.isAreaLoaded(pos, 3)) return;
            if (level.dimension() == ServerLevel.OVERWORLD ? level.getMaxLocalRawBrightness(pos.above()) >= 9
            : (level.dimension() == ServerLevel.NETHER || level.dimension() == Level.END))
            {
                for (int i = 0; i < 4; i++)
                {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3,
                            random.nextInt(3) - 1);

                    boolean isSupportedBlock = false;
                    Soil soilToSave = null;
                    for (Soil s : Soil.values())
                    {
                        isSupportedBlock = level.getBlockState(blockpos).is(s.getSoil().get()) || isSupportedBlock;
                        soilToSave = level.getBlockState(blockpos).is(s.getSoil().get()) ? s : soilToSave;
                    }

                    BlockState bState = Blocks.AIR.defaultBlockState();

                    if (isSupportedBlock && canPropagate(bState, level, blockpos))
                    {
                        GrassLikeCollection toSpread =
                                state.toString().toLowerCase().contains("grass") ?
                                        Registrar.GRASS_TYPE :
                                state.toString().toLowerCase().contains("crimson_nylium") ?
                                        Registrar.CRIMSON_NYLIUM_TYPE :
                                state.toString().toLowerCase().contains("warped_nylium") ?
                                        Registrar.WARPED_NYLIUM_TYPE :
                                state.toString().toLowerCase().contains("mycelium") ?
                                        Registrar.MYCELIUM_TYPE :
                                state.toString().toLowerCase().contains("vitalium") ?
                                        Registrar.VITALIUM_TYPE : Registrar.GRASS_TYPE;

                        bState = toSpread.bulkData().get(soilToSave).getBlock().get().defaultBlockState();

                        level.setBlockAndUpdate(
                                blockpos, bState.setValue(SnowyDirtBlock.SNOWY,
                                        Boolean.valueOf(level.getBlockState(blockpos.above()).is(Blocks.SNOW)))
                        );
                    }
                }
            }
        }
    }
}
