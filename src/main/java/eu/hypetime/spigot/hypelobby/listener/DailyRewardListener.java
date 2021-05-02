package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.utils.Inventories;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class DailyRewardListener implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        ArmorStand armorStand = (ArmorStand) event.getRightClicked();
        if (armorStand.getType().equals(EntityType.ARMOR_STAND)) {
            if(event.getRightClicked().getCustomName().equalsIgnoreCase("§6Daily Rewards")) {
                Inventories.DailyRewardInventory(event.getPlayer());
            }
            event.setCancelled(true);
        }
    }

}
