package com.tokyocodingclub.jaeyoon.sds.infection;

import com.tokyocodingclub.jaeyoon.sds.Simulator;
import com.tokyocodingclub.jaeyoon.sds.config.Config;
import org.bukkit.World;

public class Infection
{
    protected Simulator simulator;
    protected World world;
    protected int debugLevel;
    protected int debugTick;
    protected Config config;

    public Infection(Simulator simulator)
    {
        this.simulator = simulator;
        world = simulator.getWorld();
        config = simulator.getConfig();
    }

    public void applyChances()
    {
        
    }
}