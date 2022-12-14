package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.clan.ClanSQL;
import eu.hypetime.spigot.hypelobby.utils.clan.PlayerClanSQL;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

public class ScoreAPI {

     static int count = 0;
     static HashMap<Player, String> rang = new HashMap<>();

     public static void setScoreboard(Player player) {
          Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
          Objective obj = sb.getObjective("aaa");
          if (obj == null) {
               obj = sb.registerNewObjective("aaa", "bbb", "ccc");
          }
          obj.setDisplayName("§7» §6HypeTime §7«");
          obj.setDisplaySlot(DisplaySlot.SIDEBAR);

          for (Player online : Bukkit.getOnlinePlayers()) {
               IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(online.getUniqueId());
               String group = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(user).getName().toLowerCase();
               PlayerInventory inventory = player.getInventory();
               rang.put(player, group);
               if (group.equals("administrator")) {
                    registerTeamEntry(sb, "0001Admin", ChatColor.DARK_RED, "§4Admin §8× ", "§7", online);
               } else if (group.equals("manager")) {
                    registerTeamEntry(sb, "0002Manager", ChatColor.DARK_RED, "§4Manager §8× ", "§7", online);
               } else if (group.equals("srstaff")) {
                    registerTeamEntry(sb, "0003SrStaff", ChatColor.RED, "§cSrStaff §8× ", "§7", online);
               } else if (group.equals("staff")) {
                    registerTeamEntry(sb, "0004Staff", ChatColor.RED, "§cStaff §8× ", "§7", online);
               } else if (group.equals("partner")) {
                    registerTeamEntry(sb, "0005Partner", ChatColor.GREEN, "§aPartner §8× ", "§7", online);
               } else if (group.equals("mediaplus")) {
                    registerTeamEntry(sb, "0006MediaPlus", ChatColor.BLACK, "§0Media+ §8× ", "§7", online);
               } else if (group.equals("media")) {
                    registerTeamEntry(sb, "0007Media", ChatColor.DARK_PURPLE, "§5Media §8× ", "§7", online);
               } else if (group.equals("vip")) {
                    registerTeamEntry(sb, "0008Vip", ChatColor.GOLD, "§6VIP §8× ", "§7", online);
               } else if (group.equals("warrior")) {
                    registerTeamEntry(sb, "0009Warrior", ChatColor.LIGHT_PURPLE, "§dWarrior §8× ", "§7", online);
               } else if (group.equals("hyper")) {
                    registerTeamEntry(sb, "0010Hyper", ChatColor.YELLOW, "§eHyper §8× ", "§7", online);
               } else if (group.equals("default")) {
                    registerTeamEntry(sb, "0011Spieler", ChatColor.GRAY, "§7Player §8× ", "§7", online);
               }
          }
          int presents = new PlayerDataPresents().getOpenedPresentCountForPlayer(player);

          obj.getScore("").setScore(12);
          obj.getScore("§7Profil§7:").setScore(11);
          obj.getScore("§7»§6 " + player.getName()).setScore(10);
          obj.getScore(" ").setScore(9);
          obj.getScore("§7Rang§8:").setScore(8);
          obj.getScore("§7»§6 " + player.getDisplayName().split(" ")[0]).setScore(7);
          obj.getScore("  ").setScore(6);
          obj.getScore("§7Coins§7:").setScore(5);
          obj.getScore("§7»§6 " + CoinsAPI.getCoins(player)).setScore(4);
          obj.getScore("   ").setScore(3);
          obj.getScore("§7Discord§7:").setScore(2);
          obj.getScore("§7» §6hypetime.eu/discord").setScore(1);
     //obj.getScore("§7Geschenke§7:").setScore(2);
     //obj.getScore("§7»§6 " + presents + "/100").setScore(1);
          obj.getScore("    ").setScore(0);
          player.setScoreboard(sb);
}

     public static void registerTeamEntry(Scoreboard sb, String Team, @Nonnull ChatColor prefixColor, String prefix, String suffix, Player player) {
          org.bukkit.scoreboard.Team team = sb.getTeam(Team);
          if (team == null) {
               team = sb.registerNewTeam(Team);
          }
          try {
               Method method = team.getClass().getDeclaredMethod("setColor", ChatColor.class);
               method.setAccessible(true);

               method.invoke(team, prefixColor);

          } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
               exception.printStackTrace();
          }
          team.setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
          team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix) + prefixColor);
          team.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix) + prefixColor);
          team.setColor(prefixColor);
          team.addEntry(player.getName());
          player.setDisplayName(ChatColor.translateAlternateColorCodes('&', prefix + prefixColor + player.getName() + suffix));
          if (PlayerClanSQL.isInClan(player)) {
               player.setCustomName(String.valueOf(team.getPrefix()) + player.getName());
               player.setPlayerListName(String.valueOf(team.getPrefix()) + player.getName() + " §7[§e" + PlayerClanSQL.getClanTag(player) + "§7]");
               team.addPlayer((OfflinePlayer) player);
          }else {
               player.setCustomName(String.valueOf(team.getPrefix()) + player.getName());
               player.setPlayerListName(String.valueOf(team.getPrefix()) + player.getName() + "");
               team.addPlayer((OfflinePlayer) player);
          }
     }

     public static String updateTeam(Scoreboard sb, String Team, String prefix, String suffix, ChatColor entry) {
          Team team = sb.getTeam(Team);
          if (team == null) {
               team = sb.registerNewTeam(Team);
          }
          team.setPrefix(prefix);
          team.setSuffix(suffix);
          team.addEntry(entry.toString());
          return entry.toString();
     }

     public static void startScheduler() {
          Bukkit.getScheduler().runTaskTimerAsynchronously(HypeLobby.getInstance(), () -> {
               if (count > 7) {
                    count = 0;
               } else {
                    count++;
               }
               for (Player on : Bukkit.getOnlinePlayers()) {
                    switch (count) {
                         case 7:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§c§lW§f§lE§c§lI§f§lH§c§lN§f§lA§c§lC§f§lH§c§lT§f§lS §7- §c§lU§f§lP§c§lD§f§lA§c§lT§f§lE"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());
                         case 6:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6HypeTime.EU §8| §7Version §62.6"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());
                              break;
                         case 5:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§7Wir suchen §6Teammitglieder"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());
                              break;
                         case 4:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6Hacker? §8| §7/report"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());
                              break;
                         case 3:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§7Wir empfehlen die Version §61.17.1"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());
                              break;
                         case 2:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6TeamSpeak §8| §7ts.HypeTime.eu"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());

                              break;
                         case 1:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§7Wir unterstützen §b§lLabyMod"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());
                              break;
                         case 0:
                              on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6Discord §8| §7www.hypetime.eu/discord"));
                              on.setLevel(countServiceInfoSnapshotPlayerCount());
                              break;
                    }
               }
          }, 100, 100);
     }
     public static int countServiceInfoSnapshotPlayerCount() {
          int counter = 0;

          for (ServiceInfoSnapshot serviceInfoSnapshot : CloudNetDriver.getInstance().getCloudServiceProvider()
                  .getCloudServices("Proxy")) {
               counter += serviceInfoSnapshot.getProperty(BridgeServiceProperty.ONLINE_COUNT).orElse(0);
          }

          return counter;
     }
/*
     public static void startLevelBar() {
          for (Player on : Bukkit.getOnlinePlayers()) {
               Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> {
                    on.setLevel(countServiceInfoSnapshotPlayerCount());
               }, 35L);
          }
     }*/
}
