package com.github.peeftube.spiromodneo.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TrunkPlacer.class)
public abstract class TrunkPlacerMixin
{
    @ModifyReturnValue(method = "isFree", at = @At(value = "RETURN"))
    public boolean onCheckIsFree(boolean original, LevelSimulatedReader level, BlockPos pos)
    { return original && !level.isStateAtPosition(pos, p_226183_ -> p_226183_.is(BlockTags.LOGS)); }
}
