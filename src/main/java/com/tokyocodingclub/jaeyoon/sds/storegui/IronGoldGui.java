package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class IronGoldGui extends ShoppingGui
{
    public IronGoldGui(Player player, Simulator simulator)
    {
        super(player, Material.IRON_INGOT, Material.GOLD_INGOT, "Iron", "Gold", 10, 25, "Iron Ingot", "Gold Ingot",  simulator);
    }
}
