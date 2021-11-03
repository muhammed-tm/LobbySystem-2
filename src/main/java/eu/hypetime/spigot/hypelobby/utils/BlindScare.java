package eu.hypetime.spigot.hypelobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BlindScare {

    public static void updatePlayer(Player player) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25, 0));
            players.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
            players.getWorld().strikeLightningEffect(players.getLocation());
            players.playEffect(EntityEffect.HURT);
            players.playEffect(EntityEffect.HURT);
            players.playEffect(EntityEffect.HURT);
            players.playSound(players.getLocation(), Sound.ENTITY_GHAST_SCREAM, 2.0f, 3.0f);
            player.sendTitle("§7Willkommen!", "§6Happy §cHalloween§l!", 30, 40, 30);
        }
    }
}
