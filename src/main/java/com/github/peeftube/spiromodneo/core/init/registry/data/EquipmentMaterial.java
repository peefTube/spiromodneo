package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import net.minecraft.core.Holder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

public enum EquipmentMaterial
{
    LEATHER("leather", SpiroTags.Items.LEATHER_MATERIAL, null, ArmorMaterials.LEATHER),
    WOOD("wood", SpiroTags.Items.WOOD_MATERIAL, Tiers.WOOD, null),
    STONE("stone", SpiroTags.Items.STONE_MATERIAL, Tiers.STONE, null),
    CHAIN("chainmail", SpiroTags.Items.CHAINMAIL_MATERIAL, null, ArmorMaterials.CHAIN), // Chainmail doesn't have a crafting recipe iirc
    COPPER("copper", SpiroTags.Items.COPPER_MATERIAL, Registrar.T_COPPER, Registrar.A_COPPER),
    IRON("iron", SpiroTags.Items.IRON_MATERIAL, Tiers.IRON, ArmorMaterials.IRON),
    LEAD("lead", SpiroTags.Items.LEAD_MATERIAL, Registrar.T_LEAD, Registrar.A_LEAD),
    STEEL("steel", SpiroTags.Items.STEEL_MATERIAL, Registrar.T_STEEL, Registrar.A_STEEL),
    GOLD("golden", SpiroTags.Items.GOLD_MATERIAL, Tiers.GOLD, ArmorMaterials.GOLD),
    DIAMOND("diamond", SpiroTags.Items.DIAMOND_MATERIAL, Tiers.DIAMOND, ArmorMaterials.DIAMOND),
    NETHERITE("netherite", SpiroTags.Items.NETHERITE_MATERIAL, Tiers.NETHERITE, ArmorMaterials.NETHERITE);

    private final String name;
    private final TagKey<Item> associatedTag;
    private final Tier toolTier;
    private final Holder<ArmorMaterial> armorTier;

    EquipmentMaterial(String name, TagKey<Item> associatedTag, Tier toolTier, Holder<ArmorMaterial> armorTier)
    { this.name = name; this.associatedTag = associatedTag; this.toolTier = toolTier; this.armorTier = armorTier; }

    public String getName()
    { return name; }

    public TagKey<Item> getAssociatedTag()
    { return associatedTag; }

    public Tier getToolTier()
    { return toolTier; }

    public Holder<ArmorMaterial> getArmorTier()
    { return armorTier; }
}
