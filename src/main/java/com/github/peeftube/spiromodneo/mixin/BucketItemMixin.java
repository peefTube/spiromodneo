package com.github.peeftube.spiromodneo.mixin;

import com.github.peeftube.spiromodneo.util.SpiroTags;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import javax.annotation.Nullable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin
{
    @ModifyExpressionValue(method = "emptyContents(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/item/ItemStack;)Z",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/dimension/DimensionType;ultraWarm()Z"))
    public boolean onEmptyContents(boolean original, @Nullable Player player, Level level, BlockPos pos,
            @Nullable BlockHitResult result, @Nullable ItemStack container)
    { return original && level.getBiomeManager().getBiome(result.getBlockPos()).is(SpiroTags.Biomes.getColdNetherTag()); }
}
