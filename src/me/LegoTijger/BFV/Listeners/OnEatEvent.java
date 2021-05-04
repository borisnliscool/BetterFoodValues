package me.LegoTijger.BFV.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.LegoTijger.BFV.Main;
import me.LegoTijger.BFV.Utils.ConfigHandler;

public class OnEatEvent implements Listener{
	
	public OnEatEvent(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onEatEvent (PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		FileConfiguration config = ConfigHandler.getConfigFile();
		
		// Set string to the name of consumed item
		String item = e.getItem().getType().name().toLowerCase();
		
		// Exit if its a potion
		if(e.getItem().getType().equals(Material.POTION)) return;
		
		// Check if the consumed item is in the values.yml
		if(config.getString(item) != null) {
			// Cancel the event
			e.setCancelled(true);
			
			// Dont remove item if player is in creative or spectator
			if(p.getGameMode() != GameMode.CREATIVE || p.getGameMode() != GameMode.SPECTATOR) p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
	        // Add food level to player
			p.setFoodLevel((int) Math.min(p.getFoodLevel() + config.getDouble(item + ".food"), 20));
			// Add saturation level to player
			p.setSaturation((float) Math.min(p.getSaturation() + config.getDouble(item + ".saturation"), 20));
			
			// If the damage is not 0 damage the player
			if(config.getDouble(item + ".damage") != 0) {
				p.damage(config.getDouble(item + ".damage"));
			}
			return;
		}
		
		// Item is not in the values.yml
		Main.BroadcastPermission(ChatColor.translateAlternateColorCodes('&', Main.prefixStaff + Main.prefixError + String.format(Main.lang.get("wrongFoodConsumed"), p.getDisplayName(), item)), Main.adminPermission);
	}

}
