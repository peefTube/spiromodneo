package com.github.peeftube.spiromodneo.core.init.content.blocks;

import com.github.peeftube.spiromodneo.core.init.registry.data.Tappable;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;

public class TapperBlock extends HorizontalDirectionalBlock
{
    public static final MapCodec<TapperBlock> CODEC = simpleCodec(TapperBlock::new);
    public static final IntegerProperty       FILL   = IntegerProperty.create("filled", 0, 3);
    public static final EnumProperty<Tappable> OUTPUT = EnumProperty.create("material", Tappable.class);

    public TapperBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
          .setValue(FACING, Direction.NORTH)
          .setValue(FILL, 0)
          .setValue(OUTPUT, Tappable.EMPTY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    { builder.add(HorizontalDirectionalBlock.FACING, FILL, OUTPUT); }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult)
    { return doUse(state, player) ? ItemInteractionResult.SUCCESS : ItemInteractionResult.FAIL; }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
            Player player, BlockHitResult hitResult)
    { return doUse(state, player) ? InteractionResult.SUCCESS : InteractionResult.FAIL; }

    private boolean doUse(BlockState state, Player player)
    {
        if (state.getValue(FILL) == 0) { return false; }
        else
        {
            Tappable value = state.getValue(OUTPUT);
            if (value == Tappable.EMPTY) { return false; }
            else { player.addItem(new ItemStack(value.getItem(), state.getValue(FILL))); }
        }

        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light

        // First we should check if the tapper is empty. Just to be safe, we'll set the material to empty too.
        if (state.getValue(FILL) == 0) { state.setValue(OUTPUT, Tappable.EMPTY); }

        Direction toCheck = state.getValue(FACING);
        BooleanProperty propertyForInverseOfCheck =
                toCheck == Direction.NORTH ? BlockStateProperties.SOUTH :
                toCheck == Direction.SOUTH ? BlockStateProperties.NORTH :
                toCheck == Direction.EAST ? BlockStateProperties.WEST : BlockStateProperties.EAST;

        BlockState checked = level.getBlockState(pos.offset(toCheck.getNormal()));
        if (checked.is(SpiroTags.Blocks.SUPPORTS_TAPPER) && random.nextInt(7) == 0)
        {
            Block tapSupporter = checked.getBlock();
            if (tapSupporter instanceof TappableWoodBlock woodTapped)
            {
                if (state.getValue(FILL) == 0) { state.setValue(OUTPUT, woodTapped.tapOutput); }
                state.setValue(FILL, state.getValue(FILL) == 3 ? 3 : state.getValue(FILL) + 1);

                checked.setValue(propertyForInverseOfCheck, true)
                       .setValue(TappableWoodBlock.TAPPED, true);
            }
        }
    }

    @Override
    protected MapCodec<TapperBlock> codec() { return CODEC; }
}
