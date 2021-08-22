package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.SQLStats;
import eu.hypetime.spigot.hypelobby.utils.StatsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Created by Andre
    At 20:10 Uhr | 26. März. 2021
    Project GunGame
*/
public class StatsCommand implements CommandExecutor {

     @Override
     public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
          if (!(sender instanceof Player)) {
               sender.sendMessage("Nicht möglich");
               return false;
          }
          Player player = (Player) sender;
          if (args.length == 0) {
               String target = player.getName();
               player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
               player.sendMessage("§7Spieler§8: §a" + target);
               player.sendMessage("§7Kills§8: §a" + StatsManager.getKills(target));
               player.sendMessage("§7Deaths§8: §a" + StatsManager.getDeaths(target));
               player.sendMessage("§7Rank§8: §a" + SQLStats.getInstance().getRanking(target));
               player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
          } else if (args.length == 1) {
               try {
                    try {
                         String target = SQLStats.getInstance().getPlayerFromRank(Integer.parseInt(args[0]) - 1);
                         if(!target.equals("null")) {
                              player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
                              player.sendMessage("§7Spieler§8: §a" + target);
                              player.sendMessage("§7Kills§8: §a" + StatsManager.getKills(target));
                              player.sendMessage("§7Deaths§8: §a" + StatsManager.getDeaths(target));
                              player.sendMessage("§7Rank§8: §a" + SQLStats.getInstance().getRanking(target));
                              player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
                         } else {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cFehler§8: §7Es sind nur §6" + SQLStats.getInstance().listSize() + " §7Spieler registriert§8.");
                         }
                    } catch (NullPointerException exception) {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cFehler§8: §7Es sind nur §6" + SQLStats.getInstance().listSize() + " §7Spieler registriert§8.");
                    }
               } catch (NumberFormatException exception) {
                    String target = args[0];
                    if (SQLStats.getInstance().isRegisteredName(target)) {
                         player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
                         player.sendMessage("§7Spieler§8: §a" + target);
                         player.sendMessage("§7Kills§8: §a" + StatsManager.getKills(target));
                         player.sendMessage("§7Deaths§8: §a" + StatsManager.getDeaths(target));
                         player.sendMessage("§7Rank§8: §a" + SQLStats.getInstance().getRanking(target));
                         player.sendMessage("§8§m          §8[§aStats§8]§8§m          ");
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cFehler§8: §7Dieser Spieler ist dem System §cnicht §7bekannt§8.");
                    }
               }
          } else {
               player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Bitte nutze §6/stats §7oder §6/stats §8<§aSpieler§8,§aPlatz§8>");
          }
          return false;
     }
}
