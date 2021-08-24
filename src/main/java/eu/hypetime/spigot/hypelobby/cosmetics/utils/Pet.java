package eu.hypetime.spigot.hypelobby.cosmetics.utils;

import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Pet {

    COW("Kuh", new ItemAPI("§8» §5Cow", Material.COW_SPAWN_EGG, 1).build(), 500, GadgetCategory.PETS);

    private String name;
    private ItemStack item;
    private int coins;
    private GadgetCategory category;

    Pet(String name, ItemStack item, int coins, GadgetCategory category) {
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

    public static Pet getPetByItem(String name) {
        Pet pet = null;
        for (Pet value : Pet.values()) {
            if(value.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                pet = value;
            }
        }
        return pet;
    }
}
