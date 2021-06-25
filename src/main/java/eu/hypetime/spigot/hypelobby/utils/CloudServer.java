package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CloudServer implements Listener {

     public static ArrayList<ItemStack> list = new ArrayList<>();

     public void sendPlayer(Player player, String server) {
          ((IPlayerManager) CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class))
               .getPlayerExecutor(player.getUniqueId())
               .connect(server);
     }


     public void sendPlayerToGroup(Player player, String group) {
          CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServicesAsync(group).onComplete((task, serviceInfoSnapshots) -> {
               if (!serviceInfoSnapshots.isEmpty()) {
                    serviceInfoSnapshots.stream().findAny().ifPresent(send -> {
                         sendPlayer(player, group.split("-")[0]);
                    });
               }
          });
     }

     public static void initLobbys() {
          list.clear();
          CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices().forEach(serviceInfoSnapshot -> {
               if(serviceInfoSnapshot.isConnected()) {
                    if (serviceInfoSnapshot.getServiceId().getTaskName().equalsIgnoreCase("Lobby")) {
                         if (BridgeServiceProperty.PLAYERS.get(serviceInfoSnapshot).isPresent()) {
                              list.add(new ItemBuilder(Material.GUNPOWDER, BridgeServiceProperty.PLAYERS.get(serviceInfoSnapshot).get().size())
                                   .setName(serviceInfoSnapshot.getServiceId().getName()).toItemStack());
                         } else {
                              list.add(new ItemBuilder(Material.GUNPOWDER, 1)
                                   .setName(serviceInfoSnapshot.getServiceId().getName()).toItemStack());
                         }
                    } else if (serviceInfoSnapshot.getServiceId().getTaskName().equalsIgnoreCase("VIPLobby")) {
                         if (BridgeServiceProperty.PLAYERS.get(serviceInfoSnapshot).isPresent()) {
                              list.add(new ItemBuilder(Material.GLOWSTONE_DUST, BridgeServiceProperty.PLAYERS.get(serviceInfoSnapshot).get().size())
                                   .setName(serviceInfoSnapshot.getServiceId().getName()).toItemStack());
                         } else {
                              list.add(new ItemBuilder(Material.GLOWSTONE_DUST, 1)
                                   .setName(serviceInfoSnapshot.getServiceId().getName()).toItemStack());
                         }
                    }
               }
          });
     }
}
