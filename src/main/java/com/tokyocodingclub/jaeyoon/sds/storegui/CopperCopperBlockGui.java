package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CopperCopperBlockGui extends ShoppingGui
{
    public CopperCopperBlockGui(Player player, Simulator simulator)
    {
        super(player, Material.COPPER_INGOT, Material.COPPER_BLOCK, "Copper", "Copper Block", 5, 50, "Copper", "Copper Block", simulator);
    }
}
