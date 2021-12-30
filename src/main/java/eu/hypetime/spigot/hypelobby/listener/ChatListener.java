package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.utils.PlayerManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
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
        String msg = event.getMessage();
        Player player = event.getPlayer();

        if (PlayerManager.hasAccept(event.getPlayer())) {
            event.setFormat(event.getPlayer().getDisplayName() + "§8: §7" + event.getMessage());

        } else {
            TextComponent message = new TextComponent("Du musst davor die §lDatenschutz-Grundverordnung §7Akzeptieren. §7(§a§l/dsgvo accept§7)§8.");
            message.setFont("minecraft:uniform");

            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Du musst davor die §lDatenschutz-Grundverordnung §7Akzeptieren. §7(§a§l/dsgvo accept§7)§8.")));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dsgvo accept"));
            event.getPlayer().sendMessage(message);
            event.setCancelled(true);
        }
    }

}
