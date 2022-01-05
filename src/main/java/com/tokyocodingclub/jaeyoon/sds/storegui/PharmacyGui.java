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

public class PharmacyGui extends StoreGui
{
    public PharmacyGui(Player player, Simulator simulator)
    {
        this.simulator = simulator;
        this.gui = new ChestGui("Pharmacy", 27, player, this, simulator);
    }

    public void setupGui()
    {
        Player player = gui.getPlayer();

        ItemStack grayPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack buyHandSanitizer = new ItemStack(Material.SPLASH_POTION);
        ItemStack buyGoodHandSanitizer = new ItemStack(Material.LINGERING_POTION);
        ItemStack buyMask = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack buyCovidDrugs = new ItemStack(Material.TIPPED_ARROW);
        ItemStack goBack = new ItemStack(Material.ARROW);
        ItemMeta emptyMeta = grayPane.getItemMeta();
        ItemMeta handSanitizerMeta = buyHandSanitizer.getItemMeta();
        ItemMeta goodHandSanitizerMeta = buyGoodHandSanitizer.getItemMeta();
        ItemMeta maskMeta = buyMask.getItemMeta();
        ItemMeta covidDrugsMeta = buyCovidDrugs.getItemMeta();
        ItemMeta arrowMeta = goBack.getItemMeta();
        emptyMeta.setDisplayName(" ");
        arrowMeta.setDisplayName(ChatColor.RESET.toString() + "Go Back");
        handSanitizerMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Hand Sanitizer");
        goodHandSanitizerMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Good Hand Sanitizer");
        maskMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Mask");
        covidDrugsMeta.setDisplayName(ChatColor.RESET.toString() + "Buy : Covid Drugs");
        grayPane.setItemMeta(emptyMeta);
        goBack.setItemMeta(arrowMeta);

        PlayerInventory playerInventory = player.getInventory();
        if (playerInventory.firstEmpty() != -1)
        {
            List<String> handSanitizerLore = new ArrayList<String>();
            List<String> goodHandSanitizerLore = new ArrayList<String>();
            List<String> maskLore = new ArrayList<String>();
            List<String> covidDrugsLore = new ArrayList<String>();
            handSanitizerLore.add(ChatColor.RESET.toString() + "Buying§e 1 §fHand Sanitizer for");
            handSanitizerLore.add("§e100 §fcoins (total of §e100 §fcoins)");
            goodHandSanitizerLore.add(ChatColor.RESET.toString() + "Buying§e 1 §fGood Hand Sanitizer for");
            goodHandSanitizerLore.add("§e250 §fcoins (total of §e250 §fcoins)");
            covidDrugsLore.add(ChatColor.RESET.toString() + "Buying§e 1 §fCovid Drugs for");
            covidDrugsLore.add("§e2000 §fcoins (total of §e2000 §fcoins)");
            maskLore.add(ChatColor.RESET.toString() + "Buying§e 1 §fMask for");
            maskLore.add("§e400 §fcoins (total of §e400 §fcoins)");
            handSanitizerMeta.setLore(handSanitizerLore);
            goodHandSanitizerMeta.setLore(goodHandSanitizerLore);
            maskMeta.setLore(maskLore);
            covidDrugsMeta.setLore(covidDrugsLore);
            buyHandSanitizer.setItemMeta(handSanitizerMeta);
            buyGoodHandSanitizer.setItemMeta(goodHandSanitizerMeta);
            buyMask.setItemMeta(maskMeta);
            buyCovidDrugs.setItemMeta(covidDrugsMeta);
        }
        else
        {
            List<String> lore = new ArrayList<String>();
            lore.add("§4 You do not have any space in");
            lore.add("§4 your inventory!");
            handSanitizerMeta.setLore(lore);
            goodHandSanitizerMeta.setLore(lore);
            maskMeta.setLore(lore);
            covidDrugsMeta.setLore(lore);
            buyHandSanitizer.setItemMeta(handSanitizerMeta);
            buyGoodHandSanitizer.setItemMeta(goodHandSanitizerMeta);
            buyMask.setItemMeta(maskMeta);
            buyCovidDrugs.setItemMeta(covidDrugsMeta);
        }
        ItemStack handSanitizerReturn = new ItemStack(Material.SPLASH_POTION);
        ItemStack maskReturn = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack goodHandSanitizerReturn = new ItemStack(Material.LINGERING_POTION);
        ItemStack covidDrugsReturn = new ItemStack(Material.TIPPED_ARROW);

        ItemMeta handSanitizerReturnItemMeta = handSanitizerReturn.getItemMeta();
        ItemMeta maskReturnItemMeta = maskReturn.getItemMeta();
        ItemMeta goodHandSanitizerReturnItemMeta = goodHandSanitizerReturn.getItemMeta();
        ItemMeta covidDrugsReturnItemMeta = covidDrugsReturn.getItemMeta();

        handSanitizerReturnItemMeta.setCustomModelData(4);
        maskReturnItemMeta.setCustomModelData(1);
        goodHandSanitizerReturnItemMeta.setCustomModelData(5);
        covidDrugsReturnItemMeta.setCustomModelData(6);
        
        handSanitizerReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Hand Sanitizer");
        maskReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Mask");
        goodHandSanitizerReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Good Hand Sanitizer");
        covidDrugsReturnItemMeta.setDisplayName(ChatColor.RESET.toString() + "Anticovid Drugs");

        handSanitizerReturn.setItemMeta(handSanitizerReturnItemMeta);
        maskReturn.setItemMeta(maskReturnItemMeta);
        goodHandSanitizerReturn.setItemMeta(goodHandSanitizerReturnItemMeta);
        covidDrugsReturn.setItemMeta(covidDrugsReturnItemMeta);

        PotionMeta handSanitizerPotionMeta = (PotionMeta) handSanitizerReturn.getItemMeta();
        handSanitizerPotionMeta.setColor(Color.WHITE);
        handSanitizerReturn.setItemMeta(handSanitizerPotionMeta);

        Color color = Color.WHITE;
        color.setRed(120);
        color.setGreen(120);
        color.setBlue(160);
        PotionMeta goodHandSanitizerPotionMeta = (PotionMeta) goodHandSanitizerReturn.getItemMeta();
        goodHandSanitizerPotionMeta.setColor(color);
        goodHandSanitizerReturn.setItemMeta(goodHandSanitizerPotionMeta);

        GuiItem guiGrayPane = new GuiItem(grayPane);
        GuiItem guiGoBack = new GuiItem(goBack, "return");
        GuiItem guiBuyHandSanitizer = new GuiItem(buyHandSanitizer, "buy", handSanitizerReturn, 1, 100);
        GuiItem guiBuyGoodHandSanitizer = new GuiItem(buyGoodHandSanitizer, "buy", goodHandSanitizerReturn, 1, 250);
        GuiItem guiBuyMask = new GuiItem(buyMask, "buy", maskReturn, 1, 400);
        GuiItem guiBuyCovidDrugs = new GuiItem(buyCovidDrugs, "buy", covidDrugsReturn, 1, 2000);

        gui.fillWith(guiGrayPane);
        gui.addItem(guiGoBack, 4, 2);
        gui.addItem(guiBuyHandSanitizer, 1, 1);
        gui.addItem(guiBuyGoodHandSanitizer, 3, 1);
        gui.addItem(guiBuyMask, 5, 1);
        gui.addItem(guiBuyCovidDrugs, 7, 1);
    }
}
