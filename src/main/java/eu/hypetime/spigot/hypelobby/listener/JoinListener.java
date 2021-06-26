package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.RewardAPI;
import eu.hypetime.spigot.hypelobby.utils.ScoreAPI;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/*
    Created by Andre
    At 23:30 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(null);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ScoreAPI.setScoreboard(onlinePlayer);
        }
        Inventories.mainInventory(player);
        RewardAPI.createIfNotExist(player);

        player.teleport(WarpAPI.getLocation("Spawn"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(null);
    }

}
