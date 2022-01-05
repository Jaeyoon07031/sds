package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class IsSanitized2 extends Infection
{
    public IsSanitized2(Simulator simulator)
    {
        super(simulator);
    }

    private boolean checkSanitized2(Player player)
    {
        long currentTime = world.getTime();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Sanitizer2 Effect Time: " + config.getSanitize2EffectTime() + "\nLast Sanitizer2 Time: " + player.getLastSanitize2Time() + "\nCurrent Time: " + currentTime);
            }
        }
        if (currentTime - player.getLastSanitize2Time() <= config.getSanitize2EffectTime())
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
        if(checkSanitized2(player))
        {
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Sanitized2 before chance: " + player.getHasChance());
                }
            }
            player.addHasChance(config.getSanitize2DecreaseChance());
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Sanitized2 after chance: " + player.getHasChance());
                }
            }
        }
    }
}