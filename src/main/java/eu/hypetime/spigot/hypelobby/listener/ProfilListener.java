package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.SettingConfig;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spellcaster;
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
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Profil §8«")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
            }
            if (event.getSlot() == 22) {
                player.closeInventory();
                Inventories.ProfilSettingsInventory(player);
            }

        }
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Einstellungen §8«")) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleport Animation")) {
                SettingConfig.settpanimation(player, !SettingConfig.gettpanimation(player));
                Inventories.ProfilSettingsInventory(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Spawn-Position")) {
                SettingConfig.setspawnposition(player, !SettingConfig.getspawnposition(player));
                Inventories.ProfilSettingsInventory(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Sichtbarkeit")) {
                SettingConfig.setsichtbarkeit(player, !SettingConfig.getsichtbarkeit(player));
                Inventories.ProfilSettingsInventory(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")) {
                Inventories.ProfilInventory(player);

            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Sichtbarkeit")) {
                if (SettingConfig.getsichtbarkeit(player) == true) {
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
                }
        }
    }

}
