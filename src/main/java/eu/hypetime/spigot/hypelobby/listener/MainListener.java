package eu.hypetime.spigot.hypelobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
    Created by Andre
    At 23:09 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class MainListener implements Listener {

     @EventHandler
     public void onSneak(PlayerToggleSneakEvent event) {
          if(!event.getPlayer().isSneaking()) {
               event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 1));
          } else {
               event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
          }
     }
}
