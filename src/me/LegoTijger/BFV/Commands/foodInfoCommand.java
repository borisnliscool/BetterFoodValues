package me.LegoTijger.BFV.Commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.LegoTijger.BFV.Main;
import me.LegoTijger.BFV.Utils.ConfigHandler;

public class foodInfoCommand {

	// Public info method
	public static void showFoodInfo(Player p, String[] args) {
		// Check if there's a food name provided
		if(args.length == 0) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + Main.lang.get("noFoodName")));
			return;
		}
		
		// Get values.yml
		FileConfiguration config = ConfigHandler.getConfigFile();
		
		// Set food name to first argument
		String foodName = args[0];
		
		// Check if the food exists
		if(config.getString(foodName) != null) {
			// Food exists in the values.yml
			double food = config.getDouble(foodName + ".food");
			double saturation = config.getDouble(foodName + ".saturation");
			double damage = config.getDouble(foodName + ".damage");
			
			// Check if food gives damage
			if(damage != 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + String.format(Main.lang.get("foodFoundWithDamage"), foodName, (double) food, (double) saturation, (double) damage / 2)));
				return;
			}
			
			// Else send a default message
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + String.format(Main.lang.get("foodFoundNoDamage"), foodName, (double) food, (double) saturation)));
			return;
		}
		
		// Food doesn't exist in the values.yml
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + String.format(Main.lang.get("foodNotFound"), foodName)));
	}
	
}
