package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.Pet;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PetsInventory implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = ( Player ) event.getWhoClicked();
        String title = player.getOpenInventory().getTitle();
        if (title.equals("§8» §6Cosmetics §8| §6Pets §8«")) {
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
                return;
            }
            event.setCancelled(true);
            Pet pet = Pet.getPetByItem(event.getCurrentItem().getItemMeta().getDisplayName());
            if(pet != null) {
                if (CosmeticsManager.hasPet(player, pet)) {
                    player.getInventory().setItem(4, pet.getItem());
                    player.closeInventory();
                } else {
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                         + "§7Du besitzt §7dieses §6Pet §cnicht§8. \n§7Möchtest du es dir kaufen§8? §7Schreibe §aJa §7zum Kaufen und §cNein §7zum Abbrechen§8.");
                    player.closeInventory();
                }
            }
        }
    }

}
