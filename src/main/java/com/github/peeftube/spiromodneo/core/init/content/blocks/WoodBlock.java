package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.util.wood.LivingWoodBlockType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class WoodBlock extends RotatedPillarBlock
{
    public WoodBlock(Properties properties)
    { super(properties); }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context,
            ItemAbility itemAbility, boolean simulate)
    {
        if (context.getItemInHand().getItem() instanceof AxeItem)
        {
            if (!Registrar.ASHEN_OAK_WOOD.getStrippedOrElseOriginal(state).is(state.getBlock()))
            { return Registrar.ASHEN_OAK_WOOD.getStrippedOrElseOriginal(state); }
            if (!Registrar.ASHEN_BIRCH_WOOD.getStrippedOrElseOriginal(state).is(state.getBlock()))
            { return Registrar.ASHEN_BIRCH_WOOD.getStrippedOrElseOriginal(state); }
            if (!Registrar.RUBBER_WOOD.wood().getStrippedOrElseOriginal(state).is(state.getBlock()))
            { return Registrar.RUBBER_WOOD.wood().getStrippedOrElseOriginal(state); }
            if (!Registrar.MAPLE_WOOD.wood().getStrippedOrElseOriginal(state).is(state.getBlock()))
            { return Registrar.MAPLE_WOOD.wood().getStrippedOrElseOriginal(state); }
        }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
