package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.PlayerDataPresents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PresentCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;
            if(args.length == 0){
                int presents = new PlayerDataPresents().getOpenedPresentCountForPlayer(player);
                if(presents == 0){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast leider noch §ekeine");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                } else if(presents <= 10 && presents > 0){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast schon §e" + presents);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Damit zählst du zu");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7den §eGelegenheitssuchern");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                } else if(presents <= 30){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast schon §e" + presents);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Damit zählst du zu");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7den §eaktiven Findern");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                } else if(presents <= 40){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast schon §e" + presents);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Damit zählst du zu");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7den §eGlückspilzen");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                } else if(presents <= 50){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast schon §e" + presents);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Damit zählst du zu");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7den §eTeilzeitfindern");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                } else if(presents <= 60){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast schon §e" + presents);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Damit zählst du zu");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7den §ePfadfindern");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                } else if(presents <= 60){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast schon §e" + presents);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Damit zählst du zu");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7den §ePfadfindern");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                } else {
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast schon §e" + presents);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Geschenke gefunden.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Damit zählst du zu");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7den §eBerufssuchern");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------");
                }
            } else {
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cEs ist nur ein Argument erlaubt.");
            }
        } else {
            sender.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDu bist kein Spieler.");
        }

        return true;
    }
}
