package eu.hypetime.spigot.hypelobby.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

/*
    Created by Andre
    At 00:34 Uhr | 28. Juli. 2021
    Project GunBattle
*/
public class UUIDHelper {

    public static String fetchName(UUID uuid) {
        return UUIDFetcher.getName(uuid);
    }

    public static UUID fetchUUID(String playerName) {
        return UUIDFetcher.getUUID(playerName);
    }

}
