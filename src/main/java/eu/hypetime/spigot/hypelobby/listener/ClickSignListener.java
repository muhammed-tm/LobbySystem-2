package eu.hypetime.spigot.hypelobby.listener;

import org.bukkit.block.Sign;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/*
    Created by Andre
    At 00:41 Uhr | 14. Nov.. 2021
    Project HypeLobbySpigot
*/
public class ClickSignListener implements Listener {

     @EventHandler
     public void onClick(PlayerInteractEvent event) {
          if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
               if (event.getClickedBlock().getState() instanceof Sign) {
                    Sign sign = (Sign) event.getClickedBlock().getState();
                    if (sign.getLine(0).equalsIgnoreCase("§aUnnötiges Schild")) {
                         int clicks = Integer.parseInt(sign.getLine(3));
                         int clicksNew = ((clicks) + (1));
                         sign.setLine(3, String.valueOf(clicksNew));
                         sign.update();
                         switch (clicks) {
                              case 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000 -> {
                                   event.getClickedBlock().getWorld().spawn(event.getPlayer().getLocation(), Firework.class);
                              }
                         }
                    }

               }
          }
     }

     @EventHandler
     public void onCreate(SignChangeEvent event) {
          if (event.getLine(0).equalsIgnoreCase("[us]")) {
               event.setLine(0, "§aUnnötiges Schild");
               event.setLine(1, "");
               event.setLine(2, "");
               event.setLine(3, "0");
          }
     }
}
