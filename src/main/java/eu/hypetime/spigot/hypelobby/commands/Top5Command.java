package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.SQLStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Created by Andre
    At 20:06 Uhr | 26. März. 2021
    Project GunGame
*/
public class Top5Command implements CommandExecutor {

     @Override
     public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
          if (!(sender instanceof Player)) {
               sender.sendMessage("Fehler");
               return false;
          }
          Player player = (Player) sender;

          if (args.length != 0) {
               player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Nutze §6/top5");
               return false;
          }
          SQLStats.sendTop5List(player);

          return false;
     }
}
