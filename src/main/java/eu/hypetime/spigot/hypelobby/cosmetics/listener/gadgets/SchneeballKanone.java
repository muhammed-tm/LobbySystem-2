package eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class SchneeballKanone implements Listener {

    private HashMap<Integer, Item> items = new HashMap<Integer, Item>();

    private HypeLobby main;
    private HashMap<Player, Snowball> Schneeballs = new HashMap<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEnderpearlGadget(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem().getItemMeta().getDisplayName().equals("§fSchneeballKanone")) {
                Player p = e.getPlayer();
                e.setCancelled(true);

                Snowball schnee = p.launchProjectile(Snowball.class);
                p.launchProjectile(Snowball.class);

            }
        }
    }
}
