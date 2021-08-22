package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.commands.SitCommand;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;


public class DoubleJumpListener implements Listener {

    @EventHandler
    public void onFly(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setVelocity(player.getLocation().getDirection().setY(0.65).multiply(1.9));


            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1, 1F);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL) {
            if (player.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
                player.setAllowFlight(true);
                player.setFlying(false);
            }
        }
    }
}