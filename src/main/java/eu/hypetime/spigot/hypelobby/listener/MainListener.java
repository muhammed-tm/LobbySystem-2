package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.commands.BuildCommand;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
    Created by HypeTime Dev Team
    At 23:09 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class MainListener implements Listener {
/*
     @EventHandler
     public void onSneak(PlayerToggleSneakEvent event) {
          if (!event.getPlayer().isSneaking()) {
               event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 1));
          } else {
               event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
          }
     }*/

     @EventHandler
     public void onMove(EntityMoveEvent event) {
          if (event.getEntityType() == EntityType.ARMOR_STAND) {
               if (event.getEntity().getPassengers().size() == 0) {
                    event.setCancelled(true);
               }
          }
     }

     @EventHandler
     public void onClick(InventoryClickEvent event) {
          if (event.getWhoClicked().getGameMode() == GameMode.SURVIVAL) {
               event.setCancelled(true);
          } else if (event.getWhoClicked() instanceof Player) {
               Player player = (Player) event.getWhoClicked();
               if (!BuildCommand.build.contains(player)) {
                    event.setCancelled(true);
               }
          }
     }

     @EventHandler
     public void onWeather(WeatherChangeEvent event) {
          event.setCancelled(true);
     }

     @EventHandler
     public void onFood(FoodLevelChangeEvent event) {
          event.setCancelled(true);
          event.setFoodLevel(20);
     }

     @EventHandler
     public void onItemDamage(PlayerItemDamageEvent event) {
          event.setDamage(0);
          event.setCancelled(true);
     }

     @EventHandler
     public void onEntityDamage(EntityDamageEvent event) {
          if (event.getEntity().getType() == EntityType.PLAYER) {
               if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                    event.setCancelled(true);
                    WarpAPI.tpWarp((Player) event.getEntity(), "Spawn");
                    return;
               }
               if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setCancelled(!event.getEntity().getLocation().getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld()));
               } else {
                    event.setCancelled(true);
               }
          }
     }

     @EventHandler
     public void onDamage(EntityDamageByEntityEvent event) {
          if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
               Player getDamage = (Player) event.getEntity();
               Player attacker = (Player) event.getDamager();
               if (getDamage.getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())
                    && attacker.getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())) {
                    double distance = getDamage.getLocation().distance(attacker.getLocation());
                    if (distance >= 4 && !attacker.getGameMode().equals(GameMode.CREATIVE)) {
                         event.setCancelled(true);
                    } else {
                         event.setCancelled(false);
                    }
               }
          }
     }
}
