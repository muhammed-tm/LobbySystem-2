package eu.hypetime.spigot.hypelobby.utils.clan;

import eu.hypetime.spigot.hypelobby.utils.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ClanSQL {

     public static void createClan(Player player, String clanname, String clantag) {
          MySQL.update(
               "INSERT INTO clansystem_CLAN (LEADERNAME, CLANTAG, CLANNAME, MODS, MEMBER) VALUES ('" + player.getName() + "', '" + clantag + "', '" + clanname + "', '', '');");
          PlayerClanSQL.setClan(player, clanname);
          PlayerClanSQL.setEntered(player);
     }

     public static void deleteClan(Player player) {
          MySQL.update("DELETE FROM clansystem_CLAN WHERE LEADERNAME = '" + player.getName() + "'");
     }

     public static boolean existClanName(String clanname) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_CLAN WHERE CLANNAME = '" + clanname + "';");

               assert rs != null;
               if (rs.next()) {
                    return rs.getString("LEADERNAME") != null;
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return false;
     }

     public static boolean existClanTag(String clantag) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_CLAN WHERE CLANTAG = '" + clantag + "';");

               assert rs != null;
               if (rs.next()) {
                    return rs.getString("CLANTAG") != null;
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return false;
     }

     public static boolean isPlayerLeader(Player player, String clanname) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_CLAN WHERE CLANNAME = '" + clanname + "';");

               assert rs != null;
               if (rs.next()) {
                    return rs.getString("LEADERNAME").equalsIgnoreCase(player.getName());
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return false;
     }

     public static String getMemberListRaw(String name) {
          return String.valueOf(get(name, "CLANNAME", "MEMBER", "clansystem_CLAN"));
     }

     public static String[] getMemberListRawSplited(String name) {
          return String.valueOf(get(name, "CLANNAME", "MEMBER", "clansystem_CLAN")).split(";");
     }

     public static String getLeader(String name) {
          return String.valueOf(get(name, "CLANNAME", "LEADERNAME", "clansystem_CLAN"));
     }

     public static ArrayList<String> getMemberList(String name) {
          String memberList = getMemberListRaw(name);
          ArrayList<String> toreturn = new ArrayList<String>();
          if (memberList.isEmpty())
               return toreturn;
          String[] member = memberList.split(";");
          toreturn.addAll(Arrays.asList(member));
          return toreturn;
     }

     public static int getMembers(String name) {
          String memberList = getMemberListRaw(name);
          if (memberList.isEmpty())
               return 0;
          String[] member = memberList.split(";");
          return member.length;
     }

     public static void addMember(String name, String member) {
          String memberList = getMemberListRaw(name);
          memberList = memberList + member + ";";

          MySQL.update("UPDATE clansystem_CLAN SET MEMBER ='" + memberList + "' WHERE CLANNAME='" + name + "'");
     }

     public static void removeMember(String name, String member) {
          String memberList = getMemberListRaw(name);
          memberList = memberList.replace(member + ";", "");

          MySQL.update("UPDATE clansystem_CLAN SET MEMBER='" + memberList + "' WHERE CLANNAME='" + name + "'");
     }

     //Moderation
     public static String getModListRaw(String name) {
          return String.valueOf(get(name, "CLANNAME", "MODS", "clansystem_CLAN"));
     }

     public static String[] getModListRawSplited(String name) {
          return String.valueOf(get(name, "CLANNAME", "MODS", "clansystem_CLAN")).split(";");
     }


     public static ArrayList<String> getModList(String name) {
          String modList = getModListRaw(name);
          ArrayList<String> toreturn = new ArrayList<String>();
          if (modList.isEmpty())
               return toreturn;
          String[] mods = modList.split(";");
          toreturn.addAll(Arrays.asList(mods));
          return toreturn;
     }

     public static int getMods(String name) {
          String modList = getModListRaw(name);
          if (modList.isEmpty())
               return 0;
          String[] member = modList.split(";");
          return member.length;
     }

     public static void addMod(String name, String mod) {
          String modList = getModListRaw(name);
          modList = modList + mod + ";";

          MySQL.update("UPDATE clansystem_CLAN SET MODS ='" + modList + "' WHERE CLANNAME='" + name + "'");
     }

     public static void removeMod(String name, String mod) {
          String modList = getModListRaw(name);
          modList = modList.replace(mod + ";", "");

          MySQL.update("UPDATE clansystem_CLAN SET MODS ='" + modList + "' WHERE CLANNAME='" + name + "'");
     }

     public static Object get(String whereresult, String where, String select, String database) {

          ResultSet rs = MySQL.getResult("SELECT " + select + " FROM " + database + " WHERE " + where + "='" + whereresult + "'");
          try {
               assert rs != null;
               if (rs.next()) {
                    return rs.getObject(select);
               }
          } catch (SQLException e) {
               return "ERROR";
          }

          return "ERROR";
     }


     public static String getTagByName(String name) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_CLAN WHERE CLANNAME = '" + name + "';");

               assert rs != null;
               if (rs.next()) {
                    return rs.getString("CLANTAG");
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return "§cKein Clan";
     }

     public static String getNameByTag(String tag) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM clansystem_CLAN WHERE CLANTAG = '" + tag + "';");

               assert rs != null;
               if (rs.next()) {
                    return rs.getString("CLANNAME");
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return "§cKein Clan";
     }


}
