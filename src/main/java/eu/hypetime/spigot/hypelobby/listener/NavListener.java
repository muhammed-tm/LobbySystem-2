package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/*
    Created by Andre
    At 22:20 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class NavListener implements Listener {

     @EventHandler
     public void onInteract(PlayerInteractEvent event) {
          if (event.getItem() == null) return;
          if (event.getItem().getType() == Material.AIR) return;
          if (event.getItem().getType() == Material.MUSIC_DISC_13) {
               if (!event.getItem().hasItemMeta()) return;
               if (!event.getItem().getItemMeta().hasDisplayName()) return;
               if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Navigator")) {
                    Inventories.navigatorInventory(event.getPlayer());
               }
          }
     }

     @EventHandler
     public void onClick(InventoryClickEvent event) {
          Player player = (Player) event.getWhoClicked();
          if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Navigator §8«")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                    player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
               }
               /*if(event.getSlot() == 10) {
                    player.closeInventory();
                    WarpAPI.tpWarp(player, "LobbyPVP");
               }*/


               if (event.getClickedInventory() != player.getInventory()) {
                    if (event.getSlot() == 4) {
                         player.closeInventory();
                         WarpAPI.tpWarp(player, "VillageDefense");
                    }
                    if (event.getSlot() == 19) {
                         player.closeInventory();
                         WarpAPI.tpWarp(player, "KitPVP");
                    }
                    if (event.getSlot() == 21) {
                         player.closeInventory();
                         WarpAPI.tpWarp(player, "Spawn");
                    }
                    if (event.getSlot() == 23) {
                         player.closeInventory();
                         WarpAPI.tpWarp(player, "GunBattle");
                    }
                    if (event.getSlot() == 25) {
                         player.closeInventory();
                         WarpAPI.tpWarp(player, "HypeSMP");
                    }
                    if (event.getSlot() == 40) {
                         player.closeInventory();
                         Inventories.WarpNavigator(player);
                    }
                    if (event.getView().getTitle().equalsIgnoreCase("§8» §6Warps §8«")) {
                         event.setCancelled(true);
                         if (event.getCurrentItem() == null) return;
                         if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                              player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
                         }
                         if (event.getSlot() == 11) {
                              player.closeInventory();
                              WarpAPI.tpWarp(player, "RangShop");
                         }
                         if (event.getSlot() == 13) {
                              player.closeInventory();
                              WarpAPI.tpWarp(player, "NameMCReward");
                         }
                         if (event.getSlot() == 15) {
                              player.closeInventory();
                              WarpAPI.tpWarp(player, "GunBattle");
                         }
                         if (event.getSlot() == 18) {
                              player.closeInventory();
                              Inventories.navigatorInventory(player);
                         }
                    }
               }
          }
     }
}
