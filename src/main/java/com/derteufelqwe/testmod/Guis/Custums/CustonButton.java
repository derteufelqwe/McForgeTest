package com.derteufelqwe.testmod.Guis.Custums;

import com.derteufelqwe.testmod.TestMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class CustonButton extends GuiButton {

    final ResourceLocation texture = new ResourceLocation(TestMod.MODID, "textures/gui/book.png");
    int btnWidth = 16;
    int btnHeight = 14;
    int u = 175;
    int v = 1;

    public CustonButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, 16, 14, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            mc.renderEngine.bindTexture(texture);
            if(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                hovered = true;
            } else {
                hovered = false;
            }

            if(hovered) {
                v = 18;
            } else {
                v = 1;
            }
            drawTexturedModalRect(x, y, u, v, width, height);
        }
    }

}
