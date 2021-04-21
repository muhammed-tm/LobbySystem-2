package eu.hypetime.spigot.hypelobby.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

/*
    Created by Andre
    At 13:25 Uhr | 13. Apr.. 2021
    Project HypeLobbySpigot
*/
public class RodListener implements Listener {

     @EventHandler
     public void onRodListener(PlayerFishEvent event) {
          Player player = event.getPlayer();
          FishHook hook = event.getHook();
          if(player.getInventory().getItem(5).getType() == Material.FISHING_ROD) {
               if (hook.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                    Location playerLocation = player.getLocation();
                    Location hookLocation = hook.getLocation();

                    Vector vector = player.getVelocity();
                    double distance = playerLocation.distance(hookLocation);

                    vector.setX((0.5 * distance) * (hookLocation.getX() - playerLocation.getX()) / distance);
                    vector.setY(1 * (hookLocation.getY() - playerLocation.getY()) / distance - -0.05D * distance);
                    vector.setZ((0.5 * distance) * (hookLocation.getZ() - playerLocation.getZ()) / distance);

                    player.setVelocity(vector);
                    player.getInventory().getItemInHand().setDurability((short) 0);

                    player.updateInventory();
               }
          }
     }
}
