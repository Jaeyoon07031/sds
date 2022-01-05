package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class IsWashed extends Infection
{
    public IsWashed(Simulator simulator)
    {
        super(simulator);
    }

    private boolean checkWashed(Player player)
    {
        long currentTime = world.getTime();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Wash Effect Time: " + config.getWashEffectTime() + "Wash Sanitizer Time: " + player.getLastWashTime() + "\nCurrent Time: " + currentTime);
            }
        }
        if (currentTime - player.getLastWashTime() <= config.getWashEffectTime())
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
        if(checkWashed(player))
        {
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Washed before chance: " + player.getHasChance());
                }
            }
            player.addHasChance(config.getNotWashIncreaseChance());
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Washed after chance: " + player.getHasChance());
                }
            }
        }
    }
}