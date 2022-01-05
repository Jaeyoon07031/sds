package com.tokyocodingclub.jaeyoon.sds.gui;

import org.bukkit.inventory.ItemStack;

public class GuiItem 
{

    ItemStack item;
    int clickable;
    String type;
    ItemStack givenItem;
    int amount;
    int price;

    /*
    Types
    return : closes gui
    sell : sells item
    buy : buys item
    */

    public GuiItem(ItemStack item)
    {
        this.item = item;
        this.type = null;
        this.givenItem = null;
        this.amount = 0;
        this.price = 0;

        this.clickable = 0;
    }

    public GuiItem(ItemStack item, String type)
    {
        this.item = item;
        this.type = type;
        this.givenItem = null;
        this.amount = 0;
        this.price = 0;

        this.clickable = 1;
    }

    public GuiItem(ItemStack item, String type, ItemStack givenItem, int amount, int price)
    {
        this.item = item;
        this.type = type;
        this.givenItem = givenItem;
        this.amount = amount;
        this.price = price;

        this.clickable = 1;
    }

    public ItemStack getItem()
    {
        return item;
    }

    public int getClickable()
    {
        return clickable;
    }

    public String getType()
    {
        return type;
    }

    public ItemStack getGivenItem()
    {
        return givenItem;
    }

    public int getAmount()
    {
        return amount;
    }

    public int getPrice()
    {
        return price;
    }

    public void setItem(ItemStack item)
    {
        this.item = item;
    }

    public void setClickable(int clickable)
    {
        this.clickable = clickable;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setGivenItem(ItemStack givenItem)
    {
        this.givenItem = givenItem;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
}
