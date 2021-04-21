package eu.hypetime.spigot.hypelobby;

import eu.hypetime.spigot.hypelobby.commands.BuildCommand;
import eu.hypetime.spigot.hypelobby.commands.SetCommand;
import eu.hypetime.spigot.hypelobby.listener.*;
import eu.hypetime.spigot.hypelobby.tictactoe.listener.GameListener;
import eu.hypetime.spigot.hypelobby.utils.Config;
import eu.hypetime.spigot.hypelobby.utils.Constants;
import eu.hypetime.spigot.hypelobby.utils.*;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
    Created by Andre
    At 21:37 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

public class HypeLobby extends JavaPlugin {

     private static HypeLobby instance;
     private Config config;
     private Constants constants;

     @Override
     public void onEnable() {
          instance = this;

          config = new Config(getDataFolder().getAbsolutePath(), "config.yml");
          constants = new Constants(this);

          getLogger().info(constants.getPrefix() + "Das System wurde aktiviert§8.");

          registerListener();
          registerCommands();

          ScoreAPI.startScheduler();

          /*if(WarpAPI.getLocation("Belohnung") != null) {
               ResultSet resultSet = MySQL.getResult("SELECT * FROM npc WHERE Location = '" + WarpAPI.getLocation("Belohnung") + "'");
               while (true) {
                    try {
                         if (!resultSet.next()) break;
                         EntityPlayer npc = (EntityPlayer) resultSet.getObject("NPC");
                         NPC.getNPC().add(npc);
                         for (Player all : Bukkit.getOnlinePlayers()) {
                              NPC.addJoinPacket(all);
                         }
                    } catch (SQLException ignored) {

                    }
               }
          }
          */

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
          pluginManager.registerEvents(new JoinListener(), this);
          pluginManager.registerEvents(new ChatListener(), this);
          pluginManager.registerEvents(new NavListener(), this);
          pluginManager.registerEvents(new MainListener(), this);
          pluginManager.registerEvents(new BuildListener(), this);
          pluginManager.registerEvents(new PVPListener(), this);
          //pluginManager.registerEvents(new GameListener(), this);
          pluginManager.registerEvents(new EnderpearlListener(), this);
          pluginManager.registerEvents(new RodListener(), this);
     }

     private void registerCommands() {
          getCommand("build").setExecutor(new BuildCommand());
          getCommand("set").setExecutor(new SetCommand());
     }
}
