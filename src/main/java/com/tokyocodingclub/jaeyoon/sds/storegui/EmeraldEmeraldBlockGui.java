package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class EmeraldEmeraldBlockGui extends ShoppingGui
{
    public EmeraldEmeraldBlockGui(Player player, Simulator simulator)
    {
        super(player, Material.EMERALD, Material.EMERALD_BLOCK, "Emerald", "Emerald Block", 3, 30, "Emerald", "Emerald Block",  simulator);
    }
}
