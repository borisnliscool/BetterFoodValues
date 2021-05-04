package me.LegoTijger.BFV.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class permissionsCommand {
	
	public static void showPermissions(Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9&lAll permissions for the Better Food Values plugin"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3bfv.admin&b - main permission for all admins."));
	}
}
