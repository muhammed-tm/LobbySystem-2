package eu.hypetime.spigot.hypelobby.listener;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.event.PlayerNPCInteractEvent;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.CoinsAPI;
import eu.hypetime.spigot.hypelobby.utils.Inventories;
import eu.hypetime.spigot.hypelobby.utils.ScoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RankShopListener implements Listener {

    @EventHandler
    public void handleNPCInteract(PlayerNPCInteractEvent event) {
        Player player = event.getPlayer();
        NPC npc = event.getNPC();

        // checking if the player hit the NPC
        if (npc.getProfile().getName().equalsIgnoreCase("§eRang Shop")) {
            if (event.getUseAction() == PlayerNPCInteractEvent.EntityUseAction.INTERACT_AT) {
                Inventories.RankShopInventory(player);
            }

        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§8» §6Rang Shop §8«")) {
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
            }
            if (event.getSlot() == 11) {
                player.closeInventory();
                if (!player.hasPermission("lobby.hyper")) {
                    if (CoinsAPI.getCoins(player) >= 5000) {
                        CoinsAPI.removeCoins(player, 5000);
                        IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(player.getUniqueId());
                        user.getGroups().clear();
                        player.sendTitle("§6Neuen Rang", "§eHyper Rang");
                        user.addGroup("Hyper");
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                            ScoreAPI.setScoreboard(onlinePlayer);
                    } else {
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDu hast nicht genug Coins!");
                    }


                } else {
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDu hast bereits diesen Rang oder einen Höheren.");
                }
            }
            if (event.getSlot() == 15) {
                player.closeInventory();
                if (!player.hasPermission("lobby.warrior")) {
                    if (player.hasPermission("lobby.hyper")) {
                        if (CoinsAPI.getCoins(player) >= 25000) {
                            CoinsAPI.removeCoins(player, 25000);
                            IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(player.getUniqueId());
                            user.getGroups().clear();
                            player.sendTitle("§6Neuen Rang", "§dWarrior Rang");
                            user.addGroup("Warrior");
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                                ScoreAPI.setScoreboard(onlinePlayer);
                        } else {
                            player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDu hast nicht genug Coins!");

                        }
                    } else {
                        if (CoinsAPI.getCoins(player) >= 25000) {
                            CoinsAPI.removeCoins(player, 25000);
                            IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(player.getUniqueId());
                            user.getGroups().clear();
                            player.sendTitle("§6Neuen Rang", "§dWarrior Rang");
                            user.addGroup("Warrior");
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                                ScoreAPI.setScoreboard(onlinePlayer);
                        } else {
                            player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDu hast nicht genug Coins!");

                        }
                    }


                } else {
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDu hast bereits diesen Rang oder einen Höheren.");
                }
            }

        }
    }
}


