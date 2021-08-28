package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
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
          guiBuilder.setSlot(0, new ItemBuilder(Material.SADDLE).setName("§6Reiten").toItemStack(), event -> {
               player.closeInventory();
               Entity entity = PetsManager.getPet(player).getEntity();
               if (!entity.getPassengers().contains(player)) {
                    entity.addPassenger(player);
               } else {
                    entity.removePassenger(player);
               }
               return;
          });
          guiBuilder.setSlot(1, new ItemBuilder(Material.NAME_TAG).setName("§6Umbenennen").toItemStack(), event -> {
               player.closeInventory();
               Entity entity = PetsManager.getPet(player).getEntity();
               RenameListener.renameMap.put(player, entity);
               player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Bitte schreibe den Namen für dein Pet in den Chat§8.");
               return;
          });
          guiBuilder.openInventory(player);
     }

     @EventHandler
     public void onEntityClick(PlayerInteractAtEntityEvent event) {
          Player player = event.getPlayer();
          Entity entity = event.getRightClicked();
          if (PetsManager.petMap.containsKey(player)) {
               if (entity.getCustomName() != null) {
                    if (entity.equals(PetsManager.getPet(player).getEntity())) {
                         openPetSettings(player);
                    }
               }
          }
     }

     @EventHandler
     public void onUse(PlayerInteractEvent event) {
          ItemStack item = event.getItem();
          if (item == null) return;
          if (item.getItemMeta() == null) return;
          if (item.getItemMeta().getDisplayName() == null) return;
          if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Pet §7Einstellungen")) {
               openPetSettings(event.getPlayer());
          }
     }
}
