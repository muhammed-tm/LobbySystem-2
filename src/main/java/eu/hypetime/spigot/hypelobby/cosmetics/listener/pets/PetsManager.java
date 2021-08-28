package eu.hypetime.spigot.hypelobby.cosmetics.listener.pets;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/*
    Created by Andre
    At 10:25 Uhr | 25. Aug.. 2021
    Project HypeLobbySpigot
*/
public class PetsManager {

     public static Map<Player, PetEntity> petMap = new HashMap<>();

     public static PetEntity getPet(Player player) {
          if (petMap.containsKey(player)) {
               return petMap.get(player);
          } else {
               return null;
          }
     }

     public static void removePet(PetEntity pet) {
          Player player = pet.getPlayer();
          ((LivingEntity) pet.getEntity()).setHealth(0);
          petMap.remove(player);
     }

     public static PetEntity createEntityByEntityType(Player player, EntityType type) {
          Class<?> craftCreatureClass = getOrgEntityClass("CraftCreature");
          Class<?> craftPetClass = getOrgEntityClass("Craft" + type.getEntityClass().getSimpleName());
          if (!craftCreatureClass.isAssignableFrom(craftPetClass)) {
               try {
                    throw new UnsupportedEntityException("Ungültiger PetType: " + type.toString());
               } catch (UnsupportedEntityException exception) {
                    exception.printStackTrace();
               }
               return null;
          }
          if (petMap.containsKey(player)) {
               removePet(petMap.get(player));
          }
          PetEntity created = new PetEntity(player, type);
          petMap.put(player, created);
          return created;
     }

     public static Class<?> getOrgEntityClass(String className) {
          String fullName = "org.bukkit.craftbukkit." + getVersion() + "entity." + className;
          Class<?> clazz = null;
          try {
               clazz = Class.forName(fullName);
          } catch (Exception exception) {
               exception.printStackTrace();
          }
          return clazz;
     }

     public static String getVersion() {
          String name = Bukkit.getServer().getClass().getPackage().getName();
          String version = name.substring(name.lastIndexOf('.') + 1) + ".";
          return version;
     }
}
