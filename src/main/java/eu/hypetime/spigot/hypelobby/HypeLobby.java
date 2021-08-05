package eu.hypetime.spigot.hypelobby;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import eu.hypetime.spigot.hypelobby.commands.BuildCommand;
import eu.hypetime.spigot.hypelobby.commands.SetCommand;
import eu.hypetime.spigot.hypelobby.listener.*;
import eu.hypetime.spigot.hypelobby.utils.Config;
import eu.hypetime.spigot.hypelobby.utils.Constants;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/*
    Created by Andre
    At 21:37 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

public class HypeLobby extends JavaPlugin {

     private static HypeLobby instance;
     public LobbySwitcherListener lobbySwitcherListener;
     private Config config;
     private Constants constants;
     public boolean broadcast;
     public static Song s;
     public static SongPlayer sp;


     @Override
     public void onEnable() {
          instance = this;
          s = NBSDecoder.parse(new File(getDataFolder(), "Song.nbs"));
          sp = new RadioSongPlayer(s);

          config = new Config(getDataFolder().getAbsolutePath(), "config.yml");
          constants = new Constants(this);

          getLogger().info(constants.getPrefix() + "Das System wurde aktiviert§8.");

          registerListener();
          registerCommands();

          ScoreAPI.startScheduler();


          getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
     }


     public static HypeLobby getInstance() {
          return instance;
     }

     public Config getConfigFile() {
          return config;
     }

     public Constants getConstants() {
          return constants;
     }

     private void registerListener() {
          PluginManager pluginManager = Bukkit.getPluginManager();
          lobbySwitcherListener = new LobbySwitcherListener();
          pluginManager.registerEvents(new JoinListener(), this);
          pluginManager.registerEvents(new ChatListener(), this);
          pluginManager.registerEvents(new NavListener(), this);
          pluginManager.registerEvents(new MainListener(), this);
          pluginManager.registerEvents(new BuildListener(), this);
          pluginManager.registerEvents(new PVPListener(), this);
          pluginManager.registerEvents(new EnderpearlListener(), this);
          pluginManager.registerEvents(new RodListener(), this);
          pluginManager.registerEvents(lobbySwitcherListener, this);
          pluginManager.registerEvents(new DailyRewardListener(), this);
          pluginManager.registerEvents(new DailyRewardGUIListener(), this);
          pluginManager.registerEvents(new NPCListener(), this);

          IEventManager eventManager = CloudNetDriver.getInstance().getEventManager();
          eventManager.registerListener(new CloudServer());
     }

     private void registerCommands() {
          getCommand("build").setExecutor(new BuildCommand());
          getCommand("set").setExecutor(new SetCommand());
     }

}
