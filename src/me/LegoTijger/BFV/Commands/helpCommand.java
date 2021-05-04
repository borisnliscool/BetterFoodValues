package me.LegoTijger.BFV.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class helpCommand {
	
	public static void showHelp(Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9&lAll commands for the Better Food Values plugin"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv help&b - shows this help menu."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv reload&b - reloads the config."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv permissions&b - shows all the permissions of this plugin."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv info <food>&b - shows info about a certain food"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv set <food> <setting> <value>&b - set the value of a certain food."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv get <food> <setting>&b - shows the value of a sellected food."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv version&b - displays the current plugin version."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bfv author&b - displays the author of this plugin."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable settings: &3food&b, &3saturation&b and &3damage"));
	}
}
