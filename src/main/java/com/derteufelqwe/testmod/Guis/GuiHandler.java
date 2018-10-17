package com.derteufelqwe.testmod.Guis;

import com.derteufelqwe.testmod.container.TestContainer;
import com.derteufelqwe.testmod.tiles.TestTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_TUTORIAL_CONTAINER_ID = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        switch (ID) {
            case GUI_TUTORIAL_CONTAINER_ID:
                return new TestContainer(player.inventory, (TestTileEntity) te);
            default: return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        switch (ID) {
            case GUI_TUTORIAL_CONTAINER_ID:
                return new GuiContainer(player.inventory, (TestTileEntity) te);
            default: return null;
        }
    }
}
