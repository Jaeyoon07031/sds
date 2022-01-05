package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DiamondDiamondBlockGui extends ShoppingGui
{
    public DiamondDiamondBlockGui(Player player, Simulator simulator)
    {
        super(player, Material.DIAMOND, Material.DIAMOND_BLOCK, "Diamond", "Diamond Block", 200, 2000, "Diamond", "Diamond Block", simulator);
    }
}
