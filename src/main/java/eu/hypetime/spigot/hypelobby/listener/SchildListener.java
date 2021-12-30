package eu.hypetime.spigot.hypelobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SchildListener implements Listener {

    public void onEnderpearlGadget(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem().getItemMeta().getDisplayName().equals("§5Schild")) {
                Player p = e.getPlayer();
                e.setCancelled(true);


            }
        }
    }
}
