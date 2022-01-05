package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.Location;

public class IsIndoors extends Infection
{
    public IsIndoors(Simulator simulator)
    {
        super(simulator);
    }
    public void applyChances(Player player)
    {
        Location location = player.getSpigotPlayer().getLocation();
        int y = location.getBlockY();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Current Y: " + y + " Highest Block Y: " + world.getHighestBlockYAt(location));
            }
        }
        if (y < location.getWorld().getHighestBlockYAt(location))
        {
            double chance = player.getHasChance();
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Is indoors before chance: " + chance);
                }
            }
            player.setHasChance(chance * config.getIndoorIncreaseChance());
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Is indoors after chance: " + player.getHasChance());
                }
            }
        }
    }
}
