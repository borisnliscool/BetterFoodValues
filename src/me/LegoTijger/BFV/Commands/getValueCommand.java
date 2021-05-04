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
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + "You have to provide more arguments. Usage &4/bfv get <food> <setting>"));
			return;
		}
		
		FileConfiguration config = ConfigHandler.getConfigFile();
		
		String food = args[0];
		String setting = args[1];
		
		// Check if the food given exists
		if(config.getString(food) == null) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + "I'm sorry, but I couldn't find &4" + food + "&c in the &4values.yml"));
			return;
		}
		
		// Check if the setting is food, saturation or damage
		if(!(setting.equals("food") || setting.equals("saturation") || setting.equals("damage"))) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefixError + "I'm sorry, but I couldn't find &4" + setting + "&c, please pick one of the following: &4food&c, &4saturation &cor &4damage"));
			return;
		}
		
		Double value = config.getDouble(food + "." + setting);
		
		// Actually get the value, and send them to the player
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + "The &3" + setting + "&b value of &3" + food + "&b is: &3" + value));
		
	}
	
}
