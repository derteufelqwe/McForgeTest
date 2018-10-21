package com.derteufelqwe.testmod.Blocks.Container;

import com.derteufelqwe.testmod.TestMod;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;

public class OwnBlockGuiContainer extends GuiContainer {

    private static ResourceLocation texture = new ResourceLocation(TestMod.MODID, "textures/gui/ownblock.png");
    private OwnBlockTile tile;

    public OwnBlockGuiContainer(InventoryPlayer playerInv, OwnBlockTile tile) {
        super(new OwnBlockContainer(playerInv, tile));

        xSize = 176;
        ySize = 196;
        this.tile = tile;

    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        int offset;
        if (this.tile.maxCooktime > 0) {
            offset = new Double((14.0 / this.tile.maxCooktime) * this.tile.currentCooktime).intValue();
            offset = 14 - offset;
        } else {
            offset = 14;
        }

        mc.fontRenderer.drawString("Infuser", (xSize / 2) - (fontRenderer.getStringWidth("Infuser") / 2), 4, 0x000000, false);
        GlStateManager.pushMatrix(); {
            mc.renderEngine.bindTexture(texture);
            GlStateManager.color(1,1,1,1);
            drawTexturedModalRect(80, 43 + offset, 176, 0 + offset, 14, 14 - offset);
        } GlStateManager.popMatrix();

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
