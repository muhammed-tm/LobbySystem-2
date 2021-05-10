package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/*
    Created by Andre
    At 01:21 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class SetCommand implements CommandExecutor {

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
          if(args.length != 1) {
               player.sendMessage(prefix + "§cFalsche §7Benutzung§8. §7Nutze §6/set §8<§6Location§8>");
               return false;
          }
          if(!args[0].equalsIgnoreCase("Spawn")
               && !args[0].equalsIgnoreCase("KitPVP")
               && !args[0].equalsIgnoreCase("LobbyPVP")
               && !args[0].equalsIgnoreCase("Belohnung")) {
               player.sendMessage(prefix + "Bitte gebe eine gültige Location an§8. §6Locations§8:");
               player.sendMessage("§6Spawn\n§6KitPVP\n§6LobbyPVP\n§6Belohnung");
               return false;
          }

          WarpAPI.setLocation(player, args[0]);

          if(args[0].equalsIgnoreCase("Belohnung")) {
               ArmorStand armorStand = player.getLocation().getWorld().spawn(player.getLocation(), ArmorStand.class);
               armorStand.setCustomName("§6Daily Rewards");
               armorStand.setCustomNameVisible(true);
               armorStand.addDisabledSlots(EquipmentSlot.values());
          }

          return false;
     }
}
