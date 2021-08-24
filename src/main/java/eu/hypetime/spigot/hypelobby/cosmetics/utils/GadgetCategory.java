package eu.hypetime.spigot.hypelobby.cosmetics.utils;

public enum GadgetCategory {
    PETS("pets"),
    GADGETS("gadgets"),
    PARTICLE("particle"),
    BOOTS("boots");

    private String name;
    GadgetCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
