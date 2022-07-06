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
        event.setJoinMessage("Welcome " + player.getDisplayName() + " to the server!");

        // Send colored private message to player
        player.sendMessage(ChatColor.YELLOW + "Hi, welcome to the server! If you need help with anything, contact the staff team.");
    }

    // Player Quit Event
    @EventHandler()
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage("Goodbye, " + event.getPlayer().getDisplayName() + "!");
    }
}
