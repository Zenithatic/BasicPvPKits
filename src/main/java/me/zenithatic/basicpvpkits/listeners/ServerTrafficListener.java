package me.zenithatic.basicpvpkits.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class ServerTrafficListener implements Listener {

    // Declare variables
    private FileConfiguration pluginConfig;

    // Constructor for the ServerTrafficListener class
    public ServerTrafficListener(FileConfiguration pluginConfig){
        this.pluginConfig = pluginConfig;
    }

    // Player Join Event
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        // Get player object
        Player player = event.getPlayer();

        // Check if the custom welcome message is enabled in the plugin config file
        if (pluginConfig.getBoolean("CustomWelcome")){
            String serverName = pluginConfig.getString("ServerName");
            event.setJoinMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET + "" + ChatColor.YELLOW + "" + " has joined " + serverName + "!");
        }

        // Check if the custom welcome message to new players is enabled in config file
        if (pluginConfig.getBoolean("NewPlayerWelcome")){
            // Send colored private message to player if the player is new
            if (player.hasPlayedBefore()){
                return;
            }
            else{
                player.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "" + ChatColor.BOLD + "Hi, welcome to your first time on the server!");
            }
        }
    }

    // Player Quit Event
    @EventHandler()
    public void onPlayerQuit(PlayerQuitEvent event){
        // Check if the custom goodbye message is enabled in the plugin config file
        if (pluginConfig.getBoolean("CustomGoodbye")){
            event.setQuitMessage(ChatColor.YELLOW + "Goodbye, " + ChatColor.BOLD + event.getPlayer().getDisplayName() + ChatColor.RESET + "" + ChatColor.YELLOW + "!");
        }
    }
}
