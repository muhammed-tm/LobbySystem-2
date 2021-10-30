package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.commands.SitCommand;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.boots.BootsPlayer;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.PetsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticsManager;
import eu.hypetime.spigot.hypelobby.profile.manager.FriendManager;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
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
          event.joinMessage(null);
          Inventories.mainInventory(player);
          RewardAPI.createIfNotExist(player);
          StatsManager.updatePlayer(player);
          CosmeticsManager.createPlayer(player);
          //HypeLobby.getInstance().getFriendManager().loadPlayer(player.getUniqueId(), player.getName());

          player.setGameMode(GameMode.SURVIVAL);
          player.teleport(WarpAPI.getLocation("Spawn"));
          //activate when halloween is over  player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2F, 1F);
          BlindScare.updatePlayer(player); //for halloween
          for (Player onlinePlayer : Bukkit.getOnlinePlayers())
               ScoreAPI.setScoreboard(onlinePlayer);
     }

     @EventHandler
     public void onQuit(PlayerQuitEvent event) {
          event.quitMessage(null);
          if (SitCommand.sitting.contains(event.getPlayer().getUniqueId())) {
               SitCommand.sitting.remove(event.getPlayer().getUniqueId());
               event.getPlayer().getVehicle().remove();
          }
          if (HypeLobby.sp.getPlayerUUIDs().contains(event.getPlayer().getUniqueId())) {
               HypeLobby.sp.removePlayer(event.getPlayer());
          }
          if (PetsManager.petMap.containsKey(event.getPlayer())) {
               PetsManager.removePet(PetsManager.getPet(event.getPlayer()));
          }
          if (BootsPlayer.bootsPlayers.containsKey(event.getPlayer())) {
               BootsPlayer.stopParticle(event.getPlayer());
               BootsPlayer.bootsPlayers.remove(event.getPlayer());
          }
          BootsPlayer.stopParticle(event.getPlayer());
     }

}
