package com.tokyocodingclub.jaeyoon.sds;

import com.tokyocodingclub.jaeyoon.sds.config.Config;
import com.tokyocodingclub.jaeyoon.sds.database.Database;
import com.tokyocodingclub.jaeyoon.sds.gui.ChestGui;
import com.tokyocodingclub.jaeyoon.sds.commands.Commands;
import com.tokyocodingclub.jaeyoon.sds.Main;
import com.tokyocodingclub.jaeyoon.sds.infection.*;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Location;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class Simulator
{
    private World world;
    private Config config;
    private Database database;
    private Commands commands;
    private ConcurrentHashMap<UUID, Player> playerUUIDs;
    private int debugLevel;
    private int debugPeriod;
    private int debugTick;
    private boolean enabled;
    private Main plugin;
    private BukkitTask infectionTask;
    //private Infection infection; QUALITY OF LIFE: Change to using ArrayList of infection
    private List<Infection> statuses;
    private List<ChestGui> guis;
    private AteApple ateApple;
    private HasMask hasMask;
    private ImmuneDecrease immuneDecrease;
    private InfectHeal infectHeal;
    private InfectionEffects infectionEffects;
    private IsIndoors isIndoors;
    private IsNearby isNearby;
    private IsSanitized isSanitized;
    private IsSanitized2 isSanitized2;
    private IsWashed isWashed;
    private StageAdvance stageAdvance;
    private UsedDrug usedDrug;
    private UsedSoap usedSoap;

    public Simulator(Main plugin)
    {
        debugLevel = 0;
        debugPeriod = 10;
        enabled = false;
        this.plugin = plugin;
        playerUUIDs = new ConcurrentHashMap<UUID, Player>();
        statuses = new ArrayList<Infection>();
        guis = new ArrayList<ChestGui>();
    }

    public void onEnable()
    {
        world = plugin.getServer().getWorlds().get(0);
        config = new Config(plugin);
        database = new Database(this);
        commands = new Commands(this);
        ateApple = new AteApple(this);
        hasMask = new HasMask(this);
        immuneDecrease = new ImmuneDecrease(this);
        infectHeal = new InfectHeal(this);
        infectionEffects = new InfectionEffects(this);
        isIndoors = new IsIndoors(this);
        isNearby = new IsNearby(this);
        isSanitized = new IsSanitized(this);
        isSanitized2 = new IsSanitized2(this);
        isWashed = new IsWashed(this);
        stageAdvance = new StageAdvance(this);
        usedDrug = new UsedDrug(this);
        usedSoap = new UsedSoap(this);

        statuses.add(ateApple);
        statuses.add(hasMask);
        statuses.add(immuneDecrease);
        statuses.add(infectHeal);
        statuses.add(infectionEffects);
        statuses.add(isIndoors);
        statuses.add(isNearby);
        statuses.add(isSanitized);
        statuses.add(isSanitized2);
        statuses.add(isWashed);
        statuses.add(stageAdvance);
        statuses.add(usedDrug);
        statuses.add(usedSoap);
    }

    public World getWorld()
    {
        return world;
    }

    public Config getConfig()
    {
        return config;
    }

    // Debug Level:
    // 0 - Release
    // 1 - Basic Debug (debug messages only on status changes)
    // 2 - Lots of Debug (periodic debugs)
    // 3 - So much Debug, so little chatbox space (debug messages that loop through players)
    // 4 - Debug every second OWO

    public int getDebugLevel()
    {
        return debugLevel;
    }

    public void setDebugLevel(int changed)
    {
        debugLevel = changed;
    }

    public int getDebugPeriod()
    {
        return debugPeriod;
    }

    public void setDebugPeriod(int changed)
    {
        debugPeriod = changed;
    }

    public int getDebugTick()
    {
        return debugTick;
    }

    public void setDebugTick(int changed)
    {
        debugTick = changed;
    }

    public Player getSdsPlayer(UUID uuid)
    {
        if (playerUUIDs.containsKey(uuid))
        {
            return playerUUIDs.get(uuid);
        }
        else
        {
            //TODO: log and make errors
            return null;
        }
    }

    public void addPlayer(Player player)
    {
        playerUUIDs.put(player.getSpigotPlayer().getUniqueId(), player);
    }

    public void removePlayer(Player player)
    {
        playerUUIDs.remove(player.getSpigotPlayer().getUniqueId());
    }

    public Enumeration<UUID> getPlayerListKeys()
    {
        return playerUUIDs.keys();
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    public Database getDatabase()
    {
        return database;
    }

    public Commands getCommands()
    {
        return commands;
    }
    
    public void onPickup (EntityPickupItemEvent event)
    {
        if (event.getEntity() instanceof org.bukkit.entity.Player)
        {
            if (event.getItem() == null)
            {
                return;
            }
            ItemStack item = event.getItem().getItemStack();
            if (item == null)
            {
                return;
            }
            if (debugLevel > 1)
            {
                org.bukkit.entity.Player player = (org.bukkit.entity.Player)event.getEntity();
                player.sendMessage("Item " + item.getType().getKey().getKey() + " Picked Up");
            }
            if (item.getItemMeta() == null)
            {
                return;
            }
            if (item.getType() == Material.CHAINMAIL_HELMET)
            {
                if (debugLevel > 0)
                {
                    org.bukkit.entity.Player player = (org.bukkit.entity.Player)event.getEntity();
                    player.sendMessage("Item is Chainmail Helmet");
                }
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("Mask");
                meta.setCustomModelData(1);
                item.setItemMeta(meta);
            }
            if (item.getType() == Material.APPLE)
            {
                if (debugLevel > 0)
                {
                    org.bukkit.entity.Player player = (org.bukkit.entity.Player)event.getEntity();
                    player.sendMessage("Item is Apple");
                }
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("An Apple A Day Keeps The Corona Away!");
                meta.setCustomModelData(2);
                item.setItemMeta(meta);
            }
        }
    }

    public void onConsume(PlayerItemConsumeEvent event)
    {
        org.bukkit.entity.Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 3)
        {
            getSdsPlayer(player.getUniqueId()).setLastSoapTime(world.getTime());
        }
        if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 2)
        {
            getSdsPlayer(player.getUniqueId()).setLastAppleTime(world.getTime());
        }
        if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 7)
        {
            getSdsPlayer(player.getUniqueId()).setLastMilkTime(world.getTime());
        }
    }

    public void startSim()
    {
        enabled = true;
        database.startDatabase();

        if (debugLevel > 0)
        {
            plugin.getServer().broadcastMessage("Corona is here");
        }

        Iterator<? extends org.bukkit.entity.Player> iter = plugin.getServer().getOnlinePlayers().iterator();
        while (iter.hasNext())
        {
            Player player = new Player(iter.next(), this);
            database.loadData(player);
            addPlayer(player);
            player.getSpigotPlayer().sendMessage("§5[SDS]§6 Social Distancing Simulator has been enabled!"
                + "\n§5[SDS]§6 This server is currently using Social Distancing Simulator"
                + "\n§5[SDS]§6 `SDS` is a plugin which simulates the coronavirus in Minecraft!"
                + "\n§5[SDS]§6 Mine and farm materials, then sell them using /shop sell [item]!"
                + "\n§5[SDS]§6 Then, use /shop buy [store] to purchase various items to protect yourself!"
                + "\n§5[SDS]§6 Using /shop balance will show you your balance."
                + "\n§5[SDS]§6 Many activities which increase the chance of you getting covid in real life are simulated here."
                + "\n§5[SDS]§6 Don't be a Karen, wear a mask, and follow common sense to stay alive!"
                + "\n§5[SDS]§6 The goal is to make as much money as possible without catching the coronavirus."
                +" \n§5[SDS]§6 Good luck!");
        }

        infectionTask = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable()
        {
            public void run()
            {
                Iterator<? extends org.bukkit.entity.Player> iter = plugin.getServer().getOnlinePlayers().iterator();
                while (iter.hasNext())
                {   
                    org.bukkit.entity.Player bukkitPlayer = iter.next();
                    Player player = getSdsPlayer(bukkitPlayer.getUniqueId());

                    if(player == null)
                    {
                        player = joinPlayer(bukkitPlayer);
                    }

                    player.setHasChance(config.getBaseHasChance());
                    player.setHealChance(config.getBaseHealChance());
                    
                    /*for(Infection status : statuses)
                    {
                        status.applyChances(player);
                    }*/

                    isWashed.applyChances(player);
                    isIndoors.applyChances(player);
                    isNearby.applyChances(player);
                    isWashed.applyChances(player);
                    usedSoap.applyChances(player);
                    isSanitized.applyChances(player);
                    isSanitized2.applyChances(player);
                    hasMask.applyChances(player);
                    ateApple.applyChances(player);
                    immuneDecrease.applyChances(player);
                    usedDrug.applyChances(player);

                    if (debugLevel > 1)
                    {
                        if (debugTick == 0)
                        {
                            player.getSpigotPlayer().sendMessage("Heal Chance: " + player.getHealChance() + "\nHas Chance: " + player.getHasChance());
                        }
                    }

                    infectHeal.infectHeal(player);
                    infectionEffects.applyEffects(player);
                    stageAdvance.applyChances(player);
                }

                if (debugLevel > 0)
                {
                    debugTick = (debugTick + 1) % debugPeriod;
                }

            }
        }, 0, 20 );
    }

    public void endSim()
    {
        if (debugLevel > 0)
        {
            if(enabled)
            {
                plugin.getServer().broadcastMessage("Corona is gone");
            }
        }
        if(enabled)
        {
            enabled = false;
            infectionTask.cancel();
            playerUUIDs = new ConcurrentHashMap<UUID, Player>();
        }
    }

    public void onDisable()
    {
        endSim();
        database.saveAllData();
        database.stopDatabase();
    }

    private Player joinPlayer(org.bukkit.entity.Player joinedPlayer)
    {

        Player sdsPlayer = new Player(joinedPlayer, this);
        boolean playerExists = database.loadData(sdsPlayer);
        addPlayer(sdsPlayer);
        if(playerExists)
        {
            Boolean infected = sdsPlayer.getHas();
            String infectedString = "not infected";
            if (infected)
            {
                infectedString = "infected";
            }
            
            joinedPlayer.sendMessage("§5[SDS]§6 Welcome Back!\n§5[SDS]§6 You are currently: " + infectedString + "\n§5[SDS]§6 Your balance is: " + sdsPlayer.getBalance() + " coins.");
        }
        else
        {
            joinedPlayer.sendMessage("§5[SDS]§6 Welcome to the Server!"
            + "\n§5[SDS]§6 This server is currently using Social Distancing Simulator"
            + "\n§5[SDS]§6 `SDS` is a plugin which simulates the coronavirus in Minecraft!"
            + "\n§5[SDS]§6 Mine and farm materials, then sell them using /shop sell [item]!"
            + "\n§5[SDS]§6 Then, use /shop buy [store] to purchase various items to protect yourself!"
            + "\n§5[SDS]§6 Using /shop balance will show you your balance."
            + "\n§5[SDS]§6 Many activities which increase the chance of you getting covid in real life are simulated here."
            + "\n§5[SDS]§6 Don't be a Karen, wear a mask, and follow common sense to stay alive!"
            + "\n§5[SDS]§6 The goal is to make as much money as possible without catching the coronavirus."
            +" \n§5[SDS]§6 Good luck!");
        }

        return sdsPlayer;
    }

    public void onJoin(PlayerJoinEvent event)
    {
        if (enabled)
        {
            event.setJoinMessage(null);
            joinPlayer(event.getPlayer());
        }
    }

    public void onLeave(PlayerQuitEvent event)
    {
        if (enabled)
        {
            event.setQuitMessage(null);

            Player sdsPlayer = getSdsPlayer(event.getPlayer().getUniqueId());
            database.saveData(sdsPlayer);
            removePlayer(sdsPlayer);
        }
        
    }

    // Custom Model Data
    // Mask 1
    // Apple 2
    // Soap 3
    // Hand Sanitizer 4
    // Good Hand Sanitizer 5
    // Cure 6
    // Milk 7

    public void addGui(ChestGui gui)
    {
        guis.add(gui);
    }
    
    public void onInventoryInteract(InventoryClickEvent event)
    {
        for (ChestGui gui : guis)
        {
            gui.onInventoryInteract(event);
        }
    }

    public void onInventoryClose(InventoryCloseEvent event)
    {
        Inventory inventory = event.getInventory();
        for (ChestGui gui : guis)
        {
            if (gui.isInventory(inventory))
            {
                guis.remove(gui);
                return;
            }
        }
    }

    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if (debugLevel > 2)
            {
                event.getPlayer().sendMessage("Player interacted, is right click");
            }
            if (event.getItem() == null)
            {
                return;
            }
            if (event.getItem().getType() == Material.SPLASH_POTION)
            {
                if (debugLevel > 2)
                {
                    event.getPlayer().sendMessage("Item is splash potion");
                    if (event.getItem().getItemMeta().hasCustomModelData())
                    {
                        event.getPlayer().sendMessage("Item Custom Model Data: " + event.getItem().getItemMeta().getCustomModelData());
                    }
                    else
                    {
                        event.getPlayer().sendMessage("Item has no custom model data");
                    }
                }
                if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasCustomModelData())
                {
                    if(event.getItem().getItemMeta().getCustomModelData() == 4)
                    {
                        getSdsPlayer(event.getPlayer().getUniqueId()).setLastSanitizeTime(world.getTime());
                    }
                }
            }
            if (event.getItem().getType() == Material.LINGERING_POTION)
            {
                if (debugLevel > 2)
                {
                    event.getPlayer().sendMessage("Item is lingering potion");
                    if (event.getItem().getItemMeta().hasCustomModelData())
                    {
                        event.getPlayer().sendMessage("Item Custom Model Data: " + event.getItem().getItemMeta().getCustomModelData());
                    }
                    else
                    {
                        event.getPlayer().sendMessage("Item has no custom model data");
                    }
                }
                if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasCustomModelData())
                {
                    if(event.getItem().getItemMeta().getCustomModelData() == 5)
                    {
                        getSdsPlayer(event.getPlayer().getUniqueId()).setLastSanitizeTime(world.getTime());
                        getSdsPlayer(event.getPlayer().getUniqueId()).setLastSanitize2Time(world.getTime());
                    }
                }
            }
            if (event.getItem().getType() == Material.TIPPED_ARROW)
            {
                if (debugLevel > 2)
                {
                    event.getPlayer().sendMessage("Item is tipped arrow");
                    if (event.getItem().getItemMeta().hasCustomModelData())
                    {
                        event.getPlayer().sendMessage("Item Custom Model Data: " + event.getItem().getItemMeta().getCustomModelData());
                    }
                    else
                    {
                        event.getPlayer().sendMessage("Item has no custom model data");
                    }
                }
                if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasCustomModelData())
                {
                    if(event.getItem().getItemMeta().getCustomModelData() == 6)
                    {
                        getSdsPlayer(event.getPlayer().getUniqueId()).setLastDrugTime(world.getTime());
                        if (event.getHand() == EquipmentSlot.HAND)
                        {
                            event.getPlayer().sendMessage("Hand: Main hand");
                            event.getPlayer().sendMessage("Amount: " + event.getPlayer().getInventory().getItemInMainHand().getAmount());
                            if (event.getPlayer().getInventory().getItemInMainHand().getAmount() == 1)
                            {
                                event.getPlayer().getInventory().setItemInMainHand(null);
                            }
                            else
                            {
                                ItemStack replace = new ItemStack(Material.TIPPED_ARROW);
                                ItemMeta replaceMeta = replace.getItemMeta();
                                replaceMeta.setDisplayName(ChatColor.RESET.toString() + "Anticovid Drugs");
                                replaceMeta.setCustomModelData(6);
                                replace.setItemMeta(replaceMeta);
                                replace.setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);

                            }
                        }

                        if (event.getHand() == EquipmentSlot.OFF_HAND)
                        {
                            event.getPlayer().sendMessage("Hand: Off Hand");
                            event.getPlayer().sendMessage("Amount: " + event.getPlayer().getInventory().getItemInOffHand().getAmount());
                            if (event.getPlayer().getInventory().getItemInOffHand().getAmount() == 1)
                            {
                                event.getPlayer().getInventory().setItemInOffHand(null);
                            }
                            else
                            {
                                ItemStack replace = new ItemStack(Material.TIPPED_ARROW);
                                ItemMeta replaceMeta = replace.getItemMeta();
                                replaceMeta.setDisplayName(ChatColor.RESET.toString() + "Anticovid Drugs");
                                replaceMeta.setCustomModelData(6);
                                replace.setItemMeta(replaceMeta);
                                replace.setAmount(event.getPlayer().getInventory().getItemInOffHand().getAmount() - 1);
                            }
                        }
                    }
                    
                }
            }
        }
    }

    public void onPlayerDeath(PlayerDeathEvent event)
    {
        event.setDeathMessage(null);
        
        if (enabled)
        {
            Player sdsPlayer = getSdsPlayer(event.getEntity().getUniqueId());
            int balance = sdsPlayer.getBalance();

            if (balance >= config.getBaseDeathCoinLoss())
            {
                sdsPlayer.setBalance((int)((balance - config.getBaseDeathCoinLoss()) * (1.0 - config.getPercentageDeathCoinLoss())));
                event.getEntity().sendMessage("§4You died and lost " + (balance - sdsPlayer.getBalance()) + " coins!");
                if (sdsPlayer.getHas())
                {
                    sdsPlayer.setInfectionStage(1);
                    event.getEntity().sendMessage("§cYou were also infected with covid, so you were sent to the hospital.");
                    event.getEntity().sendMessage("§cYour infection has been weakened, but not cured of.");
                }
                
            }
            else
            {
                sdsPlayer.setBalance(0);
                if (event.getKeepLevel())
                {
                    event.setKeepLevel(false);
                }
                event.getEntity().sendMessage("§4You died and lost " + balance + " coins!");
                if (sdsPlayer.getHas())
                {
                    if (event.getKeepInventory())
                    {
                        event.setKeepInventory(false);
                    }
                    Block highestBlock = world.getHighestBlockAt(471, -214);
                    Location location = highestBlock.getLocation();
                    location.setX(471.3);
                    location.setY(82.56250);
                    location.setZ(-214.3);
                    event.getEntity().setBedSpawnLocation(location);
                    event.getEntity().sendMessage("§cYou also had covid, but didn't have enough money to be cured.");
                    event.getEntity().sendMessage("§cYou lost your belongings, and was sent to the hosptial.");
                }
               
            }
        }
    }

    public Logger getLogger()
    {
        return plugin.getLogger();
    }

    public File getDataFolder()
    {
        return plugin.getDataFolder();
    }

}