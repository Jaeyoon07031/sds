package com.tokyocodingclub.jaeyoon.sds;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.EntityPickupItemEvent;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.tokyocodingclub.jaeyoon.sds.Simulator;

import java.util.List;
import java.util.ArrayList;


//1 in-game second is 13.9 milliseconds

//12,950 milliseconds is 3min
//21,580 milliseconds is 5min


public class Main extends JavaPlugin implements Listener
{
    Simulator simulator;

    public Main()
    {
        simulator = new Simulator(this);
    }

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
        simulator.onEnable();
    }

    @Override
    public void onDisable()
    {
       simulator.onDisable();
    }

    @EventHandler
    public void onJoin (PlayerJoinEvent event)
    {
        simulator.onJoin(event);
    }

    @EventHandler
    public void onLeave (PlayerQuitEvent event)
    {
        simulator.onLeave(event);
    }

    @EventHandler
    public void onPickup (EntityPickupItemEvent event)
    {
        simulator.onPickup(event);
    }

    @EventHandler
    public void onConsume (PlayerItemConsumeEvent event)
    {
        simulator.onConsume(event);
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event)
    {
        simulator.onInventoryInteract(event);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event)
    {
        simulator.onInventoryClose(event);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        simulator.onPlayerInteract(event);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        simulator.onPlayerDeath(event);
    }

    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
    {
        return simulator.getCommands().onCommand(sender, cmd, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) 
    {
        return simulator.getCommands().onTabComplete(sender, cmd, commandLabel, args);
    }

}
