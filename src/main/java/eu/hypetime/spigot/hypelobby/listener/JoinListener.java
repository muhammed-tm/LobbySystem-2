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

/*
    Created by Andre
    At 23:30 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ScoreAPI.setScoreboard(onlinePlayer);
        }
        Inventories.mainInventory(player);
        RewardAPI.createIfNotExist(player);

        player.teleport(WarpAPI.getLocation("Spawn"));
    }

}
