package eu.hypetime.spigot.hypelobby.listener;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;



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
        StatsManager.updatePlayer(player);

        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(WarpAPI.getLocation("Spawn"));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2F, 1F);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(null);
        if(HypeLobby.sp.getPlayerUUIDs().contains(event.getPlayer().getUniqueId())) {
            HypeLobby.sp.removePlayer(event.getPlayer());
        }
    }

}
