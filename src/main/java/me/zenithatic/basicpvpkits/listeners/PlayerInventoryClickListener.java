package me.zenithatic.basicpvpkits.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.ArrayList;

public class PlayerInventoryClickListener implements Listener {

    // Declare variables
    FileConfiguration pluginConfig;
    boolean preventKitItemStore;

    // Constructor for the PreventPlayerFromStoringKitItemsInChests class
    public PlayerInventoryClickListener(FileConfiguration pluginConfig){
        this.pluginConfig = pluginConfig;
        this.preventKitItemStore = this.pluginConfig.getBoolean("PreventPlayerFromStoringKitItemsInChests");
    }

    // Listen for event
    @EventHandler
    public void onPlayerClickEvent(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        // Check if players cannot store items in plugin config file
        if (preventKitItemStore){
            // Go back if item does not exist
            if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null){
                return;
            }
            else{
                // Get item lore
                ArrayList<String> lore = (ArrayList<String>) e.getCurrentItem().getItemMeta().getLore();

                // Go back if no lore
                if (lore == null){
                    return;
                }

                // Check if item is a BasicKitPvPItem
                if (lore.get(0).equals("BasicPvPKitsItem") && e.getView().getTopInventory().getType() != InventoryType.CRAFTING) {
                    // Cancel action and notify player
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You may not move kit items into chests.");
                }
            }
        }
    }
}
