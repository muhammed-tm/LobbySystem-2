package eu.hypetime.spigot.hypelobby.utils;

import java.util.HashMap;

/*
    Created by Andre
    At 09:37 Uhr | 28. Apr.. 2021
    Project GunBattle - HypeTime
*/
public class StatsManager {

     public static HashMap<String, Integer> killsCache = new HashMap<>();
     public static HashMap<String, Integer> deathsCache = new HashMap<>();

     public static int getKills(String name) {
          if (!killsCache.containsKey(name)) {
               killsCache.put(name, SQLStats.getInstance().getKills(name));
          }
          return killsCache.get(name);
     }

     public static int getDeaths(String name) {
          if (!deathsCache.containsKey(name)) {
               deathsCache.put(name, SQLStats.getInstance().getDeaths(name));
          }
          return deathsCache.get(name);

     }

}
