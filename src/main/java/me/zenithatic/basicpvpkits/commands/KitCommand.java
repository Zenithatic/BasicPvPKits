package me.zenithatic.basicpvpkits.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class KitCommand implements CommandExecutor {

    // Declare variables
    private FileConfiguration pluginConfig;

    // Constructor for the KitCommand class
    public KitCommand(FileConfiguration pluginConfig){
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

                // Check if 1 kit argument was provided
                if (args.length != 1){
                    player.sendMessage(ChatColor.RED + "Incorrect command usage!");
                    return false;
                }
                else{
                    // Get kit name
                    String kitName = args[0].toLowerCase();

                    // Give Axe Kit
                    if (kitName.equals("axekit")){
                        giveKit(player, "AxeKitItems", args[0]);
                    }
                    // Give Sword Kit
                    else if (kitName.equals("swordkit")){
                        giveKit(player, "SwordKitItems", args[0]);
                    }
                    // Give Bow kit
                    else if (kitName.equals("bowkit")){
                        giveKit(player, "BowKitItems", args[0]);
                    }
                    // Give Crossbow kit
                    else if (kitName.equals("crossbowkit")){
                        giveKit(player, "CrossbowKitItems", args[0]);
                    }
                    // Give Tank kit
                    else if (kitName.equals("tankkit")){
                        giveKit(player, "TankKitItems", args[0]);
                    }
                    // if no valid kit given
                    else{
                        player.sendMessage(ChatColor.RED + "No kit with given name found!");
                        return false;
                    }
                }
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

    public void giveKit(Player player, String configKitName, String kitName){
        // Get kit items from config.yml
        List<String> itemList = pluginConfig.getStringList(configKitName);

        // Loop through and give items
        for (int i = 0; i < itemList.size(); i++){
            // Get item name and amount (0 = name, 1 = amount)
            String[] itemData = itemList.get(i).split(" ");
            String itemName = itemData[0].toUpperCase();
            int itemAmount = Integer.parseInt(itemData[1]);

            // Create ItemStack
            ItemStack item = new ItemStack(Material.getMaterial(itemName), itemAmount);

            // Check for custom enchants and apply
            if (itemData.length >= 3){
                for (int j = 2; j < itemData.length; j++){
                    // Get enchant data
                    String[] data = itemData[j].split("-");
                    String enchantName = data[0];
                    int enchantAmplifier = Integer.parseInt(data[1]);

                    // Apply enchants
                    ItemMeta meta = item.getItemMeta();
                    // true means bypass the enchantment limit in vanilla, false means follow the enchantment limit
                    meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchantName)), enchantAmplifier, true);
                    item.setItemMeta(meta);
                }
            }

            // Give player item
            player.getInventory().addItem(item);
        }

        // Notify player that they have received kit
        player.sendMessage(ChatColor.DARK_GREEN + "You have received kit " + kitName + "!");
    }
}
