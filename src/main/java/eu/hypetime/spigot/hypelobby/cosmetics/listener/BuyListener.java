package eu.hypetime.spigot.hypelobby.cosmetics.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Boots;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Gadget;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Particle;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Pet;
import eu.hypetime.spigot.hypelobby.utils.CoinsAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

/*
    Created by Andre
    At 20:52 Uhr | 24. Aug.. 2021
    Project HypeLobbySpigot
*/
public class BuyListener implements Listener {

     public static Map<Player, String> buy = new HashMap<>();

     @EventHandler
     public void onChat(AsyncPlayerChatEvent event) {
          Player player = event.getPlayer();
          if (buy.containsKey(player)) {
               event.setCancelled(true);
               if (event.getMessage().equalsIgnoreCase("Ja")) {
                    Gadget gadget = Gadget.getGadgetByItem(buy.get(player));
                    Pet pet = Pet.getPetByItem(buy.get(player));
                    Particle particle = Particle.getParticleByItem(buy.get(player));
                    Boots boots = Boots.getBootsByItem(buy.get(player));
                    if (gadget != null) {
                         if (CoinsAPI.getCoins(player) >= gadget.getCoins()) {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7wurde §aerfolgreich §7abgeschlossen§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                              CoinsAPI.removeCoins(player, gadget.getCoins());
                              CosmeticsManager.addGadget(player, gadget);
                              buy.remove(player);
                         } else {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7ist §cfehlgeschlagen§8. §7Du hast §cnicht §7genug §6Coins§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1f, 1f);
                              buy.remove(player);
                         }
                         return;
                    }
                    if (pet != null) {
                         if (CoinsAPI.getCoins(player) >= pet.getCoins()) {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7wurde §aerfolgreich §7abgeschlossen§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                              CosmeticsManager.addPet(player, pet);
                              CoinsAPI.removeCoins(player, pet.getCoins());
                              buy.remove(player);
                         } else {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7ist §cfehlgeschlagen§8. §7Du hast §cnicht §7genug §6Coins§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1f, 1f);
                              buy.remove(player);
                         }
                         return;
                    }
                    if (boots != null) {
                         if (CoinsAPI.getCoins(player) >= boots.getCoins()) {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7wurde §aerfolgreich §7abgeschlossen§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                              CosmeticsManager.addBoots(player, boots);
                              CoinsAPI.removeCoins(player, boots.getCoins());
                              buy.remove(player);
                         } else {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7ist §cfehlgeschlagen§8. §7Du hast §cnicht §7genug §6Coins§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1f, 1f);
                              buy.remove(player);
                         }
                         return;
                    }
                    if (particle != null) {
                         if (CoinsAPI.getCoins(player) >= particle.getCoins()) {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7wurde §aerfolgreich §7abgeschlossen§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                              CosmeticsManager.addParticle(player, particle);
                              CoinsAPI.removeCoins(player, particle.getCoins());
                              buy.remove(player);
                         } else {
                              player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7ist §cfehlgeschlagen§8. §7Du hast §cnicht §7genug §6Coins§8.");
                              player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1f, 1f);
                              buy.remove(player);
                         }
                         return;
                    }
               } else if(event.getMessage().equalsIgnoreCase("Nein")){
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Der §6Kauf §7wurde abgebrochen§8.");
                    buy.remove(player);
               } else {

               }
          } else {

          }
     }
}
