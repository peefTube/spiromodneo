package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.content.blocks.entity.ManualCrusherBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ManualCrusherBlock extends BaseEntityBlock
{
    public static final MapCodec<ManualCrusherBlock> CODEC = simpleCodec(ManualCrusherBlock::new);

    public ManualCrusherBlock(Properties properties) { super(properties); }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() { return CODEC; }

    @Override
    protected RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    { return new ManualCrusherBlockEntity(pos, state); }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston)
    {  }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        if (level.isClientSide()) { return null; }
        return createTickerHelper(blockEntityType, Registrar.MANUAL_CRUSHER_ENTITY.get(),
                (xLevel, bPos, state, bEnt) -> bEnt.tick(xLevel, bPos, state));
    }
}
