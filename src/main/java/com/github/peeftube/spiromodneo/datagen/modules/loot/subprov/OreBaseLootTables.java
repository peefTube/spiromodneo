package com.github.peeftube.spiromodneo.datagen.modules.loot.subprov;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.util.RLUtility;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class OreBaseLootTables implements LootTableSubProvider
{
    public static final ResourceKey<LootTable> COBBLE =
            ResourceKey.create(Registries.LOOT_TABLE, RLUtility.makeRL("ore_drops_cobble"));
    public static final ResourceKey<LootTable> COBBLE_DEEPSLATE =
            ResourceKey.create(Registries.LOOT_TABLE, RLUtility.makeRL("ore_drops_cobbled_deepslate"));
    public static final ResourceKey<LootTable> COBBLE_NETHERRACK =
            ResourceKey.create(Registries.LOOT_TABLE, RLUtility.makeRL("ore_drops_cobbled_netherrack"));

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer)
    {
        consumer.accept(COBBLE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.COBBLESTONE))
                        .setRolls(UniformGenerator.between(0, 1))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))));
        consumer.accept(COBBLE_DEEPSLATE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.COBBLED_DEEPSLATE))
                        .setRolls(UniformGenerator.between(0, 1))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))));
        consumer.accept(COBBLE_NETHERRACK,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Registrar.NETHERRACK_SET.getCobble().get()))
                        .setRolls(UniformGenerator.between(0, 1))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))));
    }
}
