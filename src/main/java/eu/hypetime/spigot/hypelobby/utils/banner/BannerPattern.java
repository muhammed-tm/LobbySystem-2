package eu.hypetime.spigot.hypelobby.utils.banner;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.PatternType;

/*
    Created by Andre
    At 19:09 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class BannerPattern {

     private PatternType pattern;

     private DyeColor color;

     public BannerPattern(PatternType pattern, DyeColor color) {
          this.pattern = pattern;
          this.color = color;
     }

     public PatternType getPattern() {
          return this.pattern;
     }

     public void setPattern(PatternType pattern) {
          this.pattern = pattern;
     }

     public DyeColor getColor() {
          return this.color;
     }

     public void setColor(DyeColor color) {
          this.color = color;
     }

}
