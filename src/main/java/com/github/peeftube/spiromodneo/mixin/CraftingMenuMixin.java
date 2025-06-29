package com.github.peeftube.spiromodneo.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CraftingMenu.class)
public abstract class CraftingMenuMixin
{
    @Shadow @Final private ContainerLevelAccess access;

    @ModifyReturnValue(method = "stillValid", at = @At(value = "RETURN"))
    public boolean stillValid(boolean original, Player player)
    {
        return !original ? this.access.evaluate((l, p) ->
                l.getBlockState(p).is(Tags.Blocks.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
                        && player.canInteractWithBlock(p, 4.0), true) : true;
    }
}
