package eu.hypetime.spigot.hypelobby.cosmetics.utils;

import com.google.common.collect.Lists;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Boots;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Gadget;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Pet;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
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
          inventory.setItem(48, new ItemBuilder(Material.SPAWNER)
               .setName("§6Pets")
               .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
               .toItemStack());
          ArrayList<Pet> pets = new ArrayList<Pet>(Arrays.asList(Pet.values()));
          int pet = 0;
          for (int i = 0; i < inventory.getSize(); i++) {
               if(pet < pets.size()) {
                    if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                         ItemStack item = pets.get(pet).getItem();
                         ItemMeta meta = item.getItemMeta();
                         meta.setLore(Lists.newArrayList("§6Preis§8: §6" + pets.get(pet).getCoins()));
                         item.setItemMeta(meta);
                         inventory.setItem(i, item);
                         pet++;
                    }
               }
          }
          return inventory;
     }

     public static Inventory GadgetsInventory() {
          Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Cosmetics §8| §6Gadgets §8«");
          resetEnchantments(inventory);
          inventory.setItem(49, new ItemBuilder(Material.FISHING_ROD)
               .setName("§6Gadgets")
               .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
               .toItemStack());
          AtomicInteger integer = new AtomicInteger(10);
          ArrayList<Gadget> gadgets = new ArrayList<Gadget>(Arrays.asList(Gadget.values()));
          int gadget = 0;
          for (int i = 0; i < inventory.getSize(); i++) {
               if(gadget < gadgets.size()) {
                    if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                         ItemStack item = gadgets.get(gadget).getItem();
                         ItemMeta meta = item.getItemMeta();
                         meta.setLore(Lists.newArrayList("§6Preis§8: §6" + gadgets.get(gadget).getCoins()));
                         item.setItemMeta(meta);
                         inventory.setItem(i, item);
                         gadget++;
                    }
               }
          }
          return inventory;
     }

     public static Inventory BootsInventory() {
          Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Cosmetics §8| §6Boots §8«");
          resetEnchantments(inventory);
          inventory.setItem(50, new ItemBuilder(Material.LEATHER_BOOTS)
               .setName("§6Boots")
               .addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
               .toItemStack());
          AtomicInteger integer = new AtomicInteger(10);
          ArrayList<Boots> boots = new ArrayList<Boots>(Arrays.asList(Boots.values()));
          int boot = 0;
          for (int i = 0; i < inventory.getSize(); i++) {
               if(boot < boots.size()) {
                    if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                         ItemStack item = boots.get(boot).getItem();
                         ItemMeta meta = item.getItemMeta();
                         meta.setLore(Lists.newArrayList("§6Preis§8: §6" + boots.get(boot).getCoins()));
                         item.setItemMeta(meta);
                         inventory.setItem(i, item);
                         boot++;
                    }
               }
          }
          return inventory;
     }

     public static void resetEnchantments(Inventory inv) {
          ItemStack glass = new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build();
          for (int i = 0; i < 9; i++) {
               inv.setItem(i, glass);
          }
          inv.setItem(9, glass);
          inv.setItem(18, glass);
          inv.setItem(27, glass);
          inv.setItem(36, glass);

          inv.setItem(17, glass);
          inv.setItem(26, glass);
          inv.setItem(35, glass);
          inv.setItem(44, glass);
          for (int i = 45; i < 53; i++) {
               inv.setItem(i, glass);
          }
          inv.setItem(48, new ItemBuilder(Material.SPAWNER)
               .setName("§6Tiere")
               .toItemStack());
          inv.setItem(49, new ItemBuilder(Material.FISHING_ROD)
               .setName("§6Gadgets")
               .toItemStack());
          inv.setItem(50, new ItemBuilder(Material.LEATHER_BOOTS)
               .setName("§6Schuhe")
               .toItemStack());
          inv.setItem(53, new ItemBuilder(Material.BARRIER)
               .setName("§7Gadget §cablegen")
               .toItemStack());
     }
}
