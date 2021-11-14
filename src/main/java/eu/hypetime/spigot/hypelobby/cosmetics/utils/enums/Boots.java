package eu.hypetime.spigot.hypelobby.cosmetics.utils.enums;

import eu.hypetime.spigot.hypelobby.cosmetics.utils.GadgetCategory;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

public enum Boots {

     MUSIC_BOOTS("Music", new ItemBuilder(Material.LEATHER_BOOTS).setName("§8» §5Music Boots").setArmorColor(Color.GRAY).toItemStack(), 500, GadgetCategory.BOOTS, Particle.NOTE),
     IceBoots("Ice", new ItemBuilder(Material.LEATHER_BOOTS).setName("§8» §6Ice Boots").setArmorColor(Color.BLUE).toItemStack(), 500, GadgetCategory.BOOTS, Particle.DRIP_WATER),
     FIRE_BOOTS("Fire", new ItemBuilder(Material.LEATHER_BOOTS).setName("§8» §5Fire Boots").setArmorColor(Color.fromRGB(100, 0, 0)).toItemStack(), 500, GadgetCategory.BOOTS, Particle.LAVA),
     LOVE_BOOTS("Love", new ItemBuilder(Material.LEATHER_BOOTS).setName("§8» §5Love Boots").setArmorColor(Color.fromRGB(200, 0, 0)).toItemStack(), 500, GadgetCategory.BOOTS, Particle.HEART);

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
