package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStartEvent;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStopEvent;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

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

     @EventListener
     public void onStart(CloudServiceStartEvent event) {
          CloudServer.loadLobbys();
          HypeLobby.getInstance().lobbySwitcherListener.updateInventory();

     }

     @EventListener
     public void onStop(CloudServiceStopEvent event) {
          CloudServer.loadLobbys();
          HypeLobby.getInstance().lobbySwitcherListener.updateInventory();
     }

     public static void loadLobbys() {
          Collection<ServiceInfoSnapshot> serviceInfoSnapshots = CloudNetDriver.getInstance().getCloudServiceProvider().getStartedCloudServices();
          list.clear();
          for (ServiceInfoSnapshot serviceInfoSnapshot : serviceInfoSnapshots) {
               if (serviceInfoSnapshot.getName().contains("Lobby")) {
                    if (serviceInfoSnapshot.getName().contains("VIPLobby")) {
                         list.add(new ItemBuilder(Material.GLOWSTONE_DUST, serviceInfoSnapshot.getProperty(BridgeServiceProperty.ONLINE_COUNT).get())
                              .setName(serviceInfoSnapshot.getServiceId().getName()).toItemStack());
                    } else {
                         list.add(new ItemBuilder(Material.GUNPOWDER, serviceInfoSnapshot.getProperty(BridgeServiceProperty.ONLINE_COUNT).get())
                              .setName(serviceInfoSnapshot.getServiceId().getName()).toItemStack());
                    }
               }
          }
     }
}
