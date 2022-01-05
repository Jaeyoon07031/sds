package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CoalCoalBlockGui extends ShoppingGui
{
    public CoalCoalBlockGui(Player player, Simulator simulator)
    {
        super(player, Material.COAL, Material.COAL_BLOCK, "Coal", "Coal Block", 5, 50, "Coal", "Coal Block", simulator);
    }
}
