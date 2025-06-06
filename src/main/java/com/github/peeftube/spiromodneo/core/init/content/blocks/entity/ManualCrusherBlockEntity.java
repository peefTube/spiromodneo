package com.github.peeftube.spiromodneo.core.init.content.blocks.entity;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.content.recipe.ManualCrusherRecipe;
import com.github.peeftube.spiromodneo.core.init.content.recipe.ManualCrusherRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.ibm.icu.text.PluralRules.Operand.i;

public class ManualCrusherBlockEntity extends BlockEntity implements MenuProvider
{
    public final ItemStackHandler inv = new ItemStackHandler(2)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
            if (!level.isClientSide())
            { level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3); }
        }
    };

    public ManualCrusherBlockEntity(BlockPos pos, BlockState blockState)
    { super(Registrar.MANUAL_CRUSHER_ENTITY.get(), pos, blockState); }

    @Override
    public Component getDisplayName()
    { return Component.translatable("block.spiromodneo.manual_crusher"); }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    { return null; }

    /** Copied from: <a href="https://tinyurl.com/2kuvcsxn">KaupenJoe Tutorials</a> */
    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider reg)
    {
        pTag.put("inventory", inv.serializeNBT(reg));
        super.saveAdditional(pTag, reg);
    }

    /** Copied from: <a href="https://tinyurl.com/2kuvcsxn">KaupenJoe Tutorials</a> */
    @Override
    protected void loadAdditional(CompoundTag cTag, HolderLookup.Provider reg)
    {
        super.loadAdditional(cTag, reg);
        inv.deserializeNBT(reg, cTag.getCompound("inventory"));
    }

    public void tick(Level level, BlockPos bPos, BlockState state)
    {
        if (hasRecipe())
        {
            setChanged(level, bPos, state);
            craftItem();
        }
    }

    private void craftItem()
    {
        Optional<RecipeHolder<ManualCrusherRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        inv.setStackInSlot(Slots.OUTPUT.getSlot(), new ItemStack(output.getItem(),
                inv.getStackInSlot(Slots.OUTPUT.getSlot()).getCount() + output.getCount()));
    }

    private boolean hasRecipe()
    {
        Optional<RecipeHolder<ManualCrusherRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) { return false; }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private boolean canInsertAmountIntoOutputSlot(int count)
    {
        int max = inv.getStackInSlot(Slots.OUTPUT.getSlot()).isEmpty() ? 64 :
                inv.getStackInSlot(Slots.OUTPUT.getSlot()).getMaxStackSize();
        int curr = inv.getStackInSlot(Slots.OUTPUT.getSlot()).getCount();

        return max >= curr + count;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output)
    {
        return inv.getStackInSlot(Slots.OUTPUT.getSlot()).isEmpty() ||
                inv.getStackInSlot(Slots.OUTPUT.getSlot()).getItem() == output.getItem();
    }

    private Optional<RecipeHolder<ManualCrusherRecipe>> getCurrentRecipe()
    {
        return this.level.getRecipeManager().getRecipeFor(Registrar.MANUAL_CRUSHER_TYPE.get(),
                new ManualCrusherRecipeInput(inv.getStackInSlot(Slots.INPUT.getSlot())), level);
    }

    public enum Slots
    {
        INPUT(0),
        OUTPUT(1);

        private final int slot;

        Slots(int slot) { this.slot = slot; }

        public int getSlot() { return slot; }
    }
}
