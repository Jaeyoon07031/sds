package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Material;

public class HasMask extends Infection
{
    public HasMask(Simulator simulator)
    {
        super(simulator);
    }
    private boolean checkMask(Player player)
    {
        if (player.getSpigotPlayer().getInventory().getHelmet() != null && player.getSpigotPlayer().getInventory().getHelmet().getType() == Material.CHAINMAIL_HELMET)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void applyChances(Player player)
    {
        if (checkMask(player))
        {
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Has mask before chance: " + player.getHasChance());
                }
            }
            player.addHasChance(config.getMaskSelfDecreaseChance());
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Has mask after chance:" + player.getHasChance());
                }
            }
        }
    }
}
