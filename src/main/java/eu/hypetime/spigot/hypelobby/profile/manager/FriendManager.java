/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.spigot.hypelobby.profile.manager;

import eu.hypetime.spigot.hypelobby.profile.sql.FriendSQL;
import eu.hypetime.spigot.hypelobby.profile.utils.FriendPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FriendManager {
     private final List<FriendPlayer> players = new ArrayList<>();

     private final FriendSQL mysql;

     public FriendManager(FriendSQL mysql) {
          this.mysql = mysql;
     }

     public FriendPlayer loadPlayer(UUID uuid, String name) {
          FriendPlayer player = this.mysql.getPlayer(uuid);
          player = getPlayer(uuid);
          player.setName(name);
          this.mysql.setPlayer(player);

          this.players.add(player);
          return player;
     }

     public void unloadPlayer(UUID uuid) {
          FriendPlayer player = getPlayer(uuid);
          if (player != null)
               this.mysql.setPlayer(player);
     }

     public FriendPlayer getPlayer(UUID uuid) {
          for (FriendPlayer player : this.players) {
               if (player.getUuid().equals(uuid))
                    return player;
          }
          return this.mysql.getPlayer(uuid);
     }

     public void removeFriend(FriendPlayer requester, FriendPlayer target) {
          if (!requester.getFriends().contains(target.getUuid()))
               return;
          target.getFriends().remove(requester.getUuid());
          requester.getFriends().remove(target.getUuid());
          this.mysql.setPlayer(target);
          this.mysql.setPlayer(requester);
          return;
     }


     public FriendSQL getMysql() {
          return this.mysql;
     }
}