package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

/*
    Created by Andre
    At 18:37 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class RenameListener implements Listener {

     public static Map<Player, Entity> renameMap = new HashMap<>();

     @EventHandler
     public void onChat(AsyncPlayerChatEvent event) {
          Player player = event.getPlayer();
          if (renameMap.containsKey(player)) {
               event.setCancelled(true);
               Entity entity = renameMap.get(player);
               entity.setCustomName(event.getMessage().replace("&", "§"));
          }
     }

     @EventHandler
     public void onQuit(PlayerQuitEvent event) {
          renameMap.remove(event.getPlayer());
     }
}
