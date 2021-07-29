package eu.hypetime.spigot.hypelobby.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
    Created by Andre
    At 11:08 Uhr | 22. Apr.. 2021
    Project GetDown
*/
public class SQLStats {

     private static SQLStats instance;

     public SQLStats() {
          instance = this;
     }

     public static SQLStats getInstance() {
          return instance;
     }

     public static String fetchName(UUID uuid) {
          // Get response from Mojang API
          try {
               URL url = null;
               url = new URL("https://api.mojang.com/user/profiles/" + uuid.toString().replace("-", "") + "/names");
               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               connection.connect();

               if (connection.getResponseCode() == 400) {
                    System.err.println("There is no player with the UUID \"" + uuid + "\"!");
                    return null;
               }

               InputStream inputStream = connection.getInputStream();
               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

               // Parse JSON response and return name
               JsonElement element = new JsonParser().parse(bufferedReader);
               JsonArray array = element.getAsJsonArray();
               JsonObject object = array.get(0).getAsJsonObject();
               return object.get("name").getAsString();
          } catch (IOException exception) {
               exception.printStackTrace();
          }
          return null;

     }

     public static void sendTop5List(Player player) {
          player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Liste der Top §65 §7Spieler in GunBattle§8:");
          if (instance.listSize() >= 5) {
               for (int i = 1; i < 6; i++) {
                    String name = instance.getPlayerFromRank(i);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Rank§8: §6" + i +
                         " §8| §7Spieler§8: §6" + name +
                         " §8| §7Kills§8: §6" + StatsManager.getKills(name));
               }
          } else {
               for (int i = 1; i < instance.listSize(); i++) {
                    String name = instance.getPlayerFromRank(i);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Rank§8: §6" + i +
                         " §8| §7Spieler§8: §6" + name +
                         " §8| §7Kills§8: §6" + StatsManager.getKills(name));
               }
          }
     }

     public boolean isRegistered(Player player) {
          try {
               ResultSet resultSet = MySQL.getResult("SELECT * FROM gunbattle_stats WHERE UUID = '" + player.getUniqueId() + "'");
               if (resultSet.next()) {
                    return resultSet.getString("UUID") != null;
               }
               resultSet.close();
          } catch (SQLException ignored) {
          }
          return false;
     }

     public boolean isRegisteredUUID(String name) {
          try {
               ResultSet resultSet = MySQL.getResult("SELECT * FROM system_UUID WHERE UUID = '" + UUIDHelper.getUUIDByName(name) + "'");
               return resultSet.next();
          } catch (SQLException ignored) {
          }
          return false;
     }

     public void createPlayer(Player player, String uuid) {
          if (!isRegistered(player)) {
               MySQL.update("INSERT INTO gunbattle_stats(UUID, Kills, Deaths) VALUES ('" + uuid + "', '0', '0')");
          }
          boolean exist = false;
          ResultSet rs = MySQL.getResult("SELECT * FROM system_UUID WHERE UUID = '" + uuid + "'");
          try {
               exist = rs.next();
               rs.close();
          } catch (SQLException e) {
               e.printStackTrace();
          }
          if (!exist) {
               MySQL.update("INSERT INTO system_UUID (Name, UUID) VALUES ('" + player.getName() + "', '" + uuid + "')");
          }
     }

     public Integer getKills(String name) {
          String uuid = UUIDHelper.getUUIDByName(name);
          ResultSet rs = MySQL.getResult("SELECT Kills FROM gunbattle_stats WHERE UUID='" + uuid + "'");
          try {
               if (rs.next()) {
                    return rs.getInt("Kills");
               }
               rs.close();
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return 0;
     }

     public Integer getDeaths(String name) {
          String uuid = UUIDHelper.getUUIDByName(name);
          ResultSet rs = MySQL.getResult("SELECT Deaths FROM gunbattle_stats WHERE UUID='" + uuid + "'");
          try {
               if (rs.next()) {
                    return rs.getInt("Deaths");
               }
               rs.close();
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return 0;
     }

     public double getKD(String uuid) {

          double kills = getKills(uuid);
          double deaths = getDeaths(uuid);

          double kd = 0.00;

          if (deaths == 0) {
               kd = kills / (deaths + 1);
          } else if (kills == 0) {
               kd = (kills + 1) / deaths;
          } else if (kills == 0 && deaths == 0) {
               kd = 0.00;
          } else {
               kd = kills / deaths;
          }

          NumberFormat n = NumberFormat.getInstance();
          n.setMaximumFractionDigits(2);
          return Double.parseDouble(n.format(kd).replace(",", "."));
     }

     public void setKills(String uuid, Integer kills) {
          MySQL.update("UPDATE gunbattle_stats SET Kills= '" + kills + "' WHERE UUID= '" + uuid + "'");
     }

     public void setDeaths(String uuid, Integer deaths) {
          MySQL.update("UPDATE gunbattle_stats SET Deaths= '" + deaths + "' WHERE UUID= '" + uuid + "'");
     }

     public Integer getRanking(String name) {
          int i = -1;
          String uuid = UUIDHelper.getUUIDByName(name);
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM gunbattle_stats ORDER BY Kills DESC");
               while (rs.next()) {
                    if (i == -1) {
                         i = 0;
                    }
                    i++;
                    if (rs.getString("UUID").equalsIgnoreCase(uuid))
                         break;
               }
               rs.close();
          } catch (Exception e) {
               e.printStackTrace();
          }
          return i;
     }

     public String getPlayerFromRank(int rank) {
          List<String> list = new ArrayList<>();
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM gunbattle_stats ORDER BY Kills DESC");

               while (rs.next()) {
                    list.add(rs.getString("UUID"));
               }
               rs.close();
          } catch (SQLException ignored) {

          }
          if (rank <= listSize()) {
               return UUIDHelper.getNameByUUID(list.get(rank - 1));
          } else {
               return null;
          }
     }

     public int listSize() {
          List<String> list = new ArrayList<>();
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM gunbattle_stats ORDER BY Kills DESC");

               while (rs.next()) {
                    list.add(rs.getString("UUID"));
               }
               rs.close();
          } catch (SQLException ignored) {

          }

          return list.size();
     }
}
