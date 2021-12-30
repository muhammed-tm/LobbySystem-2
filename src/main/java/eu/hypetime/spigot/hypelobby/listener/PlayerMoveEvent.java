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
        Player p = e.getPlayer();
        if (this.plugin.getConfig().getBoolean("shield")) {
            for (Player players : this.plugin.run.keySet()) {
                if (p != players && !p.hasPermission("shieldarmor.bypass"))
                    if (p.getLocation().distance(players.getLocation()) <= 3.0D) {
                        double Ax = p.getLocation().getX();
                        double Ay = p.getLocation().getY();
                        double Az = p.getLocation().getZ();
                        double Bx = players.getLocation().getX();
                        double By = players.getLocation().getY();
                        double Bz = players.getLocation().getZ();
                        double x = Ax - Bx;
                        double y = Ay - By;
                        double z = Az - Bz;
                        Vector v = (new Vector(x, y, z)).normalize().multiply(1.1D).setY(0.6D);
                        p.setVelocity(v);
                    }
            }
            if (this.plugin.run.containsKey(p))
                for (Entity entity : p.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
                    if (entity instanceof Player) {
                        Player target = (Player)entity;
                        if (p != target)
                            if (!target.hasPermission("shieldarmor.bypass")) {
                                double Ax = p.getLocation().getX();
                                double Ay = p.getLocation().getY();
                                double Az = p.getLocation().getZ();
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
