package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

public class StageAdvance extends Infection
{
    public StageAdvance(Simulator simulator)
    {
        super(simulator);
    }

    public void applyChances(Player player)
    {
        if (player.getHas())
        {   
            int stage = player.getInfectionStage();
            if(world.getTime() > player.getInfectionTime())
            {
                if (Math.random() < config.getStageChance())
                {
                    if(stage == 3 && Math.random() < config.getFatalChance())
                    {
                        player.setInfectionStage(stage + 2);
                        player.setInfectionTime(world.getTime() + config.getStageMaxTime() - config.getStageMinTime());
                        if (simulator.getDebugLevel() > 0)
                        {
                            player.getSpigotPlayer().sendMessage("Infection Advance");
                            player.getSpigotPlayer().sendMessage("Infection Stage" +  + player.getInfectionStage());
                            player.getSpigotPlayer().sendMessage("Infection Time: " + player.getInfectionTime());
                            player.getSpigotPlayer().sendMessage("Infection Period: " + (player.getInfectionTime() - world.getTime()));
                        }
                        return;
                    }

                    player.setInfectionStage(stage + 1);
                    player.setInfectionTime(world.getTime() + config.getStageMaxTime() - config.getStageMinTime());

                    if (simulator.getDebugLevel() > 0)
                    {
                        player.getSpigotPlayer().sendMessage("Infection Advance");
                        player.getSpigotPlayer().sendMessage("Infection Stage" +  + player.getInfectionStage());
                        player.getSpigotPlayer().sendMessage("Infection Time: " + player.getInfectionTime());
                        player.getSpigotPlayer().sendMessage("Infection Period: " + (player.getInfectionTime() - world.getTime()));
                    }

                    
                }
                else if(simulator.getDebugLevel() > 0)
                {
                    player.getSpigotPlayer().sendMessage("Infection Stage Stopped");
                }
            }
        }
    }
}