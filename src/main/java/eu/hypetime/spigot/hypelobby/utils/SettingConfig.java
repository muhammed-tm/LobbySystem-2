package eu.hypetime.spigot.hypelobby.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SettingConfig {


    public static File ConfigFile = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/", "Settings.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);
    public static void save() throws IOException {
        config.save(ConfigFile);
    }


    public static void setSound(Player p, Boolean sound) throws IOException {
        config.set(p.getName() + ".Sound", sound);
        save();
    }
    public static void setnavigatoranimation(Player p, Boolean navigator) throws IOException {
        config.set(p.getName() + ".navigator", navigator);
        save();
    }
    public static void setspawnposition(Player p, Boolean navigator) throws IOException {
        config.set(p.getName() + ".spawnposition", navigator);
        save();
    }
    public static void setsichtbarkeit(Player p, Boolean blindness) throws IOException {
        config.set(p.getName() + ".sichtbarkeit", blindness);
        save();
    }
    public static void settpanimation(Player p, Boolean teleport) throws IOException {
        config.set(p.getName() + ".tpanimation", teleport);
        save();
    }
    public static void setautofly(Player p, Boolean autofly) throws IOException {
        config.set(p.getName() + ".autofly", autofly);
        save();
    }
    public static void setxpleiste(Player p, Boolean xpleiste) throws IOException {
        config.set(p.getName() + ".xpleiste", xpleiste);
        save();
    }
    public static boolean gettpanimation(Player p) {
        return config.getBoolean(p.getName() + ".tpanimation");

    }
    public static Boolean getnavigatoranimation(Player p) {
        return config.getBoolean(p.getName() + ".navigator");

    }
    public static Boolean getspawnposition(Player p) {
        return config.getBoolean(p.getName() + ".spawnposition");

    }
    public static Boolean getsichtbarkeit(Player p) {
        return config.getBoolean(p.getName() + ".sichtbarkeit");

    }
    public static Boolean getxpleiste(Player p) {
        return config.getBoolean(p.getName() + ".xpleiste");

    }
    public static Boolean autofly(Player p) {
        return config.getBoolean(p.getName() + ".autofly");

    }
    public static Boolean getSound(Player p) {
        return config.getBoolean(p.getName() + ".Sound");
    }
}

