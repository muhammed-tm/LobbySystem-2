package eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class RaketenWerfer implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEnderpearlGadget(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem().getItemMeta().getDisplayName().equals("§6RaketenWerfer")) {
                Player p = event.getPlayer();
                event.setCancelled(true);

                Firework fw = player.getLocation().getWorld().spawn(player.getLocation(), Firework.class);
                FireworkMeta fm = fw.getFireworkMeta();
                fm.addEffects(
                        FireworkEffect.builder()
                                .with(FireworkEffect.Type.BALL_LARGE)
                                .withColor(Color.RED)
                                .withColor(Color.AQUA)
                                .withColor(Color.ORANGE)
                                .withColor(Color.YELLOW)
                                .trail(false)
                                .flicker(false)
                                .build());
                fm.setPower(0);
                fw.setFireworkMeta(fm);
            }

            }
        }
}
