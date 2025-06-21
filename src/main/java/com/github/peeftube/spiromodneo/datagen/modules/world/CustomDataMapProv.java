package com.github.peeftube.spiromodneo.datagen.modules.world;

import com.github.peeftube.spiromodneo.core.init.content.items.FuelBlockItem;
import com.github.peeftube.spiromodneo.core.init.content.items.FuelItem;
import com.github.peeftube.spiromodneo.core.init.registry.data.OreCollection;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class CustomDataMapProv extends DataMapProvider
{
    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    public CustomDataMapProv(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    { super(packOutput, lookupProvider); }

    @Override
    protected void gather()
    {
        final var furnaceFuels = builder(NeoForgeDataMaps.FURNACE_FUELS);

        for (OreCollection ore : OreCollection.ORE_COLLECTIONS)
        {
            if (ore.fuel().isFuel())
            {
                Item raw      = ore.getRawOre().i().get();
                Item blockRaw = ore.getRawOre().c().getItem().get();

                int rawTime = ore.fuel().burnTime();
                int blockTime = ore.fuel().burnTime() * 10;

                furnaceFuels.add(raw.builtInRegistryHolder(), new FurnaceFuel(rawTime), false);
                furnaceFuels.add(blockRaw.builtInRegistryHolder(), new FurnaceFuel(blockTime), false);
            }
        }
    }
}
