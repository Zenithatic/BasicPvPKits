package me.zenithatic.basicpvpkits.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    // Declare variables
    private FileConfiguration pluginConfig;
    private boolean canDropitem;

    // Constructor
    public PlayerDropItemListener(FileConfiguration pluginConfig){
        this.pluginConfig = pluginConfig;
        canDropitem = pluginConfig.getBoolean("PreventPlayerFromDroppingKitItems");
    }

    // Listen for event
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        // Get player object
        Player player = e.getPlayer();

        // Check if dropping kit items is allowed
        if (canDropitem){
            return;
        }
        else{
            // Cancel dropping and notify player
            e.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You may not drop kit items.");
        }
    }
}
