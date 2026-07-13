package dev.haxalotl.gnomicon;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class GNOMiconConfig {

    public void createConfig( ) {
        try {
            if (Files.notExists(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon"))) {
                Files.createDirectory(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon"));
            }

            if (Files.notExists(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/gnomicon.json"))) {
                Files.createFile(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/gnomicon.json"));
                Files.writeString(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/gnomicon.json"),
                        "{\n" +
                                "\"name\":\"Minecraft\"\n".indent(4) +
                                "}"
                );
            }

            if (Files.notExists(Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/icon.png"))) {
                Files.copy(FabricLoader.getInstance().getModContainer("gnomicon").orElseThrow().findPath("assets/gnomicon/icon.png").orElseThrow(), Path.of(FabricLoader.getInstance().getConfigDir() + "/gnomicon/icon.png"));
            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }



    public static void initialize() {

    }
}
