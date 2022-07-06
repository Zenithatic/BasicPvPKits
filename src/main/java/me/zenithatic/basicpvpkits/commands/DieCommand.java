package me.zenithatic.basicpvpkits.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor {

    // Listen for command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if sender is valid player
        if (sender instanceof Player){
            // Kill player and notify player
            Player p = (Player) sender;
            p.setHealth(0.0d);
            p.sendMessage(ChatColor.RED + "You have died.");
        }
        else if (sender instanceof ConsoleCommandSender){
            Bukkit.getLogger().info("Console cannot execute this command!");
        }
        else if (sender instanceof BlockCommandSender){
            Bukkit.getLogger().info("Command block cannot execute this command!");
        }

        return true;
    }
}
