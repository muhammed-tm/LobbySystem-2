package eu.hypetime.spigot.hypelobby.utils;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
    Created by Andre
    At 12:11 Uhr | 20. Apr.. 2021
    Project HypeLobbySpigot
*/
public class CoinsAPI {

     public static int getCoins(Player player) {
          try {
               PreparedStatement statement = MySQL.connection.prepareStatement("SELECT * FROM Coins WHERE UUID= '" + player.getUniqueId().toString() + "'");
               statement.executeQuery();
               ResultSet result = statement.getResultSet();
               if(result.next()) {
                    return result.getInt("Coins");
               }
               return 0;
          } catch (Exception ex) {
               ex.printStackTrace();
               return 0;
          }
     }

     public static void setCoins(Player player, int coins) {
          try {
               MySQL.update("UPDATE Coins SET Coins= '" + coins + "' WHERE UUID= '" + player.getUniqueId().toString() + "'");
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static void addCoins(Player player, int coins) {
          try {
               coins = coins + getCoins(player);
               MySQL.update("UPDATE Coins SET Coins = '" + coins + "' WHERE UUID= '" + player.getUniqueId().toString() + "'");
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static void removeCoins(Player player, int coins) {
          try {
               coins = coins - getCoins(player);
               MySQL.update("UPDATE Coins SET Coins = '" + coins + "' WHERE UUID= '" + player.getUniqueId().toString() + "'");
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static int getCoins(String name) {
          try {
               ResultSet resultSet = MySQL.getResult("SELECT * FROM Coins WHERE Spielername= '" + name + "'");
               int coins = 0;
               if(resultSet == null) {
                    return coins;
               }
               while(resultSet.next()) {
                    coins = resultSet.getInt("Coins");
               }
               resultSet.close();
               return coins;
          } catch (Exception ex) {
               ex.printStackTrace();
               return 0;
          }
     }

     public static void setCoins(String name, int coins) {
          try {
               MySQL.update("UPDATE Coins SET Coins= '" + coins + "' WHERE Spielername= '" + name + "'");
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static void addCoins(String name, int coins) {
          try {
               coins = coins + getCoins(name);
               MySQL.update("UPDATE Coins SET Coins = '" + coins + "' WHERE Spielername= '" + name + "'");
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static void removeCoins(String name, int coins) {
          try {
               coins = coins - getCoins(name);
               MySQL.update("UPDATE Coins SET Coins = '" + coins + "' WHERE Spielername= '" + name + "'");
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

}
