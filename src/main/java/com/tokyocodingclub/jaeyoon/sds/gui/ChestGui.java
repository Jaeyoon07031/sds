package com.tokyocodingclub.jaeyoon.sds.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import com.tokyocodingclub.jaeyoon.sds.storegui.StoreGui;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

import java.util.ArrayList;

public class ChestGui
{
    Inventory chestInventory;
    Player player;
    List<GuiItem> itemList = new ArrayList<GuiItem>();
    StoreGui gui;
    Simulator simulator;

    public ChestGui(String name, int size, Player player, StoreGui gui, Simulator simulator)
    {
        chestInventory = Bukkit.createInventory(null, size, name);
        this.player = player;
        this.simulator = simulator;
        this.gui = gui;
    }

    public int getRows()
    {
        return chestInventory.getSize() / 9;
    }

    public void addItem(GuiItem item, int x, int y)
    {
        chestInventory.setItem(x + (9 * y), item.getItem());
        itemList.set(x + (9 * y), item);
    }

    public void fillWith(GuiItem item)
    {
        itemList = new ArrayList<GuiItem>();
        for (int index = 0; index < chestInventory.getSize(); index++)
        {
            chestInventory.setItem(index, item.getItem());
            itemList.add(item);
        }
    }

    public void show()
    {
        player.openInventory(chestInventory);
    }

    public void close()
    {
        player.closeInventory();
        //TODO: Use Bukkit TaskScheduler instead to close
    }

    public boolean isInventory(Inventory inventory)
    {
        return chestInventory == inventory;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void onInventoryInteract(InventoryClickEvent event)
    {

        Player eventPlayer = (Player)event.getInventory().getViewers().get(0);

        if (simulator.getDebugLevel() > 1)
        {
            eventPlayer.sendMessage("Inventory clicked");
        }

        if (!event.getClickedInventory().equals(chestInventory))
        {
            return;
        }
  

        GuiItem item = itemList.get(event.getSlot());

        if (simulator.getDebugLevel() > 1)
        {
            eventPlayer.sendMessage("Inventory is Chest Inventory");
        }

        if (item.getClickable() == 1)
        {
            if (simulator.getDebugLevel() > 1)
            {
                eventPlayer.sendMessage("Item is clickable");
                eventPlayer.sendMessage("Item type: " + item.getType());
                eventPlayer.sendMessage("Item amount: " + item.getAmount());
                eventPlayer.sendMessage("Item price: " + item.getPrice());
                eventPlayer.sendMessage("Given item: " + item.getGivenItem());
            }

            String action = item.getType();
            if (action == "return")
            {
                this.close();
                return;
            }
            int amount = item.getAmount();
            int price = item.getPrice();
            if (action == "sell")
            {
                if (simulator.getDebugLevel() > 1)
                {
                    eventPlayer.sendMessage("Player Balance: " + simulator.getSdsPlayer(eventPlayer.getUniqueId()).getBalance());
                    eventPlayer.sendMessage("Item Sell Price: " + (amount * price)); 
                }

                ItemStack[] playerInventory = eventPlayer.getInventory().getContents();

                for (int i = 0; i < playerInventory.length; i++)
                {
                    if(playerInventory[i] != null && item != null && playerInventory[i].getType().equals(item.getGivenItem().getType()))
                    {
                        eventPlayer.getInventory().setItem(i, null);
                    }
                }
                com.tokyocodingclub.jaeyoon.sds.Player player = simulator.getSdsPlayer(event.getViewers().get(0).getUniqueId());
                int coins = player.getBalance();
                player.setBalance(coins + (amount * price));
                ((Player)event.getViewers().get(0)).playSound(event.getViewers().get(0).getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 0.75f);
                this.gui.setupGui();
                if (simulator.getDebugLevel() > 1)
                {
                    eventPlayer.sendMessage("Player Balance (After): " + simulator.getSdsPlayer(eventPlayer.getUniqueId()).getBalance());
                }
            }
            else if(action == "buy")
            {
                if (simulator.getDebugLevel() > 1)
                {
                    eventPlayer.sendMessage("Player Balance: " + simulator.getSdsPlayer(eventPlayer.getUniqueId()).getBalance());
                    eventPlayer.sendMessage("Item Price: " + (amount * price)); 
                }
                com.tokyocodingclub.jaeyoon.sds.Player player = simulator.getSdsPlayer(event.getViewers().get(0).getUniqueId());
                int coins = player.getBalance();
                if (coins >= (amount * price))
                {
                    event.getViewers().get(0).getInventory().addItem(item.getGivenItem());
                    player.setBalance(coins - (amount * price));
                    ((Player)event.getViewers().get(0)).playSound(event.getViewers().get(0).getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 0.75f);
                    this.gui.setupGui();
                }
                else
                {
                    eventPlayer.sendMessage("You do not have enough coins!");
                }
                if (simulator.getDebugLevel() > 1)
                {
                    eventPlayer.sendMessage("Player Balance (After): " + simulator.getSdsPlayer(eventPlayer.getUniqueId()).getBalance());
                }
                
            }

        }
        event.setCancelled(true);
    }
}
