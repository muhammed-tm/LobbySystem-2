package eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.*;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import java.util.ArrayList;
import java.util.HashMap;

/*
    Created by Andre
    At 11:56 Uhr | 13. Apr.. 2021
    Project HypeLobbySpigot
*/
public class EnderpearlListener implements Listener {

     public static ArrayList<Player> useEnderpearl = new ArrayList<>();
     static EnderPearl enderPearl;
     public HashMap<Player, EnderPearl> enderpearls = new HashMap<>();

     @EventHandler
     public void onEnderpearlListener(PlayerInteractEvent event) {
          if (event.getItem() != null && event.getItem().getType() == Material.ENDER_PEARL
               && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §5Enderpearl")) {
               if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Player player = event.getPlayer();
                    event.setCancelled(true);

                    toClay(player);
                    player.setAllowFlight(true);

                    enderPearl = player.launchProjectile(EnderPearl.class);
                    enderPearl.setPassenger(player);
                    useEnderpearl.add(player);
                    enderpearls.put(player, enderPearl);
               }
          }
     }

     public void toClay(Player player) {
          Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), new Runnable() {
               @Override
               public void run() {
                    player.getInventory().setItem(4, new ItemAPI("§7Loading...", Material.FIREWORK_STAR, 1).build());
                    toEnderPerl(player);
               }
          }, 1L);
     }

     public void toEnderPerl(Player player) {
          Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), new Runnable() {

               @Override
               public void run() {
                    if (player.getInventory().getItem(4) == null) return;
                    if (player.getInventory().getItem(4)
                         .isSimilar(new ItemAPI("§7Loading...", Material.FIREWORK_STAR, 1).build())) {
                         player.getInventory().setItem(4, new ItemAPI("§8» §5Enderpearl", Material.ENDER_PEARL, 1).build());
                         player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.B));
                         Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> {
                              player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.D));
                         }, 5L);
                         Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> {
                              player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.E));
                         }, 10L);
                         useEnderpearl.remove(player);
                    }
               }
          }, 3 * 20L);
     }

     @EventHandler
     public void onVehicleLeave(VehicleExitEvent event) {
          if (event.getExited() instanceof Player) {
               if (enderpearls.containsKey(event.getExited())) {
                    enderpearls.get(event.getExited()).remove();
                    useEnderpearl.remove(event.getExited());
                    event.getVehicle().remove();
                    Player player = (Player) event.getExited();
                    Location spawn = WarpAPI.getLocation("Spawn");
                    if (player.getLocation().getY() < -5 || (player.getLocation().getY() <= spawn.getY() && player.getLocation().subtract(0, -2, 0).distanceSquared(spawn) <= 8)) {
                         WarpAPI.tpWarp(player, "Spawn");
                    } else if (player.getLocation().getBlock().getType() != Material.AIR
                         || player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR
                         || player.getLocation().add(0, 1, 0).getBlock().getType() != Material.AIR) {
                         WarpAPI.tpWarp(player, "Spawn");
                    }
               }
          }
     }

     @EventHandler
     public void onTeleport(PlayerTeleportEvent event) {
          Player player = event.getPlayer();
          if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
               if (player.getVehicle() == null) {
                    event.setCancelled(true);
               } else {
                    player.teleport(event.getTo().add(0, 2, 0));
               }
          }
     }
}
