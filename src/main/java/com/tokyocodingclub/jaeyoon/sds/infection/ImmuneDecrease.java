package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class ImmuneDecrease extends Infection
{
    public ImmuneDecrease(Simulator simulator)
    {
        super(simulator);
    }

    public void applyChances(Player player)
    {
        int healedTimes = player.getHealCounter();
        if(healedTimes > 0)
        {
            double chance = player.getHasChance();

            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Immune Decrease Before Has Chance: " + chance);
                }
            }

            player.addHasChance(Math.pow(config.getImmuneDecreaseChance(), healedTimes));

            if (simulator.getDebugLevel() > 1)
            {
                if (simulator.getDebugTick() == 0)
                {
                    player.getSpigotPlayer().sendMessage("Immune Decrease After Has Chance: " + player.getHasChance());
                }
            }
        }
    }
}