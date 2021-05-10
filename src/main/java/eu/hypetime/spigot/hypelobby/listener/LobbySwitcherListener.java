package eu.hypetime.spigot.hypelobby.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.CloudServer;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbySwitcherListener implements Listener {

    public ItemStack lobbySwitcher;
    public Inventory inventory;

    public LobbySwitcherListener() {
        lobbySwitcher = new ItemBuilder(Material.BEACON).setName("§8» §6LobbySwitcher").toItemStack();
        inventory = Bukkit.createInventory(null, 9, "§8» §6LobbySwitcher");

        CloudServer.loadLobbys();

        CloudServer.list.forEach(itemStack -> {
            inventory.addItem(itemStack);
        });
    }

    public void updateInventory() {
        inventory.clear();

        CloudServer.list.forEach(itemStack -> {
            inventory.addItem(itemStack);
        });
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getItem().getType() == Material.AIR) return;
        if (event.getItem().getType() == Material.BEACON) {
            if (!event.getItem().hasItemMeta()) return;
            if (!event.getItem().getItemMeta().hasDisplayName()) return;
            if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6LobbySwitcher")) {
                event.getPlayer().openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = ( Player ) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equals("§8» §6LobbySwitcher")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            new CloudServer().sendPlayer(player, event.getCurrentItem().getItemMeta().getDisplayName());

        }
    }

}
