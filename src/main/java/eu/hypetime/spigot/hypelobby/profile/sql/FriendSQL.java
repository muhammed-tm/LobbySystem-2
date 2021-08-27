/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.spigot.hypelobby.profile.sql;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.profile.utils.FriendPlayer;
import eu.hypetime.spigot.hypelobby.utils.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class FriendSQL {

     public void setPlayer(FriendPlayer friendPlayer) {
          if (getPlayer(friendPlayer.getUuid()) == null) {
               StringBuilder friends = new StringBuilder();
               for (UUID uuid : friendPlayer.getFriends())
                    friends.append(uuid.toString()).append(";");
               StringBuilder requests = new StringBuilder();
               for (UUID uuid : friendPlayer.getRequests())
                    requests.append(uuid.toString()).append(";");
               MySQL.update("INSERT INTO `proxy_FriendSystem` (`uuid`, `name`, `playerFriends`, `requests`, `maxFriends`) VALUES ('" + friendPlayer
                    .getUuid() + "', '" + friendPlayer.getName() + "', '" + friends + "', '" + requests + "', '" + friendPlayer.getMaxFriends() + "')");
          } else {
               StringBuilder friends = new StringBuilder();
               for (UUID uuid : friendPlayer.getFriends())
                    friends.append(uuid.toString()).append(";");
               StringBuilder requests = new StringBuilder();
               for (UUID uuid : friendPlayer.getRequests())
                    requests.append(uuid.toString()).append(";");
               MySQL.update("UPDATE `proxy_FriendSystem` SET `name`='" + friendPlayer.getName() + "', `playerfriends`='" + friends + "', `requests`='" + requests + "', `maxFriends`='" + friendPlayer.getMaxFriends() + "' WHERE `uuid`='" + friendPlayer.getUuid().toString() + "'");
          }
     }

     public FriendPlayer getPlayer(UUID uuid) {
          ResultSet result = MySQL.getResult("SELECT * FROM `proxy_FriendSystem` WHERE `uuid`='" + uuid.toString() + "'");
          if (result == null)
               return null;
          try {
               if (result.next()) {
                    Set<UUID> friends = new HashSet<>();
                    String friendsResult = result.getString("playerFriends");
                    if (friendsResult != null)
                         for (String s : friendsResult.split(";")) {
                              if (!Objects.equals(s.trim(), "")) {
                                   friends.add(UUID.fromString(s));
                              }
                         }
                    Set<UUID> requests = new HashSet<>();
                    String requestsResult = result.getString("requests");
                    if (requestsResult != null)
                         for (String s : requestsResult.split(";")) {
                              if (!Objects.equals(s.trim(), ""))
                                   requests.add(UUID.fromString(s));
                         }
                    return new FriendPlayer(UUID.fromString(result.getString("uuid")), result.getString("name"), friends, requests, result.getInt("maxFriends"));
               }
          } catch (SQLException ex) {
               ex.printStackTrace();
          }
          return null;
     }

     public UUID getUniqueId(String name) {
          ResultSet result = MySQL.getResult("SELECT `uuid` FROM `proxy_FriendSystem` WHERE `name`='" + name + "'");
          if (result == null)
               return null;
          try {
               if (result.next())
                    return UUID.fromString(result.getString("uuid"));
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return null;
     }

     public String getName(UUID uuid) {
          ResultSet result = MySQL.getResult("SELECT `name` FROM `proxy_FriendSystem` WHERE `uuid`='" + uuid.toString() + "'");
          if (result == null)
               return null;
          try {
               if (result.next())
                    return result.getString("name");
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return null;
     }

}