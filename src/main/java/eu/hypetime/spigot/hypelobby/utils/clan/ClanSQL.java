package eu.hypetime.spigot.hypelobby.utils.clan;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.MySQL;
import org.bukkit.Bukkit;
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
          String[] memberlist = getMemberListRawSplited(name);
          ArrayList<String> toreturn = new ArrayList<>();
          if (memberlist.length == 0)
               return toreturn;
          toreturn.addAll(Arrays.asList(memberlist));
          return toreturn;
     }

     public static int getMembers(String name) {
          String memberList = getMemberListRaw(name);
          if (memberList.isEmpty())
               return 0;
          String[] member = memberList.split(";");
          return member.length;
     }

     public static ArrayList<String> getAllUser(String name) {
          ArrayList<String> all = new ArrayList<>();
          all.addAll(getModList(name));
          all.addAll(getMemberList(name));
          return all;
     }

     public static void promote(Player player, String clanname, String name) {
          if (!ClanSQL.getModList(clanname).contains(name)) {
               if (ClanSQL.getMemberList(clanname).contains(name)) {
                    ClanSQL.removeMember(clanname, name);
                    ClanSQL.addMod(clanname, name);
                    if (Bukkit.getPlayer(name) != null) {
                         Player target = Bukkit.getPlayer(name);
                         message(target, "§7Du wurdest zu §cMod §ahochgestuft§8. §aClan§8: §e" + clanname);
                         title(target, "§7Du wurdest zu §cMod", "§ahochgestuft");
                    }
                    message(player, "§7Du hast den Spieler §aerfolgreich §7zu §cMod §ahochgestuft§8.");
                    title(player, "§7Du hast §e" + name + " §7zu §cMod", "§ahochgestuft");
               } else if(isPlayerLeader(player, clanname)) {
                    message(player, "§7Du kannst dich nicht selbst promoten§8.");
               } else {
                    message(player, "§7Dieser Spieler ist §cnicht §7in deinem Clan§8.");
               }
          } else {
               message(player, "§7Der Spieler ist §cbereits §7Mod§8.");
          }
     }

     public static void demote(Player player, String clanname, String name) {
          if (ClanSQL.getModList(clanname).contains(name)) {
               if (!ClanSQL.getMemberList(clanname).contains(name)) {
                    ClanSQL.removeMod(clanname, name);
                    ClanSQL.addMember(clanname, name);
                    if (Bukkit.getPlayer(name) != null) {
                         Player target = Bukkit.getPlayer(name);
                         message(target, "§7Du wurdest zu Member §cdegradiert§8.\n §aClan§8: §e" + clanname);
                         title(target, "§7Du wurdest zu Member", "§cdegradiert");
                    }
                    message(player, "§7Du hast den Spieler §aerfolgreich §7zu Member §cdegradiert§8.");
                    title(player, "§7Du hast §e" + name + " §7zu Member", "§cdegradiert");
               } else if(isPlayerLeader(player, clanname)) {
                    message(player, "§7Du kannst dich nicht selbst promoten§8.");
               } else {
                    message(player, "§7Dieser Spieler ist §cnicht §7in deinem Clan§8.");
               }
          } else {
               message(player, "§7Der Spieler ist §ckein §7Mod§8.");
          }
     }

     public static void kick(Player player, String clanname, String name) {
          if (ClanSQL.getMemberList(clanname).contains(name)) {
               PlayerClanSQL.resetClan(name);
               PlayerClanSQL.resetEntered(name);
               ClanSQL.removeMember(clanname, name);
               if(Bukkit.getPlayer(name) != null) {
                    Player target = Bukkit.getPlayer(name);
                    message(target, "§7Du §7wurdest §7aus §7dem §7Clan §cgekickt§8.");
                    title(target, "§7Du §7wurdest §7aus §7dem §7Clan", "§cgekickt");
               }
               message(player, "§7Du §7hast §7den §7Spieler §aerfolgreich §cgekickt§8.");
               title(player, "§7Du §7hast §7den §7Spieler §aerfolgreich", "§cgekickt");
          } else {
               if (ClanSQL.getModList(clanname).contains(name)) {
                    message(player, "§7Diesen §7Spieler §7kannst §7du §cnicht §7kicken §7da §7er §cMod §7ist§8.");
               } else if(isPlayerLeader(player, clanname)) {
                    message(player, "§7Du kannst dich nicht selbst kicken§8.");
               }
          }
     }

     public static void title(Player player, String title, String subtitle) {
          player.sendTitle(title, subtitle);
     }

     public static void message(Player player, String message) {
          player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + message);
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
