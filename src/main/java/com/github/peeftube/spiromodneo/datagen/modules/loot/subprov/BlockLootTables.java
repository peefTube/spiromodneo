package com.github.peeftube.spiromodneo.datagen.modules.loot.subprov;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.*;
import com.github.peeftube.spiromodneo.util.ore.BaseStone;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider
{
    public BlockLootTables(HolderLookup.Provider reg)
    { super(Set.of(), FeatureFlags.REGISTRY.allFlags(), reg); }

    @Override
    protected void generate()
    {
        // Simple drop-self tables
        for (MetalCollection metal : MetalCollection.METAL_COLLECTIONS) { metalTables(metal); }
        dropSelf(Registrar.MANUAL_CRUSHER.get());

        // Ore tables
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreTables(ore); }
    }

    protected void metalTables(MetalCollection set)
    {
        boolean ignoreIngotBlocks = false; // For ignoring default ingot and metallic block combinations

        MetalMaterial material = set.getMat();

        if (material == MetalMaterial.IRON || material == MetalMaterial.GOLD || material == MetalMaterial.COPPER
                || material == MetalMaterial.NETHERITE )
        { ignoreIngotBlocks = true; }

        if (!ignoreIngotBlocks)
        {
            // Make this code easier to read, PLEASE..
            Block self = set.ingotData().getMetal().getBlock().get();
            dropSelf(self);
        }
    }

    protected void oreTables(OreCollection set)
    {
        // Flags for what we should ignore.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks.

        // Prepare set data.
        OreMaterial                     material = set.getMat();
        Map<StoneMaterial, OreCoupling> bulkData = set.getBulkData();

        if (material == OreMaterial.COAL || material == OreMaterial.IRON || material == OreMaterial.COPPER
                || material == OreMaterial.GOLD || material == OreMaterial.LAPIS || material == OreMaterial.REDSTONE
                || material == OreMaterial.EMERALD || material == OreMaterial.DIAMOND)
        { ignoreStone = true; }

        if (material == OreMaterial.GOLD || material == OreMaterial.QUARTZ)
        { ignoreNether = true; }

        for (StoneMaterial s : bulkData.keySet())
        {
            if (((s == StoneMaterial.STONE || s == StoneMaterial.DEEPSLATE) && ignoreStone)
                    || ((s == StoneMaterial.NETHERRACK) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination,
                          // you'll want to use loot modifiers instead in this case.

            // Make this code easier to read, PLEASE..
            Block self = bulkData.get(s).getBlock().get();
            Item oreToDrop = set.getRawOre().getRawItem().get();
            NumberProvider oreToDropAmounts = set.getDropCount();

            add(self, oreTable01(self, s.getOreBase().getAssociatedBlock().get(), oreToDrop, oreToDropAmounts));
        }

        // For block of this ore, assuming that it doesn't exist in vanilla already.
        if (!(ignoreStone || ignoreNether)) { dropSelf(set.getRawOre().getCoupling().getBlock().get()); };
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    { return Registrar.BLOCKS.getEntries().stream().<Block>map(DeferredHolder::value).toList(); }

    protected LootTable.Builder oreTable01(Block b0, Block b1, Item item, NumberProvider dropAmtRange)
    {
        HolderLookup.RegistryLookup<Enchantment> regLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(b0,
                this.applyExplosionDecay(b0, LootItem.lootTableItem(item))
                        .apply(SetItemCountFunction.setCount(dropAmtRange))
                        .apply(ApplyBonusCount.addOreBonusCount(regLookup.getOrThrow(Enchantments.FORTUNE))));
    }

    // Old code from 1.20.x, this no longer works and is here only for reference
    /* protected LootTable.Builder oreTable01(Block b0, Block b1, Item item, NumberProvider dropAmtRange)
    {
        LootPool.Builder mainPool = LootPool.lootPool();

        mainPool.setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(

                        // Silk touch.
                        LootItem.lootTableItem(b0)
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))),

                        // Otherwise:
                        EntryGroup.list(
                                // Generic drop.
                                LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(dropAmtRange))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                        .apply(ApplyExplosionDecay.explosionDecay()),

                                // Stone drop type associated with this ore variant.
                                LootItem.lootTableItem(b1)
                                        .when(LootItemRandomChanceCondition.randomChance(0.1f))))); // 10% chance to drop base block.

        return LootTable.lootTable().withPool(mainPool);
    } */
}