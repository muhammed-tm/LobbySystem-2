package eu.hypetime.spigot.hypelobby.utils.banner;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/*
    Created by Andre
    At 19:08 Uhr | 10. Apr.. 2021
    Project HypeLobbySpigot
*/
public class BannerAPI {
     private List<BannerPattern> patterns = new ArrayList<>();

     private Material banner;

     public BannerAPI(Material banner) {
          this.banner = banner;
     }

     public void addPattern(BannerPattern pattern) {
          this.patterns.add(pattern);
     }

     public void addPattern(BannerPattern pattern, int index) {
          this.patterns.add(index - 1, pattern);
     }

     public void setBanner(Material banner) {
          this.banner = banner;
     }

     public void removePattern() {
          this.patterns.remove(this.patterns.size() - 1);
     }

     public void removePattern(int layer) {
          this.patterns.remove(layer - 1);
     }

     public ItemStack getBanner() {
          ItemStack banner = new ItemStack(this.banner);
          BannerMeta meta = (BannerMeta)banner.getItemMeta();
          for (BannerPattern p : this.patterns)
               meta.addPattern(new Pattern(p.getColor(), p.getPattern()));
          banner.setItemMeta((ItemMeta)meta);
          return banner;
     }

}
