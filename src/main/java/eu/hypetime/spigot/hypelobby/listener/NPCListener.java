package eu.hypetime.spigot.hypelobby.listener;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.event.PlayerNPCInteractEvent;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
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

        // checking if the player hit the NPC
        if (event.getUseAction() == PlayerNPCInteractEvent.EntityUseAction.INTERACT) {
            if (npc.getProfile().hasName() && npc.getProfile().getName() == "§6NameMC Reward") {
                Inventories.NameMCInventory(player);
            }
            if (npc.getProfile().hasName() && npc.getProfile().getName() == "§6Daily Rewards") {
                Inventories.DailyRewardInventory(player);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = ( Player ) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6NameMC §8«")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
            }
        }
    }

}