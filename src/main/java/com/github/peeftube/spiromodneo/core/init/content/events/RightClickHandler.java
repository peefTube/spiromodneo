package com.github.peeftube.spiromodneo.core.init.content.events;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import org.joml.Random;

@EventBusSubscriber(modid = SpiroMod.MOD_ID)
public class RightClickHandler
{
    @SubscribeEvent
    public static void onUseItem(UseItemOnBlockEvent event)
    {
        if (event.getItemStack().is(Items.STICK) && event.getLevel().getBlockState(event.getPos()).is(BlockTags.DIRT))
        {
            if (0.02 >= event.getLevel().getRandom().nextFloat() && event.getPlayer() != null)
            { event.getPlayer().addItem(Registrar.SMALL_STONE.toStack()); }
        }
    }
}
