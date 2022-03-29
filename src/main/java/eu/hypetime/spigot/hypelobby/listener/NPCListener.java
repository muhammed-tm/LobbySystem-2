package eu.hypetime.spigot.hypelobby.listener;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.event.PlayerNPCInteractEvent;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.PlayerManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NPCListener implements Listener {

    @EventHandler
    public void handleNPCInteract(PlayerNPCInteractEvent event) {
        Player player = event.getPlayer();
        NPC npc = event.getNPC();
        if (!PlayerManager.hasAccept(event.getPlayer())) {
            player.sendTitle("§4§lAchtung", "§cBitte die Regeln akzeptieren");
            event.getPlayer().sendMessage("\n§7« §6HypeTimeEU §7»\n\n§7Bitte §cakzeptiere §7erst unsere §6DSGVO §7um das Netzwerk komplett nutzen zu können§8.\n§7Nutze dazu §6/dsgvo accept");
            Inventories.DSGVOInventory(player);
            return;
        }
        // checking if the player hit the NPC
        if (npc.getProfile().getName().equalsIgnoreCase("§6NameMC Reward")) {
            if (event.getUseAction() == PlayerNPCInteractEvent.EntityUseAction.INTERACT_AT) {
                Inventories.NameMCInventory(player);
            }


        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6NameMC §8«")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
            }
        }
    }

}
