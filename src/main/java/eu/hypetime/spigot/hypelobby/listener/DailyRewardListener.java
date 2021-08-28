package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.SQLStats;
import eu.hypetime.spigot.hypelobby.utils.StatsManager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class DailyRewardListener implements Listener {

     @EventHandler
     public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
          if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
               Player player = event.getPlayer();
               ArmorStand armorStand = (ArmorStand) event.getRightClicked();
               if (armorStand.getType().equals(EntityType.ARMOR_STAND)) {
                    if (event.getRightClicked().getCustomName() != null) {
                         if (event.getRightClicked().getCustomName().equalsIgnoreCase("§6Daily Rewards")) {
                              Inventories.DailyRewardInventory(event.getPlayer());
                         } else if (event.getRightClicked().getCustomName().contains("§6")) {
                              String target = event.getRightClicked().getCustomName().replace("§6", "");
                              player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
                              player.sendMessage("§7Spieler§8: §a" + target);
                              player.sendMessage("§7Kills§8: §a" + StatsManager.getKills(target));
                              player.sendMessage("§7Deaths§8: §a" + StatsManager.getDeaths(target));
                              player.sendMessage("§7Rank§8: §a" + SQLStats.getInstance().getRanking(target));
                              player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
                         }
                         event.setCancelled(true);
                    }
               }
          }
     }

}
