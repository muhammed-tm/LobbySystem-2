package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.ItemAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import eu.hypetime.spigot.hypelobby.utils.SchutzSchildManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class SchildListener implements Listener {


    public static ArrayList<Player> schutzschild = new ArrayList<>();
    public String team;

    public Player p;

    int counter;

    int time;

    BukkitRunnable task;
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem().getItemMeta().getDisplayName().equals("§6Schild")) {
                SchildInventory(p);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equals("§8» §6Schild")) {
            if (event.getCurrentItem() == null) return;
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lDEAKTIVIEREN")) {
                    if (player.hasPermission("lobby.shield")) {
                        if (schutzschild.contains(player)) {
                            schutzschild.remove(player);
                            SchutzSchildManager(p);
                            player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Das Schild wurde §cdeaktiviert");
                            player.closeInventory();
                        }
                }
                player.closeInventory();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lAKTIVIEREN")) {
                if (player.hasPermission("lobby.shield"))
                    if (!schutzschild.contains(player)) {
                        schutzschild.add(player);
                        SchutzSchildManager(player);
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§7Das Schild wurde §aaktiviert");
                        player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cDas Abusen vom Schild kann zu einem §4Bann §cführen.");
                        player.closeInventory();
                    }
            }
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1f, 1f);
                player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "§cAktion abgebrochen");
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (schutzschild.contains(p))
            schutzschild.remove(p);
    }

    public void SchildInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6Schild");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemAPI("§7", Material.BLACK_STAINED_GLASS_PANE, 1).addHideFlag().build());
        }
        if (schutzschild.contains(player)) {
                inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD)
                        .setName("§c§lDEAKTIVIEREN")
                        .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZjYjY1NzM4MWVlOTZmNWVhZGU0YzczMGVlMWExYjE0NTUyNzY1ZjFkZWUyYmNmZGFlMTc1NzkyYjAxNmZiIn19fQ==")
                        .toItemStack());

            } else {
                inventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD)
                        .setName("§a§lAKTIVIEREN\n")
                        .setSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZjYjY1NzM4MWVlOTZmNWVhZGU0YzczMGVlMWExYjE0NTUyNzY1ZjFkZWUyYmNmZGFlMTc1NzkyYjAxNmZiIn19fQ==")
                        .toItemStack());
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3.0F, 1.0F);
            }
        player.openInventory(inventory);
    }
    public ArrayList<Player> schutzschild() {
        return this.schutzschild;
    }
    public void SchutzSchildManager(final Player p) {
        int multiply = 2;
        double hight = 1.0D;
        int near = 5;
        this.task = new BukkitRunnable() {
            public void run() {
                if (SchildListener.schutzschild.contains(p)) {
                    SchildListener.this.playEffect(p.getLocation(), true);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players != p && p.getLocation().distance(players.getLocation()) <= 5.0D && SchildListener.schutzschild.contains(p))
                            for (int i = 0; i < 10; i++)
                                players.setVelocity(players.getLocation().getDirection().multiply(-2).setY(1.0D));
                    }
                } else {
                    SchildListener.this.stop();
                }
            }
        };
        this.task.runTaskTimer((Plugin) HypeLobby.getInstance(), 0L, 5L);
    }

    void stop() {
        this.task.cancel();
    }

    public void playEffect(Location loc, boolean vis) {
        for (int i = 0; i <= 8; i += (!vis && i == 3) ? 2 : 1)
            loc.getWorld().playEffect(loc, Effect.ENDER_SIGNAL, i);
    }


}