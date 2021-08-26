package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.commands.BuildCommand;
import io.papermc.paper.event.entity.EntityMoveEvent;
import net.minecraft.world.item.ItemStack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
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
          if (!event.getPlayer().isSneaking()) {
               event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 1));
          } else {
               event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
          }
     }

     @EventHandler
     public void onMove(EntityMoveEvent event) {
          if (event.getEntityType() == EntityType.ARMOR_STAND) {
               if(event.getEntity().getPassengers().size() == 0) {
                    event.setCancelled(true);
               }
          }
     }

     @EventHandler
     public void onClick(InventoryClickEvent event) {
          if (event.getWhoClicked().getGameMode() == GameMode.SURVIVAL) {
               event.setCancelled(true);
          } else if (event.getWhoClicked() instanceof Player) {
               Player player = ( Player ) event.getWhoClicked();
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
}
