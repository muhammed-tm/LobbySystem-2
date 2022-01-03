package eu.hypetime.spigot.hypelobby.cosmetics.utils;

import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Boots;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Gadget;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Particle;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.enums.Pet;
import eu.hypetime.spigot.hypelobby.utils.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CosmeticsManager {

     public static boolean isPlayerRegistered(Player player) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM lobby_gadgets WHERE UUID = '" + player.getUniqueId() + "'");
               if (rs == null) return false;
               if (!rs.next()) return false;
               return true;
          } catch (SQLException ignored) {
               return false;
          }
     }

     public static void createPlayer(Player player) {
          if (!isPlayerRegistered(player)) {
               MySQL.update("INSERT INTO lobby_gadgets(UUID, pets, gadgets, particle, boots) VALUES ('" + player.getUniqueId() + "', '', '', '', '')");
          }
     }

     public static boolean hasGadget(Player player, Gadget gadget) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM lobby_gadgets WHERE UUID = '" + player.getUniqueId() + "'");
               if (rs == null) return false;
               if (!rs.next()) return false;
               return rs.getString(gadget.getCategory().getName()).contains(gadget.getName());
          } catch (SQLException ignored) {
               return false;
          }
     }

     public static boolean hasPet(Player player, Pet pet) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM lobby_gadgets WHERE UUID = '" + player.getUniqueId() + "'");
               if (rs == null) return false;
               if (!rs.next()) return false;
               return rs.getString(pet.getCategory().getName()).contains(pet.getName());
          } catch (SQLException ignored) {
               return false;
          }
     }

     public static boolean hasParticle(Player player, Particle particle) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM lobby_gadgets WHERE UUID = '" + player.getUniqueId() + "'");
               if (rs == null) return false;
               if (!rs.next()) return false;
               return rs.getString(particle.getCategory().getName()).contains(particle.getName());
          } catch (SQLException ignored) {
               return false;
          }
     }

     public static boolean hasBoots(Player player, Boots boots) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM lobby_gadgets WHERE UUID = '" + player.getUniqueId() + "'");
               if (rs == null) return false;
               if (!rs.next()) return false;
               return rs.getString(boots.getCategory().getName()).contains(boots.getName());
          } catch (SQLException ignored) {
               return false;
          }
     }


     public static String getList(Player player, GadgetCategory category) {
          try {
               ResultSet rs = MySQL.getResult("SELECT * FROM lobby_gadgets WHERE UUID = '" + player.getUniqueId() + "'");
               if (rs == null) return "";
               if (!rs.next()) return "";
               return rs.getString(category.getName());
          } catch (SQLException ignored) {
               return "";
          }
     }

     public static void addGadget(Player player, Gadget gadget) {
          StringBuilder builder = new StringBuilder(getList(player, gadget.getCategory()));
          builder.append(";" + gadget.getName());
          MySQL.update("UPDATE lobby_gadgets SET " + gadget.getCategory().getName() + " = '" + builder.toString() + "' WHERE UUID = '" + player.getUniqueId().toString() + "'");
     }

     public static void addPet(Player player, Pet pet) {
          StringBuilder builder = new StringBuilder(getList(player, pet.getCategory()));
          builder.append(";" + pet.getName());
          MySQL.update("UPDATE lobby_gadgets SET " + pet.getCategory().getName() + " = '" + builder.toString() + "' WHERE UUID = '" + player.getUniqueId().toString() + "'");
     }

     public static void addParticle(Player player, Particle particle) {
          StringBuilder builder = new StringBuilder(getList(player, particle.getCategory()));
          builder.append(";" + particle.getName());
          MySQL.update("UPDATE lobby_gadgets SET " + particle.getCategory().getName() + " = '" + builder.toString() + "' WHERE UUID = '" + player.getUniqueId().toString() + "'");
     }

     public static void addBoots(Player player, Boots boots) {
          StringBuilder builder = new StringBuilder(getList(player, boots.getCategory()));
          builder.append(";" + boots.getName());
          MySQL.update("UPDATE lobby_gadgets SET " + boots.getCategory().getName() + " = '" + builder.toString() + "' WHERE UUID = '" + player.getUniqueId().toString() + "'");
     }

}
