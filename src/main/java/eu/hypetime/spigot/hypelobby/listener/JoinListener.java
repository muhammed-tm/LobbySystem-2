package eu.hypetime.spigot.hypelobby.listener;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
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

        player.teleport(WarpAPI.getLocation("Spawn"));

            Song s = NBSDecoder.parse(new File(HypeLobby.getInstance().getDataFolder(), "Song.nbs"));
            SongPlayer sp = new RadioSongPlayer(s);
            sp.setAutoDestroy(true);
            sp.addPlayer(event.getPlayer());
            sp.setPlaying(true);
            player.sendTitle("§aWillkommen!", "§aauf §6§lHypeTime.EU", 20, 40, 20);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(null);
    }

}
