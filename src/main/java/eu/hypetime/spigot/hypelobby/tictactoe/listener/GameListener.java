package eu.hypetime.spigot.hypelobby.tictactoe.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.tictactoe.utils.Game;
import eu.hypetime.spigot.hypelobby.tictactoe.utils.Queue;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

/*
    Created by Andre
    At 07:03 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class GameListener implements Listener {

     @EventHandler
     public void onInteract(PlayerInteractEvent event) {
          Player player = event.getPlayer();
          if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
               if (event.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
                    event.setCancelled(true);
                    if (Game.getGames().get(player) == null) {
                         if (!Queue.queue.contains(player)) {
                              Queue.queue.add(player);
                              player.sendTitle("§8» §6TicTimeToe §8«", "§7Warteschlange §abetreten§8", 3, 60, 3);
                              player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                              if (Queue.queue.size() >= 2) {
                                   Player queue1 = Queue.queue.get(0);
                                   Player queue2 = Queue.queue.get(1);
                                   new Game(queue1, queue2);
                                   Queue.removeFromQueue();
                              }
                         } else {
                              Queue.queue.remove(player);
                              player.sendTitle("§8» §6TicTimeToe §8«", "§7Warteschlange §cverlassen§8", 3, 60, 3);
                              player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1f, 1f);
                         }
                    }
               }
          }
     }

     @EventHandler
     public void onInventoryClick(InventoryClickEvent event) {
          if (event.getInventory() == null || event.getInventory().getType() != InventoryType.DISPENSER)
               return;
          event.setCancelled(true);

          Player player = (Player) event.getWhoClicked();

          if ((event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) && event.getSlot() >= 0 && event.getSlot() <= 8) {
               Game game = Game.getGames().get(player);
               if (game == null || game.isFinished())
                    return;
               if (game.isCurrentPlayer(player)) {
                    game.placeMark(player, event.getSlot());
                    game.checkWin(game, event.getSlot());
                    if (game.isFinished()) {
                         return;
                    }
                    if (game.isFull()) {
                         game.setFinished(true);
                         if (Bukkit.getBukkitVersion().contains("1.8")) {
                              game.getPlayer1().playSound(game.getPlayer1().getLocation(), Sound.valueOf("NOTE_BASS"), 1.0F, 1.0F);
                              game.getPlayer2().playSound(game.getPlayer2().getLocation(), Sound.valueOf("NOTE_BASS"), 1.0F, 1.0F);
                         } else {
                              game.getPlayer1().playSound(game.getPlayer1().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0F, 1.0F);
                              game.getPlayer2().playSound(game.getPlayer2().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0F, 1.0F);
                         }
                         game.getPlayer1().closeInventory();
                         game.getPlayer2().closeInventory();
                         game.getPlayer1().sendTitle("§8» §6TicTimeToe §8«", "§eUnentschieden");
                         game.getPlayer2().sendTitle("§8» §6TicTimeToe §8«", "§eUnentschieden");
                         Bukkit.broadcastMessage(game.getPlayer1().getName());
                         Bukkit.broadcastMessage(game.getPlayer2().getName());
                         Game.getGames().remove(game);
                         if (game != null) {
                              Bukkit.broadcastMessage("" + Game.getGames().containsValue(game));
                         } else {
                              Bukkit.broadcastMessage("Game ist null");
                         }
                         return;
                    }
               } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
               }
          }
     }

     @EventHandler
     public void onInventoryClose(InventoryCloseEvent event) {
          final Player player = (Player) event.getPlayer();
          Inventory inventory = event.getInventory();

          if (inventory.getType() == InventoryType.DISPENSER &&
               Game.getGames().containsKey(player)) {
               final Game game = Game.getGames().get(player);

               Bukkit.getScheduler().scheduleSyncDelayedTask(HypeLobby.getInstance(), () -> {
                    if (game == null || game.isFinished())
                         return;
                    player.openInventory(game.getInventory());
               }, 10L);
          }
     }

     @EventHandler
     public void onQuit(PlayerQuitEvent event) {
          Player player = event.getPlayer();
          if (Queue.queue.contains(player)) {
               Queue.queue.remove(player);
          }
          if (Game.getGames().containsKey(player)) {
               if (Game.getGames().get(player).getPlayer1() == player) {
                    Game.getGames().get(player).getPlayer2().closeInventory();
                    Game.getGames().get(player).getPlayer2().sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast §agewonnen§8.");
               } else if (Game.getGames().get(player).getPlayer2() == player) {
                    Game.getGames().get(player).getPlayer1().closeInventory();
                    Game.getGames().get(player).getPlayer1().sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast §agewonnen§8.");
               }
               Game.getGames().remove(player);
          }
     }

}
