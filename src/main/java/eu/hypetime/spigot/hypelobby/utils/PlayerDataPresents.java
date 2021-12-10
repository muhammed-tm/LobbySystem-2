package eu.hypetime.spigot.hypelobby.utils;

import org.apache.logging.log4j.core.config.Reconfigurable;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerDataPresents {

    public static File presentsfile;
    public static YamlConfiguration presentcfg;

    public void loadFile() throws IOException {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "playerData.yml");
        presentsfile = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "presents.yml");
        if (!file.exists()) {
            File folder = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents");
            if (!folder.exists())
                folder.mkdirs();
            file.createNewFile();
        }
        if (!presentsfile.exists()) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            File folder = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents");
            if (!folder.exists())
                folder.mkdirs();
            presentsfile.createNewFile();
            cfg.set("Presents.Count", Integer.valueOf(0));
            saveFile(cfg, presentsfile);
        }
        presentcfg = YamlConfiguration.loadConfiguration(presentsfile);
    }

    public int getPresentCount() {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "presents.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getInt("Presents.Count");
    }

    public void addPresent(Location loc) {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "presents.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int count = getPresentCount() + 1;
        cfg.set("Presents.Count", Integer.valueOf(count));
        cfg.set("Presents." + count, loc);
        saveFile(cfg, file);
    }

    public void setOpened(Player p, int x, int y, int z) {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "playerData.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int id = getPresentID(x, y, z);
        cfg.set(p.getUniqueId().toString() + "." + id + ".Found", Boolean.valueOf(true));
        cfg.set(p.getUniqueId().toString() + ".Amount", Integer.valueOf(getOpenedPresentCountForPlayer(p) + 1));
        saveFile(cfg, file);
    }

    public static boolean existsPlayer(Player p) {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "playerData.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return (cfg.getString(p.getUniqueId().toString() + ".Name") != null);
    }

    public void createPlayer(Player p) {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "playerData.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(p.getUniqueId().toString() + ".Name", p.getName());
        cfg.set(p.getUniqueId().toString() + ".Amount", Integer.valueOf(0));
        saveFile(cfg, file);
    }

    public static boolean hasOpened(Player p, int x, int y, int z) {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "playerData.yml");
        File presentFile = new File("./../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "presents.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        YamlConfiguration presentCfg = YamlConfiguration.loadConfiguration(file);
        int id = (new PlayerDataPresents()).getPresentID(x, y, z);
        try {
            return cfg.getBoolean(p.getUniqueId().toString() + "." + id + ".Found");
        } catch (Exception e) {
            return false;
        }
    }

    public int getOpenedPresentCountForPlayer(Player p) {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "playerData.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId().toString() + ".Name") == null) {
            cfg.set(p.getUniqueId().toString() + ".Amount", Integer.valueOf(0));
            return 0;
        }
        return cfg.getInt(p.getUniqueId().toString() + ".Amount");
    }

    public int getPresentID(int x, int y, int z) {
        File file = new File("../../../local/templates/Lobbys/default/plugins/HypeLobby/Presents", "presents.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int id = 0;
        for(int i = 0; i < 101; i++) {
            if(Integer.parseInt(cfg.getString("Presents." + i + ".X")) == x
                && Integer.parseInt(cfg.getString("Presents." + i + ".Y")) == y
                && Integer.parseInt(cfg.getString("Presents." + i + ".Z")) == z) {
                id = i;
                break;
            }
        }
        return id;
    }

    private void saveFile(YamlConfiguration cfg, File file) {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}