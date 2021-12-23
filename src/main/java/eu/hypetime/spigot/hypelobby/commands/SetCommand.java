package eu.hypetime.spigot.hypelobby.commands;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/*
    Created by HypeTime-Team
    At 01:21 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class SetCommand implements CommandExecutor {

    public static List<Player> rgbsetup = new ArrayList<>();
    String prefix = HypeLobby.getInstance().getConstants().getPrefix();

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Dies kann nur ein Spieler§8.");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("system.set")) {
            player.sendMessage(prefix + "§cNicht §7genug Rechte§8.");
            return false;
        }
        if (args.length != 1) {
            player.sendMessage(prefix + "§cFalsche §7Benutzung§8. §7Nutze §6/set §8<§6Location§8>");
            return false;
        }
        if (!args[0].equalsIgnoreCase("Spawn")
                && !args[0].equalsIgnoreCase("GunBattle")
                && !args[0].equalsIgnoreCase("HypeSMP")
                && !args[0].equalsIgnoreCase("LobbyPVP")
                && !args[0].equalsIgnoreCase("Belohnung")
                && !args[0].equalsIgnoreCase("1")
                && !args[0].equalsIgnoreCase("2")
                && !args[0].equalsIgnoreCase("3")
                && !args[0].equalsIgnoreCase("RangShop")
                && !args[0].equalsIgnoreCase("NameMCReward")
                && !args[0].equalsIgnoreCase("rgbblocks")) {
            player.sendMessage(prefix + "Bitte gebe eine gültige Location an§8. §6Locations§8:");
            player.sendMessage("§6Spawn\n§6GunBattle\n§6LobbyPVP\n§6Belohnung\n§61\n§62\n§63\n§6RangShop\n§6NameMCReward\n§6RGBBlocks");
            return false;
        }

        if (args[0].equalsIgnoreCase("rgbblocks")) {
            if (!rgbsetup.contains(player)) {
                rgbsetup.add(player);
                player.sendMessage("Setup");
            } else {
                rgbsetup.remove(player);
            }
        } else {
            WarpAPI.setLocation(player, args[0]);

            if (args[0].equalsIgnoreCase("Belohnung")) {
                ArmorStand armorStand = player.getLocation().getWorld().spawn(player.getLocation(), ArmorStand.class);
                armorStand.setCustomName("§6Daily Rewards");
                armorStand.setCustomNameVisible(true);
                armorStand.setArms(true);
                armorStand.setBasePlate(false);
                armorStand.getEquipment().setHelmet(new ItemStack(Material.TURTLE_HELMET));
                armorStand.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                armorStand.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                armorStand.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
                armorStand.setInvulnerable(true);
                armorStand.addDisabledSlots(EquipmentSlot.values());
            }
        }

        return false;
    }
}
