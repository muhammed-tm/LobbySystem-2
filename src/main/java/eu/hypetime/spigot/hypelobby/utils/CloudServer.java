package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CloudServer implements Listener {

     public static HashMap<Integer, ItemStack> list = new HashMap<>();

     public static void initLobby() {
          list.clear();
          AtomicInteger i = new AtomicInteger(12);
          CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices("Lobby").forEach(lobby -> {
               if (lobby.isConnected()) {
                    list.put(i.getAndIncrement(), new ItemBuilder(Material.GUNPOWDER).setName("§7" + lobby.getName()).toItemStack());
               }
          });
     }

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
