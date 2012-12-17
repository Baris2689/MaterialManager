package me.cybermaxke.materialmanager;

import java.io.File;

import me.cybermaxke.materialmanager.materials.MaterialData;
import me.cybermaxke.materialmanager.utils.Utils;
import me.cybermaxke.materialmanager.utils.YamlMaterial;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadMaterialCommand implements CommandExecutor {
	private JavaPlugin p;
	
	public ReloadMaterialCommand(JavaPlugin plugin) {
		plugin.getCommand("reloadmat").setExecutor(this);
		this.p = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (args.length < 1 || args.length > 2) {
	    	sender.sendMessage("To use the command enter '/reloadmat <material>'.");
	    	return true;
	    }
	    
	    if (sender instanceof Player) {
	    	Player p = (Player) sender;
	    	
	    	if (!Utils.hasPermission(p, "mm.command.reloadmat")) {
	    		p.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	    		return true;
	    	}
	    }
	    
	    if (args[0].equalsIgnoreCase("All")) {
	    	MaterialData.loadMaterials();
	    } else {
	    	File f = new File(p.getDataFolder() + File.separator + "Materials", args[0] + ".yml");
	    	if (!f.exists()) {
	    		sender.sendMessage("That material doesn't exist.");
	    		return true;
	    	}
	    	
	    	new YamlMaterial(this.p, f);
	    }
	    
	    sender.sendMessage("Material " + args[0] + " is succesfull reloaded.");
		return true;
	}
}