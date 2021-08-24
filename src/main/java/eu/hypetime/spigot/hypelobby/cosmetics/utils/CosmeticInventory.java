package eu.hypetime.spigot.hypelobby.cosmetics.utils;

import com.google.common.collect.Lists;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Boots;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Gadget;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Particle;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Pet;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.atomic.AtomicInteger;

public class CosmeticInventory {
    public static Inventory CosmeticsInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Cosmetics §8«");
        resetEnchantments(inventory);
        return inventory;
    }

    public static Inventory PetsInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Cosmetics §8| §6Pets §8«");
        resetEnchantments(inventory);
        inventory.setItem(46, new ItemBuilder(Material.SPAWNER)
                .setName("§6Pets")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        AtomicInteger integer = new AtomicInteger(10);
        for (Pet pet : Pet.values()) {
            ItemStack item = pet.getItem();
            ItemMeta meta = item.getItemMeta();
            meta.setLore(Lists.newArrayList("§6Preis§8: §6" + pet.getCoins()));
            item.setItemMeta(meta);
            inventory.setItem(integer.getAndIncrement(), item);
        }
        return inventory;
    }

    public static Inventory ParticleInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Cosmetics §8| §6Particle §8«");
        resetEnchantments(inventory);
        inventory.setItem(47, new ItemBuilder(Material.COCOA_BEANS)
                .setName("§6Particle")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        AtomicInteger integer = new AtomicInteger(10);
        for (Particle particle : Particle.values()) {
            ItemStack item = particle.getItem();
            ItemMeta meta = item.getItemMeta();
            meta.setLore(Lists.newArrayList("§6Preis§8: §6" + particle.getCoins()));
            item.setItemMeta(meta);
            inventory.setItem(integer.getAndIncrement(), item);
        }
        return inventory;
    }

    public static Inventory GadgetsInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Cosmetics §8| §6Gadgets §8«");
        resetEnchantments(inventory);
        inventory.setItem(51, new ItemBuilder(Material.FISHING_ROD)
                .setName("§6Gadgets")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        AtomicInteger integer = new AtomicInteger(10);
        for (Gadget gadget : Gadget.values()) {
            ItemStack item = gadget.getItem();
            ItemMeta meta = item.getItemMeta();
            meta.setLore(Lists.newArrayList("§6Preis§8: §6" + gadget.getCoins()));
            item.setItemMeta(meta);
            inventory.setItem(integer.getAndIncrement(), item);
        }
        return inventory;
    }

    public static Inventory BootsInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Cosmetics §8| §6Boots §8«");
        resetEnchantments(inventory);
        inventory.setItem(52, new ItemBuilder(Material.LEATHER_BOOTS)
                .setName("§6Boots")
                .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
                .toItemStack());
        AtomicInteger integer = new AtomicInteger(10);
        for (Boots boots : Boots.values()) {
            ItemStack item = boots.getItem();
            ItemMeta meta = item.getItemMeta();
            meta.setLore(Lists.newArrayList("§6Preis§8: §6" + boots.getCoins()));
            item.setItemMeta(meta);
            inventory.setItem(integer.getAndIncrement(), item);
        }
        return inventory;
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
