package com.derteufelqwe.testmod.Blocks.Container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.lwjgl.Sys;

public class OwnBlockContainer extends Container {

    public OwnBlockContainer(InventoryPlayer playerInv, OwnBlockTile tile) {
        if (tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
            IItemHandler inv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

            // GUI
            addSlotToContainer(new SlotItemHandler(inv, 0, 25, 58));
            addSlotToContainer(new SlotItemHandler(inv, 1, 79, 58));
            addSlotToContainer(new SlotItemHandler(inv, 2, 134, 58));
            addSlotToContainer(new SlotItemHandler(inv, 3, 79, 18));

            // Inventory
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 9; x++) {
                    addSlotToContainer(new Slot(playerInv, 9 + (y * 9) + x, 8 + (x * 18), 87 + (y * 18)));
                }
            }

            // Hotbar
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(playerInv, x, 8 + (x * 18), 145));
            }

        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();

            int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(stackInSlot, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stackInSlot, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            slot.onTake(playerIn, stackInSlot);

        }
        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
