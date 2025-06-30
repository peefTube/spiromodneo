package com.github.peeftube.spiromodneo.util.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;

public class SwapLootStackModifier extends LootModifier
{
    private final Item beingSwapped;
    private final Item swapTo;

    public static final MapCodec<SwapLootStackModifier> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
            IGlobalLootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions")
                        .forGetter(glm -> glm.conditions),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("from")
                                  .forGetter(SwapLootStackModifier::getFrom),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("to")
                                  .forGetter(SwapLootStackModifier::getTo))
                                                            .apply(instance, SwapLootStackModifier::new));

    private Item getFrom() { return this.beingSwapped; }
    private Item getTo() { return this.swapTo; }

    /**
     * Constructs a LootModifier.
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public SwapLootStackModifier(LootItemCondition[] conditionsIn, Item toSwap, Item swapIn)
    { super(conditionsIn); this.beingSwapped = toSwap; this.swapTo = swapIn; }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        for (ItemStack stack : List.copyOf(generatedLoot))
        {
            if (stack.getItem() == beingSwapped)
            { generatedLoot.set(generatedLoot.indexOf(stack), new ItemStack(swapTo, stack.getCount())); }
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() { return CODEC; }
}
