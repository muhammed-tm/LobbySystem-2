package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/*
    Created by Andre
    At 00:14 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class BuildCommand implements CommandExecutor {

     public static List<Player> build = new ArrayList<>();
     String prefix = HypeLobby.getInstance().getConstants().getPrefix();

     @Override
     public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
          if (!(sender instanceof Player)) {
               sender.sendMessage(prefix + "Dies kann nur ein Spieler§8.");
               return false;
          }
          Player player = (Player) sender;
          if (!player.hasPermission("system.build")) {
               player.sendMessage(prefix + "§cNicht §7genug Rechte§8.");
               return false;
          }


          if (player.getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())) {
               player.sendMessage(prefix + "Dieser Befehl kann §cnicht §7in der PVP Welt ausgeführt werden§8.");
               return false;
          }

          if (args.length == 0) {
               if (build.contains(player)) {
                    build.remove(player);
                    player.sendMessage(prefix + "Du hast den §6Baumodus §7erfolgreich §cverlassen§8.");
                    player.setGameMode(GameMode.SURVIVAL);
                    Inventories.mainInventory(player);
               } else {
                    build.add(player);
                    player.sendMessage(prefix + "Du hast den §6Baumodus §7erfolgreich §abetreten§8.");
                    player.getInventory().clear();
                    player.setGameMode(GameMode.CREATIVE);
               }
          } else if (args.length == 1) {
               Player target = Bukkit.getPlayer(args[0]);
               if (target == null) {
                    player.sendMessage(prefix + "Dieser Spieler ist aktuell §cnicht §7online§8.");
                    return false;
               }
               if (build.contains(target)) {
                    build.remove(target);
                    player.sendMessage(prefix + "Du hast den §6Baumodus §7erfolgreich §cverlassen§8.");
                    Inventories.mainInventory(target);
                    target.setGameMode(GameMode.SURVIVAL);
               } else {
                    build.add(target);
                    target.sendMessage(prefix + "Du hast den §6Baumodus §7erfolgreich §abetreten§8.");
                    target.getInventory().clear();
                    target.setGameMode(GameMode.CREATIVE);
               }
          } else {
               player.sendMessage(prefix + "§cFalsche §7Benutzung§8. §7Nutze §6/build §8<§6Spieler§8>");
          }
          return false;
     }
}
