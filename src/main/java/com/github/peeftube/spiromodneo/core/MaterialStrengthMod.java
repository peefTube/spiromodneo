package com.github.peeftube.spiromodneo.core;

import com.github.peeftube.spiromodneo.SpiroMod;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@EventBusSubscriber(modid = SpiroMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MaterialStrengthMod
{
    @SubscribeEvent
    public static void modifyDefaultComponents(ModifyDefaultComponentsEvent event)
    {
        event.modify(Items.IRON_AXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 320));
        event.modify(Items.IRON_SWORD, builder -> builder.set(DataComponents.MAX_DAMAGE, 320));
        event.modify(Items.IRON_HOE, builder -> builder.set(DataComponents.MAX_DAMAGE, 320));
        event.modify(Items.IRON_PICKAXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 320));
        event.modify(Items.IRON_SHOVEL, builder -> builder.set(DataComponents.MAX_DAMAGE, 320));
        
        event.modify(Items.GOLDEN_AXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 580));
        event.modify(Items.GOLDEN_SWORD, builder -> builder.set(DataComponents.MAX_DAMAGE, 580));
        event.modify(Items.GOLDEN_HOE, builder -> builder.set(DataComponents.MAX_DAMAGE, 580));
        event.modify(Items.GOLDEN_PICKAXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 580));
        event.modify(Items.GOLDEN_SHOVEL, builder -> builder.set(DataComponents.MAX_DAMAGE, 580));

        // Armor durability mults; this only modifies chainmail, iron, and gold, since diamond and netherite armor are balanced as of writing this
        int chainMult = 17; int ironMult = 18; int goldMult = 20;
        event.modify(Items.CHAINMAIL_HELMET, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.HELMET.getDurability(chainMult)));
        event.modify(Items.CHAINMAIL_CHESTPLATE, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.CHESTPLATE.getDurability(chainMult)));
        event.modify(Items.CHAINMAIL_LEGGINGS, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.LEGGINGS.getDurability(chainMult)));
        event.modify(Items.CHAINMAIL_BOOTS, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.BOOTS.getDurability(chainMult)));
        
        event.modify(Items.IRON_HELMET, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.HELMET.getDurability(ironMult)));
        event.modify(Items.IRON_CHESTPLATE, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.CHESTPLATE.getDurability(ironMult)));
        event.modify(Items.IRON_LEGGINGS, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.LEGGINGS.getDurability(ironMult)));
        event.modify(Items.IRON_BOOTS, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.BOOTS.getDurability(ironMult)));
        event.modify(Items.IRON_HORSE_ARMOR, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.BODY.getDurability(ironMult)));
        
        event.modify(Items.GOLDEN_HELMET, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.HELMET.getDurability(goldMult)));
        event.modify(Items.GOLDEN_CHESTPLATE, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.CHESTPLATE.getDurability(goldMult)));
        event.modify(Items.GOLDEN_LEGGINGS, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.LEGGINGS.getDurability(goldMult)));
        event.modify(Items.GOLDEN_BOOTS, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.BOOTS.getDurability(goldMult)));
        event.modify(Items.GOLDEN_HORSE_ARMOR, builder -> builder.set(DataComponents.MAX_DAMAGE, ArmorItem.Type.BODY.getDurability(goldMult)));
    }
}
