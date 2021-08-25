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
          Player player = (Player) event.getWhoClicked();
          if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Daily Rewards §8«")) {
               if(event.getCurrentItem() == null) return;
               if(event.getCurrentItem().getType() == Material.AIR) return;
               event.setCancelled(true);
               if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                    player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
                    return;
               }
               event.setCancelled(true);
               if (event.getSlot() == 9) {
                    if (RewardAPI.canReward(player, RewardType.PLAYER)) {
                         player.sendMessage(prefix + "§7Du hast §6§l100 Coins §7erhalten.");
                         CoinsAPI.addCoins(player, 100);
                         player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                         RewardAPI.setUUID(player, RewardType.PLAYER);
                    } else {
                         player.closeInventory();
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                              + "§7Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.PLAYER) + " §7abholen§8.");
                    }

               }

               if (event.getSlot() == 11) {
                    if (player.hasPermission("Lobby.Hyper")) {
                         if (RewardAPI.canReward(player, RewardType.HYPER)) {
                              player.sendMessage(prefix + "§7Du hast §6§l250 Coins §7erhalten.");
                              CoinsAPI.addCoins(player, 250);
                              player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                              RewardAPI.setUUID(player, RewardType.HYPER);
                         } else {
                              player.closeInventory();
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                                   + "§7Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.HYPER) + " §7abholen§8.");
                         }
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                              + "Du benötigst mindestens den §eHyper Rang §7um dieses Belohnung abzuholen");
                    }
               }

               if (event.getSlot() == 13) {
                    if (player.hasPermission("Lobby.Warrior")) {
                         if (RewardAPI.canReward(player, RewardType.WARRIOR)) {
                              player.sendMessage(prefix + "§7Du hast §6§l500 Coins §7erhalten.");
                              CoinsAPI.addCoins(player, 500);
                              player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                              RewardAPI.setUUID(player, RewardType.WARRIOR);
                         } else {
                              player.closeInventory();
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                                   + "§7Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.WARRIOR) + " §7abholen§8.");
                         }
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                              + "Du benötigst mindestens den §dWarrior Rang §7um dieses Belohnung abzuholen");
                    }
               }
               if (event.getSlot() == 15) {
                    if (player.hasPermission("Lobby.VIP")) {
                         if (RewardAPI.canReward(player, RewardType.VIP)) {
                              player.sendMessage(prefix + "§7Du hast §6§l750 Coins §7erhalten.");
                              CoinsAPI.addCoins(player, 750);
                              player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                              RewardAPI.setUUID(player, RewardType.VIP);
                         } else {
                              player.closeInventory();
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                                   + "§7Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.VIP) + " §7abholen§8.");
                         }
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                              + "Du benötigst mindestens den §eHyper Rang §7um dieses Belohnung abzuholen");
                    }
               }
               if (event.getSlot() == 17) {
                    if (player.hasPermission("Lobby.Media")) {
                         if (RewardAPI.canReward(player, RewardType.MEDIA)) {
                              player.sendMessage(prefix + "§7Du hast §6§l1000 Coins §7erhalten.");
                              CoinsAPI.addCoins(player, 1000);
                              player.playNote(player.getLocation(), Instrument.PIANO, Note.flat(0, Note.Tone.E));
                              RewardAPI.setUUID(player, RewardType.MEDIA);
                         } else {
                              player.closeInventory();
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                                   + "§7Du kannst deine Belohnung erst in " + RewardAPI.getRemaining(player, RewardType.MEDIA) + " §7abholen§8.");
                         }
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                              + "Du benötigst mindestens den §5Media Rang §7um dieses Belohnung abzuholen");
                    }
               }
          }
     }
}
