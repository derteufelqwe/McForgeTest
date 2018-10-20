package com.derteufelqwe.testmod.Utils;

import com.derteufelqwe.testmod.Blocks.Container.OwnBlock;
import com.derteufelqwe.testmod.Blocks.ContainerBlock;
import com.derteufelqwe.testmod.Blocks.SimpleGuiBlock;
import com.derteufelqwe.testmod.Blocks.TestBlock;
import com.derteufelqwe.testmod.Items.TestItem;

public class BlocksCreator {

    public static ContainerBlock containerBlock;
    public static OwnBlock ownBlock;

    public static void createBlocks() {

        TestBlock testBlock = new TestBlock();
        SimpleGuiBlock simpleGuiBlock = new SimpleGuiBlock();
        containerBlock = new ContainerBlock();
        ownBlock = new OwnBlock();

        TestItem testItem = new TestItem();

    }

}
