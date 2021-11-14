package eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Gadget;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
    Created by Andre
    At 01:07 Uhr | 14. Nov.. 2021
    Project HypeLobbySpigot
*/
/*public class TrailGunListener implements Listener {

     public static HashMap<Location, Material> blocks = new HashMap<>();

     @EventHandler
     public void onShoot(ProjectileLaunchEvent event) {
          if (event.getEntity().getShooter() instanceof Player) {
               Player player = (Player) event.getEntity().getShooter();
               if (event.getEntity() instanceof Snowball) {
                    Snowball snowball = (Snowball) event.getEntity();
                    if (player.getInventory().getItem(4).getType() == Material.SNOWBALL
                         && player.getInventory().getItem(4).getItemMeta().getDisplayName().equalsIgnoreCase("§8» §5Trail Gun")) {
                         player.getInventory().setItem(4, Gadget.TRAIL_GUN.getItem());
                         BukkitTask task = null;
                         BukkitTask finalTask = task;
                         task = Bukkit.getScheduler().runTaskTimer(HypeLobby.getInstance(), () -> {
                              if (!snowball.isDead()) {
                                   if (snowball.isOnGround()) {
                                        snowball.remove();
                                        Bukkit.getScheduler().cancelTask(finalTask.getTaskId());
                                        return;
                                   } else {
                                        List<Material> materials = new ArrayList<>();
                                        materials.add(Material.TERRACOTTA);
                                        materials.add(Material.WHITE_TERRACOTTA);
                                        materials.add(Material.BLUE_TERRACOTTA);
                                        materials.add(Material.CYAN_TERRACOTTA);
                                        materials.add(Material.GREEN_TERRACOTTA);
                                        materials.add(Material.LIGHT_BLUE_TERRACOTTA);
                                        materials.add(Material.LIME_TERRACOTTA);
                                        materials.add(Material.MAGENTA_TERRACOTTA);
                                        for (int i = 0; i < 10; i++) {
                                             if(snowball.getLocation().subtract(0, i, 0).getBlock().getType() != Material.AIR
                                                  && snowball.getLocation().subtract(0, i, 0).getBlock().getType() != Material.WATER) {
                                                  blocks.put(snowball.getLocation().subtract(0, i, 0), snowball.getLocation().subtract(0, i, 0).getBlock().getType());
                                                  int randomNum = ThreadLocalRandom.current().nextInt(0, materials.size() + 1);
                                                  snowball.getLocation().subtract(0, i, 0).getBlock().setType(materials.get(randomNum));
                                                  return;
                                             }
                                        }
                                   }
                              }
                         }, 0, 0);
                         Bukkit.getScheduler().scheduleSyncDelayedTask(HypeLobby.getInstance(), () -> {
                              blocks.forEach((location, material) -> {
                                   location.getBlock().setType(material);
                              });
                              player.getInventory().setItem(4, Gadget.TRAIL_GUN.getItem());
                         }, 30 * 20);
                    }
               }
          }
     }
}*/
