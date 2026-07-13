package dev.haxalotl.gnomicon;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GNOMiconConfig {

    public String configString;
    public String windowName;
    public String iconPath;

    public void createConfig() {
        try {
            if (Files.notExists(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon"))) {
                Files.createDirectory(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon"));
            }

            if (Files.notExists(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/gnomicon.json"))) {
                Files.createFile(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/gnomicon.json"));
                Files.writeString(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/gnomicon.json"),
                        "{\n" +
                                "\"name\":\"Minecraft\",\n".indent(4) +
                                "\"icon\":\"icon.png\"\n".indent(4) +
                                "}"
                );
            }

            if (Files.notExists(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/icon.png"))) {
                Files.copy(FabricLoader.getInstance().getModContainer("gnomicon").orElseThrow().findPath("assets/gnomicon/icon.png").orElseThrow(), Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/icon.png"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readConfig() {

        try {
            configString = String.join(" ", Files.readAllLines(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/gnomicon.json")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject gnomiconConfig = JsonParser.parseString(configString).getAsJsonObject();
        System.out.println(gnomiconConfig.get("name").getAsString());
        windowName = gnomiconConfig.get("name").getAsString();
        iconPath = gnomiconConfig.get("icon").getAsString();
    }

}
