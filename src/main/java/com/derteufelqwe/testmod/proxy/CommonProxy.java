package com.derteufelqwe.testmod.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void proxyPreInit(FMLPreInitializationEvent event) {
        System.out.println("---------- Proxy working on Server");
    }

    public void openMyGui() {

    }

}
