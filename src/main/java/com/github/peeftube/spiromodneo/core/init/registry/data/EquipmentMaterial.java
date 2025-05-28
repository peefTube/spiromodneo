package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import net.minecraft.core.Holder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

public enum EquipmentMaterial
{
    LEATHER("leather", Ingredient.of(Items.LEATHER), null, ArmorMaterials.LEATHER),
    WOOD("wood", Ingredient.of(ItemTags.PLANKS), Tiers.WOOD, null),
    STONE("stone", Ingredient.of(ItemTags.STONE_CRAFTING_MATERIALS), Tiers.STONE, null),
    CHAIN("chainmail", Ingredient.EMPTY, null, ArmorMaterials.CHAIN), // Chainmail doesn't have a crafting recipe iirc
    COPPER("copper", Ingredient.of(Items.COPPER_INGOT), null, null),
    IRON("iron", Ingredient.of(Items.IRON_INGOT), Tiers.IRON, ArmorMaterials.IRON),
    LEAD("lead", Ingredient.of(ingotHelper(Registrar.LEAD_METAL)), null, null),
    GOLD("golden", Ingredient.of(Items.GOLD_INGOT), Tiers.GOLD, ArmorMaterials.GOLD),
    DIAMOND("diamond", Ingredient.of(Items.DIAMOND), Tiers.DIAMOND, ArmorMaterials.DIAMOND),
    NETHERITE("netherite", Ingredient.of(Items.NETHERITE_INGOT), Tiers.NETHERITE, ArmorMaterials.NETHERITE);

    private final String name;
    private final Ingredient associatedMats;
    private final Tier toolTier;
    private final Holder<ArmorMaterial> armorTier;

    EquipmentMaterial(String name, Ingredient associatedMats, Tier toolTier, Holder<ArmorMaterial> armorTier)
    { this.name = name; this.associatedMats = associatedMats; this.toolTier = toolTier; this.armorTier = armorTier; }

    private static Item ingotHelper(MetalCollection metal)
    { return metal.ingotData().getIngot().get(); }

    public String getName()
    { return name; }

    public Ingredient getAssociatedMats()
    { return associatedMats; }

    public Tier getToolTier()
    { return toolTier; }

    public Holder<ArmorMaterial> getArmorTier()
    { return armorTier; }
}
