package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.commands.BuildCommand;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
    Created by Andre
    At 23:09 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class MainListener implements Listener {

     @EventHandler
     public void onSneak(PlayerToggleSneakEvent event) {
          if(!event.getPlayer().isSneaking()) {
               event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 1));
          } else {
               event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
          }
     }

     @EventHandler
     public void onMove(EntityMoveEvent event) {
          if(event.getEntityType() == EntityType.ARMOR_STAND) {
               event.setCancelled(true);
          }
     }

     @EventHandler
     public void onItemMove(InventoryMoveItemEvent event) {
          if(!BuildCommand.build.contains((Player) event.getSource().getViewers().get(0))) {
               event.setCancelled(true);
          }
     }

     @EventHandler
     public void onWeather(WeatherChangeEvent event) {
          event.setCancelled(true);
     }
}
