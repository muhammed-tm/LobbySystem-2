package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class PlayerMoveEvent implements Listener {
    private final HypeLobby plugin;

    public PlayerMoveEvent(HypeLobby plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
    }

    @EventHandler
    public void onMove(org.bukkit.event.player.PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (this.plugin.getConfig().getBoolean("shield")) {
            for (Player players : this.plugin.run.keySet()) {
                if (player != players && !player.hasPermission("shieldarmor.bypass"))
                    if (player.getLocation().distance(players.getLocation()) <= 3.0D) {
                        double Ax = player.getLocation().getX();
                        double Ay = player.getLocation().getY();
                        double Az = player.getLocation().getZ();
                        double Bx = players.getLocation().getX();
                        double By = players.getLocation().getY();
                        double Bz = players.getLocation().getZ();
                        double x = Ax - Bx;
                        double y = Ay - By;
                        double z = Az - Bz;
                        Vector v = (new Vector(x, y, z)).normalize().multiply(1.1D).setY(0.6D);
                        player.setVelocity(v);
                    }
            }
            if (this.plugin.run.containsKey(player))
                for (Entity entity : player.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
                    if (entity instanceof Player) {
                        Player target = (Player)entity;
                        if (player != target)
                            if (!target.hasPermission("shieldarmor.bypass")) {
                                double Ax = player.getLocation().getX();
                                double Ay = player.getLocation().getY();
                                double Az = player.getLocation().getZ();
                                double Bx = target.getLocation().getX();
                                double By = target.getLocation().getY();
                                double Bz = target.getLocation().getZ();
                                double x = Bx - Ax;
                                double y = By - Ay;
                                double z = Bz - Az;
                                Vector v = (new Vector(x, y, z)).normalize().multiply(0.8D).setY(0.6D);
                                target.setVelocity(v);
                            }
                    }
                }
        }
    }
}
