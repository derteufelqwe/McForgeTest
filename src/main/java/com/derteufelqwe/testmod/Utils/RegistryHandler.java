package com.derteufelqwe.testmod.Utils;

import com.derteufelqwe.testmod.Blocks.TestBlock;
import com.derteufelqwe.testmod.tiles.TestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber
public class RegistryHandler {

    public static List<Block> BLOCKS = new ArrayList<>();
    public static List<Item> ITEMS = new ArrayList<>();


    @SubscribeEvent // Registering all Blocks
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for(Block block : BLOCKS) {
            event.getRegistry().register(block);
        }

        System.out.println("Finished registering Blocks.");
    }

    @SubscribeEvent // Registering all Blocks as Items and Items
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Item item : ITEMS) {
            event.getRegistry().register(item);
        }

        System.out.println("Finished registering Items.");
    }


    // Registering all Models
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for(Block block : BLOCKS) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Item.getItemFromBlock(block).getRegistryName(), "inventory"));
        }

        System.out.println("Finished registering Models.");
    }

}
