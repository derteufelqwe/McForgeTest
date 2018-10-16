package com.derteufelqwe.testmod;

import com.derteufelqwe.testmod.Blocks.TestBlock;
import com.derteufelqwe.testmod.Utils.BlocksCreator;
import com.derteufelqwe.testmod.Utils.RegistryHandler;
import com.derteufelqwe.testmod.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.Sys;


@Mod(modid = TestMod.MODID, version = TestMod.VERSION)
public class TestMod {

    public static final String MODID = "testmod";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.derteufelqwe.testmod.proxy.ClientProxy", serverSide = "com.derteufelqwe.testmod.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BlocksCreator.createBlocks();

        proxy.proxyPreInit(event);
        System.out.println("---------Mod preinitialization finished.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.addSmelting(Blocks.COAL_BLOCK, new ItemStack(Items.DIAMOND, 1), 100);
        GameRegistry.addSmelting(Items.APPLE, new ItemStack(Items.GOLDEN_APPLE, 1), 1000);

        System.out.println("---------Mod initialization finished.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        System.out.println("---------Mod postinitialization finished.");
    }

}
