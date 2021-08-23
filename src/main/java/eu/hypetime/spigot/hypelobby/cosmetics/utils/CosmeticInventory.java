package eu.hypetime.spigot.hypelobby.cosmetics.utils;

import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CosmeticInventory {
    public static void CosmeticsInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Gadgets §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        inventory.setItem(46, new ItemBuilder(Material.SPAWNER)
                .setName("§6Tiere")
                .toItemStack());
        inventory.setItem(47, new ItemBuilder(Material.COCOA_BEANS)
                .setName("§6Partikel")
                .toItemStack());
        inventory.setItem(49, new ItemBuilder(Material.BARRIER)
                .setName("§cablegen")
                .toItemStack());
        inventory.setItem(51, new ItemBuilder(Material.FISHING_ROD)
                .setName("§6Gadgets")
                .toItemStack());
        inventory.setItem(52, new ItemBuilder(Material.LEATHER_BOOTS)
                .setName("§6Schuhe")
                .toItemStack());
        player.openInventory(inventory);

    }

    public static void PetsInventory(Player player) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        resetEnchantments(inventory);
        inventory.setItem(46, new ItemBuilder(Material.SPAWNER)
                .setName("§6Tiere")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        player.openInventory(inventory);
    }

    public static void ParticleInventory(Player player) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        resetEnchantments(inventory);
        inventory.setItem(47, new ItemBuilder(Material.COCOA_BEANS)
                .setName("§6Partikel")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        player.openInventory(inventory);
    }

    public static void GadgetsInventory(Player player) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        resetEnchantments(inventory);
        inventory.setItem(51, new ItemBuilder(Material.FISHING_ROD)
                .setName("§6Gadgets")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        inventory.setItem(10, new ItemBuilder(Material.FISHING_ROD)
                .setName("§7Grappling hook")
                .toItemStack());
        inventory.setItem(11, new ItemBuilder(Material.ENDER_PEARL)
                .setName("§7Enderpearl")
                .toItemStack());
        inventory.setItem(12, new ItemBuilder(Material.BOW)
                .setName("§7Teleport Bow")
                .toItemStack());
        inventory.setItem(12, new ItemBuilder(Material.FEATHER)
                .setName("§7Flight feather")
                .toItemStack());
        player.openInventory(inventory);
    }

    public static void ShoesInventory(Player player) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        resetEnchantments(inventory);
        inventory.setItem(52, new ItemBuilder(Material.LEATHER_BOOTS)
                .setName("§6Schuhe")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        player.openInventory(inventory);
    }

    public static void resetEnchantments(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        inventory.setItem(46, new ItemBuilder(Material.SPAWNER)
                .setName("§6Tiere")
                .toItemStack());
        inventory.setItem(47, new ItemBuilder(Material.COCOA_BEANS)
                .setName("§6Partikel")
                .toItemStack());
        inventory.setItem(49, new ItemBuilder(Material.BARRIER)
                .setName("§cablegen")
                .toItemStack());
        inventory.setItem(51, new ItemBuilder(Material.FISHING_ROD)
                .setName("§6Gadgets")
                .toItemStack());
        inventory.setItem(52, new ItemBuilder(Material.LEATHER_BOOTS)
                .setName("§6Schuhe")
                .toItemStack());
    }
}
