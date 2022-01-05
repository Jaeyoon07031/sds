package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class NetheriteScrapNetheriteGui extends ShoppingGui
{
    public NetheriteScrapNetheriteGui(Player player, Simulator simulator)
    {
        super(player, Material.NETHERITE_SCRAP, Material.NETHERITE_INGOT, "Netherite Scrap", "Netherite", 500, 2200, "Netherite Scrap", "Netherite Ingot",  simulator);
    }
}
