package com.derteufelqwe.testmod.Blocks;
import com.derteufelqwe.testmod.Guis.GuiHandler;
import com.derteufelqwe.testmod.TestMod;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import com.derteufelqwe.testmod.tiles.TestTileEntity;
import jdk.nashorn.internal.ir.Statement;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import scala.collection.parallel.ParIterableLike;
import scala.tools.nsc.backend.icode.Primitives;

import javax.annotation.Nullable;

public class ContainerBlock extends Block  implements ITileEntityProvider {

    public ContainerBlock() {
        super(Material.ROCK);
        setCreativeTab(CreativeTabs.FOOD);
        setRegistryName("containerblock");
        setUnlocalizedName("containerblock");
        setHardness(1f);

        RegistryHandler.BLOCKS.add(this);
        RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TestTileEntity te = (TestTileEntity) worldIn.getTileEntity(pos);
        if (te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
            playerIn.openGui(TestMod.instance, GuiHandler.GUI_TUTORIAL_CONTAINER_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TestTileEntity te = (TestTileEntity) worldIn.getTileEntity(pos);
        if(te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
            IItemHandler inventory = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
            if (inventory != null) {
                for (int i = 0; i < inventory.getSlots(); i++) {
                    if(inventory.getStackInSlot(i) != ItemStack.EMPTY) {
                        EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));
                        worldIn.spawnEntity(item);
                    }
                }
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TestTileEntity();
    }
}
