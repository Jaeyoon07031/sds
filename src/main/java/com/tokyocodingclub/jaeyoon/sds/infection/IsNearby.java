package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;

public class IsNearby extends Infection
{
    public IsNearby (Simulator simulator)
    {
        super(simulator);
    }
    
    private double checkNearby(Player player)
    {  
        double modifier = 1.0;
        double nearbyRadius = config.getNearbyRadius();
        List<Entity> nearby = player.getSpigotPlayer().getNearbyEntities(nearbyRadius, nearbyRadius, nearbyRadius);

        for(int i = 0; i < nearby.size(); i++)
        {
            if(nearby.get(i) instanceof org.bukkit.entity.Player)
            {
                org.bukkit.entity.Player other = (org.bukkit.entity.Player)nearby.get(i);
                if (simulator.getDebugLevel() > 2)
                {
                    if (simulator.getDebugTick() == 0)
                    {
                        player.getSpigotPlayer().sendMessage(other.getDisplayName() + " is Nearby");
                    }
                }
                if (hasCovid(other))
                {
                    if (hasMask(other))
                    {
                        modifier *= ((config.getNearbyInfectedIncreaseChance() - 1) * config.getMaskOtherDecreaseChance());
                    }
                    else
                    {
                        modifier *= config.getNearbyInfectedIncreaseChance();
                    }
                }
                else
                {
                    if (simulator.getDebugLevel() > 2)
                    {
                        if (simulator.getDebugTick() == 0)
                        {
                            player.getSpigotPlayer().sendMessage(other.getDisplayName() + " is clean");
                        }
                    }
                    if (hasMask(other))
                    {
                        if (simulator.getDebugLevel() > 2)
                        {
                            if (simulator.getDebugTick() == 0)
                            {
                                player.getSpigotPlayer().sendMessage(other.getDisplayName() + " is wearing a mask");
                            }
                        }
                        modifier *= 1.0 + ((config.getNearbyIncreaseChance() - 1) * config.getMaskOtherDecreaseChance());
                    }
                    else
                    {
                        modifier *= config.getNearbyIncreaseChance();
                    }
                }
            }
            else if (simulator.getDebugLevel() > 2)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage(nearby.get(i).getType().getKey().getKey() + " is Nearby and not a Player");
                    
                }
            }
        }
        return modifier;
    }

    private boolean hasMask(org.bukkit.entity.Player player)
    {
        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType().equals(Material.CHAINMAIL_HELMET))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean hasCovid(org.bukkit.entity.Player player)
    {
        if (simulator.getSdsPlayer(player.getUniqueId()).getHas())
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
        player.addHasChance(checkNearby(player));
    }
}