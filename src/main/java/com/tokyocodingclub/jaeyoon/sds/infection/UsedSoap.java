package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class UsedSoap extends Infection
{
    public UsedSoap(Simulator simulator)
    {
        super(simulator);
    }

    private boolean checkSoap(Player player)
    {
        long currentTime = world.getTime();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Soap Effect Time: " + config.getSoapEffectTime() + "\nLast soap time: " + player.getLastSoapTime() + "\nCurrent Time: " + currentTime );
            }
        }
        if (currentTime - player.getLastSoapTime() < config.getSoapEffectTime())
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
        if(checkSoap(player))
        {
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Used soap before chance: " + player.getHasChance());
                }
            }
            player.addHasChance(config.getSoapDecreaseChance());
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Used soap after chance: " + player.getHasChance());
                }
            }
        }
    }
}
