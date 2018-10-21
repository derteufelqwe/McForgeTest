package com.derteufelqwe.testmod.Blocks.Container;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class OwnBlockRecipes {

    private static final OwnBlockRecipes INSTANCE = new OwnBlockRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();


    public static OwnBlockRecipes getInstance() {
        return INSTANCE;
    }

    private OwnBlockRecipes() {
        addRecipe(new ItemStack(Blocks.GOLD_ORE), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.DIAMOND));
    }

    public void addRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
        if (getRecipeResult(input1, input2) != ItemStack.EMPTY) return;
        this.smeltingList.put(input1, input2, output);
    }

    public ItemStack getRecipeResult(ItemStack input1, ItemStack input2) {
        for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
                for(Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                    if(this.compareItemStacks(input2, ent.getKey())) {
                        return ent.getValue();
                    }
                }
            }
        }

        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && (stack1.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() {
        return this.smeltingList;
    }

}
