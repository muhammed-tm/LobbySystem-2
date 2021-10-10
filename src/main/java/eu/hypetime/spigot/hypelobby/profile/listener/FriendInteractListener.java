package eu.hypetime.spigot.hypelobby.profile.listener;

import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.profile.utils.ProfileInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

/*
    Created by Andre
    At 20:26 Uhr | 26. Aug.. 2021
    Project HypeLobbySpigot
*/
public class FriendInteractListener implements Listener {

     /*@EventHandler
     public void onInteract(PlayerInteractEvent event) {
          Player player = event.getPlayer();
          if (event.getItem() == null) return;
          if (event.getItem().getType() == null) return;
          if (event.getItem().getItemMeta() == null) return;
          if (event.getItem().getItemMeta().getDisplayName() == null) return;
          if (event.getItem().getType() == Material.PLAYER_HEAD) {
               if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Profil")) {
                    player.openInventory(ProfileInventory.FriendInventory(player));
               }
          }
     }*/

     /*@EventHandler
     public void onClick(InventoryClickEvent event) {
          Player player = (Player) event.getWhoClicked();
          if (player.getOpenInventory().getTitle().contains("§8» §6Freunde §8| §6Seite ")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getType() == null) return;
               if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    player.closeInventory();
                    player.openInventory(ProfileInventory.PlayerInventory(player, event.getCurrentItem().getItemMeta().getDisplayName().replace("§6", "")));
               }
          } else if (player.getOpenInventory().getTitle().contains("§7Freund Einstellungen§8: §6")) {
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               if (event.getCurrentItem().getType() == null) return;
               event.setCancelled(true);
               if (event.getCurrentItem().getType() == Material.ENDER_PEARL) {
                    ICloudPlayer target = ProfileInventory.playerManager
                         .getOnlinePlayer(HypeLobby.getInstance().getFriendManager().getMysql()
                              .getUniqueId(player.getOpenInventory().getTitle().replace("§7Freund Einstellungen§8: §6", "")));
                    if (target.getConnectedService() != null) {
                         sendPlayer(player, target.getConnectedService().getServerName());
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der Spieler ist aktuell §coffline§8.");
                    }
                    player.closeInventory();
                    return;
               }
               if (event.getCurrentItem().getType() == Material.BARRIER) {
                    String name = player.getOpenInventory().getTitle().replace("§7Freund Einstellungen§8: §6", "");
                    UUID targetUUID = HypeLobby.getInstance().getFriendManager().getMysql()
                         .getUniqueId(name);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast die Freundschaft mit §6" + name + " §7aufgelöst");
                    HypeLobby.getInstance().getFriendManager().removeFriend(HypeLobby.getInstance().getFriendManager().getPlayer(player.getUniqueId()),
                         HypeLobby.getInstance().getFriendManager().getPlayer(targetUUID));
                    player.closeInventory();
                    return;
               }
          }
     }*/

     public void sendPlayer(Player player, String server) {
          ProfileInventory.playerManager.getPlayerExecutor(player.getUniqueId()).connect(server);
     }
}
