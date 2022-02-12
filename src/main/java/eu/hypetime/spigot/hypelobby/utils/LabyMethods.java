package eu.hypetime.spigot.hypelobby.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.labymod.serverapi.common.widgets.WidgetScreen;
import net.labymod.serverapi.common.widgets.components.widgets.*;
import net.labymod.serverapi.common.widgets.util.Anchor;
import net.labymod.serverapi.common.widgets.util.EnumScreenAction;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.UUID;

import java.util.UUID;

public class LabyMethods {

    public static void updateBalanceDisplay(Player player, EnumBalanceType type, boolean visible, int balance) {
        JsonObject economyObject = new JsonObject();
        JsonObject cashObject = new JsonObject();

        // Visibility
        cashObject.addProperty("visible", visible);

        // Amount
        cashObject.addProperty("balance", balance);


        // The display type can be "cash" or "bank".
        economyObject.add(type.getKey(), cashObject);

        // Send to LabyMod using the API
    }

    public enum EnumBalanceType {
        CASH("cash");

        private final String key;

        EnumBalanceType(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
    }

}