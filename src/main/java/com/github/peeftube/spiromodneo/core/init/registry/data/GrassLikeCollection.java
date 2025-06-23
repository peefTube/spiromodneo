package com.github.peeftube.spiromodneo.core.init.registry.data;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.util.GenericBlockItemCoupling;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import com.github.peeftube.spiromodneo.util.TagCoupling;
import com.github.peeftube.spiromodneo.util.soil.GrassLikeUtilities;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.*;
import java.util.function.Supplier;

public record GrassLikeCollection(GrassLike type, Map<Soil, GenericBlockItemCoupling> bulkData,
                                  TagCoupling tags) implements GrassLikeUtilities
{
    public static List<GrassLikeCollection> GRASS_COLLECTIONS = new ArrayList<>();

    public static GrassLikeCollection registerCollection(GrassLike type)
    { return registerCollection(type, 0); }

    public static GrassLikeCollection registerCollection(GrassLike type, int li)
    {
        Map<Soil, GenericBlockItemCoupling> mappings = new HashMap<>();

        for (Soil s : Soil.values())
        {
            switch(s)
            {
                case DIRT ->
                {
                    switch(type)
                    {
                        case GRASS, MYCELIUM -> mappings.put(s, findPreset(s, type));
                        default -> mappings.put(s, createNew(s, type, li));
                    }
                }
                case NETHERRACK ->
                {
                    switch(type)
                    {
                        case CRIMSON_NYLIUM, WARPED_NYLIUM -> mappings.put(s, findPreset(s, type));
                        default -> mappings.put(s, createNew(s, type, li));
                    }
                }
                default -> mappings.put(s, createNew(s, type, li));
            }
        }

        String tagName = "spiro_" + type.getName() + "_grass_like";
        TagKey<Block> bTag = SpiroTags.Blocks.tag(tagName);
        TagKey<Item> iTag = SpiroTags.Items.tag(tagName);
        TagCoupling tags = new TagCoupling(bTag, iTag);

        GrassLikeCollection collection = new GrassLikeCollection(type, mappings, tags);
        GRASS_COLLECTIONS.add(collection); return collection;
    }

    private static GenericBlockItemCoupling findPreset(Soil s, GrassLike g)
    { return GrassLikeUtilities.getComboPresets().get(g).get(s); }

    private static GenericBlockItemCoupling createNew(Soil s, GrassLike g, int li)
    {
        Supplier<? extends Block> bSup;
        Supplier<? extends Item> iSup;

        String blockName = g.getName() + "_on_" + s.getName();

        switch(g)
        {
            case WARPED_NYLIUM, CRIMSON_NYLIUM ->
            {
                bSup = () -> new NyliumBlock(BlockBehaviour.Properties.of()
                        .lightLevel(state -> li).sound(SoundType.NYLIUM));
                bSup = Registrar.regBlock(blockName, bSup);
                iSup = Registrar.regSimpleBlockItem((DeferredBlock<? extends Block>) bSup);
            }
            case MYCELIUM ->
            {
                bSup = () -> new MyceliumBlock(BlockBehaviour.Properties.of()
                        .lightLevel(state -> li).sound(SoundType.GRASS));
                bSup = Registrar.regBlock(blockName, bSup);
                iSup = Registrar.regSimpleBlockItem((DeferredBlock<? extends Block>) bSup);
            }
            default ->
            {
                bSup = () -> new GrassBlock(BlockBehaviour.Properties.of()
                        .lightLevel(state -> li).sound(SoundType.GRASS));
                bSup = Registrar.regBlock(blockName, bSup);
                iSup = Registrar.regSimpleBlockItem((DeferredBlock<? extends Block>) bSup);
            }
        }

        return new GenericBlockItemCoupling(bSup, iSup);
    }
}
