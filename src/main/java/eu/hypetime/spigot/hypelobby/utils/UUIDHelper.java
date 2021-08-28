package eu.hypetime.spigot.hypelobby.utils;

import java.util.UUID;

/*
    Created by Andre
    At 00:34 Uhr | 28. Juli. 2021
    Project GunBattle
*/
public class UUIDHelper {

     public static String fetchName(UUID uuid) {
          return UUIDFetcher.getName(uuid);
     }

     public static UUID fetchUUID(String playerName) {
          return UUIDFetcher.getUUID(playerName);
     }

}
