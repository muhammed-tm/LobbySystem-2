package eu.hypetime.spigot.hypelobby.utils;

public enum RewardType {
    PLAYER("Spieler", 1000 * 60 * 60 * 12),
    HYPER("Hyper", 1000 * 60 * 60 * 12),
    WARRIOR("Warrior", 1000 * 60 * 60 * 12),
    VIP("Vip", 1000 * 60 * 60 * 24),
    MEDIA("Media", 1000 * 60 * 60 * 24);

    public String name;
    public int time;

    RewardType(String name, int time) {
        this.name = name;
        this.time = time;
    }
}
