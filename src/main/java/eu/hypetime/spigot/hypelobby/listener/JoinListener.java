package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/*
    Created by Andre
    At 23:30 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

public class JoinListener implements Listener {

     @EventHandler
     public void onJoin(PlayerJoinEvent event) {
          Player player = event.getPlayer();
          event.setJoinMessage(null);
          for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
               ScoreAPI.setScoreboard(onlinePlayer);
          }
          Inventories.mainInventory(player);
          RewardAPI.createIfNotExist(player);

          player.setGameMode(GameMode.SURVIVAL);
          player.teleport(WarpAPI.getLocation("Spawn"));
          player.sendTitle("§aWillkommen!", "§aauf §6§lHypeTime", 20, 40, 20);

          StatsManager.initPlayer(player);
     }

     @EventHandler
     public void onQuit(PlayerQuitEvent event) {
          event.setQuitMessage(null);
          if (HypeLobby.sp.getPlayerUUIDs().contains(event.getPlayer().getUniqueId())) {
               HypeLobby.sp.removePlayer(event.getPlayer());
          }
     }

}
