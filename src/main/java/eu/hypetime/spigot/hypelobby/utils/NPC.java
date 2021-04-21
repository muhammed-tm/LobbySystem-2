package eu.hypetime.spigot.hypelobby.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
    Created by Andre
    At 11:57 Uhr | 20. Apr.. 2021
    Project HypeLobbySpigot
*/
public class NPC {

      private static List<EntityPlayer> NPC = new ArrayList<>();

      public static void createNPC(Location location) {
           MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
           WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
           GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "§6Belohnung");
           EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));

           npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

           for (Player player : Bukkit.getOnlinePlayers()) {
                String[] skin = getSkin(player, "QuadrixYT");
                gameProfile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
           }

           addNPCPacket(npc);
           NPC.add(npc);
           MySQL.update("INSERT INTO npc (Location, NPC) VALUES ('" + location + "', '" + npc + "')");
           for(Entity player : location.getNearbyEntitiesByType(EntityType.PLAYER.getEntityClass(), 2d, 2d, 2d)) {
                player.sendMessage("MySQL Eintrag erfolreich");
           }
      }

      private static String[] getSkin(Player player, String name) {
           try {
               URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
               InputStreamReader reader = new InputStreamReader(url.openStream());
               String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

               URL url1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile" + uuid + "?unsigned=false");
               InputStreamReader reader1 = new InputStreamReader(url1.openStream());
               JsonObject property = new JsonParser().parse(reader1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
               String texture = property.get("value").getAsString();
               String signature = property.get("signature").getAsString();
               return new String[] {texture, signature};
           } catch(IOException exception) {
                EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
                GameProfile profile = entityPlayer.getProfile();
                Property property = profile.getProperties().get("textures").iterator().next();
                String texture = property.getValue();
                String signature = property.getSignature();
                return new String[] {texture, signature};
           }
      }

      public static void addNPCPacket(EntityPlayer npc) {
           for (Player all : Bukkit.getOnlinePlayers()) {
                PlayerConnection connection = ((CraftPlayer) all).getHandle().playerConnection;
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
                connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
           }
      }

     public static void addJoinPacket(Player player) {
          for (EntityPlayer npc : NPC) {
               PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
               connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
               connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
               connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
          }
     }

     public static List<EntityPlayer> getNPC() {
          return NPC;
     }
}
