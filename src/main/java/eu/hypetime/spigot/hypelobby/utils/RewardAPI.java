package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RewardAPI {

     public static void createIfNotExist(Player player) {
          ResultSet rs = MySQL.getResult("SELECT * FROM dailyreward WHERE UUID = '" + player.getUniqueId() + "'");

          boolean isExist = false;

          try {
               if (rs.next()) {
                    isExist = rs.getString("UUID") != null;
               }
          } catch (SQLException exception) {
               exception.printStackTrace();
               isExist = false;
          }
          if (!isExist) {
               HypeLobby.sp.addPlayer(player);
               if (!HypeLobby.sp.isPlaying()) {
                    HypeLobby.sp.setPlaying(true);
               }
               player.sendTitle("§aWillkommen!", "§aauf §6§lHypeTime", 20, 40, 20);
               MySQL.update("INSERT INTO dailyreward (UUID, Spielerwaiting, Hyperwaiting, Warriorwaiting, Vipwaiting, Mediawaiting) " +
                    "VALUES ('" + player.getUniqueId() + "', '0', '0', '0', '0', '0')");
               System.out.println("Player created");
          } else {
               if (HypeLobby.sp.getPlayerUUIDs().contains(player.getUniqueId())) {
                    HypeLobby.sp.removePlayer(player);

               }
          }
     }

     public static Long getTime(Player player, RewardType type) {
          Long time = 0L;
          ResultSet rs = MySQL.getResult("SELECT * FROM dailyreward WHERE UUID = '" + player.getUniqueId() + "'");
          try {
               if (rs.next()) {
                    time = rs.getLong(type.name + "waiting");
               }
          } catch (SQLException exception) {
               exception.printStackTrace();
               time = 0L;
               return time;
          }
          return time;
     }


     public static void setUUID(Player player, RewardType type) {
          MySQL.update("UPDATE dailyreward SET " + type.name + "waiting = '" + (System.currentTimeMillis() + type.time) + "' WHERE UUID = '" + player.getUniqueId() + "'");
     }

     public static boolean canReward(Player player, RewardType type) {
          return getTime(player, type) < System.currentTimeMillis();
     }

     public static String getRemaining(Player player, RewardType type) {
          long millis = getTime(player, type) - System.currentTimeMillis();
          long seconds = millis / 1000L;
          long minutes = 0L;
          while (seconds > 60L) {
               seconds -= 60;
               minutes++;
          }
          long hours = 0L;
          while (minutes > 60L) {
               minutes -= 60;
               hours++;
          }
          return "§e" + hours + " §7Stunde(n) §e" + minutes + " §7Minute(n) §e" + seconds + " §7Sekunden(n)";
     }
}
