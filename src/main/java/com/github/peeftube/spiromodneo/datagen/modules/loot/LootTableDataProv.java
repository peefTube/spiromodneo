package com.github.peeftube.spiromodneo.datagen.modules.loot;

import com.github.peeftube.spiromodneo.datagen.modules.loot.subprov.BlockLootTables;
import com.github.peeftube.spiromodneo.datagen.modules.loot.subprov.OreBaseLootTables;
import com.github.peeftube.spiromodneo.datagen.modules.loot.subprov.OtherLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootTableDataProv extends LootTableProvider
{
    public LootTableDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> reg)
    {
        super(output, Set.of(), List.of(new LootTableProvider
                .SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
                                        new LootTableProvider
                .SubProviderEntry(OreBaseLootTables::new, LootContextParamSets.ALL_PARAMS),
                                        new LootTableProvider
                .SubProviderEntry(OtherLootTables::new, LootContextParamSets.ALL_PARAMS)), reg);
    }
}
