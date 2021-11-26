package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Created by Andre
    At 22:05 Uhr | 13. Nov.. 2021
    Project HypeLobbySpigot
*/
public class PatchNotesCommand implements CommandExecutor {

     @Override
     public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
          if (sender instanceof Player player) {
               player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Update§8: §6v2.5.1\n" +
                    "§6§lLobby\n" +
                    "§8➥ §7Halloween Feature entfernt\n" +
                    "§8➥ §7Cosmetics hinzugefügt\n" +
                    "§8➥ §7Profile + Settings System hinzugefügt\n" +
                    "§6§lGunBattle \n" +
                    "§8➥ §7GunBattle Event System hinzugefügt\n" +
                    "§8➥ §7GunBattle bug fixes\n" +
                    "§8➥ §7GunBattle Killstreak verbessert\n" +
                    "§6§lSonstiges\n" +
                    "§8➥ §7AntiCheat update\n" +
                    "§8➥ §7Discord Commands hinzugefügt (ht!status, ht!top5)");
          }
          return false;
     }
}
