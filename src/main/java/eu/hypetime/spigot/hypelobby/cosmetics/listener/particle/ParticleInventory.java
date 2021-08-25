package eu.hypetime.spigot.hypelobby.cosmetics.listener.particle;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.BuyListener;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.boots.BootsPlayer;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.PetsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Particle;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ParticleInventory implements Listener {

     @EventHandler
     public void onClick(InventoryClickEvent event) {
          Player player = (Player) event.getWhoClicked();
          String title = player.getOpenInventory().getTitle();
          if (title.equals("§8» §6Cosmetics §8| §6Particle §8«")) {
               if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                    player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
                    return;
               }
               event.setCancelled(true);
               Particle particle = Particle.getParticleByItem(event.getCurrentItem().getItemMeta().getDisplayName());
               if (particle != null) {
                    if (CosmeticsManager.hasParticle(player, particle)) {
                         if (PetsManager.petMap.containsKey(player)) {
                              PetsManager.removePet(PetsManager.petMap.get(player));
                         }
                         BootsPlayer.stopParticle(player);
                         player.getInventory().setItem(4, particle.getItem());
                         player.closeInventory();
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                              + "§7Du besitzt §7diese §6Particle §cnicht§8. \n§7Möchtest du es dir kaufen§8? §7Schreibe §aJa §7zum Kaufen und §cNein §7zum Abbrechen§8.");
                         BuyListener.buy.put(player, event.getCurrentItem().getItemMeta().getDisplayName());
                         player.closeInventory();
                    }
               }
          }
     }

}
