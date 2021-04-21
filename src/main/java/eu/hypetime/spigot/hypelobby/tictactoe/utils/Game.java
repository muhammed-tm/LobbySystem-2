package eu.hypetime.spigot.hypelobby.tictactoe.utils;

import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.banner.BannerPattern;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Random;

/*
    Created by Andre
    At 06:10 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class Game {

     public static HashMap<Player, Game> games = new HashMap<>();
     private boolean isFinished;
     private Inventory inventory;
     private Player player1;
     private Player player2;
     private ItemStack player1Item;
     private ItemStack player2Item;
     private Player current;
     private Player winner;

     public Game(Player player1, Player player2) {
          this.isFinished = false;
          this.player1 = player1;
          this.player2 = player2;
          this.player1Item = new ItemAPI("§a" + player1.getName(), Material.GLOWSTONE_DUST, 1).build();
          this.player2Item = new ItemAPI("§c" + player2.getName(), Material.REDSTONE, 1).build();

          Random random = new Random();
          current = (random.nextInt(2) == 0) ? player1 : player2;
          this.current = current;
          current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);

          this.inventory = Bukkit.createInventory(null, InventoryType.DISPENSER, "§6TicTacToe");
          player1.openInventory(inventory);
          player2.openInventory(inventory);

          games.put(player1, this);
          games.put(player2, this);
      }

     public static HashMap<Player, Game> getGames() {
          return games;
     }

     public Inventory getInventory() {
          return inventory;
     }

     public Player getPlayer1() {
          return player1;
     }

     public Player getPlayer2() {
          return player2;
     }

     public ItemStack getPlayer1Item() {
          return player1Item;
     }

     public ItemStack getPlayer2Item() {
          return player2Item;
     }

     public boolean isFinished() {
          return isFinished;
     }

     public void setFinished(boolean finished) {
          isFinished = finished;
     }

     public Player getCurrent() {
          return current;
     }

     public Player getWinner() {
          return winner;
     }

     public void setCurrent(Player current) {
          this.current = current;
          current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
     }

     public boolean isFull() {
          boolean isFull = true;
          for (int i = 0; i <= 8; i++) {
               if (this.inventory.getItem(i) == null || this.inventory.getItem(i).getType() == Material.AIR) {
                    isFull = false;
               }
          }
          return isFull;
     }

     public void changePlayer() {
          if(this.current == player1) {
               setCurrent(player2);
          } else {
               setCurrent(player1);
          }
          current.playSound(current.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
     }

     public boolean placeMark(Player player, int slot) {
          if (slot >= 0 && slot <= 8 && (
               this.inventory.getItem(slot) == null || this.inventory.getItem(slot).getType() == Material.AIR)) {
               if(current == player1) {
                    getInventory().setItem(slot, player1Item);
                    changePlayer();
               } else {
                    getInventory().setItem(slot, player2Item);
                    changePlayer();
               }
               this.player1.updateInventory();
               this.player2.updateInventory();
               return true;
          }

          return false;
     }

     public boolean isCurrentPlayer(Player player) {
          if (current.getName() == player.getName()) {
               return true;
          } else {
               return false;
          }
     }

     public void checkWin(Game game, int slot) {
          Inventory inv = game.getInventory();
          if(check(inv, slot)) {
               if (checkT(inv, slot)) {

               }
          }
     }

     public void endGame() {
          if(isFinished) {
               Player winner = this.winner;
               this.getPlayer1().closeInventory();
               this.getPlayer2().closeInventory();
               if (winner != null) {
                    if (winner == this.getPlayer1()) {
                         winner.sendTitle("§8» §6TicTimeToe §8«", "§aGewonnen");
                         this.getPlayer2().sendTitle("§8» §6TicTimeToe §8«", "§cVerloren");
                    } else {
                         if (winner == this.getPlayer2()) {
                              winner.sendTitle("§8» §6TicTimeToe §8«", "§aGewonnen");
                              this.getPlayer1().sendTitle("§8» §6TicTimeToe §8«", "§cVerloren");
                         } else {
                              this.getPlayer1().sendTitle("§8» §6TicTimeToe §8«", "§eUnentschieden");
                              this.getPlayer2().sendTitle("§8» §6TicTimeToe §8«", "§eUnentschieden");
                         }
                    }

                    Game.getGames().remove(this.getPlayer1());
                    Game.getGames().remove(this.getPlayer2());
               } else {
                    this.getPlayer1().sendTitle("§8» §6TicTimeToe §8«", "§eUnentschieden");
                    this.getPlayer2().sendTitle("§8» §6TicTimeToe §8«", "§eUnentschieden");

                    Game.getGames().remove(this.getPlayer1());
                    Game.getGames().remove(this.getPlayer2());
               }
          }
     }

     private boolean checkNonNull(Object... o1) {
          return o1 != null;
     }

     private ItemStack getOn(Inventory inv, int slot) {
          return inv.getItem(slot);
     }

     private boolean check(Inventory inv, int slot) {
          return getOn(inv, slot) != null;
     }

     private boolean checkT(Inventory inv, int slot) {
          return getOn(inv, slot).getType() != null;
     }

}
