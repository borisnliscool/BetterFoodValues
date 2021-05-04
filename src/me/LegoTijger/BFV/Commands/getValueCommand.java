package me.LegoTijger.BFV.Commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.LegoTijger.BFV.Main;
import me.LegoTijger.BFV.Utils.ConfigHandler;

public class getValueCommand {

	public static void getValue(Player p, String[] args) {
		// Check if there's enough arguments
		if(args.length <= 1) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + String.format(Main.lang.get("noArguments"))));
			return;
		}
		
		FileConfiguration config = ConfigHandler.getConfigFile();
		
		String food = args[0];
		String setting = args[1];
		
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
		
		Double value = config.getDouble(food + "." + setting);
		
		// Actually get the value, and send them to the player
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + String.format(Main.lang.get("foodSettingInfo"), setting, food, value)));
		
	}
	
}
