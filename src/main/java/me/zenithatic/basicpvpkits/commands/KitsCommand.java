package me.zenithatic.basicpvpkits.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class KitsCommand implements CommandExecutor {

    // Declare variables
    private FileConfiguration pluginConfig;
    private String kitString = "AxeKit, SwordKit";

    // Constructor for the KitsCommand class
    public KitsCommand(FileConfiguration pluginConfig){
        this.pluginConfig = pluginConfig;
    }

    // Listen for command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if kits are enabled in plugin config
        if (pluginConfig.getBoolean("EnableKits")){
            // Check if sender is valid player
            if (sender instanceof Player){
                Player player = (Player) sender;

                // Send player list of kits
                player.sendMessage(ChatColor.DARK_GREEN + kitString);
            }
            else if (sender instanceof ConsoleCommandSender){
                Bukkit.getLogger().info(kitString);
            }
            // Notify that this command cannot be run by command block
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
