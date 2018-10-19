package com.derteufelqwe.testmod.Blocks.Container;

import com.derteufelqwe.testmod.Guis.GuiHandler;
import com.derteufelqwe.testmod.TestMod;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import com.derteufelqwe.testmod.tiles.TestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class OwnBlock extends Block implements ITileEntityProvider {

    public OwnBlock() {
        super(Material.ROCK);
        setCreativeTab(CreativeTabs.FOOD);
        setUnlocalizedName("ownblock");
        setRegistryName("ownblock");

        RegistryHandler.BLOCKS.add(this);
        RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        OwnBlockTile tile = (OwnBlockTile) worldIn.getTileEntity(pos);
        if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
            playerIn.openGui(TestMod.instance, GuiHandler.GUI_OWNBLOCK_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new OwnBlockTile();
    }
}
