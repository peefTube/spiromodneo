package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.registry.data.Tappable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

// TODO: have this block tick, checking for any tappers attached!!!!
/** Tappable wood blocks are functionally identical to existing <code>RotatedPillarBlock</code>s,
 * except in that they are also able to be "tapped," enabling liquid resources to be gathered from
 * specific trees; for instance, uses might include caoutchouc from rubber trees, or maple sap from
 * maple trees.
 */
public class TappableWoodBlock extends WoodBlock
{
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty TAPPED = BooleanProperty.create("tapped");

    public final Tappable tapOutput;

    public TappableWoodBlock(Properties properties, Tappable tapOutput)
    {
        super(properties);
        this.tapOutput = tapOutput;
        this.registerDefaultState(this.defaultBlockState().setValue(TAPPED, Boolean.FALSE)
			.setValue(NORTH, Boolean.FALSE)
			.setValue(SOUTH, Boolean.FALSE)
			.setValue(WEST, Boolean.FALSE)
			.setValue(EAST, Boolean.FALSE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    { builder.add(AXIS, NORTH, SOUTH, EAST, WEST, TAPPED); }
}
