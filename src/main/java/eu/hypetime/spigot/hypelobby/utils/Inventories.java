package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.utils.clan.ClanSQL;
import eu.hypetime.spigot.hypelobby.utils.clan.PlayerClanSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

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

    public static void main2Inventory(Player player) {
        PlayerInventory inventory = player.getInventory();
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
        inventory.setItem(7, new ItemAPI("§8» §6Cosmetics", Material.CHEST, 1).build());
        inventory.setItem(8, new ItemBuilder(Material.PLAYER_HEAD).setName("§8» §6Profil").setSkullOwner(player.getName()).toItemStack());
    }

    public static void sneakInventory(Player player) {
        PlayerInventory inventory = player.getInventory();

        inventory.setItem(1, new ItemAPI("§6Schild", Material.ENDER_EYE, 1).build());
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
        Inventory inventory = Bukkit.createInventory(null, 9 * 4, "§8» §6Navigator §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§aVillage Defense")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NiZjc4ZWE4ZTE0NGM4NjIxNGU5NTMwNDM2ZWI1MDZlNTM0YTMzZGQzMjZjNDI1MGVmMjc5ZDE5NDI0ZGMxZSJ9fX0=")
                .addLoreLine("§8» §7§lZombies §c§lvs §7§lPlayer")
                .addLoreLine("")
                .addLoreLine("§7Kämpfe mit Spielern auf verschiedenen Weisen")
                .addLoreLine("§7gegen §aZombies§7, beweise §adeine Stärke!")
                .addLoreLine("§7Nach neuen Wellen erscheinen die §averschiedensten Zombies§7,")
                .addLoreLine("§7die deine Dorfbewohner ermorden möchten.")
                .addLoreLine("")
                //.addLoreLine("§61.16-1.18.X§8")
                .addLoreLine("§4§lWARTUNGEN")

                .toItemStack());

        inventory.setItem(14, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§cKitPVP")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNmZjQxNmFhOGJlYzE2NjViOTI3MDFmYmU2OGE0ZWZmZmYzZDA2ZWQ5MTQ3NDU0ZmE3NzcxMmRkNjA3OWIzMyJ9fX0=")
                .addLoreLine("§8» §c§lFFA")
                .addLoreLine("")
                .addLoreLine("§7Kämpfe gegen Spielern mit den")
                .addLoreLine("§7verschiedensten §cKits §7die du dir §cfreischalten §7kannst!")
                .addLoreLine("")
                .addLoreLine("§61.16-1.18.X")
                .toItemStack());

        inventory.setItem(10, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§6Spawn")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY0YzIxZDE3YWQ2MzYzODdlYTNjNzM2YmZmNmFkZTg5NzMxN2UxMzc0Y2Q1ZDliMWMxNWU2ZTg5NTM0MzIifX19")
                .addLoreLine("§7Erkunde unsere §6große Lobby")
                .addLoreLine("§7und finde verschiedenste secrets")
                .toItemStack());

        inventory.setItem(12, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§6GunBattle")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBkZmM4YTM1NjNiZjk5NmY1YzFiNzRiMGIwMTViMmNjZWIyZDA0Zjk0YmJjZGFmYjIyOTlkOGE1OTc5ZmFjMSJ9fX0=")
                .addLoreLine("§8» §6§lFFA")
                .addLoreLine("")
                .addLoreLine("§7Kämpfe mit anderen Spielern auf §6verschiedenen Weisen §7und beweise,")
                .addLoreLine("§7dass du der beste Schütze oder Schwert Kämpfer auf dem Netzwerk bist!")
                .addLoreLine("")
                .addLoreLine("§6Version§8: §61.8.8-1.18.X")
                .toItemStack());

        inventory.setItem(15, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§bHypeSMP")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNmNGQ1NTVmYjNiOTM1N2FmNzU4MmYyNzM4MDBmMWRhNGFjNjliMDQyMTBjM2FhMzRlNjNkYjJhNDIzNWJiZiJ9fX0=")
                .addLoreLine("§8» §b§lSurvival")
                .addLoreLine("")
                .addLoreLine("§7Spiele mit anderen §bMinecraft Survival")
                .addLoreLine("§7welches aber einzigartige Sonderfunktionen,")
                .addLoreLine("§7wie ein §bLevelsystem§7, von unseren Plugins ermöglicht")
                .addLoreLine("")
                //.addLoreLine("§6Version§8: §61.17.1")
                .addLoreLine("§4§lWARTUNGEN")

                .toItemStack());

        inventory.setItem(19, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§6Warps")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJlMDNjYjZmYWVhOTFhNjlhNDY4NGIzMGRkNjJmMWJlZjRmODM0N2NhMjAzNmQxMjhhNzAxMWE1YTUyZDA3OSJ9fX0=")
                .addLoreLine("")
                .addLoreLine("§8§m-------------")
                .addLoreLine("§6» §7Rang Shop")
                .addLoreLine("§6» §7NameMC Reward")
                .addLoreLine("§6» §7Top3 GunBattle")
                .addLoreLine("§8§m-------------")
                .toItemStack());

        inventory.setItem(21, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§cSoon")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VkMWFiYTczZjYzOWY0YmM0MmJkNDgxOTZjNzE1MTk3YmUyNzEyYzNiOTYyYzk3ZWJmOWU5ZWQ4ZWZhMDI1In19fQ==")
                .addLoreLine("§8» §c§kSoon")
                .addLoreLine("")
                .addLoreLine("§c???")
                .addLoreLine("")
                .addLoreLine("§6Version§8: §6§kllll")
                .toItemStack());

        player.openInventory(inventory);


    }

    public static void ClanInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§6Clan §8| §7Menu");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }

        inventory.setItem(13, new ItemBuilder(Material.COMMAND_BLOCK).setName("§6Mitglieder Liste").toItemStack());

        player.openInventory(inventory);
    }

    public static void ClanUserInventory(Player player) {
        Inventory inventory;
        if (PlayerClanSQL.getClanNameExact(player) == "Kein Clan") {
            inventory = Bukkit.createInventory(null, 45, "§6Clan §8| §7Mitglieder");
            inventory.clear();
            for (int i = 0; i < 9; i++) {
                inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            }
            //Links
            inventory.setItem(9, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(18, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(27, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());

            //Rechts
            inventory.setItem(17, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(26, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(35, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            for (int i = 36; i < 45; i++) {
                inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            }
            inventory.setItem(22, new ItemBuilder(Material.BARRIER).setName("§cKein Clan").toItemStack());
        } else {
            inventory = Bukkit.createInventory(null, 54, "§6Clan §8| §7Mitglieder");
            inventory.clear();
            for (int i = 0; i < 9; i++) {
                inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            }
            //Links
            inventory.setItem(9, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(18, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(27, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(36, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());

            //Rechts
            inventory.setItem(17, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(26, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(35, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            for (int i = 44; i < 54; i++) {
                inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            }
            String leadername = ClanSQL.getLeader(PlayerClanSQL.getClanNameExact(player));
            String clanname = PlayerClanSQL.getClanNameExact(player);
            String[] dateLeader = PlayerClanSQL.getEnteredDate(leadername);
            String dateFormattedLeader = "Am " + dateLeader[0] + "." + dateLeader[1] + "." + dateLeader[2] + " um " + dateLeader[3] + ":" + dateLeader[4];
            inventory.setItem(10, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§6" + leadername)
                    .setSkullOwner(leadername)
                    .addLoreLine("§6Clan-Rang§8: §4Admin")
                    .addLoreLine("§6Beigetreten§8: §a" + dateFormattedLeader)
                    .toItemStack());

            ArrayList<String> mods = ClanSQL.getModList(clanname);
            ArrayList<String> members = ClanSQL.getMemberList(clanname);
            ArrayList<String> allUser = ClanSQL.getAllUser(clanname);
            int skip = inventory.firstEmpty();
            for (int i = 0; i < 54; i++) {
                if (allUser.size() - 1 > 0) {
                    if (inventory.getItem(i) == null) {
                        String user = allUser.get(0);
                        String[] date = PlayerClanSQL.getEnteredDate(user);
                        String dateFormatted = "Am " + date[0] + "." + date[1] + "." + date[2] + " um " + date[3] + ":" + date[4];
                        if (mods.contains(user)) {
                            inventory.setItem(i, new ItemBuilder(Material.PLAYER_HEAD)
                                    .setName("§6" + user)
                                    .setSkullOwner(user)
                                    .addLoreLine("§6Clan-Rang§8: §cMod")
                                    .addLoreLine("§6Beigetreten§8: §a" + dateFormatted)
                                    .toItemStack());
                            allUser.remove(0);
                        } else {
                            inventory.setItem(i, new ItemBuilder(Material.PLAYER_HEAD)
                                    .setName("§6" + user)
                                    .setSkullOwner(user)
                                    .addLoreLine("§6Clan-Rang§8: §7Member")
                                    .addLoreLine("§6Beigetreten§8: §a" + dateFormatted)
                                    .toItemStack());
                            allUser.remove(0);
                        }
                    }
                } else {
                    skip++;
                }
            }
        }
        player.openInventory(inventory);
    }

    public static void ClanUserManageInventory(Player player, String name) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§6Clan §8| §7" + name);

        inventory.setItem(11, new ItemBuilder(Material.DIAMOND_BLOCK)
                .setName("§aPromoten")
                .toItemStack());


        inventory.setItem(13, new ItemBuilder(Material.REDSTONE_BLOCK)
                .setName("§cDemoten")
                .toItemStack());


        inventory.setItem(15, new ItemBuilder(Material.BARRIER)
                .setName("§4Kicken")
                .toItemStack());

        player.openInventory(inventory);
    }

    public static void WarpNavigator(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6Warps §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }

        inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§6Rang Shop")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmY3NWQxYjc4NWQxOGQ0N2IzZWE4ZjBhN2UwZmQ0YTFmYWU5ZTdkMzIzY2YzYjEzOGM4Yzc4Y2ZlMjRlZTU5In19fQ==")
                .toItemStack());

        inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§6NameMC Reward")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZjYjY1NzM4MWVlOTZmNWVhZGU0YzczMGVlMWExYjE0NTUyNzY1ZjFkZWUyYmNmZGFlMTc1NzkyYjAxNmZiIn19fQ==")
                .toItemStack());

        inventory.setItem(15, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§6Top3 GunBattle")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZjYmFlNzI0NmNjMmM2ZTg4ODU4NzE5OGM3OTU5OTc5NjY2YjRmNWE0MDg4ZjI0ZTI2ZTA3NWYxNDBhZTZjMyJ9fX0=")
                .toItemStack());

        inventory.setItem(18, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§cZurück")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=")
                .toItemStack());

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 1.0F);
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

    public static void ProfilInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Profil §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD).setName("§7" + player.getName()).setSkullOwner(player.getName()).toItemStack());
        inventory.setItem(21, new ItemBuilder(Material.GOLD_BLOCK).setName("§7Deine §6Coins").setSkullOwner(player.getName()).addLoreLine("§6§l" + CoinsAPI.getCoins(player) + " §7Coins").toItemStack());
        inventory.setItem(22, new ItemBuilder(Material.COMPARATOR).setName("§6Einstellungen").toItemStack());
        if (player.hasPermission("lobby.staff"))
            inventory.setItem(23, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§cStaff Einstellungen")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzkyNjc1Yjc0ZGFlZmQ3ZWU4NjA0NWZjNjU4MGZiNmFhNmM2NGY5NzlhMTI1YjIwZmY2ZjhlYTk0OTZjZSJ9fX0=")
                    .toItemStack());
        else
            inventory.setItem(23, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§6Subtitles")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzkyNjc1Yjc0ZGFlZmQ3ZWU4NjA0NWZjNjU4MGZiNmFhNmM2NGY5NzlhMTI1YjIwZmY2ZjhlYTk0OTZjZSJ9fX0=")
                    .toItemStack());
        inventory.setItem(31, new ItemBuilder(Material.RED_BANNER).setName("§6Clan").addLoreLine("§cSoon").toItemStack());


        player.openInventory(inventory);
    }

    public static void RankShopInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6Rang Shop §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        if (!player.hasPermission("lobby.hyper")) {
            inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§eHyper Rang")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NiNjQ5YTAzYjM0OTAzODA2ZDk5N2YxZDg2ZWZkMTMxMTMyOTliYmY5NTVmNTAzMWQ1MmYwNTFiMmZmNjIxZCJ9fX0=")
                    .addLoreLine("§6Kosten §8| §675000 Coins")
                    .addLoreLine("§4§lPERMANENT")
                    .toItemStack());
        } else {
            inventory.setItem(11, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§eHyper Rang")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NiNjQ5YTAzYjM0OTAzODA2ZDk5N2YxZDg2ZWZkMTMxMTMyOTliYmY5NTVmNTAzMWQ1MmYwNTFiMmZmNjIxZCJ9fX0=")
                    .addLoreLine("§aVorhanden")
                    .toItemStack());
        }

        if (!player.hasPermission("lobby.warrior")) {

            if (player.hasPermission("lobby.hyper")) {

                inventory.setItem(15, new ItemBuilder(Material.PLAYER_HEAD)
                        .setName("§dWarrior Rang")
                        .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjllM2NkMDEwZGNiZWQ0OTI4Njk5OGI0ZmIxNDRkMzM3YmFkZjI2ZGZlZDg3NWRiM2ViYzZkZDEwMjkxYWY4YSJ9fX0=")
                        .addLoreLine("§6Kosten §8| §8| §c§m25000§l✘§a 20000 Coins")
                        .addLoreLine("§4§lPERMANENT")
                        .toItemStack());

            } else if (!player.hasPermission("lobby.warrior")) {
                inventory.setItem(15, new ItemBuilder(Material.PLAYER_HEAD)
                        .setName("§dWarrior Rang")
                        .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjllM2NkMDEwZGNiZWQ0OTI4Njk5OGI0ZmIxNDRkMzM3YmFkZjI2ZGZlZDg3NWRiM2ViYzZkZDEwMjkxYWY4YSJ9fX0=")
                        .addLoreLine("§6Kosten §8| §8| §6150000 Coins")
                        .addLoreLine("§4§lPERMANENT")
                        .toItemStack());
            }
        } else {
            inventory.setItem(15, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§dWarrior Rang")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjllM2NkMDEwZGNiZWQ0OTI4Njk5OGI0ZmIxNDRkMzM3YmFkZjI2ZGZlZDg3NWRiM2ViYzZkZDEwMjkxYWY4YSJ9fX0=")
                    .addLoreLine("§aVorhanden")
                    .toItemStack());
        }
        player.openInventory(inventory);
    }

    public static void DSGVOInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6DSGVO §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
            inventory.setItem(10, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§aAkzeptieren")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTkyZTMxZmZiNTljOTBhYjA4ZmM5ZGMxZmUyNjgwMjAzNWEzYTQ3YzQyZmVlNjM0MjNiY2RiNDI2MmVjYjliNiJ9fX0=")
                    .toItemStack());

            inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§6Lesen?")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTAzNWM1MjgwMzZiMzg0YzUzYzljOGExYTEyNTY4NWUxNmJmYjM2OWMxOTdjYzlmMDNkZmEzYjgzNWIxYWE1NSJ9fX0=")
                    .addLoreLine("§ehttps://www.hypetime.eu/privacy-policy")
                    .toItemStack());

            inventory.setItem(16, new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§cAblehnen")
                    .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdmOWM2ZmVmMmFkOTZiM2E1NDY1NjQyYmE5NTQ2NzFiZTFjNDU0M2UyZTI1ZTU2YWVmMGE0N2Q1ZjFmIn19fQ==")
                    .toItemStack());
            player.openInventory(inventory);
        }
    }

    public static void ProfilSettingsInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6Einstellungen §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        if (SettingConfig.gettpanimation(player) == false) {
            inventory.setItem(11, new ItemBuilder(Material.NETHER_STAR).setName("§6Teleport Animation").addLoreLine("§aaktiviert").toItemStack());
        } else {
            inventory.setItem(11, new ItemBuilder(Material.NETHER_STAR).setName("§6Teleport Animation").addLoreLine("§cdeaktiviert").toItemStack());

        }
        if (SettingConfig.getDoublejump(player) == false) {
            inventory.setItem(13, new ItemBuilder(Material.FEATHER).setName("§6Double Jump").addLoreLine("§aaktiviert").toItemStack());
        } else {
            inventory.setItem(13, new ItemBuilder(Material.FEATHER).setName("§6Double Jump").addLoreLine("§cdeaktiviert").toItemStack());
            player.setAllowFlight(false);
            player.setFlying(false);
        }
        if (SettingConfig.getspawnposition(player) == false) {
            inventory.setItem(15, new ItemBuilder(Material.COMPASS).setName("§6Spawn-Position").addLoreLine("§7Aktuell §8» §6Spawn").toItemStack());
        } else {
            inventory.setItem(15, new ItemBuilder(Material.COMPASS).setName("§6Spawn-Position").addLoreLine("§7Aktuell §8» §6Letzte Position").toItemStack());
        }
        inventory.setItem(18, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§cZurück")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=")
                .toItemStack());
        player.openInventory(inventory);
    }

    public static void ProfilStaffSettingsInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6S-Einstellungen §8«");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        inventory.setItem(11, new ItemBuilder(Material.NETHER_STAR).setName("§4§lStop Server").addLoreLine("§cDie Lobby §7wird gestoppt").toItemStack());
        inventory.setItem(13, new ItemBuilder(Material.BARRIER).setName("§cSoon").toItemStack());
        inventory.setItem(15, new ItemBuilder(Material.BARRIER).setName("§cSoon").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§cZurück")
                .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=")
                .toItemStack());
        player.openInventory(inventory);
    }
}