package com.derteufelqwe.testmod.container;

import com.derteufelqwe.testmod.tiles.TestTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class TestContainer extends Container {

    public TestContainer(InventoryPlayer inventoryPlayer, TestTileEntity testTileEntity) {
        if(testTileEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
            IItemHandler inventory = testTileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

            // Actual Container
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    addSlotToContainer(new SlotItemHandler(inventory, j + (i * 3), 62 + (j * 18), 17 + (i * 18)));
                }
            }

            // Player Inventory
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    addSlotToContainer(new Slot(inventoryPlayer, 9 + j + (i * 9), 8 + j * 18, 84 + i * 18));
                }
            }

            // Hotbar
            for (int i = 0; i < 9; i++) {
                addSlotToContainer(new Slot(inventoryPlayer, i, 8 + (i * 18), 142));
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
