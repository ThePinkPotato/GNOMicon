package dev.haxalotl.gnomicon;


import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;



public class GNOMicon implements ModInitializer {
	public static final String MOD_ID = "gnomicon";
	public static final GNOMiconConfig config = new GNOMiconConfig();

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("GNOME is gonna give me an aneurysm");

		config.createConfig();
		config.readConfig();

		// Please ignore all the System.out.println, it's for debugging because I'm quite dense
        try {
			Process process = new ProcessBuilder("gnome-shell", "--version").start();
			String output = new String(process.getInputStream().readAllBytes());
			process.waitFor();
			String getGnome = output.trim();
			String desktopFilename = "gnomicon-" + config.windowName + ".desktop";
			Path desktopPath = Path.of(System.getProperty("user.home") + "/.local/share/applications/" + desktopFilename);

			if (getGnome.toUpperCase().contains("GNOME")) {
				System.out.println("GNOME version: " + getGnome);

				if (Files.notExists(desktopPath)) {
					System.out.println("locked and loaded");

					System.out.println(FabricLoader.getInstance().getConfigDir());

					Files.createFile(desktopPath);
					Files.writeString(desktopPath,
							"#!/usr/bin/env xdg-open\n\n" +
							"[Desktop Entry]\n" +
							"Type=Application\n" +
							"Icon=" + FabricLoader.getInstance().getConfigDir().toString() + config.iconPath + "\n" +
							"StartupWMClass=" + config.windowName
					);
				}
			} else {
				LOGGER.info("Your computer is probably using KDE");
			}

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
