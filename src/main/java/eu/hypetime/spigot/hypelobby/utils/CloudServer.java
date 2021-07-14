package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.CloudNet;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
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

     public static void initLobby() {
          list.clear();
          for (ServiceInfoSnapshot lobby : CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices("Lobby")) {
               if(lobby.isConnected()) {
                    list.add(new ItemBuilder(Material.GUNPOWDER).setName("§7" + lobby.getName()).toItemStack());
               }
          }
     }
}
