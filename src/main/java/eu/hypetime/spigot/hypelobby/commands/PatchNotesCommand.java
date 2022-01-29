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
            player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Update§8: §6v2.6.3\n" +
                    "§8➥ §7Lottery wurde hinzugefügt\n" +
                    "§8➥ §7KitPVP Fähigkeit wurden angepasst\n" +
                    "§8➥ §7Clansystem wurde hinzugefügt\n" +
                    "§8➥ §7Clansystem GUI wurde angefangen\n" +
                    "§8➥ §7Lobby Cosmetic Bug wurde behoben\n" +
                    "§8➥ §7Lobby Warp Bug wurde behoben §7(§adanke an Infinity_dev für den Bug report§7)\n" +
                    "§8➥ §7Rang Preise wurden angepasst\n");
            player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Update§8: §6v2.6.4\n" +
                    "§8➥ §7DSGVO kick entfernt\n" +
                    "§8➥ §7Navigator wurde überarbeitet\n" +
                    "§8➥ §7Double Jump einstellungen hinzugefügt\n" +
                    "§8➥ §7Gadget Preise angepasst\n" +
                    "§8➥ §7Neue Pets hinzugefügt\n" +
                    "§8➥ §7Lobby Armorstands überarbeitet\n");

        }
        return false;
    }
}
