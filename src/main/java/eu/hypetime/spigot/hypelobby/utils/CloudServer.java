package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import org.bukkit.entity.Player;

public class CloudServer {
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
}
