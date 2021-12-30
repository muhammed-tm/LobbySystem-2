package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Created by HypeTime-Team
    At 22:05 Uhr | 13. Nov.. 2021
    Project HypeLobbySpigot
*/
public class PatchNotesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Update§8: §6v2.6\n" +
                    "§6§lNetzwerk\n" +
                    "§8➥ §7Village Defense wurde hinzugefügt\n" +
                    "§8➥ §7KitPVP wurde hinzugefügt\n" +
                    "§8➥ §7AntiCrash Update\n" +
                    "§8➥ §7Neues Forum\n");
        }
        return false;
    }
}
