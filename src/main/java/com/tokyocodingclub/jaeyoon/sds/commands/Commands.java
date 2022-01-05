package com.tokyocodingclub.jaeyoon.sds.commands;

import com.tokyocodingclub.jaeyoon.sds.Simulator;
import com.tokyocodingclub.jaeyoon.sds.config.Config;
import com.tokyocodingclub.jaeyoon.sds.database.Database;
import com.tokyocodingclub.jaeyoon.sds.storegui.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Commands 
{
    private Simulator simulator;
    private Config config;
    private Database database;

    public Commands (Simulator simulator)
    {
        this.simulator = simulator;
        this.database = simulator.getDatabase();
        config = simulator.getConfig();
    }
    boolean resetArmed = false;

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("sds")) 
        { 
            if (args.length > 0 && args.length < 4)
            {
                if(args[0].equals("start"))
                {
                    simulator.startSim();
                }
                else if (args[0].equals("end"))
                {
                    simulator.endSim();
                }
                else if (args[0].equals("save"))
                {
                    config.saveConfig();
                    sender.sendMessage("Succesfully saved configuration data =D");
                }
                else if (args[0].equals("reset"))
                {
                    if (!resetArmed || args.length != 2 || !args[1].equals("yes"))
                    {
                        sender.sendMessage("This will reset all the data. Do '/sds reset yes' to confirm");
                        if (args.length == 1)
                        {
                            resetArmed = true;
                        }
                    }
                    else
                    {
                        sender.sendMessage("Reseting Simulation Data");
                        if (simulator.getEnabled())
                        {
                            sender.sendMessage("Stopping Simulation for Reset");
                            simulator.endSim();
                        }
                        database.resetData();
                        resetArmed = false;
                        sender.sendMessage("Simulation Data Reset");
                    }
                }
                else if (args[0].equals("set") && args.length == 3)
                {
                    String command = args[1];
                    double value = 0;
                    try
                    {
                        value = Double.parseDouble(args[2]);
                    }
                    catch(NumberFormatException e)
                    {
                        return false;
                    }
                    if (command.equals("debugLevel"))
                    {
                        simulator.setDebugLevel((int)value);
                        sender.sendMessage("Succesfully set debugLevel to " + (int)value + ".");
                    }
                    else if (command.equals("debugPeriod"))
                    {
                        simulator.setDebugPeriod((int)value);
                        sender.sendMessage("Succesfully set debugPeriod to " + (int)value + ".");
                    }
                    else if (command.equals("baseHasChance"))
                    {
                        config.setBaseHasChance((double)value);
                        sender.sendMessage("Succesfully set baseHasChance to " + (double)value + ".");
                    }
                    else if (command.equals("baseHealChance"))
                    {
                        config.setBaseHealChance((double)value);
                        sender.sendMessage("Succesfully set baseHealChance to " + (double)value + ".");
                    }
                    else if (command.equals("maskSelfDecreaseChance"))
                    {
                        config.setMaskSelfDecreaseChance((double)value);
                        sender.sendMessage("Succesfully set maskSelfDecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("maskOtherDecreaseChance"))
                    {
                        config.setMaskOtherDecreaseChance((double)value);
                        sender.sendMessage("Succesfully set maskOtherDecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("indoorIncreaseChance"))
                    {
                        config.setIndoorIncreaseChance((double)value);
                        sender.sendMessage("Succesfully set indoorIncreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("nearbyIncreaseChance"))
                    {
                        config.setNearbyIncreaseChance((double)value);
                        sender.sendMessage("Succesfully set maskSelfDnearbyIncreaseChanceecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("nearbyInfectedIncreaseChance"))
                    {
                        config.setNearbyInfectedIncreaseChance((double)value);
                        sender.sendMessage("Succesfully set nearbyInfectedIncreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("nearbyRadius"))
                    {
                        config.setNearbyRadius((int)value);
                        sender.sendMessage("Succesfully set nearbyRadius to " + (int)value + ".");
                    }
                    else if (command.equals("notWashIncreaseChance"))
                    {
                        config.setNotWashIncreaseChance((double)value);
                        sender.sendMessage("Succesfully set notWashIncreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("sanitizeDecreaseChance"))
                    {
                        config.setSanitizeDecreaseChance((double)value);
                        sender.sendMessage("Succesfully set sanitizeDecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("soapDecreaseChance"))
                    {
                        config.setSoapDecreaseChance((double)value);
                        sender.sendMessage("Succesfully set soapDecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("sanitizeDecreaseChance"))
                    {
                        config.setSanitizeDecreaseChance((double)value);
                        sender.sendMessage("Succesfully set sanitizeDecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("sanitize2DecreaseChance"))
                    {
                        config.setSanitize2DecreaseChance((double)value);
                        sender.sendMessage("Succesfully set sanitize2DecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("drugHealIncreaseChance"))
                    {
                        config.setDrugHealIncreaseChance((double)value);
                        sender.sendMessage("Succesfully set drugHealIncreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("immuneDecreaseChance"))
                    {
                        config.setImmuneDecreaseChance((double)value);
                        sender.sendMessage("Succesfully set immuneDecreaseChance to " + (double)value + ".");
                    }
                    else if (command.equals("stageChance"))
                    {
                        config.setStageChance((double)value);
                        sender.sendMessage("Succesfully set stageChance to " + (double)value + ".");
                    }
                    else if (command.equals("fatalChance"))
                    {
                        config.setFatalChance((double)value);
                        sender.sendMessage("(double)value) set fatalChance to " + (double)value + ".");
                    }
                    else if (command.equals("washEffectTime"))
                    {
                        config.setWashEffectTime((long)value);
                        sender.sendMessage("Succesfully set washEffectTime to " + (long)value + ".");
                    }
                    else if (command.equals("soapEffectTime"))
                    {
                        config.setSoapEffectTime((long)value);
                        sender.sendMessage("Succesfully set soapEffectTime to " + (long)value + ".");
                    }
                    else if (command.equals("sanitizeEffectTime"))
                    {
                        config.setSanitizeEffectTime((long)value); 
                        sender.sendMessage("Succesfully set sanitizeEffectTime to " + (long)value + ".");
                    }
                    else if (command.equals("sanitize2EffectTime"))
                    {
                        config.setSanitize2EffectTime((long)value);
                        sender.sendMessage("Succesfully set sanitize2EffectTime to " + (long)value + ".");
                    }
                    else if (command.equals("drugEffectTime"))
                    {
                        config.setDrugEffectTime((long)value);
                        sender.sendMessage("Succesfully set drugEffectTime to " + (long)value + ".");
                    }
                    else if (command.equals("milkEffectTime"))
                    {
                        config.setDrugEffectTime((long)value);
                        sender.sendMessage("Succesfully set milkEffectTime to " + (long)value + ".");
                    }
                    else if (command.equals("stageMinTime"))
                    {
                        config.setStageMinTime((long)value);
                        sender.sendMessage("Succesfully set stageMinTime to " + (long)value + ".");
                    }
                    else if (command.equals("stageMaxTime"))
                    {
                        config.setStageMaxTime((long)value);
                        sender.sendMessage("Succesfully set stageMaxTime to " + (long)value + ".");
                    }
                    else if (command.equals("baseDeathCoinLoss"))
                    {
                        config.setStageMaxTime((long)value);
                        sender.sendMessage("Succesfully set baseDeathCoinLoss to " + (int)value + ".");
                    }
                    else if (command.equals("percentageDeathCoinLoss"))
                    {
                        config.setStageMaxTime((long)value);
                        sender.sendMessage("Succesfully set percentageDeathCoinLoss to " + (double)value + ".");
                    }
                }
                else if (args[0].equals("get") && args.length == 2)
                {
                    String command = args[1];
                    if (command.equals("debugLevel"))
                    {
                        sender.sendMessage("debugLevel: " + (int)simulator.getDebugLevel());
                    }
                    else if (command.equals("debugPeriod"))
                    {
                        sender.sendMessage("debugPeriod: " + (int)simulator.getDebugPeriod());
                    }
                    else if (command.equals("baseHasChance"))
                    {
                        sender.sendMessage("baseHasChance: " + (double)config.getBaseHasChance());
                    }
                    else if (command.equals("baseHealChance"))
                    {
                        sender.sendMessage("baseHealChance: " + (double)config.getBaseHealChance());
                    }
                    else if (command.equals("maskSelfDecreaseChance"))
                    {
                        sender.sendMessage("maskSelfDecreaseChance: " + (double)config.getMaskSelfDecreaseChance());
                    }
                    else if (command.equals("maskOtherDecreaseChance"))
                    {
                        sender.sendMessage("maskOtherDecreaseChance: " + (double)config.getMaskOtherDecreaseChance());
                    }
                    else if (command.equals("indoorIncreaseChance"))
                    {
                        sender.sendMessage("indoorIncreaseChance: " + (double)config.getIndoorIncreaseChance());
                    }
                    else if (command.equals("nearbyIncreaseChance"))
                    {
                        sender.sendMessage("nearbyIncreaseChance: " + (double)config.getNearbyIncreaseChance());
                    }
                    else if (command.equals("nearbyInfectedIncreaseChance"))
                    {
                        sender.sendMessage("nearbyInfectedIncreaseChance: " + (double)config.getNearbyInfectedIncreaseChance());
                    }
                    else if (command.equals("nearbyRadius"))
                    {
                        sender.sendMessage("nearbyRadius: " + (double)config.getNearbyRadius());
                    }
                    else if (command.equals("notWashIncreaseChance"))
                    {
                        sender.sendMessage("notWashIncreaseChance: " + (double)config.getNotWashIncreaseChance());
                    }
                    else if (command.equals("soapDecreaseChance"))
                    {
                        sender.sendMessage("soapDecreaseChance: " + (long)config.getSoapDecreaseChance());
                    }
                    else if (command.equals("sanitizeDecreaseChance"))
                    {
                        sender.sendMessage("sanitizeDecreaseChance: " + (double)config.getSanitizeDecreaseChance());
                    }
                    else if (command.equals("sanitize2DecreaseChance"))
                    {
                        sender.sendMessage("sanitize2DecreaseChance: " + (double)config.getSanitize2DecreaseChance());
                    }
                    else if (command.equals("drugHealIncreaseChance"))
                    {
                        sender.sendMessage("drugHealIncreaseChance: " + (double)config.getDrugHealIncreaseChance());
                    }
                    else if (command.equals("immuneDecreaseChance"))
                    {
                        sender.sendMessage("immuneDecreaseChance: " + (double)config.getImmuneDecreaseChance());
                    }
                    else if (command.equals("stageChance"))
                    {
                        sender.sendMessage("stageChance: " + (double)config.getStageChance());
                    }
                    else if (command.equals("fatalChance"))
                    {
                        sender.sendMessage("fatalChance: " + (double)config.getFatalChance());
                    }
                    else if (command.equals("washEffectTime"))
                    {
                        sender.sendMessage("washEffectTime: " + (long)config.getWashEffectTime());
                    }
                    else if (command.equals("soapEffectTime"))
                    {
                        sender.sendMessage("soapEffectTime: " + (long)config.getSoapEffectTime());
                    }
                    else if (command.equals("washEffectTime"))
                    {
                        sender.sendMessage("washEffectTime: " + (long)config.getWashEffectTime());
                    }
                    else if (command.equals("sanitizeEffectTime"))
                    {
                        sender.sendMessage("sanitizeEffectTime: " + (long)config.getSanitizeEffectTime());
                    }
                    else if (command.equals("sanitize2EffectTime"))
                    {
                        sender.sendMessage("sanitize2EffectTime: " + (long)config.getSanitize2EffectTime());
                    }
                    else if (command.equals("drugEffectTime"))
                    {
                        sender.sendMessage("drugEffectTime: " + (long)config.getDrugEffectTime());
                    }
                    else if (command.equals("milkEffectTime"))
                    {
                        sender.sendMessage("milkEffectTime: " + (long)config.getMilkEffectTime());
                    }
                    else if (command.equals("stageMinTime"))
                    {
                        sender.sendMessage("stageMinTime: " + (long)config.getStageMinTime());
                    }
                    else if (command.equals("stageMaxTime"))
                    {
                        sender.sendMessage("stageMaxTime: " + (long)config.getStageMaxTime());
                    }
                    else if (command.equals("baseDeathCoinLoss"))
                    {
                        sender.sendMessage("baseDeathCoinLoss: " + (int)config.getBaseDeathCoinLoss());
                    }
                    else if (command.equals("percentageDeathCoinLoss"))
                    {
                        sender.sendMessage("percentageDeathCoinLoss: " + (double)config.getPercentageDeathCoinLoss());
                    }
                }
                
                return true;
            }
            return false;
	    }
        
        else if(cmd.getName().equalsIgnoreCase("shop"))
        {
            if (!simulator.getEnabled())
            {
                if (sender instanceof Player)
                {
                    sender.sendMessage("The shop commands can only be run while SDS is running! Try `/sds start` first.");
                }
                return false;
            }
            if (args.length == 1)
            {
                if (args[0].equals("balance"))
                {
                    if (sender instanceof Player)
                    {
                        Player senderPlayer = (Player)sender;
                        sender.sendMessage("Your balance is " + simulator.getSdsPlayer(senderPlayer.getUniqueId()).getBalance() + " coins.");
                    }
                    
                }
                else if (args[0].equals("help"))
                {
                    sender.sendMessage("§5[SDS]§6 SDS Help" + 
                    "\n§5[SDS]§6 You have 2 basic goals." +
                    "\n§5[SDS]§6 > Try not to catch covid" +
                    "\n§5[SDS]§6 > Earn more money by selling items" + 
                    "\n§5[SDS]§6 Try other help commands to learn more about specific mechanics.");
                }
            }
            if (args.length == 2)
            {
                if(args[0].equals("buy"))
                {
                    String shop = args[1];
                    if (shop.equals("pharmacy"))
                    {
                        if (sender instanceof Player)
                        {
                            PharmacyGui gui = new PharmacyGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                        else
                        {
                            return false;
                        }
                    }
                    if (shop.equals("general_store"))
                    {
                        if (sender instanceof Player)
                        {
                            GeneralStoreGui gui = new GeneralStoreGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                        else
                        {
                            return false;
                        }
                    }
                    return true;
                }
                else if (args[0].equals("sell"))
                {
                    String shop = args[1];
                    if (shop.equals("acacia") || shop.equals("dark_oak"))
                    {
                        if (sender instanceof Player)
                        {
                            AcaciaDarkOakGui gui = new AcaciaDarkOakGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("birch") || shop.equals("jungle"))
                    {
                        if (sender instanceof Player)
                        {
                            BirchJungleGui gui = new BirchJungleGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("carrot") || shop.equals("melon"))
                    {
                        if (sender instanceof Player)
                        {
                            CarrotMelonGui gui = new CarrotMelonGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("coal"))
                    {
                        if (sender instanceof Player)
                        {
                            CoalCoalBlockGui gui = new CoalCoalBlockGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("copper"))
                    {
                        if (sender instanceof Player)
                        {
                            CopperCopperBlockGui gui = new CopperCopperBlockGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("diamond"))
                    {
                        if (sender instanceof Player)
                        {
                            DiamondDiamondBlockGui gui = new DiamondDiamondBlockGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("emerald"))
                    {
                        if (sender instanceof Player)
                        {
                            EmeraldEmeraldBlockGui gui = new EmeraldEmeraldBlockGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("iron") || shop.equals("gold"))
                    {
                        if (sender instanceof Player)
                        {
                            IronGoldGui gui = new IronGoldGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("lapis") || shop.equals("redstone"))
                    {
                        if (sender instanceof Player)
                        {
                            LapisRedstoneGui gui = new LapisRedstoneGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("netherite"))
                    {
                        if (sender instanceof Player)
                        {
                            NetheriteScrapNetheriteGui gui = new NetheriteScrapNetheriteGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("pumpkin") || shop.equals("berry"))
                    {
                        if (sender instanceof Player)
                        {
                            PumpkinBerryGui gui = new PumpkinBerryGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("spruce") || shop.equals("oak"))
                    {
                        if (sender instanceof Player)
                        {
                            SpruceOakGui gui = new SpruceOakGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }

                    if (shop.equals("wheat") || shop.equals("potato"))
                    {
                        if (sender instanceof Player)
                        {
                            WheatPotatoGui gui = new WheatPotatoGui((Player)sender, simulator);
                            gui.setupGui();
                            gui.showGui();
                            simulator.addGui(gui.getChestGui());
                        }
                    }
                    
                }
                else if (args[0].equals("help"))
                {
                    String command = args[1];
                    if (command.equals("basic"))
                    {
                        sender.sendMessage("§5[SDS]§6 Basic information about SDS" + 
                        "\n§5[SDS]§6 You have 2 basic goals." +
                        "\n§5[SDS]§6 > Try not to catch covid" +
                        "\n§5[SDS]§6 > Earn more money by selling items" + 
                        "\n§5[SDS]§6 Try other help commands to learn more about specific mechanics.");
                    }
                    if (command.equals("death"))
                    {
                        sender.sendMessage("§5[SDS]§6 About dying in SDS" + 
                        "\n§5[SDS]§6 When you die, there are 2 things that can happen." +
                        "\n§5[SDS]§6 1. You have more money than the cost of revival" +
                        "\n§5[SDS]§6 You pay the cost of revival, and lose a certain percentage of your balance." + 
                        "\n§5[SDS]§6 KeepInventory and KeepLevel depends on the server setting." +
                        "\n§5[SDS]§6 2. You don't have enough money for revival" + 
                        "\n§5[SDS]§6 Your balance is set to zero, and your inventory + levels is cleared." +
                        "\n§5[SDS]§6 (Revival cost and percentage lost can be set by an admin using /sds set)");
                    }
                    if (command.equals("shop"))
                    {
                        sender.sendMessage("§5[SDS]§6 About the SDS shops" + 
                        "\n§5[SDS]§6 You can do /shop buy or /shop sell (+ name) to buy and sell items." +
                        "\n§5[SDS]§6 1. /shop buy [shopname]" +
                        "\n§5[SDS]§6 You can choose the pharmacy and the generalstore." + 
                        "\n§5[SDS]§6 They sell items to reduce the chance of getting covid (as well as the cure!!!)" +
                        "\n§5[SDS]§6 2. /shop sell [item]" + 
                        "\n§5[SDS]§6 You can sell basic resources to earn money." +
                        "\n§5[SDS]§6 3. /shop balance" +
                        "\n§5[SDS]§6 This command displays your current balance/");
                    }
                    if (command.equals("admin"))
                    {
                        sender.sendMessage("§5[SDS]§6 Administrator commands in SDS" + 
                        "\n§5[SDS]§6 /sds start + /sds end" +
                        "\n§5[SDS]§6 Pretty self explanatory, starts or ends the simulation. All data will be saved." +
                        "\n§5[SDS]§6 /sds reset" + 
                        "\n§5[SDS]§6 Steps to reset the database. Follow the displayed steps to confirm." +
                        "\n§5[SDS]§6 /sds set" + 
                        "\n§5[SDS]§6 Changes config values in SDS." +
                        "\n§5[SDS]§6 /sds get" + 
                        "\n§5[SDS]§6 Gets current set config values in SDS." +
                        "\n§5[SDS]§6 /sds save" + 
                        "\n§5[SDS]§6 Forcefully saves the current simulation data" +
                        "\n§5[SDS]§6 (These commands require the SDS permissions.)");
                    }
                    if (command.equals("infection"))
                    {
                        sender.sendMessage("§5[SDS]§6 what-to-do-and-not-to-do-in-SDS" + 
                        "\n§5[SDS]§6 1. Being close to other people, especially if they don't have a mask or are infected." +
                        "\n§5[SDS]§6 2. Staying indoors. Go touch some grass" +
                        "\n§5[SDS]§6 3. NOT washing your hands is a no-no. Use soap and rub with water." + 
                        "\n§5[SDS]§6 4. NOT using hand sanitizer is also bad. Buy these from the shop" +
                        "\n§5[SDS]§6 5. NOT wearing a mask is cringe. Buy this." + 
                        "\n§5[SDS]§6 6. Milk and apples can give some special effects ;)" +
                        "\n§5[SDS]§6 7. There may be more things that affect your chances.");
                    }
                }
                else
                {
                    return false;
                }
            }
            return false;
        } 
        return false;
    }
    
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        List<String> list = new ArrayList<String>();
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("sds")) 
            {
                if (args.length == 0) 
                {
                    list.add("start");
                    list.add("end");
                    list.add("save");
                    list.add("reset");
                    list.add("set");
                    list.add("get");
                    return list;
                } 
                else if (args.length == 1)  
                {
                    list.add("start");
                    list.add("end");
                    list.add("save");
                    list.add("reset");
                    list.add("set");
                    list.add("get");
                    Iterator<String> iter = list.iterator();
                    while (iter.hasNext()){
                        String next = iter.next();
                        if (!next.toLowerCase().startsWith(args[0].toLowerCase())){
                            iter.remove();
                        }
                    }
                    return list;
                } 
                else if (args.length == 2 && args[0].equals("set")) 
                {
                    list.add("debugLevel");
                    list.add("debugPeriod");
                    list.add("baseHasChance");
                    list.add("baseHealChance");
                    list.add("maskSelfDecreaseChance");
                    list.add("maskOtherDecreaseChance");
                    list.add("indoorIncreaseChance");
                    list.add("nearbyIncreaseChance");
                    list.add("nearbyInfectedIncreaseChance");
                    list.add("nearbyRadius");
                    list.add("notWashIncreaseChance");
                    list.add("sanitizeDecreaseChance");
                    list.add("soapDecreaseChance");
                    list.add("sanitizeDecreaseChance");
                    list.add("sanitize2DecreaseChance");
                    list.add("drugHealIncreaseChance");
                    list.add("immuneDecreaseChance");
                    list.add("stageChance");
                    list.add("fatalChance");
                    list.add("washEffectTime");
                    list.add("soapEffectTime");
                    list.add("sanitizeEffectTime");
                    list.add("sanitize2EffectTime");
                    list.add("drugEffectTime");
                    list.add("milkEffectTime");
                    list.add("stageMinTime");
                    list.add("stageMaxTime");
                    list.add("baseDeathCoinLoss");
                    list.add("percentageDeathCoinLoss");
                    if (args[1].length() > 0)
                    {
                        Iterator<String> iter = list.iterator();
                        while (iter.hasNext()){
                            String next = iter.next();
                            if (!next.toLowerCase().startsWith(args[1].toLowerCase())){
                                iter.remove();
                            }
                        }
                    }
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("debugLevel"))
                {
                    list.add("<debug level - integer (0+)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("debugPeriod"))  
                {
                    list.add("<debug period - integer (0+)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("baseHasChance")) 
                {
                    list.add("<base has chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("baseHealChance")) 
                {
                    list.add("<base heal chance - decimal (%)>");
                    return list;
                }
                 else if (args.length == 3 && args[0].equals("set") && args[1].equals("maskSelfDecreaseChance"))  
                {
                    list.add("<mask self decrease chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("maskOtherDecreaseChance"))
                {
                    list.add("<mask other decrease chance - decimal  (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("indoorIncreaseChance"))
                {
                    list.add("<indoor increase chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("nearbyIncreaseChance"))  
                {
                    list.add("<nearby increase chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("nearbyInfectedIncreaseChance"))  
                {
                    list.add("<nearby infected increase chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("nearbyRadius"))
                {
                    list.add("<nearby radius - integer (0+ blocks) >");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("notWashIncreaseChance")) 
                {
                    list.add("<not wash increase chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("sanitizeDecreaseChance")) 
                {
                    list.add("<sanitize decrease chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("sanitize2DecreaseChance"))
                {
                    list.add("<sanitize (second effect) decrease chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("drugHealIncreaseChance")) 
                {
                    list.add("<drug heal increase chance - decimal (%)>");
                    return list;
                }
                 else if (args.length == 3 && args[0].equals("set") && args[1].equals("immuneDecreaseChance")) 
                {
                    list.add("<immune decrease chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("stageChance"))
                {
                    list.add("<stage chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("fatalChance"))
                {
                    list.add("<fatal chance - decimal (%)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("washEffectTime"))
                {
                    list.add("<wash effect time - integer (0+ ticks)>");
                    return list;
                }
                 else if (args.length == 3 && args[0].equals("set") && args[1].equals("soapEffectTime")) 
                {
                    list.add("<soap effect time - integer (0+ ticks)>");
                    return list;
                }
                 else if (args.length == 3 && args[0].equals("set") && args[1].equals("sanitizeEffectTime")) 
                {
                    list.add("<sanitize effect time - integer (0+ ticks)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("sanitize2EffectTime"))  
                {
                    list.add("<sanitize (second effect) effect time - integer (0+ ticks)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("drugEffectTime"))
                {
                    list.add("<drug effect time - integer (0+ ticks)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("milkEffectTime"))
                {
                    list.add("<milk effect time - integer (0+ ticks)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("stageMinTime")) 
                {
                    list.add("<stage min time - integer (0+ ticks)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("stageMaxTime")) 
                {
                    list.add("<stage max time - integer (0+ ticks)>");
                    return list;
                }
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("baseDeathCoinLoss")) 
                {
                    list.add("<base death coin loss - integer (>0)>");
                    return list;
                } 
                else if (args.length == 3 && args[0].equals("set") && args[1].equals("percentageDeathCoinLoss")) 
                {
                    list.add("<percentage death coin loss - decimal (%)>");
                    return list;
                } 
                else if (args.length == 2 && args[0].equals("get"))  
                {
                    list.add("debugLevel");
                    list.add("debugPeriod");
                    list.add("baseHasChance");
                    list.add("baseHealChance");
                    list.add("maskSelfDecreaseChance");
                    list.add("maskOtherDecreaseChance");
                    list.add("indoorIncreaseChance");
                    list.add("nearbyIncreaseChance");
                    list.add("nearbyInfectedIncreaseChance");
                    list.add("nearbyRadius");
                    list.add("notWashIncreaseChance");
                    list.add("sanitizeDecreaseChance");
                    list.add("soapDecreaseChance");
                    list.add("sanitizeDecreaseChance");
                    list.add("sanitize2DecreaseChance");
                    list.add("drugHealIncreaseChance");
                    list.add("immuneDecreaseChance");
                    list.add("stageChance");
                    list.add("fatalChance");
                    list.add("washEffectTime");
                    list.add("soapEffectTime");
                    list.add("sanitizeEffectTime");
                    list.add("milkEffectTime");
                    list.add("sanitize2EffectTime");
                    list.add("drugEffectTime");
                    list.add("stageMinTime");
                    list.add("stageMaxTime");
                    list.add("baseDeathCoinLoss");
                    list.add("percentageDeathCoinLoss");
                    if (args[1].length() > 0)
                    {
                        Iterator<String> iter = list.iterator();
                        while (iter.hasNext())
                        {
                            String next = iter.next();
                            if (!next.toLowerCase().startsWith(args[1].toLowerCase()))
                            {
                                iter.remove();
                            }
                        }
                    }
                    return list;
                }
            }
            else if (cmd.getName().equalsIgnoreCase("shop"))
            {
                if (args.length == 0) 
                {
                    list.add("buy");
                    list.add("sell");
                    list.add("help");
                    return list;
                }
                else if (args.length == 1)  
                {
                    list.add("buy");
                    list.add("sell");
                    list.add("help");
                    Iterator<String> iter = list.iterator();
                    while (iter.hasNext()){
                        String next = iter.next();
                        if (!next.toLowerCase().startsWith(args[0].toLowerCase())){
                            iter.remove();
                        }
                    }
                    return list;
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("buy"))
                {
                    list.add("pharmacy");
                    list.add("general_store");
                    if (args[1].length() > 0)
                    {
                        Iterator<String> iter = list.iterator();
                        while (iter.hasNext()){
                            String next = iter.next();
                            if (!next.toLowerCase().startsWith(args[1].toLowerCase())){
                                iter.remove();
                            }
                        }
                    }
                    return list;
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("sell"))
                {
                    list.add("acacia");
                    list.add("dark_oak");
                    list.add("birch");
                    list.add("jungle");
                    list.add("carrot");
                    list.add("melon");
                    list.add("coal");
                    list.add("copper");
                    list.add("diamond");
                    list.add("emerald");
                    list.add("iron");
                    list.add("gold");
                    list.add("lapis");
                    list.add("redstone");
                    list.add("netherite");
                    list.add("pumpkin");
                    list.add("berry");
                    list.add("spruce");
                    list.add("oak");
                    list.add("wheat");
                    list.add("potato");

                    if (args[1].length() > 0)
                    {
                        Iterator<String> iter = list.iterator();
                        while (iter.hasNext()){
                            String next = iter.next();
                            if (!next.toLowerCase().startsWith(args[1].toLowerCase())){
                                iter.remove();
                            }
                        }
                    }
                    return list;
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("help"))  
                {
                    list.add("basic");
                    list.add("death");
                    list.add("infection");
                    list.add("shop");
                    list.add("admin");
    
                    if (args[1].length() > 0)
                    {
                        Iterator<String> iter = list.iterator();
                        while (iter.hasNext())
                        {
                            String next = iter.next();
                            if (!next.toLowerCase().startsWith(args[1].toLowerCase()))
                            {
                                iter.remove();
                            }
                        }
                    }
                    return list;
                }
            }
        }
        return null;
    }
}
