package eu.hypetime.spigot.hypelobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/*
    Created by Andre
    At 01:54 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class ChatListener implements Listener {

     @EventHandler
     public void onChat(AsyncPlayerChatEvent event) {
          event.setFormat(event.getPlayer().getDisplayName() + "§8: §7" + event.getMessage());
     }

}
