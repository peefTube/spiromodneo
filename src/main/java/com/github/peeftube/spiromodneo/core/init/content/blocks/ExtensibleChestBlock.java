package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.content.blocks.entity.StorageBET;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ExtensibleChestBlock extends ChestBlock
{
    private final String setName;

    public ExtensibleChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityType,
            String setName)
    { super(Properties.ofFullCopy(Blocks.CHEST), blockEntityType); this.setName = setName; }

    public ExtensibleChestBlock(String setName)
    { this(StorageBET.CHEST::get, setName); }

    public String getSetName() { return setName; }

    // Copied from WoodWe'veGot
    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
    { return blockEntityType.get().create(pos, state); }
}
