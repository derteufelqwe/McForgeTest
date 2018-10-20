package com.derteufelqwe.testmod.Items;

import com.derteufelqwe.testmod.TestMod;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TestItem extends Item {

    public TestItem() {
        setCreativeTab(TestMod.tutorialTab);
        setUnlocalizedName("testitem");
        setRegistryName("testitem");

        RegistryHandler.ITEMS.add(this);
    }
}
