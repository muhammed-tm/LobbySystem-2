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

    public static void Top3Scheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HypeLobby.getInstance(), () -> {
            if (WarpAPI.getLocation("1") != null) {
                ArmorStand placeholder = (ArmorStand) WarpAPI.getLocation("2").getWorld().spawn(WarpAPI.getLocation("2"), ArmorStand.class);
                placeholder.setInvisible(true);
                ArmorStand one = (ArmorStand) placeholder.getNearbyEntities(1.5, 1.5, 1.5).stream().filter(entity -> entity != placeholder).findFirst().get();
                placeholder.remove();
                if (!SQLStats.getInstance().getPlayerFromRank(0).equals("null")) {
                    one.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(0)).toItemStack());
                    one.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(0));
                } else {
                    one.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                    one.setCustomName("§c???");
                }
            }
            if (WarpAPI.getLocation("2") != null) {
                ArmorStand placeholder = (ArmorStand) WarpAPI.getLocation("2").getWorld().spawn(WarpAPI.getLocation("2"), ArmorStand.class);
                placeholder.setInvisible(true);
                ArmorStand two = (ArmorStand) placeholder.getNearbyEntities(1.5, 1.5, 1.5).stream().filter(entity -> entity != placeholder).findFirst().get();
                placeholder.remove();

                if (!SQLStats.getInstance().getPlayerFromRank(1).equals("null")) {
                    two.setHelmet( new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(1)).toItemStack());
                    two.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(1));
                } else {
                    two.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                    two.setCustomName("§c???");
                }

            }
            if (WarpAPI.getLocation("3") != null) {
                ArmorStand placeholder = (ArmorStand) WarpAPI.getLocation("3").getWorld().spawn(WarpAPI.getLocation("3"), ArmorStand.class);
                placeholder.setInvisible(true);
                ArmorStand three = (ArmorStand) placeholder.getNearbyEntities(1.5, 1.5, 1.5).stream().filter(entity -> entity != placeholder).findFirst().get();
                placeholder.remove();
                if (!SQLStats.getInstance().getPlayerFromRank(2).equals("null")) {
                    three.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(SQLStats.getInstance().getPlayerFromRank(2)).toItemStack());
                    three.setCustomName("§6" + SQLStats.getInstance().getPlayerFromRank(2));
                } else {
                    three.setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("MHF_Question").toItemStack());
                    three.setCustomName("§c???");
                }
            }
        }, 20, 20 * 60 * 15);
    }

}
