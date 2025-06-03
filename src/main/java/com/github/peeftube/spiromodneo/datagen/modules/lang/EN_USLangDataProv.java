package com.github.peeftube.spiromodneo.datagen.modules.lang;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.*;
import com.github.peeftube.spiromodneo.util.ore.BaseStone;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;

public class EN_USLangDataProv extends LanguageProvider
{
    public EN_USLangDataProv(PackOutput output, String locale)
    { super(output, SpiroMod.MOD_ID, locale); }

    @Override
    protected void addTranslations()
    {
        // Metals
        for (MetalCollection metal : MetalCollection.METAL_COLLECTIONS) { metalParser(metal); }

        // Ores
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreParser(ore); }

        // Equipment
        for (EquipmentCollection equip : EquipmentCollection.EQUIP_COLLECTIONS) { equipParser(equip); }

        // Override name of "Nether Quartz", call it "Quartz" instead.
        add(Items.QUARTZ, "Quartz");

        // Other / Loose
        add(Registrar.SINEW.get(), "Animal Sinew");

        add(Registrar.CAST_IRON_MIXTURE.get(), "Cast Iron Mixture");
        add(Registrar.CAST_IRON.get(), "Cast Iron Ingot");
        add(Registrar.STEEL_MIXTURE.get(), "Steel Mixture");
        add(Registrar.CRUSHED_CARBON.get(), "Carbon Dust");

        // Creative tabs
        add(Registrar.TAB_TITLE_KEY_FORMULAIC + ".minerals_tab", "Ores and Raw Minerals");
    }

    protected void equipParser(EquipmentCollection set)
    {
        boolean isStock = false;

        EquipmentMaterial material = set.getMat();

        if (material == EquipmentMaterial.CHAIN || material == EquipmentMaterial.WOOD ||
                material == EquipmentMaterial.STONE || material == EquipmentMaterial.IRON ||
                material == EquipmentMaterial.LEATHER || material == EquipmentMaterial.GOLD ||
                material == EquipmentMaterial.DIAMOND || material == EquipmentMaterial.NETHERITE )
        { isStock = true; }

        if (!isStock)
        {
            String mat = material.getName().substring(0, 1).toUpperCase() + material.getName().substring(1);

            if (set.checkIfNullThenPass(set.bulkData().getTools()).getResult())
            {
                add(set.bulkData().getTools().getSword().get(), mat + " Sword");
                add(set.bulkData().getTools().getShovel().get(), mat + " Shovel");
                add(set.bulkData().getTools().getHoe().get(), mat + " Hoe");
                add(set.bulkData().getTools().getAxe().get(), mat + " Axe");
                add(set.bulkData().getTools().getPickaxe().get(), mat + " Pickaxe");
            }

            if (set.checkIfNullThenPass(set.bulkData().getArmor()).getResult())
            {
                add(set.bulkData().getArmor().getHelmet().get(), mat + " Helmet");
                add(set.bulkData().getArmor().getChestplate().get(), mat + " Chestplate");
                add(set.bulkData().getArmor().getLeggings().get(), mat + " Leggings");
                add(set.bulkData().getArmor().getBoots().get(), mat + " Boots");
            }

            if (set.checkIfNullThenPass(set.bulkData().getHorseArmor()).getResult())
            { add(set.bulkData().getHorseArmor().get(), mat + " Horse Armor"); }
        }
    }

    // Metal set handler
    protected void metalParser(MetalCollection set)
    {
        boolean ignoreIngotBlocks = false; // For ignoring default ingot and metallic block combinations

        MetalMaterial material = set.getMat();

        if (material == MetalMaterial.IRON || material == MetalMaterial.GOLD || material == MetalMaterial.COPPER
                || material == MetalMaterial.NETHERITE )
        { ignoreIngotBlocks = true; }

        if (!ignoreIngotBlocks)
        {
            // Make this code easier to read, PLEASE..
            Block b = set.ingotData().getMetal().getBlock().get();
            Item i  = set.ingotData().getIngot().get();
            String mat = material.get();

            // Readable block String:
            String blockOf = "Block of ";
            add(b, blockOf + mat.substring(0, 1).toUpperCase() + mat.substring(1));

            // Readable item String:
            String readableMat = mat.substring(0, 1).toUpperCase() + mat.substring(1) + " Ingot";
            add(i, readableMat);
        }
    }

    // Ore set handler
    protected void oreParser(OreCollection set)
    {
        // Flags for what we should ignore.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks.

        // Prepare set data.
        OreMaterial                 material = set.getMat();
        Map<StoneMaterial, OreCoupling> bulkData = set.getBulkData();

        if (material == OreMaterial.COAL || material == OreMaterial.IRON || material == OreMaterial.COPPER
                || material == OreMaterial.GOLD || material == OreMaterial.LAPIS || material == OreMaterial.REDSTONE
                || material == OreMaterial.EMERALD || material == OreMaterial.DIAMOND)
        { ignoreStone = true; }

        if (material == OreMaterial.GOLD || material == OreMaterial.QUARTZ)
        { ignoreNether = true; }

        for (StoneMaterial s : StoneMaterial.values())
        {
            if (((s == StoneMaterial.STONE || s == StoneMaterial.DEEPSLATE) && ignoreStone)
                    || ((s == StoneMaterial.NETHERRACK) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination...

            // Make this code easier to read, PLEASE..
            Block b = bulkData.get(s).block().get();
            String mat = material.get();

            // Generate a translation string and then add it to the translation set.
            String readableMat = mat.substring(0, 1).toUpperCase() + mat.substring(1) + " Ore";
            add(b, generateOreBlockString(s.getOreBase(), readableMat));
        }

        // Raw block and item; assume not vanilla.
        if (!(ignoreStone || ignoreNether))
        {
            // Make this code easier to read, PLEASE..
            Block b = set.getRawOre().getCoupling().getBlock().get();
            Item i  = set.getRawOre().getRawItem().get();
            String mat = material.get();

            // Readable block String:
            String rawMineral       = material.isGem() ? "" : "Raw ";
            String readableBlockMat = "Block of " + mat.substring(0, 1).toUpperCase() + mat.substring(1);
            add(b, rawMineral + readableBlockMat);

            // Readable item String:
            String readableMat = rawMineral + mat.substring(0, 1).toUpperCase() + mat.substring(1) ;
            add(i, readableMat);
        }
    }

    // Ore set String subroutine
    protected String generateOreBlockString(BaseStone s, String readable)
    {
        // OPTIONAL, BUT PREFERRED. Defaults to Stone string otherwise.
        Map<BaseStone, String> genFormulae = new HashMap<>();
        genFormulae.put(BaseStone.ANDESITE, readable + " (Andesite)");
        genFormulae.put(BaseStone.DIORITE, readable + " (Diorite)");
        genFormulae.put(BaseStone.GRANITE, readable + " (Granite)");
        genFormulae.put(BaseStone.CALCITE, readable + " (Calcite)");
        genFormulae.put(BaseStone.SMS, readable + " (Smooth Sandstone)");
        genFormulae.put(BaseStone.SMRS, readable + " (Smooth Red Sandstone)");
        genFormulae.put(BaseStone.DEEPSLATE, "Deepslate " + readable);
        genFormulae.put(BaseStone.TUFF, readable + " (Tuff)");
        genFormulae.put(BaseStone.DRIPSTONE, readable + " (Dripstone)");
        genFormulae.put(BaseStone.NETHERRACK, "Nether " + readable);
        genFormulae.put(BaseStone.BASALT, readable + " (Basalt)");
        genFormulae.put(BaseStone.ENDSTONE, "Ender " + readable);

        return genFormulae.getOrDefault(s, readable);
    }
}
