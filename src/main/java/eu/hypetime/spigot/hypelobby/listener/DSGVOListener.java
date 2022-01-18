package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.PlayerManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DSGVOListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String prefix = HypeLobby.getInstance().getConstants().getPrefix();
        Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6DSGVO §8«")) {
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.AIR) return;
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
                return;
            }
            event.setCancelled(true);
            if (event.getSlot() == 10) {
                PlayerManager.setAccept(player);
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast nun die DSGVO akzeptiert!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f);

                player.closeInventory();
            } else if (event.getSlot() == 13) {
                player.playSound(player.getLocation(), Sound.MUSIC_NETHER_NETHER_WASTES, 1f, 1f);

            } else if (event.getSlot() == 16) {
                PlayerManager.setDeny(player);
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1f, 1f);
                player.sendTitle("§cBitte akzeptiere die Regeln", null);
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cBitte akzeptiere die Regeln");
                Inventories.DSGVOInventory(player);
            }
        }
    }
}