package com.tokyocodingclub.jaeyoon.sds;

import java.util.UUID;

public class Player
{
    private boolean has;
    private int infectionStage;
    private long infectionTime;
    private double hasChance;
    private double healChance;
    private long lastWashTime;
    private long lastSanitizeTime;
    private long lastSoapTime;
    private long lastSanitize2Time;
    private long lastDrugTime;
    private int healCounter;
    private long lastAppleTime;
    private long lastMilkTime;
    private int balance;
    private org.bukkit.entity.Player player;

    public Player(org.bukkit.entity.Player player, Simulator simulator)
    {
        has = false;
        infectionStage = 0;
        infectionTime = 0;
        hasChance = 0;
        healChance = 0;
        lastWashTime = 0;
        lastSanitizeTime = 0;
        lastSoapTime = 0;
        lastSanitize2Time = 0;
        lastDrugTime = 0;
        healCounter = 0;
        lastAppleTime = 0;
        lastMilkTime = 0;
        balance = 0;
        this.player = player;
        simulator.addPlayer(this);
    }

    public boolean getHas()
    {
        return has;
    }

    public void setHas(Boolean changed)
    {
        has = changed;
    }

    public int getInfectionStage()
    {
        return infectionStage;
    }

    public void setInfectionStage(int changed)
    {
        infectionStage = changed;
    }

    public long getInfectionTime()
    {
        return infectionTime;
    }

    public void setInfectionTime(long changed)
    {
        infectionTime = changed;
    }

    public double getHasChance()
    {
        return hasChance;
    }

    public void setHasChance(double changed)
    {
        hasChance = changed;
    }

    public void addHasChance(double changed)
    {
        hasChance = hasChance * changed;
    }

    public double getHealChance()
    {
        return healChance;
    }

    public void setHealChance(double changed)
    {
        healChance = changed;
    }

    public void addHealChance(double changed)
    {
        healChance = healChance * changed;
    }
    
    public long getLastWashTime()
    {
        return lastWashTime;
    }

    public void setLastWashTime(long changed)
    {
        lastWashTime = changed;
    }

    public long getLastSanitizeTime()
    {
        return lastSanitizeTime;
    }

    public void setLastSanitizeTime(long changed)
    {
        lastSanitizeTime = changed;
    }

    public long getLastSanitize2Time()
    {
        return lastSanitize2Time;
    }

    public void setLastSanitize2Time(long changed)
    {
        lastSanitize2Time = changed;
    }

    public long getLastSoapTime()
    {
        return lastSoapTime;
    }

    public void setLastSoapTime(long changed)
    {
        lastSoapTime = changed;
    }

    public long getLastDrugTime()
    {
        return lastDrugTime;
    }

    public void setLastDrugTime(long changed)
    {
        lastDrugTime = changed;
    }

    public int getHealCounter()
    {
        return healCounter;
    }

    public void setHealCounter(int changed)
    {
        healCounter = changed;
    }

    public long getLastAppleTime()
    {
        return lastAppleTime;
    }

    public void setLastAppleTime(long changed)
    {
        lastAppleTime = changed;
    }

    public long getLastMilkTime()
    {
        return lastMilkTime;
    }

    public void setLastMilkTime(long changed)
    {
        lastMilkTime = changed;
    }

    public int getBalance()
    {
        return balance;
    }

    public void setBalance(int changed)
    {
        balance = changed;
    }

    public UUID getUUID()
    {
        return player.getUniqueId();
    }

    public org.bukkit.entity.Player getSpigotPlayer()
    {
        return player;
    }
}