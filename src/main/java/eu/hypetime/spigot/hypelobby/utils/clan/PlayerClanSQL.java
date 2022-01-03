package eu.hypetime.spigot.hypelobby.utils.clan;

import eu.hypetime.spigot.hypelobby.utils.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PlayerClanSQL {

     public static void registerPlayer(Player player) {

          if (!existPlayer(player.getUniqueId().toString())) {
               MySQL.update(
                    "INSERT INTO clansystem_PLAYERCLAN (PLAYER, UUID, CLAN, ENTERED, REQUESTS) VALUES ('" + player.getName() + "', '" + player.getUniqueId() + "', '', ';', ';');");
          } else {
               MySQL.update("UPDATE clansystem_PLAYERCLAN SET PLAYER = '" + player.getName() + "' WHERE UUID = '"
                    + player.getUniqueId() + "';");
          }

     }

     public static String getClanName(Player player) {
          String clan = "§cKein Clan";
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_PLAYERCLAN WHERE UUID = '" + player.getUniqueId() + "';");

               if ((!rs.next()) || (String.valueOf(rs.getString("CLAN")).equals(""))) {
                    clan = "§cKein Clan";
               } else {
                    clan = "§e" + rs.getString("CLAN");
               }
          } catch (SQLException ignored) {

          }
          return clan;
     }

     public static String getClanTag(Player player) {
          String clan = "§cKein Clan";
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_CLAN WHERE CLANNAME = '" + getClanNameExact(player) + "';");

               if ((!rs.next()) || (String.valueOf(rs.getString("CLANNAME")).equals(""))) {
                    clan = "§cKein Clan";
               } else {
                    clan = "§e" + rs.getString("CLANTAG");
               }
          } catch (SQLException ignored) {

          }
          return clan;
     }

     public static String getClanTagExact(Player player) {
          String clan = "Kein Clan";
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_CLAN WHERE CLANNAME = '" + getClanNameExact(player) + "';");

               if ((!rs.next()) || (String.valueOf(rs.getString("CLANNAME")).equals(""))) {
                    clan = "Kein Clan";
               } else {
                    clan = rs.getString("CLANTAG");
               }
          } catch (SQLException ignored) {

          }
          return clan;
     }

     public static String getClanNameExact(Player player) {
          String clan = "Kein Clan";
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_PLAYERCLAN WHERE UUID = '" + player.getUniqueId() + "';");

               if ((!rs.next()) || (String.valueOf(rs.getString("CLAN")).equals(""))) {
                    clan = "Kein Clan";
               } else {
                    clan = rs.getString("CLAN");
               }
          } catch (SQLException ignored) {

          }
          return clan;
     }

     public static boolean existPlayer(String uuid) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_PLAYERCLAN WHERE UUID = '" + uuid + "';");

               assert rs != null;
               if (rs.next()) {
                    return rs.getString("PLAYER") != null;
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return false;
     }

     public static void setEntered(Player player) {
          String simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
          MySQL.update("UPDATE clansystem_PLAYERCLAN SET ENTERED = '" + simpleDateFormat + "' WHERE UUID = '"
               + player.getUniqueId() + "';");
     }

     public static void resetEntered(Player player) {
          MySQL.update("UPDATE clansystem_PLAYERCLAN SET ENTERED = ';' WHERE UUID = '"
               + player.getUniqueId() + "';");
     }

     public static String getEntered(Player player) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_PLAYERCLAN WHERE UUID = '" + player.getUniqueId() + "';");

               if (rs != null) {
                    if (rs.next()) {
                         if (!rs.getString("ENTERED").equals(";")) {
                              String[] date = rs.getString("ENTERED").split("\\.");
                              return "§7Der Spieler ist am §a" + date[0] + "§8.§a" + date[1] + "§8.§a" + date[2] + " §7um §a" + date[3] + "§8:§a" + date[4] + " §7dem Clan beigetreten";
                         } else {
                              return "§7Aktuell ist der Spieler in §ckeinem §7Clan§8.";
                         }
                    } else {
                         return "§7Aktuell ist der Spieler in §ckeinem §7Clan§8.";
                    }
               }
          } catch (SQLException ignored) {

          }
          return "§7Aktuell ist der Spieler in §ckeinem §7Clan§8.";
     }

     public static String getEntered(String name) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_PLAYERCLAN WHERE PLAYER = '" + name + "';");

               if (rs != null) {
                    if (rs.next()) {
                         if (!rs.getString("ENTERED").equals(";")) {
                              String[] date = rs.getString("ENTERED").split("\\.");
                              return "§7Der Spieler ist am §a" + date[0] + "§8.§a" + date[1] + "§8.§a" + date[2] + " §7um §a" + date[3] + "§8:§a" + date[4] + " §7dem Clan beigetreten";
                         } else {
                              return "§7Aktuell ist der Spieler in §ckeinem §7Clan§8.";
                         }
                    } else {
                         return "§7Aktuell ist der Spieler in §ckeinem §7Clan§8.";
                    }
               }
          } catch (SQLException ignored) {

          }
          return "§7Aktuell ist der Spieler in §ckeinem §7Clan§8.";
     }

     public static String[] getEnteredDate(String name) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_PLAYERCLAN WHERE PLAYER = '" + name + "';");

               if (rs != null) {
                    if (rs.next()) {
                         if (!rs.getString("ENTERED").equals(";")) {
                              return rs.getString("ENTERED").split("\\.");
                         } else {
                              return new String[]{"§7Aktuell ist der Spieler in §ckeinem §7Clan§8."};
                         }
                    } else {
                         return new String[]{"§7Aktuell ist der Spieler in §ckeinem §7Clan§8."};
                    }
               }
          } catch (SQLException ignored) {

          }
          return new String[]{"§7Aktuell ist der Spieler in §ckeinem §7Clan§8."};

     }

     public static void setClan(Player player, String clanname) {
          if (!existPlayer(player.getUniqueId().toString())) {
               registerPlayer(player);
          }
          MySQL.update("UPDATE clansystem_PLAYERCLAN SET CLAN = '" + clanname + "' WHERE UUID = '"
               + player.getUniqueId() + "';");
     }

     public static void resetClan(Player player) {
          if (!existPlayer(player.getUniqueId().toString())) {
               registerPlayer(player);
          }
          MySQL.update("UPDATE clansystem_PLAYERCLAN SET CLAN = '' WHERE UUID = '"
               + player.getUniqueId() + "';");
     }

     public static String getRequestsListRaw(String name) {
          return String.valueOf(get(name, "PLAYER", "REQUESTS", "clansystem_PLAYERCLAN"));
     }

     public static ArrayList<String> getRequestsList(String name) {
          String requestsList = getRequestsListRaw(name);
          ArrayList<String> toreturn = new ArrayList<String>();
          if (requestsList.isEmpty())
               return toreturn;
          String[] member = requestsList.split(";");
          toreturn.addAll(Arrays.asList(member));
          return toreturn;
     }

     public static int getRequests(String name) {
          String requestsList = getRequestsListRaw(name);
          if (requestsList.isEmpty())
               return 0;
          String[] requests = requestsList.split(";");
          return requests.length;
     }

     public static void addRequest(String name, String clan) {
          String requestsList = getRequestsListRaw(name);
          if (!requestsList.contains(name)) {
               requestsList = requestsList + clan + ";";

               MySQL.update("UPDATE clansystem_PLAYERCLAN SET REQUESTS='" + requestsList + "' WHERE PLAYER='" + name + "'");
          }
     }

     public static void removeRequest(String name, String clan) {
          String requestsList = getRequestsListRaw(name);
          requestsList = requestsList.replace(clan + ";", "");

          MySQL.update("UPDATE clansystem_PLAYERCLAN SET REQUESTS='" + requestsList + "' WHERE PLAYER='" + name + "'");
     }

     public static Object get(String whereresult, String where, String select, String database) {

          ResultSet rs = MySQL.getResult("SELECT " + select + " FROM " + database + " WHERE " + where + "='" + whereresult + "'");
          try {
               assert rs != null;
               if (rs.next()) {
                    return rs.getObject(select);
               }
          } catch (SQLException e) {
               return "ERROR";
          }

          return "ERROR";
     }

     public static boolean isInClan(Player player) {
          return !getClanName(player).equals("§cKein Clan");
     }

}
