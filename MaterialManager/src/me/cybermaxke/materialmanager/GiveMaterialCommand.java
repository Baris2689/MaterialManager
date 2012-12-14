package me.cybermaxke.materialmanager;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;
import me.cybermaxke.materialmanager.materials.Material;
import me.cybermaxke.materialmanager.materials.MaterialData;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GiveMaterialCommand implements CommandExecutor {
	
	public GiveMaterialCommand(JavaPlugin plugin) {
		plugin.getCommand("givemat").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		
	    Player player = (Player) sender;
	    
	    if (args.length < 1 || args.length > 2) {
	    	player.sendMessage("To use the command enter '/givemat <material> [amount]'.");
	    	return true;
	    }	 
	    
	    Material mat = MaterialData.getMaterialById(args[0]);
	    
	    if (mat == null) {
	    	player.sendMessage("That material doesn't exist.");
	    	return true;
	    }
	    
	    int amount = 1;
	   
	    if (args.length > 1) {
	    	try {
	    		amount = Integer.parseInt(args[1]);   
	    	} catch (Exception e) {
	    		player.sendMessage("Wrong input for amount.");
	    		return true;
	    	}
	    }
	    
	    CustomItemStack is = new CustomItemStack(mat);
	    is.setAmount(amount);

	    player.getInventory().addItem(is);    
		return true;
	}
}