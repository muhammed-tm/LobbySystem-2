package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.commands.BuildCommand;
import eu.hypetime.spigot.hypelobby.commands.SetCommand;
import eu.hypetime.spigot.hypelobby.commands.SitCommand;
import eu.hypetime.spigot.hypelobby.utils.MaterialLists;
import eu.hypetime.spigot.hypelobby.utils.WarpAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/*
    Created by Andre
    At 00:14 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class BuildListener implements Listener {


    private final Method[] methods = ((Supplier<Method[]>) () -> {
        try {
            Method getHandle = Class.forName(Bukkit.getServer().getClass().getPackage().getName() + ".entity.CraftEntity").getDeclaredMethod("getHandle");
            return new Method[]{
                    getHandle, getHandle.getReturnType().getDeclaredMethod("setPositionRotation", double.class, double.class, double.class, float.class, float.class)
            };
        } catch (Exception ex) {
            return null;
        }
    }).get();
    String prefix = HypeLobby.getInstance().getConstants().getPrefix();
    List<Player> build = BuildCommand.build;

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!build.contains(event.getPlayer())) {
            if (event.getPlayer().getWorld().getName().equalsIgnoreCase(HypeLobby.getInstance().getConstants().getPVPWorld())) {
                if (event.getItemInHand().getType() != Material.PLAYER_HEAD) {
                    if (event.getPlayer().getLocation().distance(WarpAPI.getLocation("LobbyPVP")) <= 3) {
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(false);
                        Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> event.getBlock().setType(Material.REDSTONE_BLOCK), 30L);
                        Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> event.getBlock().setType(Material.AIR), 60L);
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
               /*if (player.hasPermission("presents.create")) {
                    Location blockLoc = event.getBlock().getLocation();
                    (new PlayerDataPresents()).addPresent(blockLoc);
               }*/
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!build.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (!build.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onStep(EntityChangeBlockEvent event) {
        if (!build.contains(event.getEntity())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUp(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player player)
        if (!build.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwitchHand(PlayerSwapHandItemsEvent event) {
        if (!build.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHangingBreak(HangingBreakByEntityEvent event) {
        if (!(event.getEntity() instanceof ItemFrame) && !(event.getEntity() instanceof Painting)) {
            return;
        }
        if (!(event.getRemover() instanceof Player)) {
            return;
        }
        Player remover = (Player) event.getRemover();
        if (!build.contains(remover)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHangingPlace(HangingPlaceEvent event) {
        Player player = event.getPlayer();
        if (!build.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!build.contains(player)) {
            if (event.getRightClicked() != null) {
                if (event.getRightClicked().getType() == EntityType.ITEM_FRAME) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!build.contains(player)) {
                if (SitCommand.sitting.contains(event.getPlayer().getUniqueId())) {
                    Location loc = event.getClickedBlock().getLocation();
                    Location check1 = loc.clone().add(0, 1, 0);
                    Location check2 = loc.clone().add(0, 2, 0);
                    try {
                        if (check1.getBlock().getType().isAir() && check2.getBlock().getType().isAir()) {
                            if (!MaterialLists.halfBlocks.contains(loc.getBlock().getType())) {
                                methods[1].invoke(methods[0].invoke(event.getPlayer().getVehicle()), loc.getX() + 0.5, loc.getY() - 0.6, loc.getZ() + 0.5, 0, 0);
                            } else {
                                methods[1].invoke(methods[0].invoke(event.getPlayer().getVehicle()), loc.getX() + 0.5, loc.getY() - 1.1, loc.getZ() + 0.5, 0, 0);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (event.getClickedBlock().getType() == Material.OAK_TRAPDOOR
                        || event.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR
                        || event.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR
                        || event.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR
                        || event.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR
                        || event.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR
                        || event.getClickedBlock().getType() == Material.CRIMSON_TRAPDOOR
                        || event.getClickedBlock().getType() == Material.WARPED_TRAPDOOR) {
                    event.setCancelled(true);
                }
            } else {
                if (SitCommand.sitting.contains(event.getPlayer().getUniqueId())) {
                    Location loc = event.getClickedBlock().getLocation();
                    Location check1 = loc.clone().add(0, 1, 0);
                    Location check2 = loc.clone().add(0, 2, 0);
                    try {
                        if (check1.getBlock().getType().isAir() && check2.getBlock().getType().isAir()) {
                            if (!MaterialLists.halfBlocks.contains(loc.getBlock().getType())) {
                                methods[1].invoke(methods[0].invoke(event.getPlayer().getVehicle()), loc.getX() + 0.5, loc.getY() - 0.6, loc.getZ() + 0.5, 0, 0);
                            } else {
                                methods[1].invoke(methods[0].invoke(event.getPlayer().getVehicle()), loc.getX() + 0.5, loc.getY() - 1.1, loc.getZ() + 0.5, 0, 0);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (SetCommand.rgbsetup.contains(player)) {
                int i = Objects.requireNonNullElse(WarpAPI.getInt("rgbblocks"), 0);
                WarpAPI.setLocation(event.getClickedBlock().getLocation(), "rgbblock." + i);
                WarpAPI.location.setValue("rgbblocks", (i + 1));
                WarpAPI.location.save();
            }
        }
    }
}