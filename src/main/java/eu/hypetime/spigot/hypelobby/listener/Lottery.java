package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Lottery implements Listener {

     public static HashMap<Player, Integer> chests = new HashMap<>();
     public static HashMap<Player, Integer> currentCoins = new HashMap<>();
     public static ArrayList<Player> lottery = new ArrayList<>();

     public static void openLottoInventory(Player player) {
          Inventory inv = Bukkit.createInventory(null, 9 * 3, "§aLotto");
          ArrayList<String> lore1 = new ArrayList<>();
          lore1.add("§7§m--------");
          lore1.add("");
          lore1.add("§e" + MySQL.getFreeTickets(player.getUniqueId()) + " §6§lLose");
          lore1.add("");
          lore1.add("§7§m--------");

          inv.setItem(12, utils.createlore(Material.PAPER, 1, 0, "§6Los einlösen", lore1));
          // §7§m------ \n \n §e500 §7Coins \n \n §7§m------
          ArrayList<String> lore = new ArrayList<>();
          lore.add("§7§m--------");
          lore.add("");
          lore.add("§61000 §7Coins");
          lore.add("");
          lore.add("§7§m--------");

          inv.setItem(14, utils.createlore(Material.BARRIER, 1, 0, "§aLos kaufen", lore));


          player.openInventory(inv);
     }

     @EventHandler
     public void JoinEvent(PlayerJoinEvent event) {
          Player p = event.getPlayer();
          chests.put(p, 0);
          currentCoins.put(p, 0);
     }

     @EventHandler
     public void onPlayerInteractEvent(PlayerInteractEvent e) {
          Player p = e.getPlayer();
          if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
               if (e.getClickedBlock().getType() == Material.CHEST) {
                    e.setCancelled(true);
                    if (e.getClickedBlock().getLocation().equals(WarpAPI.getLocation("Lotto"))) {
                         openLottoInventory(p);
                    }
               }
          }
     }

     @EventHandler
     public void on(InventoryClickEvent event) {
          Player player = (Player) event.getWhoClicked();
          try {
               if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLos kaufen") && event.getView().getTitle().equalsIgnoreCase("§aLotto")) {
                    if (CoinsAPI.getCoins(player) >= 1000) {
                         MySQL.addFreeTickets(player.getUniqueId(), 1);
                         CoinsAPI.removeCoins(player, 1000);
                         ArrayList<String> lore1 = new ArrayList<>();
                         lore1.add("§7§m--------");
                         lore1.add("");
                         lore1.add("§e" + MySQL.getFreeTickets(player.getUniqueId()) + " §6§lLose");
                         lore1.add("");
                         lore1.add("§7§m--------");

                         event.getInventory().setItem(12, utils.createlore(Material.PAPER, 1, 0, "§6Los einlösen", lore1));
                    } else {
                         player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast nicht genügend Coins um dir ein Los zu kaufen");
                    }
               } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Los einlösen") && MySQL.getFreeTickets(player.getUniqueId()) > 0) {
                    Inventory inv = Bukkit.createInventory(null, 9 * 1, "§c§lLos einlösen");
                    inv.setItem(0, new ItemBuilder(Material.LEGACY_STAINED_CLAY).setDyeColor(DyeColor.RED).setName("§a§lLos einlösen").toItemStack());
                    inv.setItem(4, new ItemBuilder(Material.PAPER).setName("§6Los").toItemStack());
                    inv.setItem(8, new ItemBuilder(Material.LEGACY_STAINED_CLAY).setName("§c§lKauf abbrechen").toItemStack());
                    player.openInventory(inv);
               } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lLos einlösen")) {
                    MySQL.removeFreeTickets(player.getUniqueId(), 1);
                    player.closeInventory();

                    // Reward
                    player.openInventory(getInv());
                    lottery.add(player);
               } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lKauf abbrechen")) {
                    player.closeInventory();
               } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLos kaufen") && event.getView().getTitle().equalsIgnoreCase("§c§lLos einlösen")) {
                    event.setCancelled(true);
               } else if (event.getCurrentItem().getType() == Material.ENDER_CHEST) {
                    if (chests.get(player) < 6) {
                         chests.put(player, chests.get(player) + 1);
                         List<Integer> coins = getList();
                         currentCoins.put(player, currentCoins.get(player) + coins.get(event.getSlot()));
                         ItemStack itemStack = new ItemStack(Material.PAPER);
                         ItemMeta meta = itemStack.getItemMeta();
                         meta.setDisplayName("§6" + coins.get(event.getSlot()));
                         itemStack.setItemMeta(meta);
                         event.getInventory().setItem(event.getSlot(), itemStack);
                    }
                    if (chests.get(player) == 5) {
                         chests.put(player, 0);
                         lottery.remove(player);
                         player.closeInventory();
                         CoinsAPI.addCoins(player, currentCoins.get(player));
                         ScoreAPI.setScoreboard(player);
                         int win = 0;
                         win = currentCoins.get(player) - 1000;
                         if (win > 0) {
                              player.sendTitle("§6Lottery", "§7Coins§8: §6" + currentCoins.get(player) + " §8| §c+ §7" + win + " Coins", 20, 40, 20);
                         } else {
                              player.sendTitle("§6Lottery", "§7Coins§8: §6" + currentCoins.get(player) + " §8| §7" + win + " Coins", 20, 40, 20);
                         }
                         currentCoins.put(player, 0);
                         Bukkit.getScheduler().scheduleSyncDelayedTask(HypeLobby.getInstance(), new Runnable() {
                              @Override
                              public void run() {
                                   Firework fire = player.getWorld().spawn(player.getLocation(), Firework.class);

                                   FireworkMeta meta = fire.getFireworkMeta();
                                   meta.addEffect(FireworkEffect.builder().withFade(Color.BLUE).flicker(true).withColor(Color.GREEN).with(FireworkEffect.Type.BALL_LARGE).flicker(true).build());
                                   meta.setPower(2);

                                   fire.setFireworkMeta(meta);

                              }
                         }, 0);
                    }
               }
          } catch (Exception ex) {
          }
     }

     @EventHandler
     public void onCloseInventory(InventoryCloseEvent event) {
          Inventory inventory = event.getInventory();
          if (event.getView().getTitle().equalsIgnoreCase("§6§lLottery")) {
               if (lottery.contains(event.getPlayer())) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(HypeLobby.getInstance(), () -> {
                         event.getPlayer().openInventory(inventory);
                    }, 1L);
               }
          }
     }

	/*
	 * Random r = new Random();
				int random  = r.nextInt(3) + 0;
				if(random == 0){
					API.sendTitle(p, "§6Lottery", "§c+ §750 Coins", 2, 2, 2);
					Coins.addCoins(p, 50);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}
				if(random == 1){
					API.sendTitle(p, "§6Lottery", "§c+ §750 Coins", 2, 2, 2);
					Coins.addCoins(p, 50);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}else if(random == 2){
					API.sendTitle(p, "§6Lottery", "§c+ §7100 Coins", 2, 2, 2);
					Coins.addCoins(p, 100);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}else if(random == 3){
					API.sendTitle(p, "§6Lottery", "§c+ §7200 Coins", 2, 2, 2);
					Coins.addCoins(p, 200);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}
	 */

     public Inventory getInv() {
          Inventory inv = Bukkit.createInventory(null, 54, "§6§lLottery");

          ItemStack i = new ItemStack(Material.ENDER_CHEST);
          ItemMeta m = i.getItemMeta();
          m.setDisplayName("§6§lKlick mich");
          i.setItemMeta(m);

          for (int i1 = 0; i1 < inv.getSize(); i1++) {
               inv.setItem(i1, i);
          }
          return inv;
     }

     public List<Integer> getList() {
          List<Integer> list = new LinkedList<>();

          for (int i = 0; i < 54; i++) {
               list.add(randomCoins());
          }

          Collections.shuffle(list);
          return list;
     }

     public Integer randomCoins() {
          List<Integer> arrayList = new ArrayList<>();

          for (int i = 0; i < 60; i++) {
               arrayList.add(ThreadLocalRandom.current().nextInt(250) + 25);
          }

          for (int i = 0; i < 30; i++) {
               arrayList.add(ThreadLocalRandom.current().nextInt(500) + 25);
          }
          for (int i = 0; i < 10; i++) {
               arrayList.add(ThreadLocalRandom.current().nextInt(750) + 25);
          }
          arrayList.add(1500);

          int r = new Random().nextInt(arrayList.size());
          return arrayList.get(r);
     }
}
