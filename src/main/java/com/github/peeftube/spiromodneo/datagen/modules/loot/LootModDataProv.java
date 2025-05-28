package com.github.peeftube.spiromodneo.datagen.modules.loot;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.datagen.modules.loot.subprov.OtherLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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
}
