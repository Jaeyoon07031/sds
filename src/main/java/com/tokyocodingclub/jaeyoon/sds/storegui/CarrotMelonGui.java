package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CarrotMelonGui extends ShoppingGui
{
    public CarrotMelonGui(Player player, Simulator simulator)
    {
        super(player, Material.CARROT, Material.MELON, "Carrot", "Melon", 1, 1, "Carrot", "Melon Block", simulator);
    }
}
