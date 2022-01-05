package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class IsSanitized extends Infection
{
    public IsSanitized(Simulator simulator)
    {
        super(simulator);
    }

    private boolean checkSanitized(Player player)
    {
        long currentTime = world.getTime();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Sanitizer Effect Time: " + config.getSanitizeEffectTime() + "\nLast Sanitizer Time: " + player.getLastSanitizeTime() + "\nCurrent Time: " + currentTime);
            }
        }
        if (currentTime - player.getLastSanitizeTime() <= config.getSanitizeEffectTime())
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
        if(checkSanitized(player))
        {
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Sanitized before chance: " + player.getHasChance());
                }
            }
            player.addHasChance(config.getSanitizeDecreaseChance());
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Sanitized after chance: " + player.getHasChance());
                }
            }
        }
    }
}