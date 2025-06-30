package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.Registrar.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.stats.Stat;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ExtensibleTrappedChestBlock extends ExtensibleChestBlock
{
    public ExtensibleTrappedChestBlock(String setName)
    { super(Registrar.TRAPPED_CHEST_ENTITYTYPE::get, setName); }

    // Copied from WoodWe'veGot
    @Override
    protected @NotNull Stat<ResourceLocation> getOpenChestStat()
    { return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST); }

    // Copied from WoodWe'veGot
    @Override
    public boolean isSignalSource(@NotNull BlockState state) { return true; }

    // Copied from WoodWe'veGot
    @Override
    public int getSignal(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction)
    { return Mth.clamp(ChestBlockEntity.getOpenCount(level, pos), 0, 15); }

    // Copied from WoodWe'veGot
    @Override
    public int getDirectSignal(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction)
    { return direction == Direction.UP ? state.getSignal(level, pos, direction) : 0; }
}
