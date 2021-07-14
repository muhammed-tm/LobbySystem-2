package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.utils.CloudServer;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
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

    public LobbySwitcherListener() {
        lobbySwitcher = new ItemBuilder(Material.BEACON).setName("§8» §6LobbySwitcher").toItemStack();
        CloudServer.initLobby();
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(null, 9, "§8» §6LobbySwitcher");
        CloudServer.initLobby();
        inventory.clear();
        AtomicInteger integer = new AtomicInteger(14);
        CloudServer.list.forEach(itemStack -> {
            inventory.setItem(integer.get(), itemStack);
            integer.getAndAdd(1);
        });
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null && inventory.getItem(i).getType() != Material.AIR) {
                inventory.setItem(i, new ItemAPI("§7", Material.GRAY_STAINED_GLASS_PANE, 1).build());
            }
        }
        player.openInventory(inventory);
    }

    public void updateInventory() {
        List<HumanEntity> entityList = new ArrayList<>();
        for (HumanEntity viewer : inventory.getViewers()) {
            entityList.add(viewer);
            viewer.closeInventory();
        }

        for (HumanEntity humanEntity : entityList) {
            openInventory((Player) humanEntity);
        }
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
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            new CloudServer().sendPlayer(player, event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", ""));
        }
    }
}
