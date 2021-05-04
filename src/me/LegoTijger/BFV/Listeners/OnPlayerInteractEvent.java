package me.LegoTijger.BFV.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Cake;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.LegoTijger.BFV.Main;
import me.LegoTijger.BFV.Utils.ConfigHandler;

public class OnPlayerInteractEvent implements Listener{

	public OnPlayerInteractEvent(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void playerInteractEvent(PlayerInteractEvent e)  {
		Player p = e.getPlayer();
		FileConfiguration config = ConfigHandler.getConfigFile();
		// Check if the action is right click and if player clicked a cake
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CAKE) {
			// Check if player is in creative mode
			if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;
	        // Check if food level is lower than 20
			if(p.getFoodLevel() >= 20) return;
			// Check if food is in values.yml
			if(config.getString("cake") != null) {
				e.setCancelled(true);
				// Add food level to player
				p.setFoodLevel((int) Math.min(p.getFoodLevel() + config.getDouble("cake.food"), 20));
				// Add saturation level to player
				p.setSaturation((float) Math.min(p.getSaturation() + config.getDouble("cake.saturation"), 20));
				
				// If the damage is not 0 damage the player
				if(config.getDouble("cake.damage") != 0) {
					p.damage(config.getDouble("cake.damage"));
				}

				// Get the clicked block
		        Block block = e.getClickedBlock();
		        // Get the clicked block state
				BlockState state = block.getState();
				// Get the clicked block data
				BlockData data = state.getBlockData();
				// Set cake to data
				Cake c = (Cake) data;
				// Check if bite isn't the last bite
				if(c.getBites() != 6) {
					// Update bites to add one
				    c.setBites(c.getBites() + 1);
				    // Update state
				    state.update();
				    // Update block
					block.setBlockData(data);
					return;
				}
				// Only happens when it's the last bite
				// Destroy the block
				block.breakNaturally();
				// Update state
                state.update();
			}
		}
	}
	
}
