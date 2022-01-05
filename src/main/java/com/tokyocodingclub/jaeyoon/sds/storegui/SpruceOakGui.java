package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpruceOakGui extends ShoppingGui
{
    public SpruceOakGui(Player player, Simulator simulator)
    {
        super(player, Material.SPRUCE_LOG, Material.OAK_LOG, "Spruce", "Oak", 3, 3, "Spruce Log", "Oak Log",  simulator);
    }
}
