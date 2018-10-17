package com.derteufelqwe.testmod.Guis;

import com.derteufelqwe.testmod.Guis.Custums.CustonButton;
import com.derteufelqwe.testmod.TestMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class MyGui extends GuiScreen {

    final ResourceLocation texture = new ResourceLocation(TestMod.MODID, "textures/gui/book.png");
    final int guiWidth = 175;
    final int guiHeight = 228;

    GuiButton button1;
    GuiTextField textField1;
    CustonButton myBtn;
    String title = "Button";

    final int BUTTON1 = 0;
    final int ARROW = 1;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        int centerX = (width / 2) - (guiWidth / 2);
        int centerY = (height / 2) - (guiHeight / 2);
        //drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
        GlStateManager.pushMatrix(); {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.color(1,1,1,1);

            mc.renderEngine.bindTexture(texture);
            drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
        } GlStateManager.popMatrix();
        fontRenderer.drawString("My Gui", width / 2 - fontRenderer.getStringWidth("My Gui") / 2 ,20, 0x123456);

        GlStateManager.pushMatrix(); {
            GlStateManager.translate(0, 0, 0);
            GlStateManager.scale(2,2,2);
            itemRender.renderItemAndEffectIntoGUI(new ItemStack(Blocks.PUMPKIN), centerX, centerY);
        } GlStateManager.popMatrix();

        textField1.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(button1 = new GuiButton(BUTTON1, (width / 2) - 100 / 2, (int) (height * 0.85), 100, 20, title));
        buttonList.add(myBtn = new CustonButton(ARROW, 100, 100, "Hallo"));
        textField1 = new GuiTextField(0, fontRenderer, 0, 0, 100, 20);
        super.initGui();
    }


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case BUTTON1:
                if(button1.displayString.equals("Clicked")) {
                    button1.displayString = "Button";
                } else {
                    button1.displayString = "Clicked";
                }
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        textField1.textboxKeyTyped(typedChar, keyCode);
        if(typedChar == 'e' && textField1.isFocused() == false) {
            Minecraft.getMinecraft().displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        textField1.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
