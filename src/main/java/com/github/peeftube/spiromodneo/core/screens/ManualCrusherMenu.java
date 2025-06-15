package com.github.peeftube.spiromodneo.core.screens;

import com.github.peeftube.spiromodneo.core.init.Registrar;
import com.github.peeftube.spiromodneo.core.init.content.blocks.entity.ManualCrusherBlockEntity;
import com.github.peeftube.spiromodneo.core.init.content.recipe.ManualCrusherRecipe;
import com.github.peeftube.spiromodneo.core.init.content.recipe.ManualCrusherRecipeInput;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ManualCrusherMenu extends AbstractContainerMenu
{
    public final ManualCrusherBlockEntity blockEntity;
    private final Level level;

    public ManualCrusherMenu(int id, Inventory inv, FriendlyByteBuf byteBuf)
    { this(id, inv, inv.player.level().getBlockEntity(byteBuf.readBlockPos())); }

    public ManualCrusherMenu(int id, Inventory inv, BlockEntity blockEntity)
    {
        super(Registrar.MANUAL_CRUSHER_MENU.get(), id);
        this.blockEntity = (ManualCrusherBlockEntity) blockEntity;
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        int slotInID = ManualCrusherBlockEntity.Slots.INPUT.getSlot();
        int slotOutID = ManualCrusherBlockEntity.Slots.OUTPUT.getSlot();

        this.addSlot(new SlotItemHandler(this.blockEntity.inv, slotInID, 49, 35));
        this.addSlot(new SlotItemHandler(this.blockEntity.inv, slotOutID, 107, 35));
    }

    private void addPlayerInventory(Inventory inv)
    {
        for (int r = 0; r < 3; ++r)
        { for (int c = 0; c < 9; ++c)
        { this.addSlot(new Slot(inv, c + (r * 9) + 9, 8 + (c * 18), 84 + (r * 18))); }
        }
    }

    private void addPlayerHotbar(Inventory inv)
    {
        for (int c = 0; c < 9; ++c)
        { this.addSlot(new Slot(inv, c, 8 + (c * 18), 142)); }
    }

    // COPIED FROM: https://tinyurl.com/tutbykaup01
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 1;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player player, int i)
    {
        Slot sourceSlot = slots.get(i);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (i < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT)
        {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false))
            { return ItemStack.EMPTY;  /* EMPTY_ITEM */ }
        }
        else if (i < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT)
        {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
            { return ItemStack.EMPTY; }
        }
        else
        {
            System.out.println("Invalid slotIndex:" + i);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0)
        { sourceSlot.set(ItemStack.EMPTY); }
        else { sourceSlot.setChanged(); }
        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, Registrar.MANUAL_CRUSHER.get());
    }
}
