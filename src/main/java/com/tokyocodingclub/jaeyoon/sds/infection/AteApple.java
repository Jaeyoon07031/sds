package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;


public class AteApple extends Infection
{
    public AteApple(Simulator simulator)
    {
        super(simulator);
    }

    private boolean checkApple(Player player)
    {
        long currentTime = world.getTime();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Apple Effect Time: " + config.getAppleEffectTime() + "\nLast Apple Time: " + player.getLastAppleTime() + "\nCurrent Time: " + currentTime);
            }
        }
        if (currentTime - player.getLastAppleTime() <= config.getAppleEffectTime())
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
        if(checkApple(player))
        {
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Ate apple before chance: " + player.getHasChance());
                }
            }
            player.addHasChance(config.getAppleDecreaseChance());
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Ate apple after chance: " + player.getHasChance());
                }
            }
        }
    }
}