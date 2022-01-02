package eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;

public class RocketLaunchListener implements Listener {

    public ArrayList<Player> cooldown = new ArrayList<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onRocketLauncher(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if(event.getItem() == null) return;
            if(event.getItem().getItemMeta() == null) return;
            if(event.getItem().getItemMeta().getDisplayName() == null) return;
            if (event.getItem().getItemMeta().getDisplayName().equals("§6RaketenWerfer")) {
                event.setCancelled(true);
                if (!cooldown.contains(player)) {
                    cooldown.add(player);

                    Firework fw = player.getLocation().getWorld().spawn(player.getLocation(), Firework.class);
                    FireworkMeta fm = fw.getFireworkMeta();
                    fm.addEffects(
                            FireworkEffect.builder()
                                    .with(FireworkEffect.Type.BALL_LARGE)
                                    .withColor(Color.RED)
                                    .withColor(Color.AQUA)
                                    .withColor(Color.ORANGE)
                                    .withColor(Color.YELLOW)
                                    .trail(true)
                                    .flicker(false)
                                    .build());
                    fm.setPower(1);
                    fw.setFireworkMeta(fm);
                    player.getInventory().setItem(4, new ItemAPI("§8Loading...", Material.FIREWORK_STAR, 1).build());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(HypeLobby.getInstance(), () -> {
                        cooldown.remove(player);
                        if(player.getInventory().getItem(4).equals(new ItemAPI("§8Loading...", Material.FIREWORK_STAR, 1).build()))
                            player.getInventory().setItem(4, new ItemAPI("§6RaketenWerfer", Material.TNT, 1).build());
                    }, 100L);
                }
            }
        }
    }
}
