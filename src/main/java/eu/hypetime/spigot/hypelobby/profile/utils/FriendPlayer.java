/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.spigot.hypelobby.profile.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FriendPlayer {
     private final UUID uuid;
     private final Set<UUID> friends;
     private final Set<UUID> requests;
     private String name;
     private int maxFriends;

     private Set<UUID> onlineFriends;

     public FriendPlayer(UUID uuid, String name, Set<UUID> friends, Set<UUID> requests, int maxFriends) {
          this.uuid = uuid;
          this.friends = friends;
          this.requests = requests;
          this.name = name;
          this.onlineFriends = new HashSet<>();
          this.maxFriends = maxFriends;
     }

     public UUID getUuid() {
          return this.uuid;
     }

     public Set<UUID> getFriends() {
          return this.friends;
     }

     public Set<UUID> getRequests() {
          return this.requests;
     }

     public String getName() {
          return this.name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public Set<UUID> getOnlineFriends() {
          return this.onlineFriends;
     }

     public int getMaxFriends() {
          return this.maxFriends;
     }

     public void setMaxFriends(int maxFriends) {
          this.maxFriends = maxFriends;
     }
}
