package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.content.blocks.entity.ExtensibleHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ExtensibleWallHangingSignBlock extends WallHangingSignBlock
{
    public ExtensibleWallHangingSignBlock(WoodType type, Properties properties)
    { super(type, properties); }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    { return new ExtensibleHangingSignBlockEntity(pos, state); }
}
