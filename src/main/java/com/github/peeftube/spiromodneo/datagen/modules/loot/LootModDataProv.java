package com.github.peeftube.spiromodneo.datagen.modules.loot;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.registry.data.StoneCollection;
import com.github.peeftube.spiromodneo.datagen.modules.loot.subprov.OtherLootTables;
import com.github.peeftube.spiromodneo.util.loot.SwapLootStackModifier;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LootModDataProv extends GlobalLootModifierProvider
{
    public LootModDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> reg)
    { super(output, reg, SpiroMod.MOD_ID); }

    @Override
    protected void start()
    {
        mobDrops();
        for (StoneCollection stone : StoneCollection.STONE_COLLECTIONS) { baseStoneDrops(stone); }
    }

    private void baseStoneDrops(StoneCollection set)
    {
        // This modifier is wholly unnecessary for modded blocks, or vanilla blocks
        // which already drop the appropriate cobble block
        if (BuiltInRegistries.BLOCK.getKey(set.getBaseStone().get())
                                   .getNamespace().equalsIgnoreCase("minecraft") &&
                (set.getBaseStone().get() != Blocks.STONE && set.getBaseStone().get() != Blocks.DEEPSLATE))
        {
            /* this.add("modify_" + set.material().get() + "_drops",
                    new SwapLootStackModifier(new LootItemCondition[]{
                            new LootTableIdCondition.Builder(
                                    BuiltInRegistries.BLOCK.getKey(set.getBaseStone().get())).build(),
                            new InvertedLootItemCondition(this.hasSilkTouch().build())
                    }, set.getBaseStone().get().asItem(), set.getCobble().get().asItem())); */

            this.add("modify_" + set.material().get() + "_drops",
                    new SwapLootStackModifier(new LootItemCondition[]{
                            new LootTableIdCondition.Builder(
                                    ResourceLocation.withDefaultNamespace(
                                            "blocks/" + set.material().get())).build(),
                            new InvertedLootItemCondition(this.hasSilkTouch().build())
                    }, set.getBaseStone().get().asItem(), set.getCobble().get().asItem()));
        }
    }

    private void mobDrops()
    {
        this.add("pig_drops",
                new AddTableLootModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(
                                ResourceLocation.withDefaultNamespace("entities/pig")).build()
                }, OtherLootTables.PIG_DROPS));

        List<String> sheepColors = new ArrayList<>(List.of(
                "white", "light_gray", "gray", "black", "blue", "light_blue", "brown", "red", "pink",
                "brown", "yellow", "orange", "green", "lime", "purple", "magenta"
        ));

        for (String c : sheepColors)
        {
            this.add("sheep/" + c + "_sheep_drops",
                    new AddTableLootModifier(new LootItemCondition[]{
                            new LootTableIdCondition.Builder(
                                    ResourceLocation.withDefaultNamespace("entities/sheep/" + c)).build()
                    }, OtherLootTables.SHEEP_DROPS));
        }

        this.add("cow_drops",
                new AddTableLootModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(
                                ResourceLocation.withDefaultNamespace("entities/cow")).build()
                }, OtherLootTables.COW_DROPS));
    }

    // Copied from BlockLootSubProvider.java
    protected LootItemCondition.Builder hasSilkTouch()
    {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return MatchTool.toolMatches(
            ItemPredicate.Builder.item()
                                 .withSubPredicate(
                    ItemSubPredicates.ENCHANTMENTS,
                    ItemEnchantmentsPredicate.enchantments(
                        List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Enchantments.SILK_TOUCH),
                                MinMaxBounds.Ints.atLeast(1)))
                    )
                )
        );
    }
}
