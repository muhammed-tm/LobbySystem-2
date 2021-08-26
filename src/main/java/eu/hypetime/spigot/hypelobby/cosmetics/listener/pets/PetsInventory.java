package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.BuyListener;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.boots.BootsPlayer;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Pet;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
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
                    player.closeInventory();
                    BootsPlayer.stopParticle(player);
                    PetsManager.createEntityByEntityType(player, pet.getEntityType());
                    player.getInventory().setItem(4, new ItemBuilder(Material.COMMAND_BLOCK).setName("§6Pet §7Einstellungen").toItemStack());
                } else {
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix()
                         + "§7Du besitzt §7dieses §6Pet §cnicht§8. \n§7Möchtest du es dir kaufen§8? §7Schreibe §aJa §7zum Kaufen und §cNein §7zum Abbrechen§8.");
                    BuyListener.buy.put(player, event.getCurrentItem().getItemMeta().getDisplayName());
                    player.closeInventory();
                }
            }
        }
    }

}
