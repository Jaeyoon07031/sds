package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PumpkinBerryGui extends ShoppingGui
{
    public PumpkinBerryGui(Player player, Simulator simulator)
    {
        super(player, Material.PUMPKIN, Material.SWEET_BERRIES, "Pumpkin", "Berry ", 1, 3, "Pumpkin", "Sweet Berry", simulator);
    }
}
