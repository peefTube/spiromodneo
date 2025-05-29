package com.github.peeftube.spiromodneo.util.equipment;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.util.RLUtility;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class CustomArmorMaterial
{
    public static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, int[] protection,
            Holder<SoundEvent> equipSound, int enchantability, float toughness,
            float kbResistance, TagKey<Item> ingredientSet)
    {
        ResourceLocation   loc = RLUtility.makeRL(name);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(loc));
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientSet);

        EnumMap<ArmorItem.Type, Integer> protectMappings = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type t : ArmorItem.Type.values())
        {
            int o = t.ordinal();
            protectMappings.put(t, protection[o]);
        }

        return Registrar.ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(protectMappings,
                enchantability, equipSound, ingredient, layers, toughness, kbResistance));
    }

    public static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, int[] protection,
            Holder<SoundEvent> equipSound, int enchantability, float toughness,
            float kbResistance, Supplier<Item> ingredientIn)
    {
        ResourceLocation   loc = RLUtility.makeRL(name);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(loc));
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientIn.get());

        EnumMap<ArmorItem.Type, Integer> protectMappings = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type t : ArmorItem.Type.values())
        {
            int o = t.ordinal();
            protectMappings.put(t, protection[o]);
        }

        return Registrar.ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(protectMappings,
                enchantability, equipSound, ingredient, layers, toughness, kbResistance));
    }
}
