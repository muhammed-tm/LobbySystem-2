package eu.hypetime.spigot.hypelobby.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class utils {
    public static ItemStack createItem(Material material, int subid, String displayname){

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }
    public static ItemStack createDurabilityItem(Material material, int subid, String displayname){

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);
        short s = 9999;
        item.setDurability(s);

        return item;
    }
    public static ItemStack createStick(Material material, int subid, String displayname){

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);
        item.addEnchantment(Enchantment.KNOCKBACK, 1);

        return item;
    }

    public static ItemStack createBoot(String Name, Color color) {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta imeta = (LeatherArmorMeta) item.getItemMeta();
        imeta.setDisplayName(Name);
        imeta.setColor(color);
        item.setItemMeta(imeta);
        return item;
    }

    public static ItemStack createHelmet(String Name, Color color) {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta imeta = (LeatherArmorMeta) item.getItemMeta();
        imeta.setDisplayName(Name);
        imeta.setColor(color);
        item.setItemMeta(imeta);
        return item;
    }

    public static ItemStack createLeggings(String Name, Color color) {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta imeta = (LeatherArmorMeta) item.getItemMeta();
        imeta.setDisplayName(Name);
        imeta.setColor(color);
        item.setItemMeta(imeta);
        return item;
    }
    public static ItemStack createChestplate(String Name, Color color) {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta imeta = (LeatherArmorMeta) item.getItemMeta();
        imeta.setDisplayName(Name);
        imeta.setColor(color);
        item.setItemMeta(imeta);
        return item;
    }
    public static ItemStack createAnzahlItem(Material material, int subid, String displayname, int Anzahl){

        ItemStack item = new ItemStack(material, Anzahl, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }
    public static ItemStack createLore(Material material, int subid, String displayname, String Lore){

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        List<String> list = new LinkedList<>();
        list.add(Lore);
        mitem.setLore(list);
        item.setItemMeta(mitem);

        return item;
    }

    public static ItemStack createItem(Material material, int anzahl, int subid, String displayname) {
        short neuesubid = (short) subid;
        ItemStack i = new ItemStack(material, anzahl, neuesubid);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setItemMeta(m);

        return i;
    }
    public static ItemStack createlore(Material mat, int amount, int subid, String name, ArrayList string) {

        ItemStack i = new ItemStack(mat, amount, (short)subid);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(name);
        m.setLore(string);
        i.setItemMeta(m);
        return i;

    }
}
