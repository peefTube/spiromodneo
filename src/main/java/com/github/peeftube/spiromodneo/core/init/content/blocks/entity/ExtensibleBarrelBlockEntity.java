package com.github.peeftube.spiromodneo.core.init.content.blocks.entity;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.Registrar.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ExtensibleBarrelBlockEntity extends BarrelBlockEntity
{
    public ExtensibleBarrelBlockEntity(BlockPos pos, BlockState blockState)
    { super(pos, blockState); }

    // Copied from WoodWe'veGot
    @Override
    public @NotNull BlockEntityType<?> getType() { return Registrar.BARREL_ENTITYTYPE.get(); }

    // Copied from WoodWe'veGot
    @Override
    public boolean isValidBlockState(@NotNull BlockState blockState) { return this.getType().isValid(blockState); }
}
