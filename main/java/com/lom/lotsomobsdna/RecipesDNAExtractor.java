package com.lom.lotsomobsdna;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lom.lotsomobscore.LotsOMobs;
import com.lom.lotsomobsinit.LotsOMobsItems;

public class RecipesDNAExtractor
{
    private static final RecipesDNAExtractor smeltingBase = new RecipesDNAExtractor();
    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private static final String __OBFID = "CL_00000085";

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static RecipesDNAExtractor smelting()
    {
        return smeltingBase;
    }

    private RecipesDNAExtractor()
    {
    addSmelting(new ItemStack(LotsOMobsItems.FossilTri, 1, 0), new ItemStack(LotsOMobsItems.DNATri, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilBro, 1, 0), new ItemStack(LotsOMobsItems.DNABro, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilRap, 1, 0), new ItemStack(LotsOMobsItems.DNARap, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilRex, 1, 0), new ItemStack(LotsOMobsItems.DNARex, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilPte, 1, 0), new ItemStack(LotsOMobsItems.DNAPte, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilMam, 1, 0), new ItemStack(LotsOMobsItems.DNAMam, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilSab, 1, 0), new ItemStack(LotsOMobsItems.DNASab, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilIch, 1, 0), new ItemStack(LotsOMobsItems.DNAIch, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilMsk, 1, 0), new ItemStack(LotsOMobsItems.DNAMsk, 1, 0), 0.7F);
    addSmelting(new ItemStack(LotsOMobsItems.FossilAmt, 1, 0), new ItemStack(LotsOMobsItems.DNAAmt, 1, 0), 0.7F);
    }

    public void func_151393_a(Block p_151393_1_, ItemStack p_151393_2_, float p_151393_3_)
    {
        this.func_151396_a(Item.getItemFromBlock(p_151393_1_), p_151393_2_, p_151393_3_);
    }

    public void func_151396_a(Item p_151396_1_, ItemStack p_151396_2_, float p_151396_3_)
    {
        this.addSmelting(new ItemStack(p_151396_1_, 1, 32767), p_151396_2_, p_151396_3_);
    }

    public void addSmelting(ItemStack p_151394_1_, ItemStack p_151394_2_, float p_151394_3_)
    {
        this.smeltingList.put(p_151394_1_, p_151394_2_);
        this.experienceList.put(p_151394_2_, Float.valueOf(p_151394_3_));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack p_151395_1_)
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151395_1_, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public float func_151398_b(ItemStack p_151398_1_)
    {
        float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151398_1_, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}