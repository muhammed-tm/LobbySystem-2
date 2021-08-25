package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;

/*
    Created by Andre
    At 15:09 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class PetsListener implements Listener {

     @EventHandler
     public void onEntityTrigger(EntityTargetEvent event) {
          event.setCancelled(true);
     }

     @EventHandler
     public void onDeath(EntityDeathEvent event) {
          event.getDrops().clear();
     }
}
