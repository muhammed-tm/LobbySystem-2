package eu.hypetime.spigot.hypelobby.listener;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.wrapper.Wrapper;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.CloudServer;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LobbySwitcherListener implements Listener {

     public ItemStack lobbySwitcher;
     public Inventory inventory;
     public List<Player> inventoryViewers = new ArrayList<>();

     public LobbySwitcherListener() {
          lobbySwitcher = new ItemBuilder(Material.BEACON).setName("§8» §6LobbySwitcher").toItemStack();
     }

     public void openInventory(Player player) {
          if (player.hasPermission("lobby.vip")) {
               inventory = Bukkit.createInventory(null, 27, "§8» §6LobbySwitcher");
               inventory.clear();

               //LobbyPlus
               AtomicInteger iPLobby = new AtomicInteger(0);
               CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices("LobbyPlus").stream().sorted().forEach(lobby -> {
                    if (lobby.isConnected()) {
                         int players = 1;
                         if (lobby.getProperty(BridgeServiceProperty.ONLINE_COUNT).isPresent()) {
                              players = lobby.getProperty(BridgeServiceProperty.ONLINE_COUNT).get();
                         }
                         if (lobby.getServiceId().equals(Wrapper.getInstance().getServiceId())) {
                              inventory.setItem(iPLobby.getAndIncrement(), new ItemBuilder(Material.GLOWSTONE_DUST, players)
                                   .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1).setName("§7" + lobby.getName()).toItemStack());
                         } else {
                              inventory.setItem(iPLobby.getAndIncrement(), new ItemBuilder(Material.GLOWSTONE_DUST, players).setName("§7" + lobby.getName()).toItemStack());
                         }
                    }
               });

               //Lobbys
               AtomicInteger iLobby = new AtomicInteger(18);
               CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices("Lobby").stream().sorted().forEach(lobby -> {
                    if (lobby.isConnected()) {
                         int players = 1;
                         if (lobby.getProperty(BridgeServiceProperty.ONLINE_COUNT).isPresent()) {
                              players = lobby.getProperty(BridgeServiceProperty.ONLINE_COUNT).get();
                         }
                         if (lobby.getServiceId().equals(Wrapper.getInstance().getServiceId())) {
                              inventory.setItem(iLobby.getAndIncrement(), new ItemBuilder(Material.GUNPOWDER, players)
                                   .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1).setName("§7" + lobby.getName()).toItemStack());
                         } else {
                              inventory.setItem(iLobby.getAndIncrement(), new ItemBuilder(Material.GUNPOWDER, players).setName("§7" + lobby.getName()).toItemStack());
                         }
                    }
               });

               for (int i1 = 0; i1 < inventory.getSize(); i1++) {
                    if (inventory.getItem(i1) == null || inventory.getItem(i1).getType() == Material.AIR) {
                         inventory.setItem(i1, new ItemAPI("§7", Material.GRAY_STAINED_GLASS_PANE, 1).build());
                    } else {
                         continue;
                    }
               }
          } else {
               inventory = Bukkit.createInventory(null, 9, "§8» §6LobbySwitcher");
               inventory.clear();

               AtomicInteger iLobby = new AtomicInteger(0);
               CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices("Lobby").stream().sorted().forEach(lobby -> {
                    if (lobby.isConnected()) {
                         int players = 1;
                         if (lobby.getProperty(BridgeServiceProperty.ONLINE_COUNT).isPresent()) {
                              players = lobby.getProperty(BridgeServiceProperty.ONLINE_COUNT).get();
                         }
                         if (lobby.getServiceId().equals(Wrapper.getInstance().getServiceId())) {
                              inventory.setItem(iLobby.getAndIncrement(), new ItemBuilder(Material.GUNPOWDER, players)
                                   .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1).setName("§7" + lobby.getName()).toItemStack());
                         } else {
                              inventory.setItem(iLobby.getAndIncrement(), new ItemBuilder(Material.GUNPOWDER, players).setName("§7" + lobby.getName()).toItemStack());
                         }
                    }
               });

               for (int i1 = 0; i1 < inventory.getSize(); i1++) {
                    if (inventory.getItem(i1) == null || inventory.getItem(i1).getType() == Material.AIR) {
                         inventory.setItem(i1, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                    } else {
                         continue;
                    }
               }
          }

          player.openInventory(inventory);
     }

     @EventHandler
     public void onInteract(PlayerInteractEvent event) {
          if (event.getItem() == null) return;
          if (event.getItem().getType() == Material.AIR) return;
          if (event.getItem().getType() == Material.BEACON) {
               if (!event.getItem().hasItemMeta()) return;
               if (!event.getItem().getItemMeta().hasDisplayName()) return;
               if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6LobbySwitcher")) {
                    openInventory(event.getPlayer());
               }
          }
     }

     @EventHandler
     public void onClick(InventoryClickEvent event) {
          Player player = (Player) event.getWhoClicked();
          if (player.getOpenInventory().getTitle().equals("§8» §6LobbySwitcher")) {
               if (event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) return;
               event.setCancelled(true);
               if (event.getCurrentItem() == null) return;
               player.closeInventory();
               player.sendTitle("", "§eVerbinde§7...", 2, 50, 2);
               player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
               if (event.getCurrentItem().getItemMeta().hasEnchants()) {
                    player.sendTitle("", "§cFehlgeschlagen§7!", 2, 50, 2);
               } else {
                    Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> {
                         new CloudServer().sendPlayer(player, event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", ""));
                    }, 35L);
               }
          }
     }
}
