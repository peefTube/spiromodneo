package com.github.peeftube.spiromodneo.core.init.content.blocks.entity;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ExtensibleSignBlockEntity extends SignBlockEntity
{
    public ExtensibleSignBlockEntity(BlockPos pos, BlockState blockState)
    { super(Registrar.SIGN_ENTITYTYPE.get(), pos, blockState); }
}
