package com.tokyocodingclub.jaeyoon.sds.config;

import com.tokyocodingclub.jaeyoon.sds.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class Config 
{
    private final double defaultBaseHasChance = 0.0001;
    private final double defaultBaseHealChance = 0.001;
    private final double defaultMaskSelfDecreaseChance = 0.85;
    private final double defaultMaskOtherDecreaseChance = 0.50;
    private final double defaultIndoorIncreaseChance = 1.1;
    private final double defaultNearbyIncreaseChance = 1.05;
    private final double defaultNearbyInfectedIncreaseChance = 1.3;
    private final double defaultNearbyRadius = 40;
    private final double defaultNotWashIncreaseChance = 1.25;
    private final double defaultSoapDecreaseChance = 0.90;
    private final double defaultSanitize2DecreaseChance = 0.8;
    private final double defaultSanitizeDecreaseChance = 0.70;
    private final double defaultAppleDecreaseChance = 0.8;
    private final double defaultDrugHealIncreaseChance = 20;
    private final double defaultImmuneDecreaseChance = 0.4;
    private final long defaultWashEffectTime = 13000L;
    private final long defaultSoapEffectTime = 13000L;
    private final long defaultSanitizeEffectTime = 21500L;
    private final long defaultSanitize2EffectTime = 43000L;
    private final long defaultAppleEffectTime = 12000L;
    private final long defaultDrugEffectTime = 12000L;
    private final long defaultMilkEffectTime = 12000L;
    private final double defaultStageChance = 0.8;
    private final double defaultFatalChance = 0.5;
    private final long defaultStageMinTime = 12000L;
    private final long defaultStageMaxTime = 24000L;
    private final String defaultDbFileName = "db.db";
    private final int defaultBaseDeathCoinLoss = 50;
    private final double defaultPercentageDeathCoinLoss = 0.5;
    
    FileConfiguration config;
    
    private double baseHasChance;
    private double baseHealChance;
    private double maskSelfDecreaseChance;
    private double maskOtherDecreaseChance;
    private double indoorIncreaseChance;
    private double notWashIncreaseChance;
    private double soapDecreaseChance;
    private double sanitizeDecreaseChance;
    private double sanitize2DecreaseChance;
    private double nearbyIncreaseChance;
    private double nearbyInfectedIncreaseChance;
    private double drugHealIncreaseChance;
    private double immuneDecreaseChance;
    private double appleDecreaseChance;
    private int nearbyRadius;
    private long washEffectTime;
    private long soapEffectTime;
    private long sanitizeEffectTime;
    private long sanitize2EffectTime;
    private long drugEffectTime;
    private long milkEffectTime;
    private long appleEffectTime;
    private long stageMinTime;
    private long stageMaxTime;
    private double stageChance;
    private double fatalChance;
    private String dbFileName;
    private int baseDeathCoinLoss;
    private double percentageDeathCoinLoss;
    
    Main main;

    public Config(Main main)
    {
        this.main = main;
        config = main.getConfig();

        config.addDefault("baseHasChance", defaultBaseHasChance);
        config.addDefault("baseHealChance", defaultBaseHealChance);
        config.addDefault("maskSelfDecreaseChance", defaultMaskSelfDecreaseChance);
        config.addDefault("maskOtherDecreaseChance", defaultMaskOtherDecreaseChance);
        config.addDefault("indoorIncreaseChance", defaultIndoorIncreaseChance);
        config.addDefault("nearbyIncreaseChance", defaultNearbyIncreaseChance);
        config.addDefault("nearbyInfectedIncreaseChance", defaultNearbyInfectedIncreaseChance);
        config.addDefault("nearbyRadius", defaultNearbyRadius);
        config.addDefault("notWashIncreaseChance", defaultNotWashIncreaseChance);
        config.addDefault("sanitizeDecreaseChance", defaultSanitizeDecreaseChance);
        config.addDefault("soapDecreaseChance", defaultSoapDecreaseChance);
        config.addDefault("sanitizeDecreaseChance", defaultSanitizeDecreaseChance);
        config.addDefault("sanitize2DecreaseChance", defaultSanitize2DecreaseChance);
        config.addDefault("appleDecreaseChance", defaultAppleDecreaseChance);
        config.addDefault("washEffectTime", defaultWashEffectTime);
        config.addDefault("sanitizeEffectTime", defaultSanitizeEffectTime);
        config.addDefault("soapEffectTime", defaultSoapEffectTime);
        config.addDefault("sanitizeEffectTime", defaultSanitizeEffectTime);
        config.addDefault("sanitize2EffectTime", defaultSanitize2EffectTime);
        config.addDefault("appleEffectTime", defaultAppleEffectTime);
        config.addDefault("milkEffectTime", defaultMilkEffectTime);
        config.addDefault("drugHealIncreaseChance", defaultDrugHealIncreaseChance);
        config.addDefault("immuneDecreaseChance", defaultImmuneDecreaseChance);
        config.addDefault("drugEffectTime", defaultDrugEffectTime);
        config.addDefault("stageMinTime", defaultStageMinTime);
        config.addDefault("stageMaxTime", defaultStageMaxTime);
        config.addDefault("stageChance", defaultStageChance);
        config.addDefault("fatalChance", defaultFatalChance);
        config.addDefault("dbFileName", defaultDbFileName);
        config.addDefault("baseDeathCoinLoss", defaultBaseDeathCoinLoss);
        config.addDefault("percentageDeathCoinLoss", defaultPercentageDeathCoinLoss);

        config.options().copyDefaults(true);
        main.saveConfig();
        
        baseHasChance = config.getDouble("baseHasChance");
        baseHealChance = config.getDouble("baseHealChance");
        maskSelfDecreaseChance = config.getDouble("maskSelfDecreaseChance");
        maskOtherDecreaseChance = config.getDouble("maskOtherDecreaseChance");
        indoorIncreaseChance = config.getDouble("indoorIncreaseChance");
        nearbyIncreaseChance = config.getDouble("nearbyIncreaseChance");
        nearbyInfectedIncreaseChance = config.getDouble("nearbyInfectedIncreaseChance");
        nearbyRadius = config.getInt("nearbyRadius");
        notWashIncreaseChance = config.getDouble("notWashIncreaseChance");
        sanitizeDecreaseChance = config.getDouble("sanitizeDecreaseChance");
        soapDecreaseChance = config.getDouble("soapDecreaseChance");
        sanitizeDecreaseChance = config.getDouble("sanitizeDecreaseChance");
        sanitize2DecreaseChance = config.getDouble("sanitize2DecreaseChance");
        appleDecreaseChance = config.getDouble("appleDecreaseChance");
        drugHealIncreaseChance = config.getDouble("drugHealIncreaseChance");
        immuneDecreaseChance = config.getDouble("immuneDecreaseChance");
        stageChance = config.getDouble("stageChance");
        fatalChance = config.getDouble("fatalChance");
        washEffectTime = config.getLong("washEffectTime");
        soapEffectTime = config.getLong("soapEffectTime");
        sanitizeEffectTime = config.getLong("sanitizeEffectTime");
        sanitize2EffectTime = config.getLong("sanitize2EffectTime");
        appleEffectTime = config.getLong("appleEffectTime");
        drugEffectTime = config.getLong("drugEffectTime");
        milkEffectTime = config.getLong("milkEffectTime");
        stageMinTime = config.getLong("stageMinTime");
        stageMaxTime = config.getLong("stageMaxTime");
        dbFileName = config.getString("dbFileName");
        baseDeathCoinLoss = config.getInt("baseDeathCoinLoss");
        percentageDeathCoinLoss = config.getDouble("percentageDeathCoinLoss");
    }

    public double getBaseHasChance()
    {
        return baseHasChance;
    }

    public void setBaseHasChance(double changed)
    {
        baseHasChance = changed;
    }

    public double getBaseHealChance()
    {
        return baseHealChance;
    }

    public void setBaseHealChance(double changed)
    {
        baseHealChance = changed;
    }

    public double getMaskSelfDecreaseChance()
    {
        return maskSelfDecreaseChance;
    }

    public void setMaskSelfDecreaseChance(double changed)
    {
        maskSelfDecreaseChance = changed;
    }

    public double getMaskOtherDecreaseChance()
    {
        return maskOtherDecreaseChance;
    }

    public void setMaskOtherDecreaseChance(double changed)
    {
        maskOtherDecreaseChance = changed;
    }

    public double getIndoorIncreaseChance()
    {
        return indoorIncreaseChance;
    }

    public void setIndoorIncreaseChance(double changed)
    {
        indoorIncreaseChance = changed;
    }

    public double getNotWashIncreaseChance()
    {
        return notWashIncreaseChance;
    }

    public void setNotWashIncreaseChance(double changed)
    {
        notWashIncreaseChance = changed;
    }

    public double getSoapDecreaseChance()
    {
        return soapDecreaseChance;
    }

    public void setSoapDecreaseChance(double changed)
    {
        soapDecreaseChance = changed;
    }

    public double getSanitizeDecreaseChance()
    {
        return sanitizeDecreaseChance;
    }

    public void setSanitizeDecreaseChance(double changed)
    {
        sanitizeDecreaseChance = changed;
    }

    public double getSanitize2DecreaseChance()
    {
        return sanitize2DecreaseChance;
    }

    public void setSanitize2DecreaseChance(double changed)
    {
        sanitize2DecreaseChance = changed;
    }

    public double getNearbyIncreaseChance()
    {
        return nearbyIncreaseChance;
    }

    public void setNearbyIncreaseChance(double changed)
    {
        nearbyIncreaseChance = changed;
    }

    public double getNearbyInfectedIncreaseChance()
    {
        return nearbyInfectedIncreaseChance;
    }

    public void setNearbyInfectedIncreaseChance(double changed)
    {
        nearbyInfectedIncreaseChance = changed;
    }

    public double getDrugHealIncreaseChance()
    {
        return drugHealIncreaseChance;
    }

    public void setDrugHealIncreaseChance(double changed)
    {
        drugHealIncreaseChance = changed;
    }

    public double getImmuneDecreaseChance()
    {
        return immuneDecreaseChance;
    }

    public void setImmuneDecreaseChance(double changed)
    {
        immuneDecreaseChance = changed;
    }

    public double getAppleDecreaseChance()
    {
        return appleDecreaseChance;
    }

    public void setAppleDecreaseChance(double changed)
    {
        appleDecreaseChance = changed;
    }

    public int getNearbyRadius()
    {
        return nearbyRadius;
    }

    public void setNearbyRadius(int changed)
    {
        nearbyRadius = changed;
    }

    public long getWashEffectTime()
    {
        return washEffectTime;
    }

    public void setWashEffectTime(long changed)
    {
        washEffectTime = changed;
    }

    public long getSoapEffectTime()
    {
        return soapEffectTime;
    }

    public void setSoapEffectTime(long changed)
    {
        soapEffectTime = changed;
    }

    public long getSanitizeEffectTime()
    {
        return sanitizeEffectTime;
    }

    public void setSanitizeEffectTime(long changed)
    {
        sanitizeEffectTime = changed;
    }

    public long getSanitize2EffectTime()
    {
        return sanitize2EffectTime;
    }

    public void setSanitize2EffectTime(long changed)
    {
        sanitize2EffectTime = changed;
    }

    public long getDrugEffectTime()
    {
        return drugEffectTime;
    }

    public void setDrugEffectTime(long changed)
    {
        drugEffectTime = changed;
    }

    public long getAppleEffectTime()
    {
        return appleEffectTime;
    }

    public void setAppleEffectTime(long changed)
    {
        appleEffectTime = changed;
    }

    public long getMilkEffectTime()
    {
        return milkEffectTime;
    }

    public void setMilkEffectTime(long changed)
    {
        milkEffectTime = changed;
    }

    public long getStageMinTime()
    {
        return stageMinTime;
    }

    public void setStageMinTime(long changed)
    {
        stageMinTime = changed;
    }

    public long getStageMaxTime()
    {
        return stageMaxTime;
    }

    public void setStageMaxTime(long changed)
    {
        stageMaxTime = changed;
    }

    public double getStageChance()
    {
        return stageChance;
    }

    public void setStageChance(double changed)
    {
        stageChance = changed;
    }

    public double getFatalChance()
    {
        return fatalChance;
    }

    public void setFatalChance(double changed)
    {
        fatalChance = changed;
    }

    public String getDbFileName()
    {
        return dbFileName;
    }

    public void setDbfileName(String changed)
    {
        dbFileName = changed;
    }

    public int getBaseDeathCoinLoss()
    {
        return baseDeathCoinLoss;
    }

    public void setBaseDeathCoinLoss(int changed)
    {
        baseDeathCoinLoss = changed;
    }

    public double getPercentageDeathCoinLoss()
    {
        return percentageDeathCoinLoss;
    }

    public void setPercentageDeathCoinLoss(double changed)
    {
        percentageDeathCoinLoss = changed;
    }

    public void saveConfig()
    {
        main.saveConfig();
    }
    
}
