package eu.hypetime.spigot.hypelobby.utils;

import java.sql.*;
import java.util.UUID;

/*
    Created by Andre
    At 11:07 Uhr | 20. Apr.. 2021
    Project HypeLobbySpigot
*/
public class MySQL {

    public static Connection connection;
    public static String host, username, password, database;

    public MySQL(String host, String username, String password, String database) {
        MySQL.host = host;
        MySQL.username = username;
        MySQL.password = password;
        MySQL.database = database;

        connect();
    }


    public static ResultSet getResult(String qry) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(qry);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static void update(String qry) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();
            } catch (SQLException var10) {
                var10.printStackTrace();
            }

        }


    }

    public static int getFreeTickets(UUID uuid) {

        try {
            if (isExists(uuid)) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Tickets WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ResultSet result = ps.executeQuery();
                result.next();
                int coins = result.getInt("FREE");
                result.close();
                ps.close();
                return coins;
            } else {
                createPlayer(uuid);
                getFreeTickets(uuid);
            }
        } catch (Exception ex) {
            System.out.println("NULL FORM TRY {} CATCH");
            return 0;
        }
        return 0;
    }

    public static boolean isExists(UUID uuid) {

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Tickets WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet result = ps.executeQuery();
            boolean isExisting = result.next();
            result.close();
            ps.close();
            return isExisting;
        } catch (SQLException localSQLException) {
        }
        return false;
    }

    public static boolean isExist(UUID uuid) throws SQLException {
        ResultSet rs = getResult("SELECT * FROM Teleporter WHERE UUID= '" + uuid + "'");
        if (rs.next()) {
            return rs.getBoolean("hotbar");
        }
        return false;
    }

    public static void createPlayer(UUID uuid) throws SQLException {
        // INSERT INTO `Stats`(`Name`, `UUID`, `Kills`, `Deathes`) VALUES
        // ([value-1],[value-2],[value-3],[value-4])
         if (!isExists(uuid)) {
            update("INSERT INTO Tickets(UUID, FREE) VALUES ('" + uuid + "', '0')");
        }
    }

    public static void setFreeTickets(UUID uuid, int tickets) {
        try {
            if (connection != null) {
                if (isExists(uuid)) {
                    // update("INSERT INTO rewards(UUID, LAST) VALUES ('" + UUID
                    // + "', '0')");
                    update("UPDATE Tickets SET FREE= '" + tickets + "' WHERE UUID= '" + uuid + "'");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addFreeTickets(UUID uuid, int tickets) {
        int i = getFreeTickets(uuid);
        setFreeTickets(uuid, tickets + i);
    }

    public static boolean isExistPlayerLocation(UUID uuid) throws SQLException {

        ResultSet rs = getResult("SELECT * FROM Location WHERE UUID= '" + uuid + "'");

        return rs.next();
    }

    public static boolean isExistReward(UUID uuid) throws SQLException {
        ResultSet rs = getResult("SELECT * FROM Reward WHERE UUID='" + uuid + "'");
        return rs.next();
    }

    public static void removeFreeTickets(UUID uuid, int tickets) {
        int i = getFreeTickets(uuid);
        setFreeTickets(uuid, i - tickets);
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true&useSSL=true", username, password);
            System.out.println("MySQL successfully connected");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS dailyreward (UUID VARCHAR(100), Spielerwaiting VARCHAR(100), Hyperwaiting VARCHAR(100), Warriorwaiting VARCHAR(100), Vipwaiting VARCHAR(100), Mediawaiting VARCHAR(100))");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS npc (Location VARCHAR(6400), NPC VARCHAR(6400))");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS lobby_gadgets(UUID VARCHAR(64) UNIQUE, pets TEXT, gadgets TEXT, particle TEXT, boots TEXT)");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Tickets(UUID varchar(64), FREE int);");

        } catch (SQLException ignored) {
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
    }
}
