package com.derteufelqwe.testmod.Blocks.Container;

import com.derteufelqwe.testmod.TestMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class OwnBlockGuiContainer extends GuiContainer {

    private static ResourceLocation texture = new ResourceLocation(TestMod.MODID, "textures/gui/ownblock.png");

    public OwnBlockGuiContainer(InventoryPlayer playerInv, OwnBlockTile tile) {
        super(new OwnBlockContainer(playerInv, tile));

        xSize = 176;
        ySize = 196;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        mc.fontRenderer.drawString("Infuser", (xSize / 2) - (fontRenderer.getStringWidth("Infuser") / 2), 4, 0x000000, false);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
