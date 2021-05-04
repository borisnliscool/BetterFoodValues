package me.LegoTijger.BFV;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.LegoTijger.BFV.Commands.foodInfoCommand;
import me.LegoTijger.BFV.Commands.getValueCommand;
import me.LegoTijger.BFV.Commands.helpCommand;
import me.LegoTijger.BFV.Commands.permissionsCommand;
import me.LegoTijger.BFV.Commands.setValueCommand;
import me.LegoTijger.BFV.Listeners.OnEatEvent;
import me.LegoTijger.BFV.Listeners.OnPlayerInteractEvent;
import me.LegoTijger.BFV.Utils.ConfigHandler;
import me.LegoTijger.BFV.Utils.Language;
import me.LegoTijger.BFV.Utils.VersionCheck;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin implements CommandExecutor{

	public static String prefix = "&9&lBFV &b";
	public static String prefixError = "&4&lBFV &c";
	public static String prefixStaff = "&7&o[STAFF] &r";
	public static final String adminPermission = "bfv.admin";
	public static String pluginDownloadUrl;
	static FileConfiguration config;
	public static HashMap<String, String> lang;
	VersionCheck vc;
	File cFile;
	
	Server server = Bukkit.getServer();
	ConsoleCommandSender console = server.getConsoleSender();
	
	@Override
	public void onEnable() {
		// Setup all Language stuff
		config = this.getConfig();
		Language.setup();
		this.saveConfig();
		cFile = new File(getDataFolder(), "config.yml");
		
		// Set the lang variable to the lang variable from the language class.
		lang = Language.getLangMap();
		setupLangVars();
		
		// Set the command executor to this class
		this.getCommand("bfv").setExecutor(this);
		new OnEatEvent(this);
		new OnPlayerInteractEvent(this);
		
		// Setup Custom Config
		ConfigHandler.setup();
		ConfigHandler.getConfigFile().options().copyDefaults(true);
		ConfigHandler.saveConfigFile();
		
		// Create a new Versioncheck object
		vc = new VersionCheck(this);
		String currentVersion = vc.getCurrentVersion();
		String latestVersion = vc.getLatestVersion();
		pluginDownloadUrl = vc.downloadUrl;
		
		// Check if plugin is not up to date
		if(!vc.isUpToDate()) {
			String line = "&4<!> ----------------------------------------------------------- <!>";
			// Send out of date message to console
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
			console.sendMessage("");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixError + "Your plugin is not up to date! Your current version is: " + ChatColor.DARK_RED + currentVersion + ChatColor.RED + ", and the latest version is: " + ChatColor.DARK_RED + latestVersion + ChatColor.RED + "! You can download the latest version here: " + ChatColor.DARK_RED + pluginDownloadUrl + ChatColor.RED + " No support will be given for this version of the plugin."));
			console.sendMessage("");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
			// Send out of date message to all online staff
			BroadcastPermission(ChatColor.translateAlternateColorCodes('&', line), adminPermission);
			BroadcastPermission("", adminPermission);
			BroadcastPermission(ChatColor.translateAlternateColorCodes('&', prefixStaff + prefixError + "Your plugin is not up to date! Your current version is: " + ChatColor.DARK_RED + currentVersion + ChatColor.RED + ", and the latest version is: " + ChatColor.DARK_RED + latestVersion + ChatColor.RED + "! You can download the latest version here: " + ChatColor.DARK_RED + pluginDownloadUrl + ChatColor.RED + " No support will be given for this version of the plugin."), adminPermission);
			BroadcastPermission("", adminPermission);
			BroadcastPermission(ChatColor.translateAlternateColorCodes('&', line), adminPermission);
			return;
		}
		
		// Send successfull launch message to console
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Successfully started and is up to date!"));
	}
	
	@Override
	public void onDisable() {
		// Save config file
		ConfigHandler.saveConfigFile();
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Successfully stopped."));
	}
	
	// Public getter for config file
	public static FileConfiguration getConfiguration() {
		return config;
	}
	
	// Public method for setting up all the variables
	public static void setupLangVars() {
		prefix = lang.get("prefix");
		prefixError = lang.get("prefixError");
		prefixStaff = lang.get("prefixStaff");
	}
	
	// Method for broadcasting to players with certain permission
	public static void BroadcastPermission(String msg, String permission) {
		// Loop over all online players
		for(Player p : Bukkit.getOnlinePlayers()) {
			// Check if player has permission
			if(p.hasPermission(permission)) {
				// Send player the message
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
			}
		}
	}
	
	// Method for sending the author message
	@SuppressWarnings("deprecation")
	private void sendAuthorMessage(Player p) {
		TextComponent pre = new TextComponent("BFV ");
		pre.setColor(ChatColor.GOLD);
		pre.setBold(true);
		TextComponent center = new TextComponent("This plugin was created by LegoTijger, you can find my youtube channel ");
		center.setColor(ChatColor.YELLOW);
		TextComponent sub = new TextComponent("here");
		sub.setColor(ChatColor.GOLD);
		sub.setBold(true);
		sub.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/c/borisnlgaming"));
		sub.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here to visit my youtube.").color(ChatColor.YELLOW).create()));
		p.spigot().sendMessage(pre, center, sub);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Check if user has permission
		if(!(sender.hasPermission(adminPermission))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixError + lang.get("noPermission")));
			return false;
		}
		
		// Check if user provided arguments
		if(args.length == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixError + lang.get("noArguments")));
			return false;
		}
		
		String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
		
		// Switch over all commands
		switch(args[0]) {
			case "help":
				// sSend help menu to player
				helpCommand.showHelp((Player) sender);
				break;
			case "reload":
				// Reload config then send success message
				ConfigHandler.reloadConfigFile();
				config = YamlConfiguration.loadConfiguration(cFile);
				Language.setup();
				setupLangVars();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Successfully reloaded!"));
				break;
			case "permissions":
				permissionsCommand.showPermissions((Player) sender);
				break;
			case "info":
				foodInfoCommand.showFoodInfo((Player) sender, commandArgs);
				break;
			case "version":
				if(vc.isUpToDate()) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Your current plugin version is: &3" + getDescription().getVersion() + " &bthis is the latest version."));
					break;
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Your current plugin version is: &3" + getDescription().getVersion() + "&b there's an update available at &3" + pluginDownloadUrl));
				
				break;
			case "set":
				setValueCommand.setValue((Player) sender, commandArgs);
				break;
			case "get":
				getValueCommand.getValue((Player) sender, commandArgs);
				break;
			case "author":
				sendAuthorMessage((Player) sender);
				break;
			default:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixError + lang.get("unknownCommand")));
				break;
		}
		return false;
	}
}
