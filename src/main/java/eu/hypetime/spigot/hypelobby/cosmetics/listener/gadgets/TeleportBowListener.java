package eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitTask;

/*
    Created by Andre
    At 19:31 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class TeleportBowListener implements Listener {

     @EventHandler
     public void onSwapItem(PlayerItemHeldEvent event) {
          if (event.getNewSlot() == 4) {
               if (event.getPlayer().getInventory().getItem(4).getType() == Material.BOW) {
                    event.getPlayer().getInventory().setItem(9, new ItemBuilder(Material.ARROW).setName("§6Teleport Arrow").toItemStack());
               }
          } else {
               if (event.getPlayer().getInventory().contains(Material.ARROW)) {
                    event.getPlayer().getInventory().remove(Material.ARROW);
               }
          }
     }

     @EventHandler
     public void onShoot(ProjectileLaunchEvent event) {
          if (event.getEntity().getShooter() instanceof Player) {
               Player player = (Player) event.getEntity().getShooter();
               if (event.getEntity() instanceof Arrow) {
                    Arrow arrow = (Arrow) event.getEntity();
                    if (player.getInventory().getItem(4).getType() == Material.BOW
                         && player.getInventory().getItem(4).getItemMeta().getDisplayName().equalsIgnoreCase("§8» §5Teleport Bow")) {
                         BukkitTask task = null;
                         BukkitTask finalTask = task;
                         task = Bukkit.getScheduler().runTaskTimer(HypeLobby.getInstance(), () -> {
                              if (!arrow.isDead()) {
                                   if (arrow.isOnGround()) {
                                        player.teleport(arrow.getLocation());
                                        arrow.remove();
                                        Bukkit.getScheduler().cancelTask(finalTask.getTaskId());
                                        return;
                                   }
                              }
                         }, 0, 20L);
                    }
               }
          }
     }
}
