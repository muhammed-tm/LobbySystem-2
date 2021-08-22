package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
    Created by Andre
    At 11:08 Uhr | 22. Apr.. 2021
    Project GetDown
*/
public class SQLStats {

    private static SQLStats instance;

    public SQLStats() {
        instance = this;
    }

    public static SQLStats getInstance() {
        return instance;
    }

    public static void sendTop5List(Player player) {
        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Liste der Top §65 §7Spieler in GunBattle§8:");
        if (instance.listSize() >= 5) {
            for (int i = 0; i < 6; i++) {
                String name = instance.getPlayerFromRank(i);
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Rank§8: §6" + (i + 1) +
                        " §8| §7Spieler§8: §6" + name +
                        " §8| §7Kills§8: §6" + StatsManager.getKills(name));
            }
        } else {
            for (int i = 0; i < instance.listSize(); i++) {
                String name = instance.getPlayerFromRank(i);
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Rank§8: §6" + (i + 1) +
                        " §8| §7Spieler§8: §6" + name +
                        " §8| §7Kills§8: §6" + StatsManager.getKills(name));
            }
        }
    }

    public boolean isRegistered(Player player) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM gunbattle_stats WHERE UUID = '" + player.getUniqueId() + "'");
            if (resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
            resultSet.close();
        } catch (SQLException ignored) {
        }
        return false;
    }

    public boolean isRegisteredName(String name) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM gunbattle_stats WHERE UUID = '" + UUIDHelper.fetchUUID(name).toString() + "'");
            if (resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
            resultSet.close();
        } catch (SQLException ignored) {
        }
        return false;
    }


    public void createPlayer(Player player, String uuid) {
        if (!isRegistered(player)) {
            MySQL.update("INSERT INTO gunbattle_stats(UUID, Kills, Deaths) VALUES ('" + uuid + "', '0', '0')");
        }
    }

    public Integer getKills(String name) {
        String uuid = UUIDHelper.fetchUUID(name).toString();
        ResultSet rs = MySQL.getResult("SELECT Kills FROM gunbattle_stats WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getInt("Kills");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Integer getDeaths(String name) {
        String uuid = UUIDHelper.fetchUUID(name).toString();
        ResultSet rs = MySQL.getResult("SELECT Deaths FROM gunbattle_stats WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getInt("Deaths");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getKD(String uuid) {

        double kills = getKills(uuid);
        double deaths = getDeaths(uuid);

        double kd = 0.00;

        if (deaths == 0) {
            kd = kills / (deaths + 1);
        } else if (kills == 0) {
            kd = (kills + 1) / deaths;
        } else if (kills == 0 && deaths == 0) {
            kd = 0.00;
        } else {
            kd = kills / deaths;
        }

        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(2);
        return Double.parseDouble(n.format(kd).replace(",", "."));
    }

    public void setKills(String uuid, Integer kills) {
        MySQL.update("UPDATE gunbattle_stats SET Kills= '" + kills + "' WHERE UUID= '" + uuid + "'");
    }

    public void setDeaths(String uuid, Integer deaths) {
        MySQL.update("UPDATE gunbattle_stats SET Deaths= '" + deaths + "' WHERE UUID= '" + uuid + "'");
    }

    public Integer getRanking(String name) {
        int i = -1;
        String uuid = UUIDHelper.fetchUUID(name).toString();
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM gunbattle_stats ORDER BY Kills DESC");
            while (rs.next()) {
                if (i == -1) {
                    i = 0;
                }
                i++;
                if (rs.getString("UUID").equalsIgnoreCase(uuid))
                    break;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public String getPlayerFromRank(int rank) {
        if (rank <= listSize()) {
            List<String> list = new ArrayList <>();
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM gunbattle_stats ORDER BY Kills DESC");

                while (rs.next()) {
                    list.add(rs.getString("UUID"));
                }
                rs.close();
            } catch (SQLException ignored) {

            }
            return UUIDHelper.fetchName(UUID.fromString(list.get(rank)));
        } else {
            return "null";
        }
    }

    public int listSize() {
        List < String > list = new ArrayList <>();
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM gunbattle_stats ORDER BY Kills DESC");

            while (rs.next()) {
                list.add(rs.getString("UUID"));
            }
            rs.close();
        } catch (SQLException ignored) {

        }

        return list.size();
    }
}
