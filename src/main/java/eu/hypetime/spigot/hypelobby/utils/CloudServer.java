package eu.hypetime.spigot.hypelobby.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStartEvent;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStopEvent;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.ServiceInfoSnapshotUtil;
import de.dytanic.cloudnet.ext.bridge.bukkit.event.BukkitCloudServiceInfoUpdateEvent;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class CloudServer implements Listener {

    static ArrayList < ItemStack > list = new ArrayList <>();


    public void sendPlayer(Player player, String server) {
        ((IPlayerManager)CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class))
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
    public void onStart(CloudServiceStartEvent event){
        if(event.getServiceInfo().getServiceId().getName().split("-")[0].equals("Lobby")){
            while (ServiceInfoSnapshotUtil.isOnline(event.getServiceInfo())){
                HypeLobby.getInstance().lobbySwitcherListener.updateInventory();
            }
        }

    }

    @EventListener
    public void onStop(CloudServiceStopEvent event){
        if(event.getServiceInfo().getServiceId().getName().split("-")[0].equals("Lobby")){
            while (!ServiceInfoSnapshotUtil.isOnline(event.getServiceInfo())){
                HypeLobby.getInstance().lobbySwitcherListener.updateInventory();
            }
        }
    }

    @EventListener
    public void onC(BukkitCloudServiceInfoUpdateEvent event){
        if(event.getServiceInfoSnapshot().getServiceId().getName().split("-")[0].equals("Lobby")){
            HypeLobby.getInstance().lobbySwitcherListener.updateInventory();
        }
    }

    public static ArrayList < ItemStack > getLobbys() {
        Collection < ServiceInfoSnapshot > lobbys = CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices("Lobby");
        for (ServiceInfoSnapshot servers : lobbys) {
            if (ServiceInfoSnapshotUtil.isOnline(servers)) {
                Integer players = ServiceInfoSnapshotUtil.getPlayers(servers).size();
                list.add(new ItemBuilder(Material.GUNPOWDER, players).setName(servers.getServiceId().getName()).toItemStack());
            }
        }
        return list;
    }
}
