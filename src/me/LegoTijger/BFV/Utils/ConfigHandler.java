package me.LegoTijger.BFV.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {

	private static File file;
	private static FileConfiguration config;
	
	// Finds or generates the custom config file.
	public static void setup() {
		// Tries to find file
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("BFV").getDataFolder(), "values.yml");
		
		// Checks if it exists and if not tries to creat one
		if(!file.exists()) {
			try {
				// Successfully created file
				file.createNewFile();
			} catch (IOException e) {
				// Something went wrong while creating the file
				Bukkit.getLogger().severe("Something went wrong while trying to create values.yml");
			}
		}
		
		// Assign the file to the config variable
		config = YamlConfiguration.loadConfiguration(file);
		setDefaults();
	}
	
	// Public config file getter
	public static FileConfiguration getConfigFile() {
		return config;
	}
	
	// Public config saver
	public static void saveConfigFile() {
		try {
			// Successfully saved file
			config.save(file);
			reloadConfigFile();
		} catch (IOException e) {
			// Something went wrong while creating the file
			Bukkit.getLogger().severe("Something went wrong while trying to create values.yml");
		}
	}
	
	// Public config reloader
	public static void reloadConfigFile() {
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	// Set all the defaults
	private static void setDefaults() {
		addConfigItem("apple", 4, 2.4f, 0);
		addConfigItem("baked_potato", 5, 6, 0);
		addConfigItem("beetroot", 1, 1.2f, 0);
		addConfigItem("beetroot_soup", 6, 7.2f, 0);
		addConfigItem("bread", 5, 6f, 0);
		addConfigItem("cake", 2, 0.4f, 1);
		addConfigItem("carrot", 3, 3.6f, 0);
		addConfigItem("chorus_fruit", 4, 2.4f, 1);
		addConfigItem("cooked_beef", 8, 12.8f, 0);
		addConfigItem("cooked_chicken", 6, 7.2f, 0);
		addConfigItem("cooked_cod", 5, 6f, 0);
		addConfigItem("cooked_mutton", 6f, 9.6f, 0);
		addConfigItem("cooked_porkchop", 8, 12.8f, 0);
		addConfigItem("cooked_rabbit", 5, 6f, 0);
		addConfigItem("cooked_salmon", 6, 9.6f, 0);
		addConfigItem("cookie", 2, 0.4f, 1);
		addConfigItem("dried_kelp", 1, 0.6f, 1);
		addConfigItem("enchanted_golden_apple", 4, 9.4f, 0);
		addConfigItem("golden_apple", 4, 9.6f, 0);
		addConfigItem("golden_carrot", 6, 14.4f, 0);
		addConfigItem("honey_bottle", 6, 1.2f, 0);
		addConfigItem("melon_slice", 2, 1.2f, 0);
		addConfigItem("mushroom_stew", 6, 7.2f, 0);
		addConfigItem("poisonous_potato", 2, 1.2f, 1);
		addConfigItem("potato", 1, 0.6f, 0);
		addConfigItem("pufferfish", 1, 0.2f, 2);
		addConfigItem("pumpkin_pie", 8, 4.8f, 0);
		addConfigItem("rabbit_stew", 10, 12f, 0);
		addConfigItem("beef", 3, 1.8f, 0);
		addConfigItem("chicken", 2, 1.2f, 0);
		addConfigItem("cod", 2, 0.4f, 0);
		addConfigItem("mutton", 2, 1.2f, 0);
		addConfigItem("porkchop", 3, 1.8f, 0);
		addConfigItem("rabbit", 3, 1.8f, 0);
		addConfigItem("salmon", 2, 0.4f, 0);
		addConfigItem("rotten_flesh", 4, 0.8f, 0);
		addConfigItem("spider_eye", 2, 3.2f, 0);
		addConfigItem("suspicious_stew", 6, 7.2f, 0);
		addConfigItem("sweet_berries", 2, 0.4f, 0);
		addConfigItem("tropical_fish", 1, 0.2f, 0);
	}
	
	// Small function for adding food item in the config
	private static void addConfigItem(String name, float food, float d, float damage) {
		config.addDefault(name + ".food", food);
		config.addDefault(name + ".saturation", d);
		config.addDefault(name + ".damage", damage);
	}
	
}
