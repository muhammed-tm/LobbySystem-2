package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PresentSetupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("presents.create")) {
                if (args.length == 0) {
                    player.getInventory().addItem(new ItemBuilder(Material.PLAYER_HEAD)
                            .setName("§6Present")
                            .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ3Mjc1OWRiZmIzYWQ2Y2MwNGUxM2UzYjM5MDViZjRiMjU4OWE3MTVjNzBkODQ2MjdhNzE2ZDczYmViMDBmNCJ9fX0=")
                            .addLoreLine("§aPlatziere mich")
                            .toItemStack());
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast ein Geschenk erhalten. Platziere es um es hinzuzufügen");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 1.0F);
                } else {
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cEs ist nur ein Argument erlaubt.");
                }
            } else {
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDazu hast du keine Rechte.");
            }
        }
        return false;
    }
}
