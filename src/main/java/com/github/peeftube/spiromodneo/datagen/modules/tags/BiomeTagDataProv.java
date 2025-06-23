package com.github.peeftube.spiromodneo.datagen.modules.tags;

import com.github.peeftube.spiromodneo.SpiroMod;
import com.github.peeftube.spiromodneo.core.init.content.worldgen.biome.NeoBiomes;
import com.github.peeftube.spiromodneo.util.SpiroTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BiomeTagDataProv extends BiomeTagsProvider
{
    public BiomeTagDataProv(PackOutput output,
            CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper)
    { super(output, provider, SpiroMod.MOD_ID, existingFileHelper); }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        tag(SpiroTags.Biomes.RUBY_SPAWNABLE)
                .addTags(BiomeTags.IS_JUNGLE)
                .add(Biomes.LUSH_CAVES);

        tag(SpiroTags.Biomes.LIMBO_GARDEN)
                .add(NeoBiomes.NETHER_LIMBO_GARDEN);
        tag(SpiroTags.Biomes.getColdNetherTag())
                .addTags(SpiroTags.Biomes.LIMBO_GARDEN);
        tag(SpiroTags.Biomes.IS_CUSTOM_NETHER)
                .addTags(SpiroTags.Biomes.getColdNetherTag());
        tag(BiomeTags.IS_NETHER)
                .addTags(SpiroTags.Biomes.IS_CUSTOM_NETHER);
    }
}
