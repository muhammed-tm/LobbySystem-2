package eu.hypetime.spigot.hypelobby.cosmetics.utils.enums;

import eu.hypetime.spigot.hypelobby.cosmetics.utils.GadgetCategory;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public enum Pet {

    COW("Kuh", new ItemAPI("§8» §5Cow", Material.COW_SPAWN_EGG, 1).build(), 500, GadgetCategory.PETS, EntityType.COW),
    SHEEP("Schaf", new ItemAPI("§8» §5Sheep", Material.SHEEP_SPAWN_EGG, 1).build(), 500, GadgetCategory.PETS, EntityType.SHEEP),
    BLAZE("Blaze", new ItemAPI("§8» §5Blaze", Material.BLAZE_SPAWN_EGG, 1).build(), 500, GadgetCategory.PETS, EntityType.BLAZE),
    FOX("Fuchs", new ItemAPI("§8» §5Fox", Material.FOX_SPAWN_EGG, 1).build(), 500, GadgetCategory.PETS, EntityType.FOX),
    CREEPER("Creeper", new ItemAPI("§8» §5Creeper", Material.CREEPER_SPAWN_EGG, 1).build(), 500, GadgetCategory.PETS, EntityType.CREEPER),
    WOLF("Wolf", new ItemAPI("§8» §5Wolf", Material.WOLF_SPAWN_EGG, 1).build(), 500, GadgetCategory.PETS, EntityType.WOLF),
    ENDERMAN("Enderman", new ItemAPI("§8» §5Enderman", Material.ENDERMAN_SPAWN_EGG, 1).build(), 100, GadgetCategory.PETS, EntityType.ENDERMAN);

    private String name;
    private ItemStack item;
    private int coins;
    private GadgetCategory category;
    private EntityType entityType;

    Pet(String name, ItemStack item, int coins, GadgetCategory category, EntityType entityType) {
        this.name = name;
        this.item = item;
        this.coins = coins;
        this.category = category;
        this.entityType = entityType;
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

    public EntityType getEntityType() {
        return entityType;
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
