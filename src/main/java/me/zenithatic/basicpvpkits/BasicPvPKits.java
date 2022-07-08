package me.zenithatic.basicpvpkits;

import me.zenithatic.basicpvpkits.commands.DieCommand;
import me.zenithatic.basicpvpkits.commands.EChestCommand;
import me.zenithatic.basicpvpkits.commands.KitCommand;
import me.zenithatic.basicpvpkits.commands.KitsCommand;
import me.zenithatic.basicpvpkits.listeners.KitRestriction;
import me.zenithatic.basicpvpkits.listeners.PlayerDropItemListener;
import me.zenithatic.basicpvpkits.listeners.ServerTrafficListener;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasicPvPKits extends JavaPlugin implements Listener {

    // Plugin startup logic
    @Override
    public void onEnable() {
        // Indicate that plugin is ready
        Bukkit.getLogger().info("BasicPvPKits Loaded!");

        // Load up config
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Register event handlers for the plugin
        this.getServer().getPluginManager().registerEvents(new ServerTrafficListener(this.getConfig()), this);
        this.getServer().getPluginManager().registerEvents(new KitRestriction(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDropItemListener(this.getConfig()), this);

        // Register commands
        this.getCommand("die").setExecutor(new DieCommand(this.getConfig()));
        this.getCommand("echest").setExecutor(new EChestCommand(this.getConfig()));
        this.getCommand("kit").setExecutor(new KitCommand(this.getConfig()));
        this.getCommand("kits").setExecutor(new KitsCommand(this.getConfig()));
    }

    // Plugin shutdown logic
    @Override
    public void onDisable() {
        // Indicate that plugin is unloaded
        Bukkit.getLogger().info("BasicPvPKits Unloaded!");
    }
}
