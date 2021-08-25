package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftCreature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/*
    Created by Andre
    At 10:25 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class PetEntity {

     private final Player player;
     private final EntityType petType;
     private final Entity entity;
     private String name;
     private int followTask;

     public PetEntity(Player player, EntityType petType) {
          this.player = player;
          this.petType = petType;
          this.entity = player.getWorld().spawnEntity(player.getLocation(), this.petType);
          this.entity.setCustomNameVisible(true);
          this.entity.setCustomName("§6" + player.getName() + "´s Pet");
          this.entity.setInvulnerable(true);
          ((LivingEntity) this.entity).setSilent(true);
          ((LivingEntity) this.entity).setAI(true);
          startFollowTask();
     }

     public void startFollowTask() {
          followTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(HypeLobby.getInstance(), () -> {
               follow();
          }, 0L, 20L);
     }

     public Player getPlayer() {
          return player;
     }

     public Entity getEntity() {
          return entity;
     }

     public int getFollowTask() {
          return followTask;
     }

     private void follow() {
          if (entity.isDead()) {
               Bukkit.getScheduler().cancelTask(followTask);
               return;
          }

          if (entity.getLocation().distanceSquared(player.getLocation()) <= 16) {
               return;
          }

          if (entity.getWorld() != player.getWorld()) {
               if (!player.isOnGround())
                    return;
               entity.teleport(player);
          }

          if (entity.getLocation().distanceSquared(player.getLocation()) > 100) {
               if (!player.isOnGround())
                    return;
               entity.teleport(player);
          } else {
               CraftCreature creature = (CraftCreature) entity;
               Location loc = player.getLocation();
               creature.getHandle().getNavigation().a(loc.getX(), loc.getY(), loc.getZ(), 1.4);
          }
     }
}
