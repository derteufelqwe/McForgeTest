package com.derteufelqwe.testmod.tabs;

import com.derteufelqwe.testmod.Utils.BlocksCreator;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TestTab extends CreativeTabs {

    public TestTab() {
        super("tutorialtab");


    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(BlocksCreator.containerBlock);
    }
}
