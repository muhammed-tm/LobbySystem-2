package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.commands.SitCommand;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.boots.BootsPlayer;
import eu.hypetime.spigot.hypelobby.cosmetics.listener.pets.PetsManager;
import eu.hypetime.spigot.hypelobby.cosmetics.utils.CosmeticsManager;
import eu.hypetime.spigot.hypelobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;



/*
    Created by Andre
    At 23:30 Uhr | 09. Apr.. 2021
    Project HypeLobbySpigot
*/

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(null);
        Inventories.mainInventory(player);
        RewardAPI.createIfNotExist(player);
        StatsManager.updatePlayer(player);
        CosmeticsManager.createPlayer(player);

        player.setGameMode(GameMode.SURVIVAL);

        if (SettingConfig.getspawnposition(player) == false)
             player.teleport(WarpAPI.getLocation("Spawn"));

        player.sendTitle("§6Willkommen", "§7" + player.getName());
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2F, 1F); //activate when halloween is over
        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
            ScoreAPI.setScoreboard(onlinePlayer);
        if (!PlayerDataPresents.existsPlayer(player))
            (new PlayerDataPresents()).createPlayer(player);
        if (!PlayerManager.hasAccept(player))
            Inventories.DSGVOInventory(player);

        if (player.getName().equals("quele"))
            player.getWorld().strikeLightningEffect(player.getLocation());
        if (player.getName().equals("DasAkkusativer"))
            player.sendMessage("§4§lAKKU_GHG WAS GHGT???");
        if (player.getName().equals("McRafy"))
            player.sendMessage("§4§lHaste heute Sport gemacht???");
         player.setFoodLevel(20);


          /* Halloween Update <>
          BlindScare.updatePlayer(player);
          Halloween Update <> */
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(null);
        if (SitCommand.sitting.contains(event.getPlayer().getUniqueId())) {
            SitCommand.sitting.remove(event.getPlayer().getUniqueId());
            event.getPlayer().getVehicle().remove();
        }
        if (HypeLobby.sp.getPlayerUUIDs().contains(event.getPlayer().getUniqueId()))
            HypeLobby.sp.removePlayer(event.getPlayer());

        if (PetsManager.petMap.containsKey(event.getPlayer()))
            PetsManager.removePet(PetsManager.getPet(event.getPlayer()));

        if (BootsPlayer.bootsPlayers.containsKey(event.getPlayer())) {
            BootsPlayer.stopParticle(event.getPlayer());
            BootsPlayer.bootsPlayers.remove(event.getPlayer());
        }
        BootsPlayer.stopParticle(event.getPlayer());
    }

}
