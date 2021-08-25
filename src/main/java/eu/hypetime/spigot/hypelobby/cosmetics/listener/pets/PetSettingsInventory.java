package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import eu.hypetime.spigot.hypelobby.utils.GuiBuilder;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/*
    Created by Andre
    At 15:33 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class PetSettingsInventory implements Listener {

     public static void openPetSettings(Player player) {
          GuiBuilder guiBuilder = new GuiBuilder(null, 1, "");
          guiBuilder.setSlot(0, new ItemBuilder(Material.SADDLE).setName("§6Auf den Kopf setzen").toItemStack(), event -> {
               player.closeInventory();
               player.addPassenger(PetsManager.getPet(player).getEntity());
               return;
          });
          guiBuilder.openInventory(player);
     }

     @EventHandler
     public void onEntityClick(PlayerInteractAtEntityEvent event) {
          Player player = event.getPlayer();
          Entity entity = event.getRightClicked();
          if (entity.getCustomName() != null) {
               if (entity.getCustomName().equalsIgnoreCase("§6" + player.getName() + "´s Pet")) {
                    openPetSettings(player);
               }
          }
     }

     @EventHandler
     public void onUse(PlayerInteractEvent event) {
          ItemStack item = event.getItem();
          if(item == null) return;
          if(item.getItemMeta() == null) return;
          if(item.getItemMeta().getDisplayName() == null) return;
          if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Pet §7Einstellungen")) {
               openPetSettings(event.getPlayer());
          }
     }
}
