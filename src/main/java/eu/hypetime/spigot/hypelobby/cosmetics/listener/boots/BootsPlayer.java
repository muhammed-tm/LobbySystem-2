package eu.hypetime.spigot.hypelobby.cosmetics.listener.boots;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/*
    Created by Andre
    At 17:15 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class BootsPlayer {

     public static Map<Player, BootsPlayer> bootsPlayers = new HashMap<>();

     private Player player;
     private Particle particle;
     private int task;

     public BootsPlayer(Player player, Particle particle) {
          this.player = player;
          this.particle = particle;
          this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(HypeLobby.getInstance(), () -> {
               player.getWorld().spawnParticle(particle, player.getLocation(), 10, 0.1, 0.5, 0.1);
          }, 0, 0L);
          bootsPlayers.put(player, this);
     }

     public static void stopParticle(Player player) {
          if (bootsPlayers.containsKey(player)) {
               Bukkit.getScheduler().cancelTask(bootsPlayers.remove(player).task);
               player.getInventory().setItem(EquipmentSlot.FEET, new ItemStack(Material.AIR));
               player.getInventory().setItem(4, new ItemAPI("§7Gadget §8» §c✖", Material.BARRIER, 1).build());
          }
     }

     public Player getPlayer() {
          return player;
     }

     public Particle getParticle() {
          return particle;
     }

     public int getTask() {
          return task;
     }
}
