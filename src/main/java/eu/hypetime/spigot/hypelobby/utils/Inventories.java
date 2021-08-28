package eu.hypetime.spigot.hypelobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffectType;

/*
    Created by Andre
    At 18:17 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class Inventories {

     public static void mainInventory(Player player) {
          PlayerInventory inventory = player.getInventory();
          inventory.clear();
          inventory.setChestplate(new ItemStack(Material.AIR));
          inventory.setLeggings(new ItemStack(Material.AIR));
          inventory.setBoots(new ItemStack(Material.AIR));

          inventory.setItem(0, new ItemAPI("§8» §6Navigator", Material.MUSIC_DISC_13, 1).build());
          inventory.setItem(1, new ItemAPI("§8» §6LobbySwitcher", Material.BEACON, 1).build());
          /*int random = new Random().nextInt(2);
          switch (random) {
               case 0:
                    inventory.setItem(6, new ItemAPI("§8» §5Enderpearl", Material.ENDER_PEARL, 1).build());
                    break;
               case 1:
                    inventory.setItem(6, new ItemAPI("§8» §5Grappling hook", Material.FISHING_ROD, 1).build());
                    break;

          }*/
          inventory.setItem(4, new ItemAPI("§7Gadget §8» §c✖", Material.BARRIER, 1).build());
          inventory.setItem(7, new ItemAPI("§8» §6Cosmetics", Material.CHEST, 1).build());
          inventory.setItem(8, new ItemBuilder(Material.PLAYER_HEAD).setName("§8» §6Profil").setSkullOwner(player.getName()).toItemStack());
     }

     public static void pvpInventory(Player player) {
          PlayerInventory inventory = player.getInventory();
          inventory.clear();
          inventory.setHelmet(new ItemStack(Material.IRON_HELMET));
          inventory.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
          inventory.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
          inventory.setBoots(new ItemStack(Material.IRON_BOOTS));

          inventory.setItem(0, new ItemBuilder(Material.STONE_SWORD)
               .setName("§6Zerstörer")
               .setInfinityDurability()
               .toItemStack());
          inventory.setItem(1, new ItemBuilder(Material.FISHING_ROD)
               .setName("§6Angel")
               .setInfinityDurability()
               .toItemStack());

          inventory.setItem(2, new ItemBuilder(Material.SPLASH_POTION)
               .setPotionEffect(PotionEffectType.HEAL)
               .setName("§6Heilung")
               .setInfinityDurability()
               .toItemStack());
          inventory.setItem(3, new ItemBuilder(Material.SPLASH_POTION)
               .setPotionEffect(PotionEffectType.HEAL)
               .setName("§6Heilung")
               .setInfinityDurability()
               .toItemStack());

          inventory.setItem(4, new ItemBuilder(Material.SANDSTONE, 16)
               .setName("§6Blöcke")
               .toItemStack());
          inventory.setItem(5, new ItemBuilder(Material.COBWEB, 2)
               .setName("§6Spinnennetz")
               .toItemStack());

          inventory.setItem(8, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§8» §cVerlassen")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ==")
               .toItemStack());
     }

     public static void navigatorInventory(Player player) {
          Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6Navigator §8«");
          for (int i = 0; i < inventory.getSize(); i++) {
               inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
          }

          /*inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§6LobbyPVP")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQyZjc4ZDAxNTE5OTc3NjhlZDBjNTM0Yjc2YzI0YjMyOGViZTg0NDg2YjQzYzA4M2JiYzUyODM0MGUyIn19fQ==")
               .toItemStack());
*/
          inventory.setItem(12, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§6Spawn")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGViMDdlZWEzODBhZjU4ZGM5MWVlZWUxNWQ5NWQ4NzkwYTA3ODFjNjk1ZWMwYThmZDhmZTMxZDQ4MzljYTU2MiJ9fX0=")
               .toItemStack());

          inventory.setItem(14, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§6GunBattle")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBkZmM4YTM1NjNiZjk5NmY1YzFiNzRiMGIwMTViMmNjZWIyZDA0Zjk0YmJjZGFmYjIyOTlkOGE1OTc5ZmFjMSJ9fX0=")
               .toItemStack());
          player.openInventory(inventory);


     }

     public static void DailyRewardInventory(Player player) {
          Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6Daily Rewards §8«");
          for (int i = 0; i < inventory.getSize(); i++) {
               inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
          }

          inventory.setItem(9, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§7Player Belohnung")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzI3NDcxMzlmMzM4YTQ2YTQ1OTAzYjc2MzNmMjcwNTAyZDY0ZjA2NjUxNWY0ZDJmOWRlZjgwY2ZmOTExNTBmZiJ9fX0===")
               .toItemStack());

          inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§eHyper Belohnung")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZjZTVkODViNDg5NTY2MmYzMzY0MjljNWJmNWNhMmNhMzNmNjE5NWJmMjQ3NjIyMjQxMjM4NDEwOTY3NTgifX19=")
               .toItemStack());
          inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§dWarrior Belohnung")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Q1ZWUyNGFmYjQ0OTg0ZmE4OTU0YmFkYjdlMjg5NzY4MTUwZTJiMjkwODRjODE3NjhlMjhhNjc3ZGRlODkifX19=")
               .toItemStack());
          inventory.setItem(15, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§6VIP Belohnung")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzkyZTFlNjA3NWY0YjJlYTZjOTI5YTg2ZDU4MzY3NDI5OThjYWJhMGMwYTI3Mzc5YzY4MWE3Y2UzNTZmYjIzZSJ9fX0==")
               .toItemStack());
          inventory.setItem(17, new ItemBuilder(Material.PLAYER_HEAD)
               .setName("§dMedia Reward")
               .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTJiY2YwNTYwNWNmMjZjYWJjMzFjNjhlNzU3MTZiNjRhZGVjOTM1MTUzMGYzNjUwODI5ODFiZTcwMWUwMTZiYSJ9fX0==")
               .toItemStack());
          player.openInventory(inventory);


     }

     public static void NameMCInventory(Player player) {
          Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6NameMC §8«");
          for (int i = 0; i < inventory.getSize(); i++) {
               inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
          }
          if (NameMCReward.hasAccept(player)) {
               inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§6NameMC Reward")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZjYjY1NzM4MWVlOTZmNWVhZGU0YzczMGVlMWExYjE0NTUyNzY1ZjFkZWUyYmNmZGFlMTc1NzkyYjAxNmZiIn19fQ===")
                    .addLoreLine("§aBelohnung Angefordert")
                    .toItemStack());
          } else {
               if (NameMCReward.check(player)) {
                    inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
                         .setName("§6NameMC Reward")
                         .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZjYjY1NzM4MWVlOTZmNWVhZGU0YzczMGVlMWExYjE0NTUyNzY1ZjFkZWUyYmNmZGFlMTc1NzkyYjAxNmZiIn19fQ===")
                         .addLoreLine("§cBelohnung nicht angefordert")
                         .addLoreLine("§7Befehl: §6/vote get")
                         .toItemStack());
               } else {
                    inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
                         .setName("§6NameMC Reward")
                         .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZjYjY1NzM4MWVlOTZmNWVhZGU0YzczMGVlMWExYjE0NTUyNzY1ZjFkZWUyYmNmZGFlMTc1NzkyYjAxNmZiIn19fQ===")
                         .addLoreLine("§cBelohnung nicht angefordert")
                         .toItemStack());
               }
          }
          if (NameMCReward.check(player)) {
               inventory.setItem(13, new ItemBuilder(Material.PAPER)
                    .setName("§7https://namemc.com/server/hypetime.eu")
                    .addLoreLine("§aDu hast den Server geliked")
                    .toItemStack());
          } else {
               inventory.setItem(13, new ItemBuilder(Material.PAPER)
                    .setName("§7https://namemc.com/server/hypetime.eu")
                    .addLoreLine("§cDu hast nicht den Server geliked")
                    .toItemStack());
          }
          inventory.setItem(15, new ItemBuilder(Material.GOLD_INGOT)
               .setName("§aReward")
               .addLoreLine("")
               .addLoreLine("§8» §77 Tage den §eHyper Rang")
               .addLoreLine("§8» §e2500 Coins")
               .addLoreLine("§8» §7Eine Nachricht an alle Spieler")
               .addLoreLine("§8(§7Discord §6&§7 Minecraft§8)§7.")
               .addLoreLine("")
               .addLoreLine("§6/reward get§7/§6check§7/§6info")
               .toItemStack());

          player.openInventory(inventory);

     }
}