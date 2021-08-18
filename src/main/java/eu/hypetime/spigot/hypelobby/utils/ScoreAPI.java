package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.banner.BannerAPI;
import eu.hypetime.spigot.hypelobby.utils.banner.BannerPattern;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.PatternType;
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
		obj.setDisplayName(" §7» §6HypeTimeEU §7« ");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		for (Player on : Bukkit.getOnlinePlayers()) {
			if (Bukkit.getOnlinePlayers().size() >= 1) {
				IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(on.getUniqueId());
				String group = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(user).getName().toLowerCase();
				PlayerInventory inventory = on.getInventory();
				BannerAPI banner = null;
				rang.put(on, group);
				if (group.equals("administrator")) {
					registerTeamEntry(sb, "0001Admin", ChatColor.DARK_RED, "§4Admin §8× ", "§7", on);
					banner = new BannerAPI(Material.RED_WALL_BANNER);
				} else if (group.equals("manager")) {
					registerTeamEntry(sb, "0002Manager", ChatColor.DARK_RED, "§4Manager §8× ", "§7", on);
					banner = new BannerAPI(Material.RED_WALL_BANNER);
				} else if (group.equals("srstaff")) {
					registerTeamEntry(sb, "0003SrStaff", ChatColor.RED, "§cSrStaff §8× ", "§7", on);
					banner = new BannerAPI(Material.RED_BANNER);
				} else if (group.equals("staff")) {
					registerTeamEntry(sb, "0004Staff", ChatColor.RED, "§cStaff §8× ", "§7", on);
					banner = new BannerAPI(Material.RED_BANNER);
				} else if (group.equals("partner")) {
					registerTeamEntry(sb, "0005Partner", ChatColor.GREEN, "§aPartner §8× ", "§7", on);
					banner = new BannerAPI(Material.GREEN_BANNER);
				} else if (group.equals("mediaplus")) {
					BannerPattern pattern;
					banner = new BannerAPI(Material.BLACK_BANNER);
					switch (count) {
						case 0:
							banner = new BannerAPI(Material.RED_BANNER);
						case 1:
							banner = new BannerAPI(Material.BLACK_BANNER);
							break;
						case 2:
							banner = new BannerAPI(Material.BLACK_BANNER);
							pattern = new BannerPattern(PatternType.BORDER, DyeColor.RED);
							banner.addPattern(pattern);
							break;
						case 3:
							banner = new BannerAPI(Material.RED_BANNER);
							pattern = new BannerPattern(PatternType.BORDER, DyeColor.BLACK);
							banner.addPattern(pattern);
							break;
					}
					registerTeamEntry(sb, "0006MediaPlus", ChatColor.BLACK, "§0Media+ §8× ", "§7", on);

				} else if (group.equals("media")) {
					registerTeamEntry(sb, "0007Media", ChatColor.DARK_PURPLE, "§5Media §8× ", "§7", on);
					banner = new BannerAPI(Material.PURPLE_BANNER);
				} else if (group.equals("vip")) {
					registerTeamEntry(sb, "0008Vip", ChatColor.GOLD, "§6VIP §8× ", "§7", on);
					banner = new BannerAPI(Material.ORANGE_BANNER);
				} else if (group.equals("warrior")) {
					registerTeamEntry(sb, "0009Warrior", ChatColor.LIGHT_PURPLE, "§dWarrior §8× ", "§7", on);
					banner = new BannerAPI(Material.PINK_BANNER);
				} else if (group.equals("hyper")) {
					registerTeamEntry(sb, "0010Hyper", ChatColor.YELLOW, "§eHyper §8× ", "§7", on);
					banner = new BannerAPI(Material.YELLOW_BANNER);
				} else if (group.equals("default")) {
					registerTeamEntry(sb, "0011Spieler", ChatColor.GRAY, "§7Player §8× ", "§7", on);
					banner = new BannerAPI(Material.LIGHT_GRAY_BANNER);
				}

				if (banner != null) {
					inventory.setHelmet(banner.getBanner());
				}
			}
		}

		obj.getScore("").setScore(12);
		obj.getScore("§7Profil§7:").setScore(11);
		obj.getScore("§7»§6 " + player.getName()).setScore(10);
		obj.getScore(" ").setScore(9);
		obj.getScore("§7Rang§8:").setScore(8);
		obj.getScore("§7»§6 " + player.getDisplayName().split(" ")[0]).setScore(7);
		obj.getScore("  ").setScore(6);
		obj.getScore("§7Discord§7:").setScore(5);
		obj.getScore("§7»§6 TTaMD4u").setScore(4);
		obj.getScore("   ").setScore(3);
		obj.getScore("§7TeamSpeak§7:").setScore(2);
		obj.getScore("§7» §6ts.HypeTime.eu").setScore(1);
		obj.getScore("    ").setScore(0);
		player.setScoreboard(sb);
	}

    public static void registerTeamEntry(Scoreboard sb, String Team, @Nonnull ChatColor prefixColor, String prefix, String suffix, Player player) {
        Team team = sb.getTeam(Team);
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
            if (count > 3) {
                count = 0;
            } else {
                count++;
            }
            for (Player on : Bukkit.getOnlinePlayers()) {
                switch (count) {
                    case 6:
                        on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6HypeTime.EU §8| §7Version §62.4"));
                        break;
                    case 5:
                        on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§7Wir suchen §6Teammitglieder"));
                        break;
                    case 4:
                        on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6Hacker? §8| §7/report"));
                        break;
                    case 3:
						on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§7Wir empfehlen die Version §61.17.1"));
						break;
                    case 2:
                        on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6TeamSpeak §8| §7ts.HypeTime.eu"));
                        break;
                    case 1:
                        on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§7Wir unterstützen §b§lLabyMod"));
                        break;
					case 0:
						on.spigot().sendMessage(ChatMessageType.ACTION_BAR, UUID.randomUUID(), TextComponent.fromLegacyText("§6Discord §8| §7www.hypetime.eu/discord"));
						break;
                }
            }
        }, 100, 100);
    }

}
