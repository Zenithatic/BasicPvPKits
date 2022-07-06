package me.zenithatic.basicpvpkits.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

public class ServerTrafficListener implements Listener {
    // Player Join Event
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        // Get player object
        Player player = event.getPlayer();

        // Change default join message to custom message
        event.setJoinMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET + "" + ChatColor.YELLOW + "" + " has joined the server!");

        // Send colored private message to player if new player
        if (player.hasPlayedBefore()){
            return;
        }
        else{
            player.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "" + ChatColor.BOLD + "Hi, welcome to your first time on the server!");
        }
    }

    // Player Quit Event
    @EventHandler()
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage(ChatColor.YELLOW + "Goodbye, " + ChatColor.BOLD + event.getPlayer().getDisplayName() + ChatColor.RESET + "" + ChatColor.YELLOW + "!");
    }
}
