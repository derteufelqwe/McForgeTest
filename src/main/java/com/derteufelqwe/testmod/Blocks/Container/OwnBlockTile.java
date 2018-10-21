package com.derteufelqwe.testmod.Blocks.Container;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class OwnBlockTile extends TileEntity implements ITickable {

    ItemStackHandler inv = new ItemStackHandler(4);
    private final String name = "ownBlockInv";

    public int burnTime = 0;
    public int currentBurnTime = 0;
    public int maxCooktime = 100;
    public int currentCooktime = 80;


    public void decrStackSize(int index, int count) {
        this.inv.getStackInSlot(index).setCount(this.inv.getStackInSlot(index).getCount() - count);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        inv.deserializeNBT(compound.getCompoundTag(name));
        this.currentCooktime = compound.getInteger("burnTime");
        this.currentBurnTime = compound.getInteger("currentBurnTime");
        this.currentCooktime = compound.getInteger("maxCookTime");
        this.currentCooktime = compound.getInteger("currentCookTime");
        System.out.println("Reading from NBT");
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag(name, inv.serializeNBT());
        compound.setInteger("burnTime", burnTime);
        compound.setInteger("currentBurnTime", currentBurnTime);
        compound.setInteger("maxCookTime", maxCooktime);
        compound.setInteger("currentCookTime", currentCooktime);
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
        ItemStack res = OwnBlockRecipes.getInstance().getRecipeResult(this.inv.getStackInSlot(0), this.inv.getStackInSlot(2));
        System.out.println("Result: " + res + " = " + res.getItem());
    }
}
