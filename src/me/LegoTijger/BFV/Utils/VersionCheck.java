package me.LegoTijger.BFV.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.plugin.PluginDescriptionFile;

import me.LegoTijger.BFV.Main;

public class VersionCheck{

	static Main main;
	public String downloadUrl;
	
	public VersionCheck(Main main) {
		VersionCheck.main = main;
	}
	
	// Public method for getting the latest version of the plugin
	public String getLatestVersion() {
		try {
			URL url = new URL("https://www.borisnl.nl/bfv");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
			String version = in.readLine();
			downloadUrl = in.readLine();
			return version;
		} catch(IOException e) { }
		return null;
	}

	// Public method for getting the current version of the plugin
	public String getCurrentVersion() {
		PluginDescriptionFile pdf = main.getDescription();
		return pdf.getVersion();
	}

	// Public method for checking if the plugin is up to date
	public boolean isUpToDate() {
		if(getCurrentVersion().equals(getLatestVersion())) {
			return true;
		}
		return false;
	}
}
