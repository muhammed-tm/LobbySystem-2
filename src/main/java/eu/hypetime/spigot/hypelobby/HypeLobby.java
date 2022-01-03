package eu.hypetime.spigot.hypelobby;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import eu.hypetime.spigot.hypelobby.commands.*;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.BuyListener;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.CosmeticsListener;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.boots.BootsInventory;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.gadgets.*;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.particle.ParticleInventory;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.PetSettingsInventory;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.PetsInventory;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.PetsListener;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.RenameListener;
import eu.hypetime.spigot.hypelobby.listener.*;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    Created by Andre
    At 21:37 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

public class HypeLobby extends JavaPlugin {

     public static Song s;
     public static SongPlayer sp;
     private static HypeLobby instance;
     private static ExecutorService executorService;
     public LobbySwitcherListener lobbySwitcherListener;
     public boolean broadcast;
     private Config config;
     private Constants constants;
     //private FriendManager friendManager;
     public HashMap<Player, BukkitRunnable> run = new HashMap<>();

     public static HypeLobby getInstance() {
          return instance;
     }

     public static ExecutorService getExecutorService() {
          return executorService;
     }
     private static ArrayList<UUID> noplayersvisible;

     @Override
     public void onEnable() {
          instance = this;
          s = NBSDecoder.parse(new File(getDataFolder(), "Song.nbs"));
          sp = new RadioSongPlayer(s);

          loadFiles();
          config = new Config(getDataFolder().getAbsolutePath(), "config.yml");
          constants = new Constants(this);
          //friendManager = new FriendManager(new FriendSQL());

          executorService = Executors.newCachedThreadPool();

          getLogger().info(constants.getPrefix() + "Das System wurde aktiviert§8.");

          registerListener();
          registerCommands();

          ScoreAPI.startScheduler();
         // ScoreAPI.startLevelBar();

          List<Material> materials = new ArrayList<>();
          materials.add(Material.TERRACOTTA);
          materials.add(Material.WHITE_TERRACOTTA);
          materials.add(Material.BLUE_TERRACOTTA);
          materials.add(Material.CYAN_TERRACOTTA);
          materials.add(Material.GREEN_TERRACOTTA);
          materials.add(Material.LIGHT_BLUE_TERRACOTTA);
          materials.add(Material.LIME_TERRACOTTA);
          materials.add(Material.MAGENTA_TERRACOTTA);
          final int[] i = {0};
          final int[] i2 = {0};
          Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
               if (i[0] == 8) {
                    i[0] = 0;
                    i2[0] += 1;
                    if (i2[0] == 8) {
                         i2[0] = 0;
                    }
               }
               WarpAPI.getLocation("rgbblock." + i[0]).getBlock().setType(materials.get(i2[0]));
               i[0] += 1;
          }, 5, 5);
          Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
               Block block = WarpAPI.getLocation("Lotto").getBlock();
               block.setType(Material.CHEST);
               ((Directional) block.getBlockData()).setFacing(BlockFace.SOUTH);
               WarpAPI.getLocation("Lotto").getBlock().setType(Material.CHEST);
               ArmorStand armorStand = WarpAPI.getLocation("Lotto").getWorld().spawn(WarpAPI.getLocation("Lotto").add(0.5, -0.5, 0.5), ArmorStand.class);
               armorStand.setCustomName("§6Lotto");
               armorStand.setCustomNameVisible(true);
               armorStand.setArms(true);
               armorStand.setBasePlate(false);
               armorStand.setInvisible(true);
               armorStand.setInvulnerable(true);
               armorStand.addDisabledSlots(EquipmentSlot.values());

               ArmorStand reward = WarpAPI.getLocation("Belohnung").getWorld().spawn(WarpAPI.getLocation("Belohnung"), ArmorStand.class);
               reward.setCustomName("§6Daily Rewards");
               reward.setCustomNameVisible(true);
               reward.setArms(true);
               reward.setBasePlate(false);
               reward.getEquipment().setHelmet(new ItemStack(Material.TURTLE_HELMET));
               reward.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
               reward.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
               reward.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
               reward.setInvulnerable(true);
               reward.addDisabledSlots(EquipmentSlot.values());
          }, 20);

          getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
     }

     public Config getConfigFile() {
          return config;
     }

     public Constants getConstants() {
          return constants;
     }

     private void registerListener() {
          new SQLStats();
          StatsManager.Top3Scheduler();

          PluginManager pluginManager = Bukkit.getPluginManager();
          lobbySwitcherListener = new LobbySwitcherListener();
          pluginManager.registerEvents(new JoinListener(), this);
          pluginManager.registerEvents(new ChatListener(), this);
          pluginManager.registerEvents(new NavListener(), this);
          pluginManager.registerEvents(new ProfilListener(), this);
          pluginManager.registerEvents(new MainListener(), this);
          pluginManager.registerEvents(new BuildListener(), this);
          pluginManager.registerEvents(lobbySwitcherListener, this);
          pluginManager.registerEvents(new DailyRewardListener(), this);
          pluginManager.registerEvents(new DailyRewardGUIListener(), this);
          pluginManager.registerEvents(new NPCListener(), this);
          pluginManager.registerEvents(new SitCommand(), this);
          pluginManager.registerEvents(new DoubleJumpListener(), this);
          pluginManager.registerEvents(new ClickSignListener(), this);
          pluginManager.registerEvents(new SneakListener(), this);
          //pluginManager.registerEvents(new FriendInteractListener(), this);

          //Cosmetics
          pluginManager.registerEvents(new CosmeticsListener(), this);
          pluginManager.registerEvents(new BuyListener(), this);

          pluginManager.registerEvents(new SchildListener(), this);


          //SHOP
          pluginManager.registerEvents(new RankShopListener(), this);

          //Present System
          //pluginManager.registerEvents(new PresentInteractListener(), this);

          //DSGVO
          pluginManager.registerEvents(new DSGVOListener(), this);

          //Gadgets
          pluginManager.registerEvents(new GadgetsInventory(), this);
          pluginManager.registerEvents(new RodListener(), this);
          pluginManager.registerEvents(new EnderpearlListener(), this);
          pluginManager.registerEvents(new TeleportBowListener(), this);
          //pluginManager.registerEvents(new TrailGunListener(), this);
          pluginManager.registerEvents(new RocketLaunchListener(), this);

          //Pets
          pluginManager.registerEvents(new PetsInventory(), this);
          pluginManager.registerEvents(new PetsListener(), this);
          pluginManager.registerEvents(new PetSettingsInventory(), this);
          pluginManager.registerEvents(new RenameListener(), this);

          //Boots
          pluginManager.registerEvents(new BootsInventory(), this);

          //Particle
          pluginManager.registerEvents(new ParticleInventory(), this);
          pluginManager.registerEvents(new Lottery(), this);
     }

     private void registerCommands() {
          getCommand("build").setExecutor(new BuildCommand());
          getCommand("set").setExecutor(new SetCommand());
          getCommand("stats").setExecutor(new StatsCommand());
          getCommand("top5").setExecutor(new Top5Command());
          getCommand("sit").setExecutor(new SitCommand());
          getCommand("patchnotes").setExecutor(new PatchNotesCommand());
          //getCommand("createpresent").setExecutor(new PresentSetupCommand());
          //getCommand("presents").setExecutor(new PresentCommand());
     }

    /*public FriendManager getFriendManager() {
          return friendManager;
     }*/
    private void loadFiles() {
         try {
              (new PlayerDataPresents()).loadFile();
         } catch (IOException e) {
              e.printStackTrace();
         }
    }

     public static ArrayList<UUID> getNonplayersvisible() {
          return noplayersvisible;
     }
}
