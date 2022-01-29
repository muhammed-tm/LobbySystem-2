package eu.hypetime.spigot.hypelobby.utils;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/*
    Created by Andre
    At 09:57 Uhr | 08. Apr.. 2021
    Project KitPVP - HypeTime
*/
public class WarpAPI {

     public static Config location = new Config("../../../local/templates/Lobbys/default/plugins/HypeLobby/", "location.yml");

     public static void setLocation(Player player, String name) {
          Location playerLoc = player.getLocation();
          Location loc = new Location(playerLoc.getWorld(), playerLoc.getX(), playerLoc.getY(), playerLoc.getZ(), playerLoc.getYaw(), playerLoc.getPitch());
          location.setValue("loc." + name.toLowerCase(), loc);
          location.save();
          player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast §aerfolgreich §7den §6Spawnpunkt §7von §6" + name + " §7gesetzt§8.");
     }

     public static void setLocation(Location locationSet, String name) {
          Location loc = new Location(locationSet.getWorld(), locationSet.getX(), locationSet.getY(), locationSet.getZ(), locationSet.getYaw(), locationSet.getPitch());
          location.setValue("loc." + name.toLowerCase(), loc);
          location.save();
     }

     public static void tpWarp(Player player, String name) {
          name = name.toLowerCase();
          if (!isExist(name.toLowerCase())) {
               player.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Es ist ein §4Fehler §7aufgetreten§8. §7Bitte melde dich im Support§8.");
               return;
          }
          Location loc = location.c.getLocation("loc." + name);
          if (player.getOpenInventory() != null) {
               player.getOpenInventory().close();
          }
          Particle particle = Particle.FLAME;

          if (SettingConfig.gettpanimation(player) == false) {
               player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
               player.setVelocity(new Vector(0, 2.5, 0));
               PacketPlayOutWorldParticles packet1 = new PacketPlayOutWorldParticles(CraftParticle.toNMS(Particle.MOB_APPEARANCE), false, 0, 0, 0f, 0f, 0f, 0f, 0, 1);
               ((CraftPlayer) player).getHandle().b.sendPacket(packet1);
               Bukkit.getScheduler().runTaskLater(HypeLobby.getInstance(), () -> player.teleport(loc), 15L);
          }else {
               player.teleport(loc);
               player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2F, 1F);
          }
     }

     public static Location getLocation(String name) {
          name = name.toLowerCase();
          if (!isExist(name.toLowerCase())) {
               return null;
          }
          return location.c.getLocation("loc." + name);
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
          return location.getString("loc." + name) != null;
     }
}
