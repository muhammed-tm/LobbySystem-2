package eu.hypetime.spigot.hypelobby.cosmetics.listener;

import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CosmeticsListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getItem().getType() == Material.AIR) return;
        if (event.getItem().getType() == Material.CHEST) {
            if (!event.getItem().hasItemMeta()) return;
            if (!event.getItem().getItemMeta().hasDisplayName()) return;
            if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Cosmetics")) {
                event.getPlayer().openInventory(CosmeticInventory.CosmeticsInventory());
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = ( Player ) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Cosmetics §8«") || player.getOpenInventory().getTitle().contains("§8» §6Cosmetics §8| ")) {
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
            }
            if (event.getSlot() == 46) {
                player.openInventory(CosmeticInventory.PetsInventory());
            }
            if (event.getSlot() == 47) {
                player.openInventory(CosmeticInventory.ParticleInventory());
            }
            if (event.getSlot() == 49) {
            }
            if (event.getSlot() == 51) {
                player.openInventory(CosmeticInventory.GadgetsInventory());
            }
            if (event.getSlot() == 52) {
                player.openInventory(CosmeticInventory.BootsInventory());
            }
        }
    }


}
