package eu.hypetime.spigot.hypelobby.utils;

import java.sql.*;

/*
    Created by Andre
    At 11:07 Uhr | 20. Apr.. 2021
    Project HypeLobbySpigot
*/
public class MySQL {

     public static Connection connection;
     public static String host, username, password, database;

     public MySQL(String host, String username, String password, String database) {
          MySQL.host = host;
          MySQL.username = username;
          MySQL.password = password;
          MySQL.database = database;

          connect();
     }

     public void connect() {
          try {
               connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true&useSSL=true", username, password);
               System.out.println("MySQL successfully connected");
               connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS dailyreward (UUID VARCHAR(100), Timestamp VARCHAR(100))");
               connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS npc (Location VARCHAR(6400), NPC VARCHAR(6400))");
          } catch (SQLException ignored) {
          }
     }

     public void disconnect() {
          try {
               connection.close();
          } catch (SQLException ignored) {
          }
     }

     public static ResultSet getResult(String qry) {
          PreparedStatement ps = null;
          ResultSet rs = null;

          try {
               ps = connection.prepareStatement(qry);
               rs = ps.executeQuery();
               return rs;
          } catch (SQLException var4) {
               var4.printStackTrace();
               return null;
          }
     }

     public static void update(String qry) {
          PreparedStatement ps = null;
          try {
               ps = connection.prepareStatement(qry);
               ps.executeUpdate();
          } catch (SQLException var11) {
               var11.printStackTrace();
          } finally {
               try {
                    assert ps != null;
                    ps.close();
               } catch (SQLException var10) {
                    var10.printStackTrace();
               }

          }


     }


}
