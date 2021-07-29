package eu.hypetime.spigot.hypelobby.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/*
    Created by Andre
    At 00:34 Uhr | 28. Juli. 2021
    Project GunBattle
*/
public class UUIDHelper {

     public static HashMap<String, String> uuidCache = new HashMap<>();
     public static HashMap<String, String> nameCache = new HashMap<>();

     public static String getUUIDByName(String name) {
          String returnValue = "";
          if (uuidCache.containsKey(name)) {
               returnValue = uuidCache.get(name);
          } else {
               ResultSet rs = MySQL.getResult("SELECT * FROM system_UUID WHERE Name='" + name + "'");
               try {
                    if (rs.next()) {
                         returnValue = rs.getString("UUID");
                    }
                    rs.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
               uuidCache.put(name, returnValue);
          }
          return returnValue;
     }

     public static String getNameByUUID(String uuid) {
          String returnValue = "";
          if (nameCache.containsKey(uuid)) {
               returnValue = nameCache.get(uuid);
          } else {
               ResultSet rs = MySQL.getResult("SELECT * FROM system_UUID WHERE UUID='" + uuid + "'");
               try {
                    if (rs.next()) {
                         returnValue = rs.getString("Name");
                    }
                    rs.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
               nameCache.put(uuid, returnValue);
          }
          return returnValue;
     }


}
