package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.utils.Inventories;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakListener implements Listener {

    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()) {
            if (player.hasPermission("lobby.shield")) {
                Inventories.sneakInventory(player);
            }
        } else {
            Inventories.main2Inventory(player);
        }
    }


}
