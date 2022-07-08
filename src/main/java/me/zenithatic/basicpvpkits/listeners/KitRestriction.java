package me.zenithatic.basicpvpkits.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.*;

public class KitRestriction implements Listener {

    // Create list of players on cooldown
    private static ArrayList<Player> cooldownList = new ArrayList<>();

    // Method to check if a player is on life cooldown
    public static boolean isOnCooldown(Player player){
        if (cooldownList.contains(player)){
            return true;
        }
        else{
            return false;
        }
    }

    // Method to add players to cooldown list
    public static void putOnCooldown(Player player){
        cooldownList.add(player);
    }

    // Listen for event
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        // Take player off cooldown
        if (cooldownList.contains(event.getEntity())){
            cooldownList.remove(event.getEntity());
        }
    }
}
