package me.zenithatic.basicpvpkits;

import org.bukkit.plugin.java.JavaPlugin;

public final class BasicPvPKits extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("BasicPvPKits Loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("BasicPvPKits Unloaded!");
    }
}
