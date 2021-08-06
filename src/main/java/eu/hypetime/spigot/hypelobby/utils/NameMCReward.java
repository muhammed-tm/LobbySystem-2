package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NameMCReward {
    public static boolean check(Player player) {
        try {
            final URL url = new URL(
                    "https://api.namemc.com/server/hypetime.eu/likes?profile=" + player.getUniqueId().toString());
            try {
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                final BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = null;
                boolean ret = false;
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.toLowerCase();
                    if (line.contains("true")) {
                        ret = true;
                        break;
                    }
                }
                bufferedReader.close();
                return ret;
            } catch (IOException exception1) {
                exception1.printStackTrace();
                return hasAccept(player);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static boolean hasAccept(Player player) {
        ResultSet result = null;
        try {
            result =  MySQL.getResult("SELECT * FROM proxy_Rewards WHERE UUID = '" + player.getUniqueId().toString() + "'");
            assert result != null;
            if (result.next()) {
                return result.getString("NameMC").equalsIgnoreCase("true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert result != null;
                result.close();
            } catch (SQLException ignored) {

            }
        }
        return false;
    }
}
