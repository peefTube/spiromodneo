package com.github.peeftube.spiromodneo.datagen.modules.tags;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.OreMaterial;
import com.github.peeftube.spiromodneo.core.init.registry.data.StoneCollection;
import com.github.peeftube.spiromodneo.core.init.registry.data.StoneMaterial;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import com.github.peeftube.spiromodneo.util.ore.OreCoupling;
import com.github.peeftube.spiromodneo.util.stone.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.concurrent.CompletableFuture;

import static com.github.peeftube.spiromodneo.util.stone.StoneSetPresets.getPresets;

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

        // Stone collections
        for (StoneCollection stone : StoneCollection.STONE_COLLECTIONS) { stoneTags(stone); }

        // Tool level setup
        tag(SpiroTags.Blocks.INCORRECT_FOR_COPPER)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(SpiroTags.Blocks.NEEDS_GOLD_TOOL)
                .addTag(SpiroTags.Blocks.NEEDS_STEEL_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(SpiroTags.Blocks.INCORRECT_FOR_LEAD)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(SpiroTags.Blocks.NEEDS_GOLD_TOOL)
                .addTag(SpiroTags.Blocks.NEEDS_STEEL_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .remove(BlockTags.NEEDS_IRON_TOOL)
                .remove(BlockTags.NEEDS_STONE_TOOL)
                .addTag(SpiroTags.Blocks.NEEDS_STEEL_TOOL);
        tag(SpiroTags.Blocks.INCORRECT_FOR_STEEL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
    }

    private void stoneTags(StoneCollection set)
    {
        TagKey<Block> tag = set.tags().getBlockTag();
        tag(tag).add(set.getBaseStone().get());

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
                    {
                        if (k2 == StoneSubBlockType.WALL)
                        { tag(BlockTags.WALLS).add(knownCouplingReadBlockFromKeys(data, k0, k1, k2)); }
                    }
                }
            }
        }
    }
    private Block knownCouplingReadBlockFromKeys(StoneData d,
            StoneBlockType k0, StoneVariantType k1, StoneSubBlockType k2)
    { return d.getCouplingForKeys(k0, k1, k2).getBlock().get(); }

    private void oreTags(OreCollection set)
    {
        TagKey<Block> tag = set.getOreBT();
        TagKey<Block> stockTag = set.getMat().getAOT();

        OreMaterial m = set.getMat();

        for (OreCoupling c : set.getBulkData().values())
        {
            Block o = c.getBlock().get();
            tag(tag).add(o);
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(o);

            switch(m)
            {
                case COPPER, IRON, LEAD, LAPIS -> { tag(BlockTags.NEEDS_STONE_TOOL).add(o); }
                case GOLD, REDSTONE -> { tag(BlockTags.NEEDS_IRON_TOOL).add(o); }
                case RUBY, EMERALD ->
                {
                    tag(BlockTags.NEEDS_IRON_TOOL).remove(o);
                    tag(SpiroTags.Blocks.NEEDS_GOLD_TOOL).add(o);
                }
                case DIAMOND->
                {
                    tag(BlockTags.NEEDS_IRON_TOOL).remove(o);
                    tag(SpiroTags.Blocks.NEEDS_STEEL_TOOL).add(o);
                }
                case METHANE_ICE ->
                {
                    tag(BlockTags.NEEDS_IRON_TOOL).add(o);
                    tag(BlockTags.PIGLIN_REPELLENTS).add(o);
                }
            }
        }

        Block b = set.getRawOre().getCoupling().getBlock().get();
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(b);

        switch(m)
        {
            case LEAD -> { tag(BlockTags.NEEDS_STONE_TOOL).add(b); }
            case RUBY, EMERALD ->
            {
                tag(BlockTags.NEEDS_IRON_TOOL).remove(b);
                tag(SpiroTags.Blocks.NEEDS_GOLD_TOOL).add(b);
            }
            case DIAMOND ->
            {
                tag(BlockTags.NEEDS_IRON_TOOL).remove(b);
                tag(SpiroTags.Blocks.NEEDS_STEEL_TOOL).add(b);
            }
            case METHANE_ICE ->
            {
                tag(BlockTags.NEEDS_IRON_TOOL).add(b);
                tag(BlockTags.PIGLIN_REPELLENTS).add(b);
            }
        }

        if (stockTag != null) { tag(stockTag).addTag(tag); }
    }
}
