package me.zenithatic.basicpvpkits;

import me.zenithatic.basicpvpkits.listeners.ServerTrafficListener;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasicPvPKits extends JavaPlugin implements Listener {

    // Plugin startup logic
    @Override
    public void onEnable() {
        // Indicate that plugin is ready
        Bukkit.getLogger().info("BasicPvPKits Loaded!");

        // Register event handlers for the plugin
        this.getServer().getPluginManager().registerEvents(new ServerTrafficListener(), this);
    }

    // Plugin shutdown logic
    @Override
    public void onDisable() {
        // Indicate that plugin is unloaded
        Bukkit.getLogger().info("BasicPvPKits Unloaded!");
    }

}
