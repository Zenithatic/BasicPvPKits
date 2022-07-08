package me.zenithatic.basicpvpkits.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDropItemListener implements Listener {

    // Declare variables
    private FileConfiguration pluginConfig;
    private boolean disabledItemDropping;

    // Constructor
    public PlayerDropItemListener(FileConfiguration pluginConfig){
        this.pluginConfig = pluginConfig;
        disabledItemDropping = this.pluginConfig.getBoolean("PreventPlayerFromDroppingKitItems");
    }

    // Listen for event
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        // Get player object
        Player player = e.getPlayer();

        // Check if dropping kit items is allowed
        if (disabledItemDropping){
            // Cancel dropping if item is a kit item and notify player
            ItemStack item = e.getItemDrop().getItemStack();

            if (item.getItemMeta().getLore() == null){
                // go back if no lore found
                return;
            }
            else if (item.getItemMeta().getLore().size() != 0 && item.getItemMeta().getLore().get(0).equals("BasicKitPvPItem")){
                // Cancel dropping
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You may not drop kit items.");
            }
        }
        else{
            return;
        }
    }
}
