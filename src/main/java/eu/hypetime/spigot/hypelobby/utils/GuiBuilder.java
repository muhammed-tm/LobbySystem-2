package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class GuiBuilder implements Listener {
     private Inventory inventory;
     private boolean isOpen;
     private Map<Integer, Consumer<InventoryClickEvent>> clickActions;

     public GuiBuilder(InventoryHolder owner, int rows) {
          if (rows >= 7)
               rows = 6;
          this.inventory = Bukkit.createInventory(owner, rows * 9);

          this.clickActions = new HashMap<>();
          this.isOpen = false;
          Bukkit.getPluginManager().registerEvents(this, HypeLobby.getInstance());
     }

     public GuiBuilder(InventoryHolder owner, InventoryType type) {
          this.inventory = Bukkit.createInventory(owner, type);

          this.clickActions = new HashMap<>();
          this.isOpen = false;
          Bukkit.getPluginManager().registerEvents(this, HypeLobby.getInstance());
     }

     public GuiBuilder(InventoryHolder owner, int rows, String title) {
          if (rows >= 7)
               rows = 6;
          this.inventory = Bukkit.createInventory(owner, rows * 9, title);

          this.clickActions = new HashMap<>();
          this.isOpen = false;
          Bukkit.getPluginManager().registerEvents(this, HypeLobby.getInstance());
     }

     public GuiBuilder(InventoryHolder owner, InventoryType type, String title) {
          this.inventory = Bukkit.createInventory(owner, type, title);

          this.clickActions = new HashMap<>();
          this.isOpen = false;
          Bukkit.getPluginManager().registerEvents(this, HypeLobby.getInstance());
     }

     public GuiBuilder setSlot(int slot, ItemStack item) {
          this.inventory.setItem(slot, item);
          return this;
     }

     public GuiBuilder setSlot(int slot, ItemStack item, int amount) {
          item.setAmount(amount);
          this.inventory.setItem(slot, item);
          return this;
     }

     public GuiBuilder setSlot(int slot, ItemStack item, Consumer<InventoryClickEvent> clickAction) {
          this.inventory.setItem(slot, item);
          this.clickActions.put(slot, clickAction);
          return this;
     }

     public GuiBuilder setSlot(int slot, ItemStack item, int amount, Consumer<InventoryClickEvent> clickAction) {
          item.setAmount(amount);
          this.inventory.setItem(slot, item);
          this.clickActions.put(slot, clickAction);
          return this;
     }

     public GuiBuilder setSlot(ItemStack item, Consumer<InventoryClickEvent> clickAction, int... slots) {
          for (int slot : slots) {
               this.inventory.setItem(slot, item);
               this.clickActions.put(slot, clickAction);
          }
          return this;
     }

     public GuiBuilder setSlot(ItemStack item, int... slots) {
          for (int slot : slots) {
               this.inventory.setItem(slot, item);
          }
          return this;
     }

     public GuiBuilder placeHolder(ItemStack item) {
          for (int i = 0; i < this.inventory.getSize(); i++) {
               if (this.inventory.getItem(i) == null)
                    this.setSlot(i, item);
          }
          return this;
     }

     public GuiBuilder replaceItem(ItemStack item, int slot, ItemStack replaceItem) {
          if (this.inventory.contains(item, slot)) {
               this.setSlot(slot, replaceItem);
          }
          return this;
     }

     public GuiBuilder openInventory(Player player) {
          player.openInventory(this.inventory);
          this.isOpen = true;

          return this;
     }

     public GuiBuilder updateInventory(Player player) {
          player.updateInventory();
          return this;
     }

     public GuiBuilder closeInventory(Player player) {
          player.closeInventory();
          return this;
     }

     public int firstItem(ItemStack item) {
          int firstItemStack = 0;
          for (int i = 0; i >= this.inventory.getSize(); i++) {
               if (this.inventory.getItem(i) == item) {
                    firstItemStack = i;
                    break;
               }
          }
          return firstItemStack;
     }

     public ItemStack firstItem() {
          ItemStack firstItemStack = null;
          for (int i = 0; i >= this.inventory.getSize(); i++) {
               if (this.inventory.getItem(i) != null) {
                    firstItemStack = this.inventory.getItem(i);
                    break;
               }
          }
          return firstItemStack;
     }

     public GuiBuilder clear() {
          for (int i = 0; i >= this.inventory.getSize(); i++) {
               this.setSlot(i, new ItemStack(Material.AIR));
          }
          return this;
     }

     public int firstEmpty() {
          int firstEmpty = 0;
          for (int i = 0; i >= this.inventory.getSize(); i++) {
               if (this.inventory.getItem(i) == null) {
                    firstEmpty = i;
                    break;
               }
          }
          return firstEmpty;
     }

     public boolean contains(ItemStack item, int slot) {
          boolean contains = false;
          if (this.inventory.getItem(slot) == item) {
               contains = true;
          }
          return contains;
     }

     public boolean contains(ItemStack item) {
          boolean contains = false;
          for (int i = 0; i >= this.inventory.getSize(); i++) {
               if (this.inventory.getItem(i) == item) {
                    contains = true;
                    break;
               }
          }
          return contains;
     }

     public ItemStack contains(int slot) {
          return this.inventory.getItem(slot);
     }

     public ItemStack getItem(int slot) {
          return this.inventory.getItem(slot);
     }

     public int getSize() {
          return this.inventory.getSize();
     }

     public String getTitle() {
          return this.inventory.getViewers().get(0).getOpenInventory().getTitle();
     }

     public InventoryType getType() {
          return this.inventory.getType();
     }

     public InventoryHolder getHolder() {
          return this.inventory.getHolder();
     }

     public int getMaxStackSize() {
          return this.inventory.getMaxStackSize();
     }

     public GuiBuilder setMaxStackSize(int maxStack) {
          this.inventory.setMaxStackSize(maxStack);
          return this;
     }

     public List<HumanEntity> getViewers() {
          return this.inventory.getViewers();
     }

     public Inventory asInventory() {
          return this.inventory;
     }

     @EventHandler
     public void executeActions(InventoryClickEvent inventoryClickEvent) {
          Inventory clickedInventory = inventoryClickEvent.getClickedInventory();
          if (clickedInventory != this.inventory)
               return;

          inventoryClickEvent.setCancelled(true);

          if (this.clickActions.containsKey(inventoryClickEvent.getSlot()))
               this.clickActions.get(inventoryClickEvent.getSlot()).accept(inventoryClickEvent);
     }

}

