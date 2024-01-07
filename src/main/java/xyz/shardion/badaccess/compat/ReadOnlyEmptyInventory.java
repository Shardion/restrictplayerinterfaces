package xyz.shardion.badaccess.compat;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ReadOnlyEmptyInventory extends InventoryBasic {
    public ReadOnlyEmptyInventory(int slotCount) {
        super("Distorted Space" /* obscure reference time */, false, slotCount);
    }

    @Override
    public @NotNull ItemStack removeStackFromSlot(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack decrStackSize(int index, int count) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack addItem(@NotNull ItemStack stack) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int index, @NotNull ItemStack stack) { }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int index, @NotNull ItemStack stack) {
        return false;
    }
}
