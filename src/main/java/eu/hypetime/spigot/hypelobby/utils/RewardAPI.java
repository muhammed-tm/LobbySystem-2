package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RewardAPI {

    public static HashMap<Player, Map<String, Long>> rewardTime = new HashMap<>();

    public static void createIfNotExist(Player player) {
        ResultSet rs = MySQL.getResult("SELECT * FROM dailyreward WHERE UUID = '" + player.getUniqueId() + "'");

        boolean isExist = false;

        try {
            if(rs.next()) {
                isExist = rs.getString("UUID") != null;
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
            isExist = false;
        }
        if(!isExist) {
            MySQL.update("INSERT INTO dailyreward (UUID, Spielerwaiting, Hyperwaiting, Warriorwaiting, Vipwaiting, Mediawaiting) " +
                 "VALUES ('" + player.getUniqueId() + "', '0', '0', '0', '0', '0')");
        }
    }

    public static Long getTime(Player player, RewardType type) {
        Long time = 0L;
        if(!rewardTime.containsKey(player)) {
            ResultSet rs = MySQL.getResult("SELECT * FROM dailyreward WHERE UUID = '" + player.getUniqueId() + "'");
            try {
                if (rs.next()) {
                    time = rs.getLong(type.name + "waiting");
                    Map<String, Long> reward = new HashMap<>();
                    reward.put(type.name, (type.time + System.currentTimeMillis()));
                    return time;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
                time = 0L;
                return time;
            }
        } else {
            Map<String, Long> reward =  rewardTime.get(player);
            time = reward.get(player);
            return time;
        }
        return 0L;
    }


    public static void setUUID(Player player, RewardType type) {
        MySQL.update("UPDATE dailyreward SET " + type.name + "waiting = '" + (System.currentTimeMillis() + type.time) + "' WHERE UUID = '" + player.getUniqueId() + "'");
        Map<String, Long> reward = new HashMap<>();
        reward.put(type.name, (System.currentTimeMillis() + type.time));
        rewardTime.put(player, reward);
    }

    public static boolean canReward(Player player, RewardType type) {
        return getTime(player, type) < System.currentTimeMillis();
    }

    public static String getRemaining(Player player, RewardType type) {
        long millis = getTime(player, type) - System.currentTimeMillis();
        long seconds = millis / 1000L;
        long minutes = 0L;
        while (seconds > 60L) {
            seconds -= 60;
            minutes++;
        }
        long hours = 0L;
        while (minutes > 60L) {
            minutes -= 60;
            hours++;
        }
        return "§e" + hours + " §7Stunde(n) §e" + minutes + " §7Minute(n) §e" + seconds + " §7Sekunden(n)";
    }
}
