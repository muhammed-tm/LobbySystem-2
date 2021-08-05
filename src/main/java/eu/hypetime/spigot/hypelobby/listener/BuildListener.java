package eu.hypetime.spigot.hypelobby.listener;

import com.sun.jdi.event.StepEvent;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.commands.BuildCommand;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

/*
    Created by Andre
    At 00:14 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class BuildListener implements Listener {


     String prefix = HypeLobby.getInstance().getConstants().getPrefix();

     List < Player > build = BuildCommand.build;

     @EventHandler
     public void onPlace(BlockPlaceEvent event) {
          if (!build.contains(event.getPlayer())) {
               if (event.getPlayer().getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())) {
                    if (event.getItemInHand().getType() != Material.PLAYER_HEAD) {
                         if ((event.getPlayer().getLocation().distance(WarpAPI.getLocation("LobbyPVP")) <= 3) || (event.getBlock().getLocation().distance(WarpAPI.getLocation("LobbyPVP")) <= 3)) {
                              event.setCancelled(true);
                         } else {
                              event.setCancelled(false);
                              Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> event.getBlock().setType(Material.REDSTONE_BLOCK), 30L);
                              Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> event.getBlock().setType(Material.AIR), 60L);
                         }
                    } else {
                         event.setCancelled(true);
                    }
               } else {
                    event.setCancelled(true);
               }
          }
     }

     @EventHandler
     public void onBreak(BlockBreakEvent event) {
          if (!build.contains(event.getPlayer())) {
               event.setCancelled(true);
          }
     }

     @EventHandler
     public void onDrop(PlayerDropItemEvent event) {
          if (!build.contains(event.getPlayer())) {
               event.setCancelled(true);
          }
     }

     @EventHandler
     public void onStep(EntityChangeBlockEvent event) {
          if (!build.contains(event.getEntity())) {
               event.setCancelled(true);
          }
     }

     @EventHandler
     public void onPickUp(PlayerAttemptPickupItemEvent event) {
          if (!build.contains(event.getPlayer())) {
               event.setCancelled(true);
          }
     }

}