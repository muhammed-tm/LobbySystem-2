package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/*
    Created by Andre
    At 09:37 Uhr | 28. Apr.. 2021
    Project GunBattle - HypeTime
*/
public class StatsManager {

     public static HashMap<String, Integer> killsCache = new HashMap<>();
     public static HashMap<String, Integer> deathsCache = new HashMap<>();

     public static void updatePlayer(Player player) {
          String name = player.getName();
          killsCache.put(name, SQLStats.getInstance().getKills(name));
          deathsCache.put(name, SQLStats.getInstance().getDeaths(name));
     }

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

     /*public static void Top3Scheduler() {
          Bukkit.getScheduler().scheduleSyncRepeatingTask(HypeLobby.getInstance(), () -> {
               if (WarpAPI.getLocation("1") != null) {
                    if (!WarpAPI.getLocation("1").getNearbyEntities(1, 1, 1).stream().filter(entity -> entity.getType() == EntityType.ARMOR_STAND).findFirst().isPresent()) {
                         ArmorStand one = (ArmorStand) WarpAPI.getLocation("1").getWorld().spawn(WarpAPI.getLocation("1"), ArmorStand.class);
                         if (!SQLStats.getInstance().getPlayerFromRank(0).equals("null")) {
                              one.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(0)).toItemStack());
                              one.setCustomNameVisible(true);
                              one.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(0));
                         } else {
                              one.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                              one.setCustomNameVisible(true);
                              one.setCustomName("§c???");
                         }
                         one.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                         one.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                         one.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                    }
               }
               if (WarpAPI.getLocation("2") != null) {
                    if (!WarpAPI.getLocation("2").getNearbyEntities(1, 1, 1).stream().filter(entity -> entity.getType() == EntityType.ARMOR_STAND).findFirst().isPresent()) {
                         ArmorStand two = (ArmorStand) WarpAPI.getLocation("2").getWorld().spawn(WarpAPI.getLocation("2"), ArmorStand.class);
                         if (!SQLStats.getInstance().getPlayerFromRank(1).equals("null")) {
                              two.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(1)).toItemStack());
                              two.setCustomNameVisible(true);
                              two.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(1));
                         } else {
                              two.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                              two.setCustomName("§c???");
                         }
                         two.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                         two.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                         two.setBoots(new ItemStack(Material.IRON_BOOTS));
                    }
               }
               if (WarpAPI.getLocation("3") != null) {
                    if (!WarpAPI.getLocation("3").getNearbyEntities(1, 1, 1).stream().filter(entity -> entity.getType() == EntityType.ARMOR_STAND).findFirst().isPresent()) {
                         ArmorStand three = (ArmorStand) WarpAPI.getLocation("3").getWorld().spawn(WarpAPI.getLocation("3"), ArmorStand.class);
                         if (!SQLStats.getInstance().getPlayerFromRank(2).equals("null")) {
                              three.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(2)).toItemStack());
                              three.setCustomNameVisible(true);
                              three.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(2));
                         } else {
                              three.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                              three.setCustomNameVisible(true);
                              three.setCustomName("§c???");
                         }
                         three.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                         three.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                         three.setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                    }
               }
          }, 20, 20 * 60 * 15);
     }
*/
}
