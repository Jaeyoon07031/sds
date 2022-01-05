package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AcaciaDarkOakGui extends ShoppingGui
{
    public AcaciaDarkOakGui(Player player, Simulator simulator)
    {
        super(player, Material.ACACIA_LOG, Material.DARK_OAK_LOG, "Acacia", "Dark Oak", 3, 3, "Acacia Log", "Dark Oak Log", simulator);
    }
}
