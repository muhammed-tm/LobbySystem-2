package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.UUID;

public class RewardAPI {

    public static Config rewarduuid = new Config(HypeLobby.getInstance().getDataFolder().getAbsolutePath(), "reward.yml");

    public static void setUUID(Player player, RewardType type) {
        UUID uuid = player.getUniqueId();

        rewarduuid.setValue(uuid + "." + type.name + ".waiting", System.currentTimeMillis() + type.time);
        rewarduuid.save();
    }

    public static boolean canReward(Player player, RewardType type) {
        return rewarduuid.getLong(player.getUniqueId() + "." + type.name + ".waiting") < System.currentTimeMillis();
    }

    public static String getRemaining(Player player, RewardType type) {
        long millis = rewarduuid.getLong(player.getUniqueId() + "." + type.name + ".waiting") - System.currentTimeMillis();
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
