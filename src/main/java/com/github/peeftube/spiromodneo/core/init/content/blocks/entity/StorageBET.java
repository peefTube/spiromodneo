package com.github.peeftube.spiromodneo.core.init.content.blocks.entity;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.registry.data.WoodCollection;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

/** Storage Block Entity Type */
public class StorageBET
{
    public static final Supplier<BlockEntityType<BarrelBlockEntity>> BARREL =
            Registrar.BLOCK_ENTITIES.register("barrel",
                    () -> BlockEntityType.Builder.of(BarrelBlockEntity::new,
                    WoodCollection.WOOD_COLLECTIONS.stream().map(WoodCollection::getBarrelAsBlock)
                                                   .toArray(Block[]::new))
                             .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "barrel")));

    public static final Supplier<BlockEntityType<ExtensibleChestBlockEntity>> CHEST =
            Registrar.BLOCK_ENTITIES.register("chest",
                    () -> BlockEntityType.Builder.of(ExtensibleChestBlockEntity::new,
                    WoodCollection.WOOD_COLLECTIONS.stream().map(WoodCollection::getChestAsBlock)
                                                   .toArray(Block[]::new))
                             .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "chest")));

    public static final Supplier<BlockEntityType<ExtensibleTrappedChestBlockEntity>> TRAPPED_CHEST =
            Registrar.BLOCK_ENTITIES.register("trapped_chest",
                    () -> BlockEntityType.Builder.of(ExtensibleTrappedChestBlockEntity::new,
                    WoodCollection.WOOD_COLLECTIONS.stream().map(WoodCollection::getTrappedChestAsBlock)
                                                   .toArray(Block[]::new))
                             .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "trapped_chest")));
}
