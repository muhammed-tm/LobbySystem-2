package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

/*
    Created by Andre
    At 09:37 Uhr | 28. Apr.. 2021
    Project GunBattle - HypeTime
*/
public class StatsManager {

    public static HashMap < String, Integer > killsCache = new HashMap <>();
    public static HashMap < String, Integer > deathsCache = new HashMap <>();

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

    public static void Top3Scheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HypeLobby.getInstance(), () -> {
            if (WarpAPI.getLocation("1") != null) {
                ArmorStand one = ( ArmorStand ) WarpAPI.getLocation("1").getNearbyEntitiesByType(EntityType.ARMOR_STAND.getEntityClass(), 2).stream().findFirst().get();
                if (!SQLStats.getInstance().getPlayerFromRank(0).equals("null")) {
                    one.setItem(EquipmentSlot.HEAD, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(0)).toItemStack());
                    one.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(0));
                } else {
                    one.setItem(EquipmentSlot.HEAD, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                    one.setCustomName("§c???");
                }
            }
            if (WarpAPI.getLocation("2") != null) {
                ArmorStand two = ( ArmorStand ) WarpAPI.getLocation("2").getNearbyEntitiesByType(EntityType.ARMOR_STAND.getEntityClass(), 2).stream().findFirst().get();
                if (!SQLStats.getInstance().getPlayerFromRank(1).equals("null")) {
                    two.setItem(EquipmentSlot.HEAD, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(1)).toItemStack());
                    two.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(1));
                } else {
                    two.setItem(EquipmentSlot.HEAD, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                    two.setCustomName("§c???");
                }
            }
            if (WarpAPI.getLocation("3") != null) {
                ArmorStand three = ( ArmorStand ) WarpAPI.getLocation("3").getNearbyEntitiesByType(EntityType.ARMOR_STAND.getEntityClass(), 2).stream().findFirst().get();
                if (!SQLStats.getInstance().getPlayerFromRank(2).equals("null")) {
                    three.setItem(EquipmentSlot.HEAD, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(2)).toItemStack());
                    three.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(2));
                } else {
                    three.setItem(EquipmentSlot.HEAD, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                    three.setCustomName("§c???");
                }
            }
        }, 20, 20 * 60 * 15);
    }

}
