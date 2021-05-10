package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.CoinsAPI;
import eu.hypetime.spigot.hypelobby.utils.RewardAPI;
import eu.hypetime.spigot.hypelobby.utils.RewardType;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DailyRewardGUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String prefix = HypeLobby.getInstance().getConstants().getPrefix();
        Player player = ( Player ) event.getWhoClicked();
        if (event.getInventory().getViewers().get(0).getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Daily Rewards §8«")) {

            //if(player.getOpenInventory().getTopInventory().getViewers().get(0).getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Daily Rewards §8«")) {
            //if (player.getOpenInventory().getTitle().equals("§8» §6Daily Rewards §8«")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
            }
            if (event.getSlot() == 9) {
                event.setCancelled(true);
                if (RewardAPI.canReward(player, RewardType.PLAYER)) {
                    player.sendMessage(prefix + "§7Du hast §6§l100 Coins §7erhalten.");
                    CoinsAPI.addCoins(player.getName(), 100);
                    RewardAPI.setUUID(player, RewardType.PLAYER);
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                } else {
                    player.closeInventory();
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                            + "Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.PLAYER) + " §7abholen§8.");
                }
                //WarpAPI.setLocation(player, args[0]);

            }
        }

        if (event.getSlot() == 11) {
            event.setCancelled(true);
            if (player.hasPermission("Lobby.Hyper")) {
                if (RewardAPI.canReward(player, RewardType.HYPER)) {
                    player.sendMessage(prefix + "§7Du hast §6§l250 Coins §7erhalten.");
                    CoinsAPI.addCoins(player.getName(), 250);
                    RewardAPI.setUUID(player, RewardType.HYPER);
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                } else {
                    player.closeInventory();
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                            + "Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.HYPER) + " §7abholen§8.");
                }
            } else {
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                        + "Du benötigst mindestens den §eHyper Rang §7um dieses Belohnung abzuholen");
            }
        }

        if (event.getSlot() == 13) {
            event.setCancelled(true);
            if (player.hasPermission("Lobby.Warrior")) {
                if (RewardAPI.canReward(player, RewardType.WARRIOR)) {
                    player.sendMessage(prefix + "§7Du hast §6§l500 Coins §7erhalten.");
                    CoinsAPI.addCoins(player.getName(), 500);
                    RewardAPI.setUUID(player, RewardType.WARRIOR);
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                } else {
                    player.closeInventory();
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                            + "Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.WARRIOR) + " §7abholen§8.");
                }
            } else {
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                        + "Du benötigst mindestens den §dWarrior Rang §7um dieses Belohnung abzuholen");
            }
        }
        if (event.getSlot() == 15) {
            event.setCancelled(true);
            if (player.hasPermission("Lobby.VIP")) {
                if (RewardAPI.canReward(player, RewardType.VIP)) {
                    player.sendMessage(prefix + "§7Du hast §6§l750 Coins §7erhalten.");
                    CoinsAPI.addCoins(player.getName(), 500);
                    RewardAPI.setUUID(player, RewardType.VIP);
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                } else {
                    player.closeInventory();
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                            + "Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.VIP) + " §7abholen§8.");
                }
            } else {
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                        + "Du benötigst mindestens den §eHyper Rang §7um dieses Belohnung abzuholen");
            }
        }
        if (event.getSlot() == 17) {
            event.setCancelled(true);
            if (player.hasPermission("Lobby.Media")) {
                if (RewardAPI.canReward(player, RewardType.MEDIA)) {
                    player.sendMessage(prefix + "§7Du hast §6§l1000 Coins §7erhalten.");
                    CoinsAPI.addCoins(player.getName(), 500);
                    RewardAPI.setUUID(player, RewardType.MEDIA);
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                } else {
                    player.closeInventory();
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                            + "Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.MEDIA) + " §7abholen§8.");
                }
            } else {
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                        + "Du benötigst mindestens den §5Media Rang §7um dieses Belohnung abzuholen");
            }
        }
    }
}
