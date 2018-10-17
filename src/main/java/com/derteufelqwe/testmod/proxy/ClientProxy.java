package com.derteufelqwe.testmod.proxy;

import com.derteufelqwe.testmod.Guis.MyGui;
import com.derteufelqwe.testmod.Guis.SimpleGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void proxyPreInit(FMLPreInitializationEvent event) {
        System.out.println("---------- Proxy working on client");
    }

    @Override
    public void openMyGui() {
        Minecraft.getMinecraft().displayGuiScreen(new MyGui());
    }
}
