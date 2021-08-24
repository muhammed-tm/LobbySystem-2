package eu.hypetime.spigot.hypelobby.cosmetics.utils;

import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Boots {

    MUSIC_BOOTS("Music", new ItemAPI("§8» §5Music Boots", Material.LEATHER_BOOTS, 1).build(), 500, GadgetCategory.BOOTS);

    private String name;
    private ItemStack item;
    private int coins;
    private GadgetCategory category;

    Boots(String name, ItemStack item, int coins, GadgetCategory category) {
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

    public static Boots getGadgetByItem(String name) {
        Boots boots = null;
        for (Boots value : Boots.values()) {
            if(value.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                boots = value;
            }
        }
        return boots;
    }

}
