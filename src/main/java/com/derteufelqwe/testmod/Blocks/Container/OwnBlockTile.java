package com.derteufelqwe.testmod.Blocks.Container;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Items;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class OwnBlockTile extends TileEntity implements ITickable {

    ItemStackHandler inv = new ItemStackHandler(4);
    private final String name = "ownBlockInv";


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        inv.deserializeNBT(compound.getCompoundTag(name));
        System.out.println("Reading from NBT");
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag(name, inv.serializeNBT());
        System.out.println("Writing to NBT");
        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inv : super.getCapability(capability, facing);
    }

    // Stuff for the Working Tile Entity


    @Override
    public void update() {

    }
}
