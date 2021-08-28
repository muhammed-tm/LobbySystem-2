package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftCreature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/*
    Created by Andre
    At 15:09 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class PetsListener implements Listener {

     private final Method[] methods = ((Supplier<Method[]>) () -> {
          try {
               Method getHandle = Class.forName(Bukkit.getServer().getClass().getPackage().getName() + ".entity.CraftEntity").getDeclaredMethod("getHandle");
               return new Method[]{
                    getHandle, getHandle.getReturnType().getDeclaredMethod("setPositionRotation", double.class, double.class, double.class, float.class, float.class)
               };
          } catch (Exception ex) {
               return null;
          }
     }).get();

     @EventHandler
     public void onEntityTrigger(EntityTargetEvent event) {
          event.setCancelled(true);
     }

     @EventHandler
     public void onDeath(EntityDeathEvent event) {
          event.getDrops().clear();
     }

     @EventHandler
     public void onMove(PlayerInteractEvent event) {
          Player player = event.getPlayer();
          if (event.getAction() == Action.LEFT_CLICK_AIR) {
               if (PetsManager.getPet(player) != null) {
                    Location loc = player.getTargetBlock(10).getLocation();
                    ((CraftCreature) PetsManager.getPet(player).getEntity()).getHandle().getNavigation().a(loc.getX(), loc.getY(), loc.getZ(), 1.4);
               }
          }
     }

}
