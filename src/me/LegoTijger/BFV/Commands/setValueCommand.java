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
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + "You have to provide more arguments. Usage &4/bfv set <food> <setting> <value>"));
			return;
		}
		
		FileConfiguration config = ConfigHandler.getConfigFile();
		
		String food = args[0];
		String setting = args[1];
		String value = args[2];
		Double parsedValue;
		
		// Check if the food given exists
		if(config.getString(food) == null) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + "I'm sorry, but I couldn't find &4" + food + "&c in the &4values.yml"));
			return;
		}
		
		// Check if the setting is food, saturation or damage
		if(!(setting.equals("food") || setting.equals("saturation") || setting.equals("damage"))) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + "I'm sorry, but I couldn't find &4" + setting + "&c, please pick one of the following: &4food, saturation, damage"));
			return;
		}
		
		// Check if the value is a number
		try {
			parsedValue = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + "&4" + value + "&c is not a number."));
			return;
		}
		
		// Check if user input is a different from actual value
		if(config.getDouble(food + "." + setting) == parsedValue) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + "Nothing changed, the &3" + setting + "&b value of &3" + food + "&b is already &3" + value));
			return;
		}
		
		// Actually set the value, save and send the player a success message
		config.set(food + "." + setting, parsedValue);
		ConfigHandler.saveConfigFile();
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + "Successfully set the &3" + setting + "&b value of &3" + food + "&b to &3" + value));
		
	}
	
}
