package eu.hypetime.spigot.hypelobby.cosmetics.utils.enums;

import eu.hypetime.spigot.hypelobby.cosmetics.utils.GadgetCategory;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

public enum Boots {

     MUSIC_BOOTS("Music", new ItemAPI("§8» §5Music Boots §7(§cSoon§7)", Material.LEATHER_BOOTS, 1).build(), 500, GadgetCategory.BOOTS, Particle.NOTE);

     private String name;
     private ItemStack item;
     private int coins;
     private GadgetCategory category;
     private Particle particle;

     Boots(String name, ItemStack item, int coins, GadgetCategory category, Particle particle) {
          this.name = name;
          this.item = item;
          this.coins = coins;
          this.category = category;
          this.particle = particle;
     }

     public static Boots getBootsByItem(String name) {
          Boots boots = null;
          for (Boots value : Boots.values()) {
               if (value.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                    boots = value;
               }
          }
          return boots;
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

     public Particle getParticle() {
          return particle;
     }

}
