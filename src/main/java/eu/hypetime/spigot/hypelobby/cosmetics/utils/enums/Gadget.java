package eu.hypetime.spigot.hypelobby.cosmetics.utils.enums;

import eu.hypetime.spigot.hypelobby.cosmetics.utils.GadgetCategory;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Gadget {
     ROD("Rod", new ItemAPI("§8» §5Grappling hook", Material.FISHING_ROD, 1).build(), 125, GadgetCategory.GADGETS),
     ENDER_PEARL("Enderpearl", new ItemAPI("§8» §5Enderpearl", Material.ENDER_PEARL, 1).build(), 125, GadgetCategory.GADGETS),
     TELEPORT_BOW("Teleport Bow", new ItemAPI("§8» §5Teleport Bow", Material.BOW, 1).addHideFlag().build(), 125, GadgetCategory.GADGETS),
     RAKETEN_WERFER("Raketenwerfer", new ItemAPI("§6RaketenWerfer", Material.TNT, 1).addHideFlag().build(), 450, GadgetCategory.GADGETS);
     //SCHNOWBALLCANON("SchneeballKanone", new ItemAPI("§fSchneeballKanone", Material.SNOW_BLOCK, 1).addHideFlag().build(), 500, GadgetCategory.GADGETS);

     //TRAIL_GUN("Trail Gun", new ItemAPI("§8» §5Trail Gun", Material.SNOWBALL, 1).addHideFlag().build(), 125, GadgetCategory.GADGETS)

     private String name;
     private ItemStack item;
     private int coins;
     private GadgetCategory category;

     Gadget(String name, ItemStack item, int coins, GadgetCategory category) {
          this.name = name;
          this.item = item;
          this.coins = coins;
          this.category = category;
     }

     public static Gadget getGadgetByItem(String name) {
          Gadget gadget = null;
          for (Gadget value : Gadget.values()) {
               if (value.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                    gadget = value;
               }
          }
          return gadget;
     }

     public String getName() {
          return name;
     }

     public ItemStack getItem() {
          return item;
     }

     public int getCoins() {
          return coins;
     }

     public GadgetCategory getCategory() {
          return category;
     }
}
