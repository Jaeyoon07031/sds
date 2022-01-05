package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class WheatPotatoGui extends ShoppingGui
{
    public WheatPotatoGui(Player player, Simulator simulator)
    {
        super(player, Material.WHEAT, Material.POTATO, "Wheat", "Potato", 1, 1, "Wheat", "Potato",  simulator);
    }
}
