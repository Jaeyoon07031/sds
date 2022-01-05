package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class UsedDrug extends Infection
{
    public UsedDrug(Simulator simulator)
    {
        super(simulator);
    }

    private boolean checkDrug(Player player)
    {
        long currentTime = world.getTime();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Drug Effect Time: " + config.getDrugEffectTime() + "\nLast Drug Time: " + player.getLastDrugTime() + "\nCurrent Time: " + currentTime);
            }
        }
        if (currentTime - player.getLastDrugTime() < config.getDrugEffectTime())
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
        if(checkDrug(player))
        {
            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Used Drug Before Heal Chance: " + player.getHealChance());
                }
                player.addHealChance(config.getDrugHealIncreaseChance());

                if (simulator.getDebugLevel() > 1)
                {
                    if (simulator.getDebugTick() == 0)
                    {
                        player.getSpigotPlayer().sendMessage("Used Drug After Heal Chance: " + player.getHealChance());
                    }
                }
            }
        }
    }
}