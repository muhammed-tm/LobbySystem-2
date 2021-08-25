package eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.BuyListener;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.boots.BootsPlayer;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.PetsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Gadget;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticsManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GadgetsInventory implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = ( Player ) event.getWhoClicked();
        String title = player.getOpenInventory().getTitle();
        if (title.equals("§8» §6Cosmetics §8| §6Gadgets §8«")) {
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
                return;
            }
            event.setCancelled(true);
            Gadget gadget = Gadget.getGadgetByItem(event.getCurrentItem().getItemMeta().getDisplayName());
            if(gadget != null) {
                if (CosmeticsManager.hasGadget(player, gadget)) {
                    if(PetsManager.petMap.containsKey(player)) {
                        PetsManager.removePet(PetsManager.petMap.get(player));
                    }
                    BootsPlayer.stopParticle(player);
                    player.getInventory().setItem(4, gadget.getItem());
                    player.closeInventory();
                } else {
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                         + "§7Du besitzt §7dieses §6Gadget §cnicht§8. \n§7Möchtest du es dir kaufen§8? §7Schreibe §aJa §7zum Kaufen und §cNein §7zum Abbrechen§8.");
                    BuyListener.buy.put(player, event.getCurrentItem().getItemMeta().getDisplayName());
                    player.closeInventory();
                }
            }
        }
    }

}
