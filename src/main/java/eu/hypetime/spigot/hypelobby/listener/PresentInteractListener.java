package eu.hypetime.spigot.hypelobby.listener;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.CoinsAPI;
import eu.hypetime.spigot.hypelobby.utils.PlayerDataPresents;
import eu.hypetime.spigot.hypelobby.utils.ScoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

public class PresentInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        int presents = new PlayerDataPresents().getOpenedPresentCountForPlayer(player);
        if (e.getClickedBlock().getType() == Material.PLAYER_HEAD && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (PlayerDataPresents.hasOpened(player, (int) e.getClickedBlock().getX(), (int) e.getClickedBlock().getY(), (int) e.getClickedBlock().getZ())) {
                e.setCancelled(true);
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDu hast dieses Geschenk bereits gefunden.");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 3, 1);
            } else {
                if (presents != 100) {
                    new PlayerDataPresents().setOpened(player, (int) e.getClickedBlock().getX(), (int) e.getClickedBlock().getY(), (int) e.getClickedBlock().getZ());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 1);
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------------");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast ein neues Geschenk");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7gefunden. Wir gratulieren");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7dir herzlich.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast jetzt §e" + new PlayerDataPresents().getOpenedPresentCountForPlayer(player));
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7gefundene Geschenke.");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§e(+50 Coins)");
                    player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------------");
                    CoinsAPI.addCoins(player, 50);
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                        ScoreAPI.setScoreboard(onlinePlayer);
                }else {
                        new PlayerDataPresents().setOpened(player, (int) e.getClickedBlock().getX(), (int) e.getClickedBlock().getY(), (int) e.getClickedBlock().getZ());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 1);
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------------");
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast ein neues Geschenk");
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7gefunden. Wir gratulieren");
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7dir herzlich.");
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Du hast jetzt §e" + new PlayerDataPresents().getOpenedPresentCountForPlayer(player));
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7gefundene Geschenke.");
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§e(+2000 Coins)");
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§8§m---------------------------");
                        IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(player.getUniqueId());
                        if(!(player.hasPermission("lobby.warrior"))) {
                        user.getGroups().clear();
                        player.sendTitle("§6Neuen Rang", "§dWarrior Rang (30d)");
                        user.addGroup("Warrior", 30, TimeUnit.DAYS);
                    }else {
                            player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Da du bereits einen §dWarrior Rang §7oder einen Höheren hast, erhälst du §6+5000 Coins");
                            CoinsAPI.addCoins(player, 5000);
                        }
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            ScoreAPI.setScoreboard(onlinePlayer);
                            onlinePlayer.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + player.getPlayer().getDisplayName() + " hat 100 Geschenke gefunden!");
                        }
                        CoinsAPI.addCoins(player, 2000);
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                            ScoreAPI.setScoreboard(onlinePlayer);
                }
            }
        }
        }
    }

