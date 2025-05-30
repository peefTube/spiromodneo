package com.github.peeftube.spiromodneo.datagen.modules.world.util.helpers;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class TargetRuleData
{
    public static final class TargetRules
    {
        public static final RuleTest BLOCK_STONE = new BlockMatchTest(Blocks.STONE);
        public static final RuleTest BLOCK_DEEPSLATE = new BlockMatchTest(Blocks.DEEPSLATE);
        public static final RuleTest BLOCK_ANDESITE = new BlockMatchTest(Blocks.ANDESITE);
        public static final RuleTest BLOCK_DIORITE = new BlockMatchTest(Blocks.DIORITE);
        public static final RuleTest BLOCK_GRANITE = new BlockMatchTest(Blocks.GRANITE);
        public static final RuleTest BLOCK_CALCITE = new BlockMatchTest(Blocks.CALCITE);
        public static final RuleTest BLOCK_TUFF = new BlockMatchTest(Blocks.TUFF);
        public static final RuleTest BLOCK_DRIPSTONE = new BlockMatchTest(Blocks.DRIPSTONE_BLOCK);
        public static final RuleTest BLOCK_NETHERRACK = new BlockMatchTest(Blocks.NETHERRACK);

        // public static final RuleTest TAG_SANDSTONE = new TagMatchTest();
        // public static final RuleTest TAG_RED_SANDSTONE = new TagMatchTest();
        // public static final RuleTest TAG_BASALT = new TagMatchTest();
        public static final RuleTest TAG_TERRACOTTA = new TagMatchTest(BlockTags.TERRACOTTA);
    }
}
