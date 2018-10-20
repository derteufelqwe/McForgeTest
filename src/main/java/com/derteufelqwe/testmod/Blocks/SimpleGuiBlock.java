package com.derteufelqwe.testmod.Blocks;

import com.derteufelqwe.testmod.TestMod;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SimpleGuiBlock extends Block {

    public SimpleGuiBlock() {
        super(Material.ROCK);
        setCreativeTab(TestMod.tutorialTab);
        setUnlocalizedName("simpleguiblock");
        setRegistryName("simpleguiblock");
        setHardness(1f);

        RegistryHandler.BLOCKS.add(this);
        RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            TestMod.proxy.openMyGui();
        }

        return true;
    }
}
