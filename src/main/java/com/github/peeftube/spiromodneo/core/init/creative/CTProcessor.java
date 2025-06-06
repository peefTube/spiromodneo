package com.github.peeftube.spiromodneo.core.init.creative;

import com.github.peeftube.spiromodneo.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedHashSet;
import java.util.Set;

public class CTProcessor
{
    public static Set<ItemStack> precacheMineralsTab()
    {
        Set<ItemStack> output = new LinkedHashSet<>();

        // Run over all ore sets.
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS)
        {
            for (OreCoupling c : ore.getBulkData().values())
            {
                ItemStack iS = c.getItem().get().getDefaultInstance();
                output.add(iS);
            }

            ItemStack iSBlock = ore.getRawOre().getCoupling().getItem().get().getDefaultInstance();
            ItemStack iSItem = ore.getRawOre().getRawItem().get().getDefaultInstance();
            output.add(iSBlock); output.add(iSItem);
        }

        return output;
    }
}