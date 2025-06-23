package com.github.peeftube.spiromodneo.mixin;

import com.github.peeftube.spiromodneo.util.SpiroTags;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import javax.annotation.Nullable;

@Mixin(FluidType.class)
public abstract class FluidTypeMixin
{
    @ModifyExpressionValue(method = "isVaporizedOnPlacement", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/dimension/DimensionType;ultraWarm()Z"))
    public boolean onCheckVaporized(boolean original, Level level, BlockPos pos, FluidStack stack)
    { return original && !level.getBiomeManager().getBiome(pos).is(SpiroTags.Biomes.getColdNetherTag()); }
}
