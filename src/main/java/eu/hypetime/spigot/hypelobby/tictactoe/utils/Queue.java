package eu.hypetime.spigot.hypelobby.tictactoe.utils;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

/*
    Created by Andre
    At 08:14 Uhr | 11. Apr.. 2021
    Project HypeLobbySpigot
*/
public class Queue {

     public static List<Player> queue = Lists.newArrayList();

     public static void removeFromQueue() {
          queue.remove(0);
          queue.remove(0);
     }
}
