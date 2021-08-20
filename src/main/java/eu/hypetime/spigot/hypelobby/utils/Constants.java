package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;

/*
    Created by Andre
    At 21:47 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/
public class Constants {

     private final String prefix;
     private final String pvpWorld;

     public Constants(HypeLobby hypeLobby) {
          Config config = hypeLobby.getConfigFile();
          config.addDefault("prefix", "&6HypeTime &8| &7");
          config.addDefault("pvpworld", "pvp");
          config.copyDefaults();
          prefix = config.getString("prefix").replace("&", "§");
          pvpWorld = config.getString("pvpworld");
          new MySQL("185.194.236.246", "muhammedtuerkmen_minecraft", "a73n0bX&", "muhammedtuerkmen_minecraft");
     }

     public String getPrefix() {
          return prefix;
     }

     public String getPVPWorld() {
          return pvpWorld;
     }
}
