package eu.hypetime.spigot.hypelobby.utils;

public enum RewardType {
     PLAYER("Spieler", 1000 * 60 * 60 * 12),
     HYPER("Hyper", 1000 * 60 * 60 * 12),
     WARRIOR("Warrior", 1000 * 60 * 60 * 12),
     VIP("Vip", 1000 * 60 * 60 * 24),
     MEDIA("Media", 1000 * 60 * 60 * 24);

     public String name;
     public long time;

     RewardType(String name, long time) {
          this.name = name;
          this.time = time;
     }
}
