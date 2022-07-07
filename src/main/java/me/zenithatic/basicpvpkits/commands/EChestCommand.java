package me.zenithatic.basicpvpkits.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class EChestCommand implements CommandExecutor {

    // Declare variables
    private FileConfiguration pluginConfig;

    // Constructor for the EChestCommand class
    public EChestCommand(FileConfiguration pluginConfig){
        this.pluginConfig = pluginConfig;
    }

    // Listen for command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if echest command is enabled in plugin config
        if (pluginConfig.getBoolean("EnableEChestCommand")){
            // Check if sender is valid player
            if (sender instanceof Player){
                Player player = (Player) sender;

                // Open player echest
                Inventory echest = player.getEnderChest();
                player.openInventory(echest);
            }
            // Tell user that command is not executable from non player
            else if (sender instanceof ConsoleCommandSender){
                Bukkit.getLogger().info("Console cannot execute this command!");
            }
            else if (sender instanceof BlockCommandSender){
                Bukkit.getLogger().info("Command block cannot execute this command!");
            }
        }
        else{
            // Tell user that command is not available on the server
            Player p = (Player) sender;
            p.sendMessage(ChatColor.RED + "This command is not enabled on this server.");
        }

        return true;
    }
}
