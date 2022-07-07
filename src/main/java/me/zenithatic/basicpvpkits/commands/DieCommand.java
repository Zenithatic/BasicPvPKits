package me.zenithatic.basicpvpkits.commands;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor {

    // Declare variables
    private FileConfiguration pluginConfig;

    // Constructor for the DieCommand class
    public DieCommand(FileConfiguration pluginConfig){
        this.pluginConfig = pluginConfig;
    }

    // Listen for command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if Die command is enabled in the config file
        if (pluginConfig.getBoolean("EnableDieCommand")){
            // Check if sender is valid player
            if (sender instanceof Player){
                // Kill player and notify player
                Player p = (Player) sender;
                p.setHealth(0.0d);
                p.sendMessage(ChatColor.RED + "You have died.");
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
