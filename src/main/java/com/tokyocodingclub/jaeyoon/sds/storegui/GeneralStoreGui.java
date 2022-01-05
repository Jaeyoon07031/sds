package com.tokyocodingclub.jaeyoon.sds.storegui;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.PlayerInventory;

import com.tokyocodingclub.jaeyoon.sds.Simulator;
import com.tokyocodingclub.jaeyoon.sds.gui.ChestGui;
import com.tokyocodingclub.jaeyoon.sds.gui.GuiItem;

public class GeneralStoreGui extends StoreGui
{
    public GeneralStoreGui(Player player, Simulator simulator)
    {
        this.simulator = simulator;
        this.gui = new ChestGui("General Store", 27, player, this, simulator);
    }

    public void setupGui()
    {
        Player player = gui.getPlayer();

        ItemStack grayPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack buyHandSanitizer = new ItemStack(Material.SPLASH_POTION);
        ItemStack buySoap = new ItemStack(Material.POTION);
        ItemStack buyMask = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack buyMilk = new ItemStack(Material.MILK_BUCKET);
        ItemStack goBack = new ItemStack(Material.ARROW);
        ItemMeta emptyMeta = grayPane.getItemMeta();
        ItemMeta handSanitizerMeta = buyHandSanitizer.getItemMeta();
        ItemMeta soapMeta = buySoap.getItemMeta();
        ItemMeta maskMeta = buyMask.getItemMeta();
        ItemMeta milkMeta = buyMilk.getItemMeta();
        ItemMeta arrowMeta = goBack.getItemMeta();
        emptyMeta.setDisplayName(" ");
        arrowMeta.setDisplayName(ChatColor.RESET.toString() + "Go Back");
        handSanitizerMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Hand Sanitizer");
        soapMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Soap");
        maskMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Mask");
        milkMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Cold Medicine");

        grayPane.setItemMeta(emptyMeta);
        goBack.setItemMeta(arrowMeta);

        PlayerInventory playerInventory = player.getInventory();
        if (playerInventory.firstEmpty() != -1)
        {
            List<String> handSanitizerLore = new ArrayList<String>();
            List<String> soapLore = new ArrayList<String>();
            List<String> maskLore = new ArrayList<String>();
            List<String> milkLore = new ArrayList<String>();
            handSanitizerLore.add(ChatColor.RESET.toString() + "Buying §e1 §fHand Sanitizer for");
            handSanitizerLore.add("§e100 §fcoins (total of §e100 §fcoins)");
            soapLore.add(ChatColor.RESET.toString() + "Buying §e1 §fSoap for");
            soapLore.add("§e80 §fcoins (total of §e80 §fcoins)");
            maskLore.add(ChatColor.RESET.toString() + "Buying §e1 §fMask for");
            maskLore.add("§e400 §fcoins (total of §e400 §fcoins)");
            milkLore.add(ChatColor.RESET.toString() + "Buying §e1 §fMilk for");
            milkLore.add("§e50 §fcoins (total of §e50 §fcoins)");
            handSanitizerMeta.setLore(handSanitizerLore);
            soapMeta.setLore(soapLore);
            maskMeta.setLore(maskLore);
            milkMeta.setLore(milkLore);
            buyHandSanitizer.setItemMeta(handSanitizerMeta);
            buySoap.setItemMeta(soapMeta);
            buyMask.setItemMeta(maskMeta);
            buyMilk.setItemMeta(milkMeta);
        }
        else
        {
            List<String> lore = new ArrayList<String>();
            lore.add("§4 You do not have any space in");
            lore.add("§4 your inventory!");
            handSanitizerMeta.setLore(lore);
            soapMeta.setLore(lore);
            maskMeta.setLore(lore);
            milkMeta.setLore(lore);
            buyHandSanitizer.setItemMeta(handSanitizerMeta);
            buySoap.setItemMeta(soapMeta);
            buyMask.setItemMeta(maskMeta);
            buyMilk.setItemMeta(milkMeta);
        }
        ItemStack handSanitizerReturn = new ItemStack(Material.SPLASH_POTION);
        ItemStack soapReturn = new ItemStack(Material.POTION);
        ItemStack maskReturn = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack milkReturn = new ItemStack(Material.MILK_BUCKET);

        ItemMeta handSanitizerReturnItemMeta = handSanitizerReturn.getItemMeta();
        ItemMeta soapReturnItemMeta = soapReturn.getItemMeta();
        ItemMeta maskReturnItemMeta = maskReturn.getItemMeta();
        ItemMeta milkReturnItemMeta = milkReturn.getItemMeta();

        handSanitizerReturnItemMeta.setCustomModelData(4);
        soapReturnItemMeta.setCustomModelData(3);
        maskReturnItemMeta.setCustomModelData(1);
        milkReturnItemMeta.setCustomModelData(7);

        handSanitizerReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Hand Sanitizer");
        soapReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Soap");
        maskReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Mask");
        milkReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Cold Medicine");

        handSanitizerReturn.setItemMeta(handSanitizerReturnItemMeta);
        soapReturn.setItemMeta(soapReturnItemMeta);
        maskReturn.setItemMeta(maskReturnItemMeta);
        milkReturn.setItemMeta(milkReturnItemMeta);

        Color color = Color.WHITE;
        color.setRed(250);
        color.setGreen(220);
        color.setBlue(250);

        PotionMeta soapPotionMeta = (PotionMeta) soapReturn.getItemMeta();
        soapPotionMeta.setColor(color);
        soapReturn.setItemMeta(soapPotionMeta);
        
        PotionMeta handSanitizerPotionMeta = (PotionMeta) handSanitizerReturn.getItemMeta();
        handSanitizerPotionMeta.setColor(Color.WHITE);
        handSanitizerReturn.setItemMeta(handSanitizerPotionMeta);

        GuiItem guiGrayPane = new GuiItem(grayPane);
        GuiItem guiGoBack = new GuiItem(goBack, "return");
        GuiItem guiBuyHandSanitizer = new GuiItem(buyHandSanitizer, "buy", handSanitizerReturn, 1, 100);
        GuiItem guiBuySoap = new GuiItem(buySoap, "buy", soapReturn, 1, 80);
        GuiItem guiBuyMask = new GuiItem(buyMask, "buy", maskReturn, 1, 400);
        GuiItem guiBuyMilk = new GuiItem(buyMilk, "buy", milkReturn, 1, 50);

        gui.fillWith(guiGrayPane);
        gui.addItem(guiGoBack, 4, 2);
        gui.addItem(guiBuyHandSanitizer, 3, 1);
        gui.addItem(guiBuySoap, 1, 1);
        gui.addItem(guiBuyMask, 5, 1);
        gui.addItem(guiBuyMilk, 7, 1);
    }
}
