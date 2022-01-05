package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LapisRedstoneGui extends ShoppingGui
{
    public LapisRedstoneGui(Player player, Simulator simulator)
    {
        super(player, Material.LAPIS_LAZULI, Material.REDSTONE, "Lapis", "Redstone", 15, 2, "Lapis", "Redstone",  simulator);
    }
}
