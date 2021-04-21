package eu.hypetime.spigot.hypelobby.utils;

/*
    Created by Andre
    At 21:47 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

     public File f;
     public YamlConfiguration c;
     public FileConfiguration fc;

     public Config(String DateiPfad, String DateiName) {
          this.f = new File(DateiPfad, DateiName);
          this.c = YamlConfiguration.loadConfiguration(this.f);
          this.fc = YamlConfiguration.loadConfiguration(this.f);
          if(!this.f.exists()) {
               try {
                    this.f.createNewFile();
               } catch (IOException e) {

               }
          }
     }

     public Config setValue(String name, Object inhalt) {
          c.set(name, inhalt);
          save();
          return this;
     }

     public int getInt(String name) {
          load();
          return c.getInt(name);
     }

     public Double getDouble(String name) {
          load();
          return c.getDouble(name);
     }

     @SuppressWarnings("unchecked")
     public ArrayList<UUID> getUUIDMap(String name) {
          return (ArrayList<UUID>) c.getList(name);
     }

     @SuppressWarnings("unchecked")
     public ArrayList<String> getMap(String name) {
          return (ArrayList<String>) c.getList(name);
     }

     public void addDefault(String name, Object value) {
          load();
          if(c.get(name) == null) {
               setValue(name, value);
               save();
          }

     }

     public void copyDefaults() {
          c.options().copyDefaults();
     }

     public String getString(String name) {
          load();
          return ChatColor.translateAlternateColorCodes('&', c.getString(name));
     }

     public boolean getBoolean(String name) {
          return c.getBoolean(name);
     }

     public long getLong(String name) {
          return c.getLong(name);
     }

     public List<String> getStringList(String name) {
          return c.getStringList(name);
     }

     public Set<String> getKeys(boolean deep) {
          return c.getKeys(deep);
     }

     public ConfigurationSection getConfigurationSection(String Section) {
          return c.getConfigurationSection(Section);
     }

     public boolean exist() {
          return this.f.exists();
     }

     public Config load() {
          try {
               c.load(f);
          } catch (IOException | InvalidConfigurationException e) {
               e.printStackTrace();
          }
          return this;
     }

     public Config save() {
          try {
               this.c.save(this.f);
          } catch (IOException ignored) {
          }
          return this;
     }
}

