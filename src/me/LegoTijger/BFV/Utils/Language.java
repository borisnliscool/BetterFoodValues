package me.LegoTijger.BFV.Utils;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import me.LegoTijger.BFV.Main;

public class Language {

	static HashMap<String, String> lang = new HashMap<String, String>();
	static FileConfiguration config;
	
	// Public method for setting up the HashMap and config file
	public static void setup() {
		config = Main.getConfiguration();
		setDefaults();
		lang.put("prefix", config.getString("language.prefix"));
		lang.put("prefixError", config.getString("language.prefixError"));
		lang.put("prefixStaff", config.getString("language.prefixStaff"));
		lang.put("noPermission", config.getString("language.noPermission"));
		lang.put("noArguments", config.getString("language.noArguments"));
		lang.put("unkownCommand", config.getString("language.unkownCommand"));
		lang.put("noFoodName", config.getString("language.noFoodName"));
		lang.put("foodNotFound", config.getString("language.foodNotFound"));
		lang.put("foodFoundNoDamage", config.getString("language.foodFoundNoDamage"));
		lang.put("foodFoundWithDamage", config.getString("language.foodFoundWithDamage"));
		lang.put("settingNotFound", config.getString("language.settingNotFound"));
		lang.put("foodSettingInfo", config.getString("language.foodSettingInfo"));
		lang.put("notANumber", config.getString("language.notANumber"));
		lang.put("nothingChanged", config.getString("language.nothingChanged"));
		lang.put("changeSuccessfull", config.getString("language.changeSuccessfull"));
		lang.put("wrongFoodConsumed", config.getString("language.wrongFoodConsumed"));
		// Set the defaults
		config.options().copyDefaults(true);
	}
	
	// public getter for the lang HashMap
	public static HashMap<String, String> getLangMap() {
		return lang;
	}
	
	// Method for creating defaults in the config file.
	static void setDefaults() {
		config.addDefault("language.prefix", "&9&lBFV &b");
		config.addDefault("language.prefixError", "&4&lBFV &c");
		config.addDefault("language.prefixStaff", "&7&o[STAFF] &r");
		config.addDefault("language.noPermission", "&cI'm sorry, but you dont have permission to perform this command.");
		config.addDefault("language.noArguments", "&cYou need to provide arguments. Try &4/bfv help &cfor help.");
		config.addDefault("language.unkownCommand", "&cI'm sorry but I don't recognize that command. Try &4/bfv help &cfor help.");
		config.addDefault("language.noFoodName", "&cYou need to provide a food name.");
		config.addDefault("language.foodNotFound", "&cI'm sorry, but I couldn't find the food &4%n&b!");
		config.addDefault("language.foodFoundNoDamage", "&3%s&b gives you &3%s&b food points and &3%s&b saturation.");
		config.addDefault("language.foodFoundWithDamage", "&3%s&b gives you &3%s&b food points and &3%s&b saturation. But be carefull, you could take up to &3%s&b hearts of damage when eating this!");
		config.addDefault("language.settingNotFound", "I'm sorry, but I couldn't find &4%s&c, please pick one of the following: &4food&c, &4saturation &cor &4damage");
		config.addDefault("language.foodSettingInfo", "The &3%s&b value of &3%s&b is: &3%s");
		config.addDefault("language.notANumber", "&4%s&c is not a number.");
		config.addDefault("language.nothingChanged", "Nothing changed, the &3%s&b value of &3%s&b is already &3%s");
		config.addDefault("language.changeSuccessfull", "Successfully set the &3%s&b value of &3%s&b to &3%s");
		config.addDefault("language.wrongFoodConsumed", "&4%s&c just ate: &4%s &cand it's not in the &4values.yml&c! No special effects have been given.");
	}
	
}
