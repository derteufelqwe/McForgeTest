package com.derteufelqwe.testmod.Guis;


import com.derteufelqwe.testmod.TestMod;
import com.derteufelqwe.testmod.container.TestContainer;
import com.derteufelqwe.testmod.tiles.TestTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import java.awt.geom.RectangularShape;

public class TestGuiContainer extends GuiContainer {

    private static ResourceLocation texture = new ResourceLocation(TestMod.MODID, "textures/gui/container.png");

    public TestGuiContainer(InventoryPlayer player, TestTileEntity testTileEntity) {
        super(new TestContainer(player, testTileEntity));
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        fontRenderer.drawString("Container Gui", 60, 5, 0x120000, false);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
