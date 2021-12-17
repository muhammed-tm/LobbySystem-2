package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class PlayerManager {
    public static boolean hasAccept(Player player) {
        boolean returnValue = false;
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM proxy_PlayerData WHERE UUID = '" + player.getUniqueId().toString() + "'");
            if(result == null) returnValue = false;
            if (result.next()) {
                returnValue = result.getString("DSGVO").equalsIgnoreCase("true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public static void setAccept(Player player) {
        MySQL.update("UPDATE proxy_PlayerData SET DSGVO = 'true' WHERE UUID = '" + player.getUniqueId().toString() + "'");
    }

    public static void setDeny(Player player) {
        MySQL.update("UPDATE proxy_PlayerData SET DSGVO = 'deny' WHERE UUID = '" + player.getUniqueId().toString() + "'");
    }
}
