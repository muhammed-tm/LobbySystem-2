package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.SettingConfig;
import eu.hypetime.spigot.hypelobby.utils.clan.ClanSQL;
import eu.hypetime.spigot.hypelobby.utils.clan.PlayerClanSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.IOException;

public class ProfilListener implements Listener {

     @EventHandler
     public void onInteract(PlayerInteractEvent event) {
          if (event.getItem() == null) return;
          if (event.getItem().getType() == Material.AIR) return;
          if (!event.getItem().hasItemMeta()) return;
          if (!event.getItem().getItemMeta().hasDisplayName()) return;
          if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Profil")) {
               Inventories.ProfilInventory(event.getPlayer());
          }
     }

     @EventHandler
     public void onClick(InventoryClickEvent event) throws IOException {
          Player player = (Player) event.getWhoClicked();
          if (event.getView().getTitle().equalsIgnoreCase("§8» §6Profil §8«")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                    player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
               }
               if (event.getSlot() == 22) {
                    player.closeInventory();
                    Inventories.ProfilSettingsInventory(player);
               }
               if (event.getSlot() == 23) {
                    player.closeInventory();
                    if(player.hasPermission("lobby.staff"))
                    Inventories.ProfilStaffSettingsInventory(player);
                    else
                         player.sendMessage("§7Diese Funktion folgt in kürze§8.");
               }
               if (event.getSlot() == 31) {
                    player.closeInventory();
                    if(player.getName().equalsIgnoreCase("quele") || player.getName().equalsIgnoreCase("QuadrixYT")) {
                         Inventories.ClanInventory(player);
                    } else {
                         player.sendMessage("§7Diese Funktion folgt in kürze§8.");
                    }
               }

          }
          if (event.getView().getTitle().equalsIgnoreCase("§6Clan §8| §7Menu")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getItemMeta() == null) return;
               if (event.getSlot() == 13) {
                    player.closeInventory();
                    Inventories.ClanUserInventory(player);
               }
          }
          if (event.getView().getTitle().equalsIgnoreCase("§6Clan §8| §7Mitglieder")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getItemMeta() == null) return;
               if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    if (ClanSQL.isPlayerLeader(player, PlayerClanSQL.getClanNameExact(player))) {
                         player.closeInventory();
                         Inventories.ClanUserManageInventory(player, event.getCurrentItem().getItemMeta().getDisplayName().replace("§6", ""));
                    } else {
                         return;
                    }
               }
          }
          if (event.getView().getTitle().contains("§6Clan §8| §7")
               && !event.getView().getTitle().contains("Mitglieder")
               && !event.getView().getTitle().contains("Menu")) {
               event.setCancelled(true);
               if (event.getSlot() == 11) {
                    ClanSQL.promote(player, PlayerClanSQL.getClanNameExact(player), event.getView().getTitle().replace("§7", "").split(" ")[2]);
               }
               if (event.getSlot() == 13) {
                    ClanSQL.demote(player, PlayerClanSQL.getClanNameExact(player), event.getView().getTitle().replace("§7", "").split(" ")[2]);
               }
               if (event.getSlot() == 15) {
                    ClanSQL.kick(player, PlayerClanSQL.getClanNameExact(player), event.getView().getTitle().replace("§7", "").split(" ")[2]);
               }
          }
          if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Einstellungen §8«")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getItemMeta() == null) return;
               if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE)
                    player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);

               if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleport Animation")) {
                    SettingConfig.settpanimation(player, !SettingConfig.gettpanimation(player));
                    Inventories.ProfilSettingsInventory(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
               }
               if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Spawn-Position")) {
                    SettingConfig.setspawnposition(player, !SettingConfig.getspawnposition(player));
                    Inventories.ProfilSettingsInventory(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
               }
               if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Double Jump")) {
                    SettingConfig.setDoublejump(player, !SettingConfig.getDoublejump(player));
                    Inventories.ProfilSettingsInventory(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
               }
               if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")) {
                    Inventories.ProfilInventory(player);

               }
               /*if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Sichtbarkeit")) {
                    if (SettingConfig.getDoublejump(player) == true) {
                         HypeLobby.getNonplayersvisible().remove(player.getUniqueId());
                         for (Player all : Bukkit.getOnlinePlayers()) {
                              player.showPlayer(HypeLobby.getInstance(), all);
                         }
                         player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 3F);

                    } else {
                         HypeLobby.getNonplayersvisible().add(player.getUniqueId());
                         for (Player all : Bukkit.getOnlinePlayers()) {
                              player.hidePlayer(HypeLobby.getInstance(), all);
                         }
                         player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 3F);
                    }
               }*/
          }
          if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6S-Einstellungen §8«")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getItemMeta() == null) return;
               if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE)
                    player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);

               if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lStop Server")) {
                    if(player.hasPermission("lobby.srstaff")) {
                    if((Bukkit.getOnlinePlayers().size() <= 4)) {
                         Bukkit.shutdown();
                    }else {
                         if(player.hasPermission("lobby.manager")) {
                              Bukkit.shutdown();
                         }else {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDiese Funktion ist erst ab den Manager Rang möglich, da zu viele Spieler auf der Lobby sind.");

                         }
                         }
                    }else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDiese Funktion ist erst ab den SrStaff Rang möglich");
                    }
               }

          }
}
}
