package com.github.peeftube.spiromodneo.core.init.content.blocks.entity;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.Registrar.*;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ExtensibleHangingSignBlockEntity extends SignBlockEntity
{
    private static final int MAX_TEXT_LINE_WIDTH = 60;
    private static final int TEXT_LINE_HEIGHT = 9;

    public ExtensibleHangingSignBlockEntity(BlockPos pos, BlockState blockState)
    { super(Registrar.HANGING_SIGN_ENTITYTYPE.get(), pos, blockState); }

    public int getTextLineHeight() { return TEXT_LINE_HEIGHT; }

    public int getMaxTextLineWidth() { return MAX_TEXT_LINE_WIDTH; }

    public SoundEvent getSignInteractionFailedSoundEvent()
    { return SoundEvents.WAXED_HANGING_SIGN_INTERACT_FAIL; }
}
