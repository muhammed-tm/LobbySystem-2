package eu.hypetime.spigot.hypelobby.profile.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class ProfileInventory {

     public static IPlayerManager playerManager = CloudNetDriver.getInstance().getServicesRegistry()
          .getFirstService(IPlayerManager.class);

     public static Inventory ProfileInventory() {
          Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Profile §8«");
          resetEnchantments(inventory);
          return inventory;
     }

     public static Inventory FriendInventory(Player player) {
          Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Profile §8| §6Freunde §8«");
          resetEnchantments(inventory);
          inventory.setItem(45, new ItemBuilder(Material.GOLDEN_HELMET)
               .setName("§6Freunde")
               .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
               .toItemStack());
          for (String online : getList(player.getUniqueId()).get("online")) {
               inventory.addItem(new ItemBuilder(Material.PLAYER_HEAD).setName("§6" + online).setSkullOwner(online).addLoreLine("§aOnline §7auf §6" +
                    playerManager.getOnlinePlayer(HypeLobby.getInstance().getFriendManager().getMysql().getUniqueId(online)).getConnectedService().getServerName()).toItemStack());
          }
          for (String offline : getList(player.getUniqueId()).get("offline")) {
               inventory.addItem(new ItemBuilder(Material.PLAYER_HEAD).setName("§6" + offline).setSkullOwner(offline).addLoreLine("§cOffline").toItemStack());
          }
          return inventory;
     }

     public static Inventory PlayerInventory(Player player, String name) {
          Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§7Freund Einstellungen§8: §6" + name);
          for (int i = 0; i < 9; i++) {
               inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
          }
          for (int i = 18; i < 27; i++) {
               inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
          }

          if (playerManager.getOnlinePlayers(name).size() > 0) {
               inventory.setItem(12, new ItemBuilder(Material.PLAYER_HEAD).setName("§6" + name)
                    .setSkullOwner(name)
                    .addLoreLine("§aOnline §7auf §6" +
                         playerManager.getOnlinePlayer(HypeLobby.getInstance().getFriendManager().getMysql().getUniqueId(name)).getConnectedService().getServerName()).toItemStack());

               inventory.setItem(14, new ItemBuilder(Material.ENDER_PEARL).setName("§7Freund §6nachspringen").toItemStack());
          } else {
               inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§6" + name)
                    .setSkullOwner(name)
                    .addLoreLine("§cOffline").toItemStack());
          }

          return inventory;
     }

     public static void resetEnchantments(Inventory inventory) {
          for (int i = 36; i < 45; i++) {
               inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
          }
          inventory.setItem(45, new ItemBuilder(Material.GOLDEN_HELMET)
               .setName("§6Freunde")
               .toItemStack());
     }

     public static HashMap<String, List<String>> getList(UUID uuid) {
          Set<UUID> friendList = HypeLobby.getInstance().getFriendManager().getPlayer(uuid).getFriends();
          List<String> fl = new ArrayList<>();
          for (UUID friend : friendList) {
               fl.add(HypeLobby.getInstance().getFriendManager().getMysql().getName(friend));
          }
          List<String> offline = new ArrayList<>();
          List<String> online = new ArrayList<>();
          for (String entry : fl) {
               if (playerManager.getOnlinePlayers(entry).size() != 0) {
                    online.add(entry);
               } else {
                    offline.add(entry);
               }
          }
          Collections.sort(online);
          Collections.sort(offline);

          HashMap<String, List<String>> hash = new HashMap<>();
          hash.put("online", online);
          hash.put("offline", offline);
          return hash;
     }
}
