package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import net.minecraft.server.v1_16_R3.PacketPlayOutRespawn;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftParticle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

/*
    Created by Andre
    At 01:04 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class PVPListener implements Listener {

     public static HashMap<Player, Integer> pvp = new HashMap<>();

     @EventHandler
     public void onWorldChange(PlayerChangedWorldEvent event) {
          if(event.getPlayer().getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())) {
               Inventories.pvpInventory(event.getPlayer());
               Scoreboard scoreboard = event.getPlayer().getScoreboard();
               scoreboard.clearSlot(DisplaySlot.SIDEBAR);
               event.getPlayer().setScoreboard(scoreboard);
          } else {
               //ScoreAPI.setScoreboard(event.getPlayer());
               Inventories.mainInventory(event.getPlayer());
          }
     }

     @EventHandler
     public void onLeave(PlayerInteractEvent event) {
          if(event.getItem() == null) return;
          if(!event.getItem().hasItemMeta()) return;
          if(event.getItem().getType() == null) return;
          if(event.getItem().getType() == Material.AIR) return;
          if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cVerlassen")) {
               if(!pvp.containsKey(event.getPlayer())) {
                    WarpAPI.tpWarp(event.getPlayer(), "Spawn");
               } else {
                    event.getPlayer().sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du bist noch im §cKampf§8.");
               }
          }
     }

     @EventHandler
     public void onEntityDamage(EntityDamageEvent event) {
          if(event.getEntity().getType() == EntityType.PLAYER) {
               if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setCancelled(!event.getEntity().getLocation().getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld()));
               } else {
                    event.setCancelled(true);
               }
          }
     }

     @EventHandler
     public void onDamage(EntityDamageByEntityEvent event) {
          if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
               Player getDamage = (Player) event.getEntity();
               Player attacker = (Player) event.getDamager();
               if(getDamage.getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())
                    && attacker.getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())) {
                    double distance = getDamage.getLocation().distance(attacker.getLocation());
                    if(distance >= 4 && !attacker.getGameMode().equals(GameMode.CREATIVE)) {
                         event.setCancelled(true);
                    } else {
                         event.setCancelled(false);
                    }
               }
          }
     }

     @EventHandler
     public void onDeath(PlayerDeathEvent event) {
          Player player = event.getEntity();

          event.setDeathMessage(null);
          event.getDrops().clear();
          player.spigot().respawn();
     }

     @EventHandler
     public void onRespawn(PlayerRespawnEvent event) {
          Player player = event.getPlayer();
          player.teleport(WarpAPI.getLocation("LobbyPVP"));
          Inventories.pvpInventory(player);
     }
}
