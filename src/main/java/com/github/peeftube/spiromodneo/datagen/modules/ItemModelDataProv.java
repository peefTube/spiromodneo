package com.github.peeftube.spiromodneo.datagen.modules;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.*;
import com.github.peeftube.spiromodneo.util.DataCheckResult;
import com.github.peeftube.spiromodneo.util.RLUtility;
import com.github.peeftube.spiromodneo.util.equipment.ArmorSet;
import com.github.peeftube.spiromodneo.util.equipment.ToolSet;
import com.github.peeftube.spiromodneo.util.metal.MetalUtilities;
import com.github.peeftube.spiromodneo.util.ore.BaseStone;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import com.github.peeftube.spiromodneo.util.stone.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.function.Supplier;

import static com.github.peeftube.spiromodneo.util.stone.StoneSetPresets.getPresets;

public class ItemModelDataProv extends ItemModelProvider
{
    public ItemModelDataProv(PackOutput output, ExistingFileHelper eFH)
    {
        super(output, SpiroMod.MOD_ID, eFH);
    }

    @Override
    protected void registerModels()
    {
        // ============================================================================================================
        // Normal items
        itemParser(Registrar.SINEW);
        itemParser(Registrar.CAST_IRON_MIXTURE);
        itemParser(Registrar.CAST_IRON);
        itemParser(Registrar.STEEL_MIXTURE);
        itemParser(Registrar.WEAK_STEEL_MIXTURE);
        itemParser(Registrar.CRUSHED_CARBON);
        for (EquipmentCollection equip : EquipmentCollection.EQUIP_COLLECTIONS) { equipmentSetDesign(equip); }

        // ============================================================================================================
        // Block items
        for (MetalCollection metal : MetalCollection.METAL_COLLECTIONS) { metalSetDesign(metal); }
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreSetDesign(ore); }
        for (StoneCollection stone : StoneCollection.STONE_COLLECTIONS) { stoneSetDesign(stone); }
        blockParser(Registrar.MANUAL_CRUSHER_ITEM);
    }

    protected void equipmentSetDesign(EquipmentCollection set)
    {
        DataCheckResult toolCheck = set.checkIfNullThenPass(set.bulkData().getTools());
        DataCheckResult armorCheck = set.checkIfNullThenPass(set.bulkData().getArmor());
        DataCheckResult horseCheck = set.checkIfNullThenPass(set.bulkData().getHorseArmor());

        ToolSet tools;
        ArmorSet armor;
        DeferredItem<Item> horseArmor;

        if (toolCheck.getResult())
        {
            tools = set.bulkData().getTools();
            itemParser((DeferredItem<Item>) tools.getSword(), 1);
            itemParser((DeferredItem<Item>) tools.getShovel(), 1);
            itemParser((DeferredItem<Item>) tools.getHoe(), 1);
            itemParser((DeferredItem<Item>) tools.getAxe(), 1);
            itemParser((DeferredItem<Item>) tools.getPickaxe(), 1);
        }

        if (armorCheck.getResult())
        {
            armor = set.bulkData().getArmor();
            itemParser((DeferredItem<Item>) armor.getHelmet());
            itemParser((DeferredItem<Item>) armor.getChestplate());
            itemParser((DeferredItem<Item>) armor.getLeggings());
            itemParser((DeferredItem<Item>) armor.getBoots());
        }

        if (horseCheck.getResult())
        {
            horseArmor = (DeferredItem<Item>) set.bulkData().getHorseArmor();
            itemParser((DeferredItem<Item>) horseArmor);
        }
    }

    protected void metalSetDesign(MetalCollection set)
    {
        boolean ignoreIngotBlocks = false; // For ignoring default ingot and metallic block combinations

        MetalMaterial material = set.getMat();

        if (material == MetalMaterial.IRON || material == MetalMaterial.GOLD || material == MetalMaterial.COPPER
                || material == MetalMaterial.NETHERITE )
        { ignoreIngotBlocks = true; }

        if (!ignoreIngotBlocks)
        {
            // This set does not exist already. Vanilla metals are very thorough on this front.
            // Make this code easier to read, PLEASE..
            Supplier<Item> i = set.ingotData().getIngot();
            Supplier<Item> b = set.ingotData().getMetal().getItem();

            // This part is extremely easy, fortunately.
            blockParser((DeferredItem<Item>) b);
            itemParser((DeferredItem<Item>) i);
        }
    }

    private void stoneSetDesign(StoneCollection set)
    {
        StoneData     data = set.bulkData();
        StoneMaterial mat  = set.material();

        for (StoneBlockType k0 : StoneBlockType.values())
        {
            for (StoneVariantType k1 : StoneVariantType.values())
            {
                for (StoneSubBlockType k2 : StoneSubBlockType.values())
                {
                    boolean available = data.doesCouplingExistForKeys(k0, k1, k2);
                    String  baseKey   = ExistingStoneCouplings.getKey(set.material(), k0, k1, StoneSubBlockType.DEFAULT);
                    String  key       = ExistingStoneCouplings.getKey(set.material(), k0, k1, k2);

                    boolean isDefault      = k2 == StoneSubBlockType.DEFAULT;
                    boolean textureIsStock = getPresets().containsKey(isDefault ? key : baseKey);
                    String  ns             = getPresets().containsKey(baseKey) ? "minecraft" : SpiroMod.MOD_ID;

                    if (available && !getPresets().containsKey(key))
                    { blockParser((DeferredItem<Item>) set.bulkData().getCouplingForKeys(k0, k1, k2).getItem()); }
                }
            }
        }
    }

    // Ore BlockItem handler subroutine
    // Copied from BlockstateDataProv.java
    protected void oreSetDesign(OreCollection set)
    {
        // Flags for what we should ignore.
        boolean ignoreStone  = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks and raw ore item.

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
            { continue; } // Do nothing, we're using a combination which already has a BlockItem

            // Make this code easier to read, PLEASE..
            Supplier<Item> i = bulkData.get(s).item();

            // This part is extremely easy, fortunately.
            blockParser((DeferredItem<Item>) i);
        }

        // Raw block and item; assume not vanilla.
        if (!(ignoreStone || ignoreNether))
        {
            // Make this code easier to read, PLEASE..
            Supplier<Item> b = set.getRawOre().getCoupling().getItem();
            Supplier<Item> i = set.getRawOre().getRawItem();

            // This part is extremely easy, fortunately.
            blockParser((DeferredItem<Item>) b);
            itemParser((DeferredItem<Item>) i);
        }
    }

    // Block item model creation subroutine
    protected void blockParser(DeferredItem<Item> item)
    {
        String path = "block/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath();
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), modLoc(path));
    }

    private ItemModelBuilder itemParser(DeferredItem<Item> item)
    { return itemParser(item, 0, item.getId().getPath()); }

    private ItemModelBuilder itemParser(DeferredItem<Item> item, int type)
    { return itemParser(item, type, item.getId().getPath()); }

    private ItemModelBuilder itemParser(DeferredItem<Item> item, int type, String imageName)
    {
        switch(type)
        {
            case 1 ->
            {
                // Handheld item.
                return withExistingParent(item.getId().getPath(),
                        RLUtility.invokeRL("minecraft:item/handheld")).texture("layer0",
                        RLUtility.makeRL("item/" + imageName));
            }
            default ->
            {
                // Assume basic type.
                return withExistingParent(item.getId().getPath(),
                        RLUtility.invokeRL("minecraft:item/generated")).texture("layer0",
                        RLUtility.makeRL("item/" + imageName));
            }
        }
    }
}
