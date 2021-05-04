package me.LegoTijger.BFV.Commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.LegoTijger.BFV.Main;
import me.LegoTijger.BFV.Utils.ConfigHandler;

public class setValueCommand {

	public static void setValue(Player p, String[] args) {
		// Check if there's enough arguments
		if(args.length <= 2) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + Main.lang.get("noArguments")));
			return;
		}
		
		FileConfiguration config = ConfigHandler.getConfigFile();
		
		String food = args[0];
		String setting = args[1];
		String value = args[2];
		Double parsedValue;
		
		// Check if the food given exists
		if(config.getString(food) == null) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + String.format(Main.lang.get("foodNotFound"), food)));
			return;
		}
		
		// Check if the setting is food, saturation or damage
		if(!(setting.equals("food") || setting.equals("saturation") || setting.equals("damage"))) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + String.format(Main.lang.get("settingNotFound"), setting)));
			return;
		}
		
		// Check if the value is a number
		try {
			parsedValue = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + String.format(Main.lang.get("notANumber"), value)));
			return;
		}
		
		// Check if user input is a different from actual value
		if(config.getDouble(food + "." + setting) == parsedValue) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + String.format(Main.lang.get("nothingChanged"), setting, food, value)));
			return;
		}
		
		// Actually set the value, save and send the player a success message
		config.set(food + "." + setting, parsedValue);
		ConfigHandler.saveConfigFile();
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + Main.prefix + String.format(Main.lang.get("nothingChanged"), setting, food, value)));
		
	}
	
}
