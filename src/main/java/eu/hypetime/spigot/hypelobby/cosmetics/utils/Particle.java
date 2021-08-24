package eu.hypetime.spigot.hypelobby.cosmetics.utils;

import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Particle {

    FLAME_CIRCLE("Flame Circle", new ItemAPI("§8» §5Music Boots", Material.FLINT_AND_STEEL, 1).build(), 500, GadgetCategory.PARTICLE);

    private String name;
    private ItemStack item;
    private int coins;
    private GadgetCategory category;

    Particle(String name, ItemStack item, int coins, GadgetCategory category) {
        this.name = name;
        this.item = item;
        this.coins = coins;
        this.category = category;
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

    public static Particle getGadgetByItem(String name) {
        Particle particle = null;
        for (Particle value : Particle.values()) {
            if(value.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                particle = value;
            }
        }
        return particle;
    }

}
