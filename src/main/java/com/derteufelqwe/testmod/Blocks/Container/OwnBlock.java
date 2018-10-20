package com.derteufelqwe.testmod.Blocks.Container;

import com.derteufelqwe.testmod.Guis.GuiHandler;
import com.derteufelqwe.testmod.TestMod;
import com.derteufelqwe.testmod.Utils.BlocksCreator;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import com.derteufelqwe.testmod.tiles.TestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockStone;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class OwnBlock extends Block implements ITileEntityProvider {

    public static PropertyDirection FACING = BlockHorizontal.FACING;
    public static PropertyBool BURNING = PropertyBool.create("burning");

    public OwnBlock() {
        super(Material.ROCK);
        setCreativeTab(TestMod.tutorialTab);
        setUnlocalizedName("ownblock");
        setRegistryName("ownblock");

        setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));

        RegistryHandler.BLOCKS.add(this);
        RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            OwnBlockTile tile = (OwnBlockTile) worldIn.getTileEntity(pos);
            if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
                playerIn.openGui(TestMod.instance, GuiHandler.GUI_OWNBLOCK_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing) state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {
                face = EnumFacing.SOUTH;
            } else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {
                face = EnumFacing.NORTH;
            } else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {
                face = EnumFacing.EAST;
            } else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {
                face = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tile = worldIn.getTileEntity(pos);

        if (active) {
            worldIn.setBlockState(pos, BlocksCreator.ownBlock.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
        } else {
            worldIn.setBlockState(pos, BlocksCreator.ownBlock.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);
        }

        if (tile != null) {
            tile.validate();
            worldIn.setTileEntity(pos, tile);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new OwnBlockTile();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {BURNING, FACING});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if (facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }
}
