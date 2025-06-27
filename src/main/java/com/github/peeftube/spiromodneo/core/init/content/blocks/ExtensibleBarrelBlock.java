package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.content.blocks.entity.ExtensibleBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtensibleBarrelBlock extends BarrelBlock
{
    public ExtensibleBarrelBlock() { super(Properties.ofFullCopy(Blocks.BARREL)); }

    // Copied from WoodWe'veGot
    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
    { return new ExtensibleBarrelBlockEntity(pos, state); }
}
