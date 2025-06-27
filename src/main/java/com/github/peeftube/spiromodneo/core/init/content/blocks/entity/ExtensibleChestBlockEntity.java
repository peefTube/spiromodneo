package com.github.peeftube.spiromodneo.core.init.content.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ExtensibleChestBlockEntity extends ChestBlockEntity
{
    protected ExtensibleChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState)
    { super(type, pos, blockState); }

    public ExtensibleChestBlockEntity(BlockPos pos, BlockState blockState)
    { this(StorageBET.CHEST.get(), pos, blockState); }
}
