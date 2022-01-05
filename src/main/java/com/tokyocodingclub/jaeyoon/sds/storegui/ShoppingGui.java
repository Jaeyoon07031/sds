package com.tokyocodingclub.jaeyoon.sds.storegui;

import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

import com.tokyocodingclub.jaeyoon.sds.Simulator;
import com.tokyocodingclub.jaeyoon.sds.gui.ChestGui;
import com.tokyocodingclub.jaeyoon.sds.gui.GuiItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingGui extends StoreGui
{
    Material material1;
    Material material2;
    String item1;
    String item2;
    String itemFull1;
    String itemFull2;
    int price1;
    int price2;

    public ShoppingGui(Player player, Material material1, Material material2, String item1, String item2, int price1, int price2, String itemFull1, String itemFull2, Simulator simulator)
    {
        this.simulator = simulator;
        gui = new ChestGui(item1 + " and " + item2 + " trader ", 36, player, this, simulator);
        this.material1 = material1;
        this.material2 = material2;
        this.item1 = item1;
        this.item2 = item2;
        this.price1 = price1;
        this.price2 = price2;
        this.itemFull1 = itemFull1;
        this.itemFull2 = itemFull2;
    }

    public ChestGui getChestGui()
    {
        return gui;
    }

    public void showGui()
    {
        gui.show();
    }

    public void close()
    {
        gui.close();
    }

    public void setupGui()
    {
        Player player = gui.getPlayer();

        int itemCount1 = 0;
        int itemCount2 = 0;

        ItemStack grayPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack sellItem1 = new ItemStack(material1);
        ItemStack sellItem2 = new ItemStack(material2);
        ItemStack goBack = new ItemStack(Material.ARROW);
        ItemMeta emptyMeta = grayPane.getItemMeta();
        ItemMeta itemMeta1 = sellItem1.getItemMeta();
        ItemMeta itemMeta2 = sellItem2.getItemMeta();
        ItemMeta arrowMeta = goBack.getItemMeta();
        emptyMeta.setDisplayName(" ");
        arrowMeta.setDisplayName(ChatColor.RESET.toString() + "Go Back");
        itemMeta1.setDisplayName(ChatColor.RESET.toString() + "Sell: " + itemFull1);
        itemMeta2.setDisplayName(ChatColor.RESET.toString() + "Sell: " + itemFull2);
        
        if (player.getInventory().contains(material1) || player.getInventory().contains(material2))
        {

            ItemStack[] playerInventory = player.getInventory().getContents();

            for (ItemStack item:playerInventory)
            {
                if(item != null && item.getType().equals(material1))
                {
                    itemCount1 = itemCount1 + item.getAmount();
                }

                else if(item != null && item.getType().equals(material2))
                {
                    itemCount2 = itemCount2 + item.getAmount();
                }
            }

            if (!player.getInventory().contains(material1))
            {
                List<String> lore = new ArrayList<String>();
                lore.add("§4You do not have any of this");
                lore.add("§4item to sell!");
                itemMeta1.setLore(lore);
            }
            else
            {
                List<String> lore = new ArrayList<String>();
                lore.add("§fSelling §e" + itemCount1 + " §f" + itemFull1 + "(s) at");
                lore.add("§e" + price1 +"§f coins each for §e" + (itemCount1 * price1) + "§f coins.");
                lore.add("§f===========================");
                lore.add("§4Click to sell!");
                itemMeta1.setLore(lore);
            }

            if (!player.getInventory().contains(material2))
            {
                List<String> lore = new ArrayList<String>();
                lore.add("§4You do not have any of this");
                lore.add("§4item to sell!");
                itemMeta2.setLore(lore);
            }
            else
            {
                List<String> lore = new ArrayList<String>();
                lore.add("§fSelling §e" + itemCount2 + " §f" + itemFull2 + "(s) at");
                lore.add("§e" + price2 + "§f coins each for §e" + (itemCount2 * price2) + "§f coins.");
                lore.add("§f===========================");
                lore.add("§4Click to sell!");
                itemMeta2.setLore(lore);
            }
        }
        else
        {
            List<String> lore = new ArrayList<String>();
            lore.add("§4You do not have any of this");
            lore.add("§4item to sell!");
            itemMeta1.setLore(lore);
            itemMeta2.setLore(lore);
        }

        sellItem1.setItemMeta(itemMeta1);
        sellItem2.setItemMeta(itemMeta2);
        grayPane.setItemMeta(emptyMeta);
        goBack.setItemMeta(arrowMeta);

        ItemStack item1ItemStack = new ItemStack(material1);
        ItemStack item2ItemStack = new ItemStack(material2);
 
        GuiItem guiGrayPane = new GuiItem(grayPane);
        GuiItem guiGoBack = new GuiItem(goBack, "return");
        GuiItem guiSell1 = new GuiItem(sellItem1, "sell", item1ItemStack, itemCount1, price1);
        GuiItem guiSell2 = new GuiItem(sellItem2, "sell", item2ItemStack, itemCount2, price2);
        
        gui.fillWith(guiGrayPane);
        gui.addItem(guiGoBack, 4, 3);
        gui.addItem(guiSell1, 2, 1);
        gui.addItem(guiSell2, 6, 1);
    }
}
