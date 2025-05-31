package com.github.peeftube.spiromodneo.datagen.modules.tags;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BlockTagDataProv extends BlockTagsProvider
{
    public BlockTagDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper eFH)
    { super(output, lookup, SpiroMod.MOD_ID, eFH); }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        // Stone-ore replaceable tags
        tag(SpiroTags.Blocks.SANDSTONE_ORE_REPLACEABLES)
                .add(Blocks.SANDSTONE)
                .add(Blocks.SMOOTH_SANDSTONE);
        tag(SpiroTags.Blocks.RED_SANDSTONE_ORE_REPLACEABLES)
                .add(Blocks.RED_SANDSTONE)
                .add(Blocks.SMOOTH_RED_SANDSTONE);
        tag(SpiroTags.Blocks.BASALT_ORE_REPLACEABLES)
                .add(Blocks.BASALT)
                .add(Blocks.SMOOTH_BASALT);

        // Ores
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreTags(ore); }
    }

    private void oreTags(OreCollection set)
    {
        TagKey<Block> tag = set.getOreBT();
        TagKey<Block> stockTag = set.getMat().getAOT();

        for (OreCoupling c : set.getBulkData().values())
        {
            tag(tag).add(c.getBlock().get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(c.getBlock().get());
        }

        Block b = set.getRawOre().getCoupling().getBlock().get();
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(b);

        if (stockTag != null) { tag(stockTag).addTag(tag); }
    }
}
