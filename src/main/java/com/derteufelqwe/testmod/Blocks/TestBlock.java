package com.derteufelqwe.testmod.Blocks;

import com.derteufelqwe.testmod.TestMod;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import com.sun.deploy.panel.IProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Random;

public class TestBlock extends Block {

    public TestBlock() {
        super(Material.ROCK);
        setCreativeTab(TestMod.tutorialTab);
        setUnlocalizedName("testblock");
        setRegistryName("testblock");
        setHardness(3f);

        RegistryHandler.BLOCKS.add(this);
        RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.GLASS);
    }

    @Override
    public int quantityDropped(Random random) {
        return 10;
    }



}
