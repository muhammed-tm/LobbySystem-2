package eu.hypetime.spigot.hypelobby.cosmetics.utils;

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
        if(!isPlayerRegistered(player)) {
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


}
