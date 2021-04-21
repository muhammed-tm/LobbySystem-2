package eu.hypetime.spigot.hypelobby.utils;

import com.destroystokyo.paper.ParticleBuilder;
import eu.hypetime.spigot.hypelobby.HypeLobby;
import net.minecraft.server.v1_16_R3.PacketDataSerializer;
import net.minecraft.server.v1_16_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R3.CraftParticle;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/*
    Created by Andre
    At 09:57 Uhr | 08. Apr.. 2021
    Project KitPVP - HypeTime
*/
public class WarpAPI {

     public static Config location = new Config(HypeLobby.getInstance().getDataFolder().getAbsolutePath(), "location.yml");

     public static void setLocation(Player player, String name) {
          Location playerLoc = player.getLocation();
          Location loc = new Location(playerLoc.getWorld(), playerLoc.getX(), playerLoc.getY(), playerLoc.getZ(), playerLoc.getYaw(), playerLoc.getPitch());
          location.setValue("loc.world." + name.toLowerCase(), loc.getWorld().getName());
          location.setValue("loc.x." + name.toLowerCase(), loc.getX());
          location.setValue("loc.y." + name.toLowerCase(), loc.getY());
          location.setValue("loc.z." + name.toLowerCase(), loc.getZ());
          location.setValue("loc.yaw." + name.toLowerCase(), loc.getYaw());
          location.setValue("loc.pitch." + name.toLowerCase(), loc.getPitch());
          location.save();
          player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast §aerfolgreich §7den §6Spawnpunkt §7von §6" + name + " §7gesetzt§8.");
     }

     public static void tpWarp(Player player, String name) {
          name = name.toLowerCase();
          if(!isExist(name.toLowerCase())) {
               player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Es ist ein §4Fehler §7aufgetreten§8. §7Bitte melde dich im Support§8.");
               return;
          }
          Location loc = new Location(Bukkit.getWorld(getName("loc.world." + name)), getDouble("loc.x." + name), getDouble("loc.y." + name), getDouble("loc.z." + name),
               getFloat("loc.yaw." + name), getFloat("loc.pitch." + name));
          if(player.getOpenInventory() != null) {
               player.getOpenInventory().close();
          }
          Particle particle = Particle.FLAME;
          ParticleBuilder builder = particle.builder();
          builder.location(player.getLocation());
          builder.offset(0, 0, 0);
          builder.count(100);
          builder.receivers(10);
          builder.spawn();
          player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
          player.setVelocity(new Vector(0, 2.5, 0));
          PacketPlayOutWorldParticles packet1 = new PacketPlayOutWorldParticles(CraftParticle.toNMS(Particle.MOB_APPEARANCE), false, 0, 0, 0f, 0f, 0f, 0f, 0, 1);
          ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet1);
          Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> player.teleport(loc), 15L);
     }

     public static Location getLocation(String name) {
          name = name.toLowerCase();
          Location loc = new Location(Bukkit.getWorld(getName("loc.world." + name)), getDouble("loc.x." + name), getDouble("loc.y." + name), getDouble("loc.z." + name),
               getFloat("loc.yaw." + name), getFloat("loc.pitch." + name));
          return loc;
     }

     public static String getName(String value) {
          return location.getString(value);
     }

     public static Integer getInt(String value) {
          return location.getInt(value);
     }

     public static Double getDouble(String value) {
          return location.getDouble(value);
     }

     public static Float getFloat(String value) {
          return (float) location.getInt(value);
     }

     public static boolean isExist(String name) {
          return location.getString("loc.world." + name) != null;
     }
}
