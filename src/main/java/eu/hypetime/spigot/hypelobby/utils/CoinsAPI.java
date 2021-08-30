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
               PreparedStatement statement = MySQL.connection.prepareStatement("SELECT * FROM Coins WHERE UUID= '" + player.getUniqueId() + "'");
               statement.executeQuery();
               ResultSet result = statement.getResultSet();
               if (result.next()) {
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
               MySQL.update("UPDATE Coins SET Coins= '" + coins + "' WHERE UUID= '" + player.getUniqueId() + "'");
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static void addCoins(Player player, int coins) {
          try {
               int newCoins = ((getCoins(player)) + (coins));
               setCoins(player, newCoins);
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static void removeCoins(Player player, int coins) {
          try {
               int newCoins = ((getCoins(player)) - (coins));
               setCoins(player, newCoins);
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

}
