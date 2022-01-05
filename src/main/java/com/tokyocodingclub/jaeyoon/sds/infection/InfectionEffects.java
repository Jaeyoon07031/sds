package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.Simulator;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InfectionEffects extends Infection
{
    private PotionEffect slowness = PotionEffectType.SLOW.createEffect(20, 1);
    private PotionEffect miningFatigue = PotionEffectType.SLOW_DIGGING.createEffect(20, 1);
    private PotionEffect confusion = PotionEffectType.CONFUSION.createEffect(20, 1);
    private PotionEffect blindness = PotionEffectType.BLINDNESS.createEffect(20, 1);
    private PotionEffect hunger = PotionEffectType.HUNGER.createEffect(20, 1);
    private PotionEffect poison = PotionEffectType.POISON.createEffect(20, 1);
    private PotionEffect wither = PotionEffectType.WITHER.createEffect(20, 1);
    
    public InfectionEffects(Simulator simulator)
    {
        super(simulator);
    }

    private boolean checkMilk(Player player)
    {
        long currentTime = world.getTime();
        if (simulator.getDebugLevel() > 1)
        {
            if (simulator.getDebugTick() == 0)
            {
                player.getSpigotPlayer().sendMessage("Milk Effect Time: " + config.getMilkEffectTime() + "\nLast Milk Time: " + player.getLastMilkTime() + "\nCurrent Time: " + currentTime);
            }
        }
        if (currentTime - player.getLastMilkTime() <= config.getMilkEffectTime())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void applyEffects(Player player)
    {
        if (player.getHas())
        {
            if (checkMilk(player))
            {
                return;
            }
            else
            {
                int stage = player.getInfectionStage();
                org.bukkit.entity.Player spigotPlayer = player.getSpigotPlayer();
                switch (stage)
                {
                    case 5:
                        wither.apply(spigotPlayer);
                        confusion.apply(spigotPlayer);
                    case 4:
                        poison.apply(spigotPlayer);
                    case 3:
                        blindness.apply(spigotPlayer);
                        hunger.apply(spigotPlayer);
                        miningFatigue.apply(spigotPlayer);
                    case 2:
                    slowness.apply(spigotPlayer);
                    default:
                        break;
                }
            }
        }
    }
}
