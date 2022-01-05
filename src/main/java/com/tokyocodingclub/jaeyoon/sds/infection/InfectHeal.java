package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class InfectHeal extends Infection
{
    public InfectHeal(Simulator simulator)
    {
        super(simulator);
    }

    public void infectHeal(Player player)
    {
        double rand = Math.random();
        boolean isInfected = player.getHas();
        if (isInfected) 
        {
            if (simulator.getDebugLevel() > 3)
            {
                player.getSpigotPlayer().sendMessage("Heal Chance: " + player.getHealChance() + " Random: " + rand);
            }
            if (rand <= player.getHealChance())
            {
                player.setHas(false);
                player.setInfectionStage(0);
                player.setInfectionStage(0);
                player.setHealChance(player.getHealCounter() + 1);
                player.getSpigotPlayer().sendMessage("You are now healed.");
            }
        }
        else
        {
            if (simulator.getDebugLevel() > 3)
            {
                player.getSpigotPlayer().sendMessage("Has Chance: " + player.getHasChance() + " Random: " + rand);
            }
            if (rand <= player.getHasChance())
            {
                player.setHas(true);
                player.setInfectionStage(1);
                player.setInfectionTime(simulator.getWorld().getTime() + config.getStageMaxTime() - config.getStageMinTime());
                player.getSpigotPlayer().sendMessage("You are now infected.");
            }
        }
    }
}
