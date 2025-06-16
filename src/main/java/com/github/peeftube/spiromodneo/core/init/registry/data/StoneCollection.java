package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.util.stone.StoneData;
import com.github.peeftube.spiromodneo.util.stone.StoneUtilities;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A stone collection contains data related to all potential construction uses of a given type of stone.
 * This is not something which would ordinarily find use in vanilla Minecraft, but for the purposes of this mod
 * is used to enable all given stone types to be refined similarly to vanilla stone, and to provide all available
 * variations (polished, mossy, etc.) to all stone types. This adds a significant amount of data, so having some
 * method of structuring the data will be necessary.
 * @param material Defines the base material of the collection. The oreBase field of the enum is not used here
 *                 as this is exclusively restricted to use in ore and worldgen purposes.
 * @param bulkData This contains the majority of the data for the collection.
 */
public record StoneCollection(StoneMaterial material, StoneData bulkData) implements StoneUtilities
{
    public static List<StoneCollection> STONE_COLLECTIONS = new ArrayList<>();

    public static StoneCollection registerCollection(StoneMaterial material)
    {
        StoneCollection collection = new StoneCollection(material,
                StoneUtilities.generateData(material));
        STONE_COLLECTIONS.add(collection); return collection;
    }

    public Map<Integer, Map<Supplier<Block>, Boolean>> returnBlockData()
    {
        Map<Integer, Map<Supplier<Block>, Boolean>> mappings = new HashMap<>();

        Map<Supplier<Block>, Boolean> rawMappings =
                this.bulkData.getStone().getValues(); mappings.put(0, rawMappings);
        Map<Supplier<Block>, Boolean> cobbleMappings =
                this.bulkData.getCobble().getValues(); mappings.put(1, cobbleMappings);
        Map<Supplier<Block>, Boolean> smoothMappings =
                this.bulkData.getSmooth().getValues(); mappings.put(2, smoothMappings);
        Map<Supplier<Block>, Boolean> polishedMappings =
                this.bulkData.getPolished().getValues(); mappings.put(3, polishedMappings);
        Map<Supplier<Block>, Boolean> bricksMappings =
                this.bulkData.getBricks().getValues(); mappings.put(4, bricksMappings);
        Map<Supplier<Block>, Boolean> tilesMappings =
                this.bulkData.getTile().getValues(); mappings.put(5, tilesMappings);
        Map<Supplier<Block>, Boolean> cutMappings =
                this.bulkData.getCut().getValues(); mappings.put(6, cutMappings);
        Map<Supplier<Block>, Boolean> miscMappings =
                this.bulkData.getMisc().getValues(); mappings.put(7, miscMappings);

        return mappings;
    }
}