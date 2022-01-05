package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BirchJungleGui extends ShoppingGui
{
    public BirchJungleGui(Player player, Simulator simulator)
    {
        super(player, Material.BIRCH_LOG, Material.JUNGLE_LOG, "Birch", "Jungle Wood ", 3, 3, "Birch Log", "Jungle Wood Log", simulator);
    }
}
